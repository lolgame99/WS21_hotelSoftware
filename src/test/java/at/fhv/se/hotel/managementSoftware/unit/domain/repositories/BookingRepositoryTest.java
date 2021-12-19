package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;


@SpringBootTest
@Transactional
public class BookingRepositoryTest {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void when_all_test_bookings_are_loaded() {
		//given
		int expectedCount = 6;
		
		//when
		List<Booking> booking = bookingRepository.getAllBookings();
		
		//then
		assertEquals(booking.size(), expectedCount);
	}
	
	@Test
	void when_checkInDate_return_all() throws Exception {
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(20);
		LocalDate checkOutDate = LocalDate.now().plusDays(30);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = customerRepository.getAllCustomers().get(2).getCustomerId();
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		RoomCategory category = roomCategoryRepository.getAllRoomCategories().get(2);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(category, 1);
		
		Booking expectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
		//when
		bookingRepository.addBooking(expectedBooking);
		List<Booking> actualBooking = bookingRepository.getBookingsByCheckInDate(checkInDate);
		
		//then
		assertEquals(expectedBooking.getBookingId().getId(), actualBooking.get(0).getBookingId().getId());
		assertEquals(expectedBooking.getCheckInDate(), actualBooking.get(0).getCheckInDate());
		assertEquals(expectedBooking.getCheckOutDate(), actualBooking.get(0).getCheckOutDate());
		assertEquals(expectedBooking.getCreditCardNumber(), actualBooking.get(0).getCreditCardNumber());
		assertEquals(expectedBooking.getCreditCardValid(), actualBooking.get(0).getCreditCardValid());
		assertEquals(expectedBooking.getCustomerId().getId(), actualBooking.get(0).getCustomerId().getId());
		assertEquals(expectedBooking.getGuestCount(), actualBooking.get(0).getGuestCount());
		assertEquals(expectedBooking.getBookingStatus(), actualBooking.get(0).getBookingStatus());
		assertEquals(expectedBooking.getCategoryCount(), actualBooking.get(0).getCategoryCount());
	}
	
	@Test
	void when_given_booking_is_added_return_equal() throws InvalidBookingException {
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(20);
		LocalDate checkOutDate = LocalDate.now().plusDays(30);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = customerRepository.getAllCustomers().get(2).getCustomerId();
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		RoomCategory category = roomCategoryRepository.getAllRoomCategories().get(2);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(category, 1);
		
		Booking expectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
		//when
		bookingRepository.addBooking(expectedBooking);
		Optional<Booking> actualBooking = bookingRepository.getBookingById(expectedBooking.getBookingId());
		
		//then
		assertEquals(expectedBooking.getBookingId().getId(), actualBooking.get().getBookingId().getId());
		assertEquals(expectedBooking.getCheckInDate(), actualBooking.get().getCheckInDate());
		assertEquals(expectedBooking.getCheckOutDate(), actualBooking.get().getCheckOutDate());
		assertEquals(expectedBooking.getCreditCardNumber(), actualBooking.get().getCreditCardNumber());
		assertEquals(expectedBooking.getCreditCardValid(), actualBooking.get().getCreditCardValid());
		assertEquals(expectedBooking.getCustomerId().getId(), actualBooking.get().getCustomerId().getId());
		assertEquals(expectedBooking.getGuestCount(), actualBooking.get().getGuestCount());
		assertEquals(expectedBooking.getBookingStatus(), actualBooking.get().getBookingStatus());
		assertEquals(expectedBooking.getCategoryCount(), actualBooking.get().getCategoryCount());
		
	}
	
	@Test
	void when_given_invalid_bookingId_return_empty() {
		//given
		BookingId invalBookingId = new BookingId("asdf");
		
		//when
		Optional<Booking> actualBooking = bookingRepository.getBookingById(invalBookingId);
		
		//then
		assertTrue(actualBooking.isEmpty());
	}
	
	@Test
	void when_given_readyBookings_by_checkInDate_return_equal() throws InvalidBookingException {
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(22);
		LocalDate checkOutDate = LocalDate.now().plusDays(30);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = customerRepository.getAllCustomers().get(2).getCustomerId();
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		RoomCategory category = roomCategoryRepository.getAllRoomCategories().get(2);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(category, 1);
		
		Booking expectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		Booking unexpectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, BookingStatus.PENDING, categoryCount);
		
		
		//when
		bookingRepository.addBooking(expectedBooking);
		List<Booking> actualBooking = bookingRepository.getReadyBookingsByCheckInDate(checkInDate);
		
		//then
		assertEquals(expectedBooking.getBookingId().getId(), actualBooking.get(0).getBookingId().getId());
		assertEquals(expectedBooking.getCheckInDate(), actualBooking.get(0).getCheckInDate());
		assertEquals(expectedBooking.getCheckOutDate(), actualBooking.get(0).getCheckOutDate());
		assertEquals(expectedBooking.getCreditCardNumber(), actualBooking.get(0).getCreditCardNumber());
		assertEquals(expectedBooking.getCreditCardValid(), actualBooking.get(0).getCreditCardValid());
		assertEquals(expectedBooking.getCustomerId().getId(), actualBooking.get(0).getCustomerId().getId());
		assertEquals(expectedBooking.getGuestCount(), actualBooking.get(0).getGuestCount());
		assertEquals(expectedBooking.getBookingStatus(), actualBooking.get(0).getBookingStatus());
		assertEquals(expectedBooking.getCategoryCount(), actualBooking.get(0).getCategoryCount());
	}
	
	@Test
	 void delete_bookingById_does_not_return_anything() throws Exception {
		// given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(22);
		LocalDate checkOutDate = LocalDate.now().plusDays(30);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = customerRepository.getAllCustomers().get(2).getCustomerId();
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		RoomCategory category = roomCategoryRepository.getAllRoomCategories().get(2);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(category, 1);
		
		Booking expectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
	        
	    //when
	    bookingRepository.addBooking(expectedBooking);
	    bookingRepository.deleteBookingById(expectedBooking.getBookingId());
	    List<Booking> allBookings  = bookingRepository.getAllBookings();
	    boolean isDeleted = true;
	    
	    for (int i = 0; i < allBookings.size(); i++) {
			if (allBookings.get(i).getBookingId().getId() == expectedBooking.getBookingId().getId()) {
				isDeleted = false;
			}
		}
	    
	    //then
	    assertTrue(isDeleted);
	        
	  }   
	
}
