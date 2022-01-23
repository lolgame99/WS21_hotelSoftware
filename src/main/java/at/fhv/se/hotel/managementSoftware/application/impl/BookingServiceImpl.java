package at.fhv.se.hotel.managementSoftware.application.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

@Component
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingAssignmentRepository bookingAssignmentRepository;
	
	@Override
	public List<BookingOverviewDTO> getAllBookings() {
		List<Booking> bookings = bookingRepository.getAllBookings();
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			Optional<CustomerOverviewDTO> customer = customerService.getCustomerOverviewById(b.getCustomerId().getId());
			bookingDTOs.add(BookingOverviewDTO.createFromBooking(b, customer.get()));
		}
		
		return bookingDTOs;
	}

	@Override
	public List<BookingOverviewDTO> getBookingsByDate(LocalDate date) {
		List<Booking> bookings = bookingRepository.getBookingsByCheckInDate(date);
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			Optional<CustomerOverviewDTO> customer = customerService.getCustomerOverviewById(b.getCustomerId().getId());
			bookingDTOs.add(BookingOverviewDTO.createFromBooking(b, customer.get()));
		}
		
		return bookingDTOs;
	}

	@Override
	public void addBooking(Booking booking) {
		Map<RoomCategory, Integer> categoryCount = booking.getCategoryCount();
		for (HashMap.Entry<RoomCategory, Integer> entry : categoryCount.entrySet()) {
		    RoomCategory key = entry.getKey();
		    Integer value = entry.getValue();
		    BookingAssignment ba = BookingAssignment.create(bookingAssignmentRepository.nextIdentity(), key, value, booking);
		    bookingAssignmentRepository.addBookingAssignment(ba);
		}
		bookingRepository.addBooking(booking);
	}

	@Override
	public void addBookingFromData(BookingData bookingData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate, LocalDate convertedBirthDate) throws Exception {
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(bookingData.getCustomerId()));
		Boolean customerCreated = customer.isEmpty();
		List<BookingAssignment> bookingAssignments = new ArrayList<BookingAssignment>();
		if(customerCreated) {
			if (bookingData.getCompanyName() == "" || bookingData.getCompanyName() == null) {
				IndividualCustomer individualCustomer = IndividualCustomer.create(
						customerRepository.nextIdentity(),
						bookingData.getFirstName(),
						bookingData.getLastName(),
						convertedBirthDate,
						new Address(bookingData.getStreetName(),bookingData.getStreetNumber(),bookingData.getCity(),bookingData.getPostcode(),bookingData.getCountry()),
						bookingData.getEmail(),
						bookingData.getPhoneNumber(),
						bookingData.getGender()
						);
				
				if (bookingData.getMiddleName() != "") {
					individualCustomer.addMiddleName(bookingData.getMiddleName());
				}
				
				customer = Optional.of(individualCustomer);
			}else {
				CompanyCustomer companyCustomer = CompanyCustomer.create(
						customerRepository.nextIdentity(), 
						bookingData.getCompanyName(), 
						new Address(bookingData.getStreetName(),bookingData.getStreetNumber(),bookingData.getCity(),bookingData.getPostcode(),bookingData.getCountry()),
						bookingData.getEmail(),
						bookingData.getPhoneNumber(),
						bookingData.getDiscountRate().multiply(BigDecimal.valueOf(-1)));
				customer = Optional.of(companyCustomer);
			}
			
			
		}
		
		HashMap<RoomCategory, Integer> categoryCount = new HashMap<RoomCategory, Integer>();
		if (bookingData.getCategoryValues().size() == 0) {
			throw new InvalidBookingException("Booking could not be created <br/> Atleast one category has to be selected");
		}
		for (int i = 0; i < bookingData.getCategoryValues().size(); i++) {
			if(categoryCount.containsKey(roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get())) {
				throw new InvalidBookingException("Booking could not be created <br/> The same category can't be selected more than once");
			}
			RoomCategory category = roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get();
			categoryCount.put(
					category, 
					bookingData.getCategoryAmounts().get(i));
			
		}
		Booking booking = Booking.create(
				bookingRepository.nextIdentity(),
				convertedCheckInDate,
				convertedCheckOutDate,
				bookingData.getCreditCardNumber(),
				bookingData.getCreditCardValid(),
				customer.get().getCustomerId(),
				bookingData.getGuestCount(),
				BookingStatus.PAID,
				categoryCount);
		
		for (HashMap.Entry<RoomCategory, Integer> entry : categoryCount.entrySet()) {
		    RoomCategory key = entry.getKey();
		    Integer value = entry.getValue();
		    BookingAssignment ba = BookingAssignment.create(bookingAssignmentRepository.nextIdentity(), key, value, booking);
		    bookingAssignmentRepository.addBookingAssignment(ba);
		}
		
		if (customerCreated) {
			customerRepository.addCustomer(customer.get());
		}
		bookingRepository.addBooking(booking);
		
	}

	@Override
	public Optional<BookingDetailsDTO> getBookingDetailsById(String id) {
		Optional<Booking> booking = bookingRepository.getBookingById(new BookingId(id));
		Optional<BookingDetailsDTO> dto = Optional.empty();
		if (booking.isPresent()) {
			Optional<CustomerDetailsDTO> customer = customerService.getCustomerDetailsById(booking.get().getCustomerId().getId());
			dto = Optional.of(BookingDetailsDTO.createFromBooking(booking.get(), customer.get()));
		}
		return dto;
	}

	@Override
	public List<BookingDetailsDTO> getReadyBookingsByDate(LocalDate date) {
		List<Booking> bookings = bookingRepository.getReadyBookingsByCheckInDate(date);
		List<BookingDetailsDTO> bookingDTOs= new ArrayList<BookingDetailsDTO>();
		
		for (Booking b : bookings) {
			Optional<CustomerDetailsDTO> customer = customerService.getCustomerDetailsById(b.getCustomerId().getId());
			bookingDTOs.add(BookingDetailsDTO.createFromBooking(b, customer.get()));
		}
		
		return bookingDTOs;
	}

	@Override
	public void updateBooking(BookingData bookingData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate,
			LocalDate convertedBirthDate) throws Exception {
		CustomerId oldCustomerId = new CustomerId(bookingData.getCustomerId());
		customerRepository.deleteCustomerById(oldCustomerId);
		IndividualCustomer customer = IndividualCustomer.create(
				oldCustomerId,
				bookingData.getFirstName(),
				bookingData.getLastName(),
				convertedBirthDate,
				new Address(bookingData.getStreetName(),bookingData.getStreetNumber(),bookingData.getCity(),bookingData.getPostcode(),bookingData.getCountry()),
				bookingData.getEmail(),
				bookingData.getPhoneNumber(),
				bookingData.getGender()
				);
		if (bookingData.getMiddleName() != "") {
			customer.addMiddleName(bookingData.getMiddleName());
		}
		
		HashMap<RoomCategory, Integer> categoryCount = new HashMap<RoomCategory, Integer>();
		for (int i = 0; i < bookingData.getCategoryValues().size(); i++) {
			if(categoryCount.containsKey(roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get())) {
				throw new InvalidBookingException("Booking could not be created <br/> The same category can't be selected more than once");
			}
			categoryCount.put(
					roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get(), 
					bookingData.getCategoryAmounts().get(i));
		}
		
		BookingId oldBookingId = new BookingId(bookingData.getBookingId());
		bookingRepository.deleteBookingById(oldBookingId);
		Booking booking = Booking.create(
				oldBookingId,
				convertedCheckInDate,
				convertedCheckOutDate,
				bookingData.getCreditCardNumber(),
				bookingData.getCreditCardValid(),
				oldCustomerId,
				bookingData.getGuestCount(),
				bookingData.getBookingStatus(),
				categoryCount);
		
		customerRepository.addCustomer(customer);
		bookingRepository.addBooking(booking);
	}
	
	
}
