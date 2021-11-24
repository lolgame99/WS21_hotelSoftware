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
	
	@Test
	void when_add_customer_middlename() throws InvalidCustomerException {
		//given
		CustomerId customerId = new CustomerId("C2");
		String firstName = "Yusuf";
		String middleName = "Emir";
		String lastName = "Cetinkaya";
		LocalDate birthdate = LocalDate.of(1999, 5, 30);
		Address address = new Address("Teststraße", "10", "Höchst", "6973", "Austria");
		String email = "YusufEmir@rhyta.com";
		String phoneNumber = "+436648795210";
		Gender gender = Gender.MALE;
		
		//when
		Customer customer2 = Customer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender).addMiddleName(middleName);

		//then
		assertEquals(customerId, customer2.getCustomerId());
		assertEquals(firstName, customer2.getFirstName());
		assertEquals(middleName, customer2.getMiddleName());
		assertEquals(lastName, customer2.getLastName());
		assertEquals(birthdate, customer2.getBirthdate());
		assertEquals(address, customer2.getAddress());
		assertEquals(email, customer2.getEmail());
		assertEquals(phoneNumber, customer2.getPhoneNumber());
		assertEquals(gender, customer2.getGender());
	}
	
	@Test
	void when_customer_is_under_18() throws InvalidCustomerException {
		//given
		CustomerId customerId = new CustomerId("C3");
		String firstName = "Yusuf";
		String middleName = "Emir";
		String lastName = "Cetinkaya";
		LocalDate birthdate = LocalDate.of(2010, 5, 30);
		Address address = new Address("Teststraße", "10", "Höchst", "6973", "Austria");
		String email = "YusufEmir@rhyta.com";
		String phoneNumber = "+436648795210";
		Gender gender = Gender.MALE;
		
		//when
		Customer customer = Customer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender).addMiddleName(middleName);

		//then
		assertEquals(customerId, customer.getCustomerId());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(middleName, customer.getMiddleName());
		assertEquals(lastName, customer.getLastName());
		assertEquals(birthdate, customer.getBirthdate());
		assertEquals(address, customer.getAddress());
		assertEquals(email, customer.getEmail());
		assertEquals(phoneNumber, customer.getPhoneNumber());
		assertEquals(gender, customer.getGender());
	}
	
}
