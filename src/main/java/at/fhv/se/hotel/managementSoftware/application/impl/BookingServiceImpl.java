package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;

@Component
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<BookingOverviewDTO> getAllBookings() {
		List<Booking> bookings = bookingRepository.getAllBookings();
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			int totalRoomCount = 0;
			for(int categoryCount : b.getCategoryCount().values()) {
				totalRoomCount += categoryCount;
			}
			
			bookingDTOs.add(BookingOverviewDTO.builder()
					.withId(b.getBookingId())
					.withCheckInDate(b.getCheckInDate())
					.withCustomer(b.getCustomer())
					.withGuestCount(b.getGuestCount())
					.withRoomCount(totalRoomCount)
					.withRoomCategory(b.getRoomCategory())
					.build()
					);
		}
		
		return bookingDTOs;
	}

	@Override
	public List<BookingOverviewDTO> getBookingsByDate(LocalDate date) {
		List<Booking> bookings = bookingRepository.getBookingsByCheckInDate(date);
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			int totalRoomCount = 0;
			for(int categoryCount : b.getCategoryCount().values()) {
				totalRoomCount += categoryCount;
			}
			
			bookingDTOs.add(BookingOverviewDTO.builder()
					.withId(b.getBookingId())
					.withCheckInDate(b.getCheckInDate())
					.withCustomer(b.getCustomer())
					.withGuestCount(b.getGuestCount())
					.withRoomCount(totalRoomCount)
					.withRoomCategory(b.getRoomCategory())
					.build()
					);
		}
		
		return bookingDTOs;
	}

	@Override
	public void addBooking(Booking booking) {
		bookingRepository.addBooking(booking);
	}
	
	
	
}
