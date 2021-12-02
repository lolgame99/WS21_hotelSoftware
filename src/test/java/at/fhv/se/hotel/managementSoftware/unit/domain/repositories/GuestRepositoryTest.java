package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;

@SpringBootTest
@Transactional
public class GuestRepositoryTest {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Test
	void when_given_guest_is_added_return_equal() {
		//given
		Guest expectedGuest = Guest.create(new GuestId("1"), "Test", "Gast", "0552380085");
		
		//when
		guestRepository.addGuest(expectedGuest);
		Optional<Guest> actualGuest = guestRepository.getGuestById(expectedGuest.getGuestId());
		
		//then
		assertEquals(expectedGuest.getGuestId().getId(), actualGuest.get().getGuestId().getId());
		assertEquals(expectedGuest.getFirstName(), actualGuest.get().getFirstName());
		assertEquals(expectedGuest.getLastName(), actualGuest.get().getLastName());
		assertEquals(expectedGuest.getPhoneNumber(), actualGuest.get().getPhoneNumber());
		
	}
	
	@Test
	void when_given_invalid_guestId_return_empty() {
		//given
		GuestId invalGuestId = new GuestId("007");
		
		//when
		Optional<Guest> actualGuest = guestRepository.getGuestById(invalGuestId);
		
		//then
		assertTrue(actualGuest.isEmpty());
	}
	
	@Test
	void when_all_test_guests_are_loaded() {
		//given
		String[] expectedGuestFirstNames = {"Ulrich","Franziska"};
		String[] expectedGuestLastNames = {"Vogler","Nachbauer"};
		
		//when
		String[] actualGuestFirstNames = new String[2];
		String[] actualGuestLastNames = new String[2];
		List<Guest> guests = guestRepository.getAllGuests();
		for (int i = 0; i < guests.size(); i++) {
			actualGuestFirstNames[i] = guests.get(i).getFirstName();
			actualGuestLastNames[i] = guests.get(i).getLastName();
		}
		
		//then
		assertArrayEquals(expectedGuestFirstNames, actualGuestFirstNames);
		assertArrayEquals(expectedGuestLastNames, actualGuestLastNames);
	}
	
	
}
