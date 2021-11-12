package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO.Builder;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

@Component
public class StayServiceImpl implements StayService{

	@Autowired
	private StayRepository stayRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<StayDetailsDTO> getAllStays() {
		List<Stay> stays = stayRepository.getAllStays();
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			 Builder dtoBuilder = StayDetailsDTO.builder()
					.withId(s.getStayId())
					.withCheckInDate(s.getCheckInDate())
					.withCheckOutDate(s.getCheckOutDate())
					.withCreditCardNumber(s.getCreditCardNumber())
					.withCustomer(customerRepository.getCustomerById(s.getCustomerId()).get())
					.withGuest(guestRepository.getGuestByStayId(s.getStayId()).get())
					.withNumberOfGuests(s.getGuestCount());
			 
			Optional<Booking> booking = bookingRepository.getBookingById(s.getBookingId());
			if (booking.isPresent()) {
				dtoBuilder.withBooking(booking.get());
			}
			
			stayDTOs.add(dtoBuilder.build());
							
		}
			
		return stayDTOs;
	}

	@Override
	public List<StayDetailsDTO> getCurrentStays(LocalDate date) {
		List<Stay> stays = stayRepository.getCurrentStays(date); 
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			 Builder dtoBuilder = StayDetailsDTO.builder()
					.withId(s.getStayId())
					.withCheckInDate(s.getCheckInDate())
					.withCheckOutDate(s.getCheckOutDate())
					.withCreditCardNumber(s.getCreditCardNumber())
					.withCustomer(customerRepository.getCustomerById(s.getCustomerId()).get())
					.withGuest(guestRepository.getGuestByStayId(s.getStayId()).get())
					.withNumberOfGuests(s.getGuestCount());
			 
			Optional<Booking> booking = bookingRepository.getBookingById(s.getBookingId());
			if (booking.isPresent()) {
				dtoBuilder.withBooking(booking.get());
			}
			
			stayDTOs.add(dtoBuilder.build());
							
		}
			
		return stayDTOs;
	}

	@Override
	public void addStayFromData(StayData stayData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate,
			LocalDate convertedBirthDate) {
		// TODO Auto-generated method stub
		
	}

}
