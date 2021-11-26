package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public class StayTest {
	
	@Test
	void when_create_stay_for_walk_in_guest() throws InvalidStayException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//when
		Stay stay = Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId);
		
		
		//then
		assertEquals(stayId, stay.getStayId());
		assertEquals(checkInDate, stay.getCheckInDate());
		assertEquals(checkOutDate, stay.getCheckOutDate());
		assertEquals(guestCount, stay.getGuestCount());
		assertEquals(creditCardNumber, stay.getCreditCardNumber());
		assertEquals(customerId, stay.getCustomerId());
		assertEquals(guestId, stay.getGuestId());
	}
	
	@Test
	void when_checkOutDate_is_before_checkInDate() throws InvalidStayException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.of(2021, 11, 24);
		LocalDate checkOutDate = LocalDate.of(2021, 11, 20);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//when....then
		assertThrows(InvalidStayException.class, () -> Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId));
		
		
	}
	
	@Test
	void when_checkInDate_is_in_the_past() throws InvalidStayException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.of(2021, 11, 23);
		LocalDate checkOutDate = LocalDate.of(2021, 11, 28);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//when...then
		assertThrows(InvalidStayException.class, () -> Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId));

		
	}
	
	@Test
	void when_guestCount_is_at_least_one() throws InvalidStayException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 0;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//when...then
		assertThrows(InvalidStayException.class, () -> Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId));
		
		
		
	}
	
	
	@Test
	void when_create_stay_from_booking() throws InvalidBookingException {
		//given from Stay
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.of(2021, 11, 24);
		LocalDate checkOutDate = LocalDate.of(2021, 11, 28);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		BookingId bookingId = new BookingId("B12");

		//given from Booking
		String creditCardValid = "05/22";
		BookingStatus bookingStatus = BookingStatus.PAID;
		Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, null);
		HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
		categoryCount.put(null, 1);
		
		//when
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
		
		//then
	}
	
	

}
