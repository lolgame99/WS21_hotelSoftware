package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

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
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;

@SpringBootTest
@Transactional
public class StayRepositoryTest {
	
	@Autowired
	private StayRepository stayRepository;

	@Test
	void when_create_stay_for_walk_in_guest_is_added() throws InvalidStayException {
		//given
		Stay expectedStay = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(14), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		
		//when
		stayRepository.addStay(expectedStay);
		Optional<Stay> actualStay = stayRepository.getStayById(expectedStay.getStayId());
		
		//then
		assertEquals(expectedStay.getStayId().getId(), actualStay.get().getStayId().getId());
		assertEquals(expectedStay.getCheckInDate(), actualStay.get().getCheckInDate());
		assertEquals(expectedStay.getCheckOutDate(), actualStay.get().getCheckOutDate());
		assertEquals(expectedStay.getGuestCount(), actualStay.get().getGuestCount());
		assertEquals(expectedStay.getCreditCardNumber(), actualStay.get().getCreditCardNumber());
		assertEquals(expectedStay.getCustomerId().getId(), actualStay.get().getCustomerId().getId());
		assertEquals(expectedStay.getGuestId().getId(), actualStay.get().getGuestId().getId());
	}
	
	@Test
	void when_create_stay_from_booking_is_added() throws Exception {
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("asfg");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
		Stay expectedStay = Stay.createFromBooking(new StayId("234"), booking, new GuestId("235"));
		
		//when
		stayRepository.addStay(expectedStay);
		Optional<Stay> actualStay = stayRepository.getStayById(new StayId("234"));
		
		//then
		assertEquals(expectedStay.getBookingId().getId(), actualStay.get().getBookingId().getId());
		assertEquals(expectedStay.getStayId().getId(), actualStay.get().getStayId().getId());
		assertEquals(expectedStay.getCheckInDate(), actualStay.get().getCheckInDate());
		assertEquals(expectedStay.getCheckOutDate(), actualStay.get().getCheckOutDate());
		assertEquals(expectedStay.getGuestCount(), actualStay.get().getGuestCount());
		assertEquals(expectedStay.getCreditCardNumber(), actualStay.get().getCreditCardNumber());
		assertEquals(expectedStay.getCustomerId().getId(), actualStay.get().getCustomerId().getId());
		assertEquals(expectedStay.getGuestId().getId(), actualStay.get().getGuestId().getId());
		
	}
	
	@Test
	void when_all_stays_are_loaded() {
		//given
		int expectedStayCount = 2;
		
		//when
		List<Stay> stay = stayRepository.getAllStays();
		int actualStayCount = stay.size();
		
		//then
		assertEquals(expectedStayCount, actualStayCount);
	}
	
	@Test
	void when_given_invalid_StayId_return_empty() {
		//given
		StayId invalidStayId = new StayId("fla13");
		
		//when
		Optional<Stay> actualStay = stayRepository.getStayById(invalidStayId);
		
		//then
		assertTrue(actualStay.isEmpty());
	}

	@Test
	void when_all_stays_are_loaded_return_current() throws InvalidStayException {
		//given
		Stay expectedStay = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(14), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		Stay unexpectedStay = Stay.createForWalkIn(new StayId("123"), LocalDate.now().plusDays(7), LocalDate.now().plusDays(14), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		
		//when
		stayRepository.addStay(unexpectedStay);
		stayRepository.addStay(expectedStay);
		
		List<Stay> actualCurrentStays = stayRepository.getCurrentStays(LocalDate.now());
		
		//then
		assertEquals(3, actualCurrentStays.size());
	}
}
