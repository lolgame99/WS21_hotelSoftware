package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;

public class BookingTest {
	@Test
	void when_create_booking() throws InvalidCustomerException {
		
		//given
		BookingId bookingId;
		LocalDate checkInDate;
		LocalDate checkOutDate;
		String creditCardNumber;
		String creditCardValid;
		CustomerId customerId;
		int guestCount;
		BookingStatus bookingStatus;

		HashMap<RoomCategory, Integer> categoryCount;
		
		//when
		Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
		//then
		assertEquals(bookingId, booking.getBookingId());
		assertEquals(checkInDate, booking.getCheckInDate());
		assertEquals(checkOutDate, booking.getCheckOutDate());
		assertEquals(creditCardNumber, booking.getCreditCardNumber());
		assertEquals(creditCardValid, booking.getCreditCardValid());
		assertEquals(customerId, booking.getCustomerId());
		assertEquals(guestCount, booking.getBookingStatus());
		assertEquals(bookingStatus, booking.getCategoryCount());
		
		
	}
	
	
}
