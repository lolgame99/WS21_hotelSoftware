package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.GuestService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomAssignmentException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

@Component
public class StayServiceImpl implements StayService{

	@Autowired
	private StayRepository stayRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
		
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	@Override
	public List<StayDetailsDTO> getAllStays() {
		List<Stay> stays = stayRepository.getAllStays();
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			Optional<BookingDetailsDTO> booking = Optional.empty();
			CustomerDetailsDTO customer = customerService.getCustomerDetailsById(s.getCustomerId().getId()).get();
			GuestDetailsDTO guest = guestService.getGuestById(s.getGuestId().getId()).get();
			if(s.getBookingId() != null) {
				booking = bookingService.getBookingDetailsById(s.getBookingId().getId());
			}
			stayDTOs.add(StayDetailsDTO.createFromStay(s, booking, customer, guest, roomAssignmentService.getRoomAssignmentsByStayId(s.getStayId())));				
		}
			
		return stayDTOs;
	}

	@Override
	public List<StayDetailsDTO> getCurrentStays(LocalDate date) {
		List<Stay> stays = stayRepository.getCurrentStays(date); 
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			Optional<BookingDetailsDTO> booking = Optional.empty();
			CustomerDetailsDTO customer = customerService.getCustomerDetailsById(s.getCustomerId().getId()).get();
			GuestDetailsDTO guest = guestService.getGuestById(s.getGuestId().getId()).get();
			if(s.getBookingId() != null) {
				booking = bookingService.getBookingDetailsById(s.getBookingId().getId());
			}
			stayDTOs.add(StayDetailsDTO.createFromStay(s, booking, customer, guest, roomAssignmentService.getRoomAssignmentsByStayId(s.getStayId())));		
		}
			
		return stayDTOs;
	}

	@Override
	public void addStayFromData(StayData stayData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate,
			LocalDate convertedBirthDate) throws Exception{
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(stayData.getCustomerId()));
		Optional<Booking> booking = bookingRepository.getBookingById(new BookingId(stayData.getBookingId()));
		Guest guest = null;
		
		Boolean customerCreated = customer.isEmpty();
		Stay stay = null;
		if(customerCreated) {
			customer = Optional.of(Customer.create(
					customerRepository.nextIdentity(),
					stayData.getFirstName(),
					stayData.getLastName(),
					convertedBirthDate,
					new Address(stayData.getStreetName(),stayData.getStreetNumber(),stayData.getCity(),stayData.getPostcode(),stayData.getCountry()),
					stayData.getEmail(),
					stayData.getPhoneNumber(),
					stayData.getGender()
					));
			if (stayData.getMiddleName() != null) {
				customer.get().addMiddleName(stayData.getMiddleName());
			}
		}
		
		if(stayData.getGuest().equals("newGuest")) {
			guest = Guest.create(guestRepository.nextIdentity(), stayData.getGuestFirstName(), stayData.getGuestLastName(), stayData.getGuestPhoneNumber());
			if (stayData.getGuestMiddleName() != null) {
				guest.addMiddleName(stayData.getGuestMiddleName());
			}
		}else{
			guest = Guest.createFromCustomer(guestRepository.nextIdentity(), customer.get());
		}
		guestRepository.addGuest(guest);
		
		if(booking.isPresent()) {
			stay = Stay.createFromBooking(stayRepository.nextIdentity(), booking.get(), guest.getGuestId());
			booking.get().checkedIn();
		}else {
			stay = Stay.createForWalkIn(
					stayRepository.nextIdentity(),
					convertedCheckInDate,
					convertedCheckOutDate,
					stayData.getGuestCount(),
					stayData.getCreditCardNumber(),
					customer.get().getCustomerId(),
					guest.getGuestId()
					);
		}
		
		for (int i = 0; i < stayData.getRoomNumbers().size(); i++) {
			Optional<Room> room = roomRepository.getRoomByNumber(new RoomId(stayData.getRoomNumbers().get(i)));
			if (room.isEmpty()) {
				throw new InvalidRoomAssignmentException("RoomAssignment could not be created <br> Room with number "+stayData.getRoomNumbers().get(i)+" does not exist");
			} else if (stayData.getRoomNumbers().size() != stayData.getRoomNumbers().stream().distinct().collect(Collectors.toList()).size()) {
				throw new InvalidRoomAssignmentException("RoomAssignment could not be created <br> Room with number"+stayData.getRoomNumbers().get(i)+" can't be allocated twice");
			}
			
			List<RoomAssignment> occupiedRooms = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(stay.getCheckInDate(), stay.getCheckOutDate());
			for (RoomAssignment ra : occupiedRooms) {
				if (ra.getRoomNumber().getId().equals(room.get().getRoomNumber().getId())) {
					throw new InvalidRoomAssignmentException("RoomAssignment could not be created <br> Room with number "+stayData.getRoomNumbers().get(i)+" is already occupied");
				}
			}
			
			roomAssignmentRepository.addRoomAssignment(RoomAssignment.create(new RoomId(stayData.getRoomNumbers().get(i)), stay));
		} 
		
		
		if (customerCreated) {
			customerRepository.addCustomer(customer.get());
		}
		stayRepository.addStay(stay);
		
	}

	@Override
	public Optional<StayDetailsDTO> getStayById(String id) {
		Optional<StayDetailsDTO> dto = Optional.empty();
		Optional<Stay> stay = stayRepository.getStayById(new StayId(id));
		Optional<BookingDetailsDTO> booking = Optional.empty();
		if(stay.get().getBookingId() != null) {
			 booking = bookingService.getBookingDetailsById(stay.get().getBookingId().getId());
		}

		dto = Optional.of(StayDetailsDTO.createFromStay(stay.get(),
				booking,
				customerService.getCustomerDetailsById(stay.get().getCustomerId().getId()).get(),
				guestService.getGuestById(stay.get().getGuestId().getId()).get(),
				roomAssignmentService.getRoomAssignmentsByStayId(stay.get().getStayId())));
		
		
				
		return dto;
	}

}
