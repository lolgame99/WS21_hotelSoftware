package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidGuestException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class GuestTest {
	
	@Test
	void when_create_new_walk_in_guest() {
		//given
		GuestId guestId = new GuestId("C1");
		String firstName = "Rebecca";
		String lastName = "Musterfrau";
		String phoneNumber = "+49512789230";
		
		//when
		Guest guest = Guest.create(guestId, firstName, lastName, phoneNumber);
		
		//then
		assertEquals(guestId, guest.getGuestId());
		assertEquals(firstName, guest.getFirstName());
		assertEquals(lastName, guest.getLastName());
		assertEquals(phoneNumber, guest.getPhoneNumber());
		
		
		
		
	}
	
	@Test
	void when_create_new_guest_from_customer() throws InvalidCustomerException, InvalidGuestException {
		//given
		GuestId guestId = new GuestId("C2");
		CustomerId customerId = new CustomerId("C1");
		String firstName = "Johnny";
		String lastName = "Doe";
		LocalDate birthdate = LocalDate.of(1992, 7, 22);
		Address address = new Address("Kornmarktplatz", "10", "Bregenz", "6900", "Austria");
		String email = "JohnnyDoe@rhyta.com";
		String phoneNumber = "+436648795210";
		Gender gender = Gender.MALE;
		IndividualCustomer customer = IndividualCustomer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender);
		
		//when
		Guest guest = Guest.createFromCustomer(guestId, customer);
		
		
		//then
		assertEquals(guestId, guest.getGuestId());
		assertEquals(firstName, guest.getFirstName());
		assertEquals(lastName, guest.getLastName());
		assertEquals(phoneNumber, guest.getPhoneNumber());
		
	}
	
	@Test
	void when_create_new_guest_from_companyCustomer_throws() throws InvalidCustomerException {
		//given
		CompanyCustomer customer = CompanyCustomer.create(new CustomerId("1"), "Firma AG", new Address("Fabrikstrasse", "3", "Wien", "1010", "Austria"), "Fabrik@email.com", "+43664273949", BigDecimal.valueOf(10));
		
		//when...then
		assertThrows(InvalidGuestException.class, () -> Guest.createFromCustomer(new GuestId("1"), customer));
		
	}
	
	@Test
	void when_add_middlename_to_new_guest() {
		//given
		GuestId guestId = new GuestId("C1");
		String firstName = "Rebecca";
		String middleName = "Maria";
		String lastName = "Musterfrau";
		String phoneNumber = "+49512789230";
		
		//when
		Guest guest = Guest.create(guestId, firstName, lastName, phoneNumber).addMiddleName(middleName);
		
		//then
		assertEquals(guestId, guest.getGuestId());
		assertEquals(firstName, guest.getFirstName());
		assertEquals(middleName, guest.getMiddleName());
		assertEquals(lastName, guest.getLastName());
		assertEquals(phoneNumber, guest.getPhoneNumber());

	}
}
