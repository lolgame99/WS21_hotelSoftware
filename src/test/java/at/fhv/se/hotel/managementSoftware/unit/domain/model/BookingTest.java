package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class BookingTest {
	@Test
	void when_create_booking() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
		//when
		Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
		//then
		assertEquals(bookingId, booking.getBookingId());
		assertEquals(checkInDate, booking.getCheckInDate());
		assertEquals(checkOutDate, booking.getCheckOutDate());
		assertEquals(creditCardNumber, booking.getCreditCardNumber());
		assertEquals(creditCardValid, booking.getCreditCardValid());
		assertEquals(customerId, booking.getCustomerId());
		assertEquals(bookingStatus, booking.getBookingStatus());
		assertEquals(categoryCount, booking.getCategoryCount());
		
		
	}
	
	@Test
	void when_checkoutdate_before_checkindate() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(7);
		LocalDate checkOutDate = LocalDate.now();
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        
		//when...then
		assertThrows(InvalidBookingException.class, () -> Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount));
		
	}
	
	@Test
	void when_checkindate_bevore_today() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().minusDays(7);
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        
		//when...then
		assertThrows(InvalidBookingException.class, () -> Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount));
		
		
	}
	
	@Test
	void when_guestcount_under1() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 0;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        
		//when...then
		assertThrows(InvalidBookingException.class, () -> Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount));
		
		
	}
	
	@Test
	void when_under_one_roomcategory() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 0;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(null, null);
		
        
		//when...then
		assertThrows(InvalidBookingException.class, () -> Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount));
		
		
	}
	
	@Test
	void when_not_enough_beds_for_guests() throws InvalidCustomerException, InvalidBookingException {
		
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 5;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 1);
		
        
		//when...then
		assertThrows(InvalidBookingException.class, () -> Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount));
		
		
	}
	
}
