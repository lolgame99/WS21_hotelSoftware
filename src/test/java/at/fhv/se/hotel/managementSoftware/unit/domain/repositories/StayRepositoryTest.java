package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
		Stay stayExpected = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(14), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		
		//when
		stayRepository.addStay(stayExpected);
		Optional<Stay> stayActual = stayRepository.getStayById(stayExpected.getStayId());
		
		//then
		assertEquals(stayExpected.getStayId().getId(), stayActual.get().getStayId().getId());
		assertEquals(stayExpected.getCheckInDate(), stayActual.get().getCheckInDate());
		assertEquals(stayExpected.getCheckOutDate(), stayActual.get().getCheckOutDate());
		assertEquals(stayExpected.getGuestCount(), stayActual.get().getGuestCount());
		assertEquals(stayExpected.getCreditCardNumber(), stayActual.get().getCreditCardNumber());
		assertEquals(stayExpected.getCustomerId().getId(), stayActual.get().getCustomerId().getId());
		assertEquals(stayExpected.getGuestId().getId(), stayActual.get().getGuestId().getId());
	}
	
	@Test
	void when_create_stay_from_booking_is_added() throws InvalidStayException {
		//given
		//Booking booking = new Booking(new BookingId("435"), LocalDate.now(), LocalDate.now().plusDays(14), "3829 2929 2223", "valid", new CustomerId("C2"), 6, BookingStatus.PAID, booking.getCategoryCount());
		//Stay stayExpected = Stay.createFromBooking(new StayId("234"), booking, new GuestId("235"));
		
		//Problem: beim new Booking erstellen, wegen Hashmap
	}
	
	@Test
	void when_all_stays_are_loaded() {
		//given
		//String[] expectedStayIds = {"0", "1", "2", "3", "4"};
		
		//when
		String[] actualStayIds = new String[5];
		List<Stay> stay = stayRepository.getAllStays();
		for (int i = 0; i < stay.size(); i++) {
			actualStayIds[i] = stay.get(i).getStayId().getId();
		}
		
		//then
		//assertArrayEquals(expectedStayIds, actualStayIds);
		
		//Problem: bei expectedStaysIds muss ich die UUID aufrufen oder herausfinden, weil sonst ein Fehler kommt, dass die Array-Indizes nicht übereinstimmen
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
	void when_all_current_stays_are_loaded() {
		
	}
}
