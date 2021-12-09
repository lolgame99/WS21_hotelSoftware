package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.GuestService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

@SpringBootTest
public class GuestServiceTest {

	@Autowired
	private GuestService guestService;
	
	@MockBean
	private GuestRepository guestRepository;
	
	@Test
	void when_getAll_Guests_returns_all() throws InvalidCustomerException {
		//given
		List<Guest> allGuests = new ArrayList<Guest>();
		allGuests.add(Guest.create(new GuestId("1"), "Test", "Gast", "0552380085"));
		
		Mockito.when(guestRepository.getAllGuests()).thenReturn(allGuests);
		
		//when
		List<GuestDetailsDTO> dtos = guestService.getAllGuests();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(allGuests.get(0).getGuestId().getId(), dtos.get(0).getGuestId().getId());
		assertEquals(allGuests.get(0).getFirstName(), dtos.get(0).getFirstName());
		assertEquals(allGuests.get(0).getLastName(), dtos.get(0).getLastName());
		assertEquals(allGuests.get(0).getPhoneNumber(), dtos.get(0).getPhoneNumber());
	}
	
	@Test
	void when_given_guestId_return_Guest() throws InvalidCustomerException {
		//given;
		Optional<Guest> guest = Optional.of(Guest.create(new GuestId("1"), "Test", "Gast", "0552380085"));
		Mockito.when(guestRepository.getGuestById(any(GuestId.class))).thenReturn(guest);
		
		//when
		Optional<GuestDetailsDTO> dto = guestService.getGuestById(guest.get().getGuestId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(guest.get().getGuestId().getId(), dto.get().getGuestId().getId());
		assertEquals(guest.get().getFirstName(), dto.get().getFirstName());
		assertEquals(guest.get().getLastName(), dto.get().getLastName());
		assertEquals(guest.get().getPhoneNumber(), dto.get().getPhoneNumber());
	}
}
