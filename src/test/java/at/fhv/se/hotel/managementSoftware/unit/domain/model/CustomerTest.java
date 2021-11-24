package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

	@Test
	void when_create_customer() throws InvalidCustomerException {
		
		//given
		CustomerId customerId = new CustomerId("C1");
		String firstName = "Johnny";
		String lastName = "Doe";
		LocalDate birthdate = LocalDate.of(1992, 7, 22);
		Address address = new Address("Kornmarktplatz", "10", "Bregenz", "6900", "Austria");
		String email = "JohnnyDoe@rhyta.com";
		String phoneNumber = "+436648795210";
		Gender gender = Gender.MALE;
		
		//when
		Customer customer = Customer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender);
		
		//then
		assertEquals(customerId, customer.getCustomerId());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(lastName, customer.getLastName());
		assertEquals(birthdate, customer.getBirthdate());
		assertEquals(address, customer.getAddress());
		assertEquals(email, customer.getEmail());
		assertEquals(phoneNumber, customer.getPhoneNumber());
		assertEquals(gender, customer.getGender());
		
		
	}
	
}
