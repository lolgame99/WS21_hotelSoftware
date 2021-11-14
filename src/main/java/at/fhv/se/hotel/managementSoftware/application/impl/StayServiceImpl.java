package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.GuestService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
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
	
	
	
	@Override
	public List<StayDetailsDTO> getAllStays() {
		List<Stay> stays = stayRepository.getAllStays();
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			BookingDetailsDTO booking = null;
			CustomerDetailsDTO customer = customerService.getCustomerDetailsById(s.getCustomerId().getId()).get();
			GuestDetailsDTO guest = guestService.getGuestById(s.getGuestId().getId()).get();
			if(s.getBookingId() != null) {
				booking = bookingService.getBookingDetailsById(s.getBookingId().getId()).get();
			}
			stayDTOs.add(StayDetailsDTO.createFromStay(s, booking, customer, guest));				
		}
			
		return stayDTOs;
	}

	@Override
	public List<StayDetailsDTO> getCurrentStays(LocalDate date) {
		List<Stay> stays = stayRepository.getCurrentStays(date); 
		List<StayDetailsDTO> stayDTOs = new ArrayList<StayDetailsDTO>();
		
		for (Stay s : stays) {
			BookingDetailsDTO booking = null;
			CustomerDetailsDTO customer = customerService.getCustomerDetailsById(s.getCustomerId().getId()).get();
			GuestDetailsDTO guest = guestService.getGuestById(s.getGuestId().getId()).get();
			if(s.getBookingId() != null) {
				booking = bookingService.getBookingDetailsById(s.getBookingId().getId()).get();
			}
			stayDTOs.add(StayDetailsDTO.createFromStay(s, booking, customer, guest));		
		}
			
		return stayDTOs;
	}

	@Override
	public void addStayFromData(StayData stayData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate,
			LocalDate convertedBirthDate) {
		// TODO Auto-generated method stub
		
	}

}
