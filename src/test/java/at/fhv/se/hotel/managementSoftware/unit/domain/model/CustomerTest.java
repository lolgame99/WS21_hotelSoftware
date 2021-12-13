package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
	
	@Test
	void when_create_customer() throws InvalidCustomerException {
		//given
		CustomerId customerId = new CustomerId("C1");
		String fName = "Yusuf";
		String lName = "Cetinkaya";
		LocalDate birthdate = LocalDate.of(1995, 5, 20);
		Address address = new Address("Kornmarktplatz", "10", "Bregenz", "6900", "Austria");
		String email = "Yusuf@gmail.com";
		String phoneNumber = "+432929219129";
		Gender gender = Gender.MALE;
		
		//when
		IndividualCustomer customer1 = IndividualCustomer.create(customerId, fName, lName, birthdate, address, email, phoneNumber, gender);
		IndividualCustomer customer2 = IndividualCustomer.create(new CustomerId("C2"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
		//then
		assertEquals(customerId, customer1.getCustomerId());
		assertEquals(fName, customer1.getFirstName());
		assertEquals(lName, customer1.getLastName());
		assertNotEquals(customer1, customer2);
	}

//	@Test
//	void when_create_customer() throws InvalidCustomerException {
//		
//		//given
//		CustomerId customerId = new CustomerId("C1");
//		String firstName = "Johnny";
//		String lastName = "Doe";
//		LocalDate birthdate = LocalDate.of(1992, 7, 22);
//		Address address = new Address("Kornmarktplatz", "10", "Bregenz", "6900", "Austria");
//		String email = "JohnnyDoe@rhyta.com";
//		String phoneNumber = "+436648795210";
//		Gender gender = Gender.MALE;
//		
//		//when
//		Customer customer = Customer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender);
//		
//		//then
//		assertEquals(customerId, customer.getCustomerId());
//		assertEquals(firstName, customer.getFirstName());
//		assertEquals(lastName, customer.getLastName());
//		assertEquals(birthdate, customer.getBirthdate());
//		assertEquals(address, customer.getAddress());
//		assertEquals(email, customer.getEmail());
//		assertEquals(phoneNumber, customer.getPhoneNumber());
//		assertEquals(gender, customer.getGender());
//		
//		
//	}
	
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
		IndividualCustomer customer2 = IndividualCustomer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender).addMiddleName(middleName);

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
	void when_customer_is_under_18() {
		//given
		CustomerId customerId = new CustomerId("C3");
		String firstName = "Yusuf";
		String middleName = "Emir";
		String lastName = "Cetinkaya";
		LocalDate birthdate = LocalDate.of(2010, 5, 30);
		Address address = new Address("Teststrasse", "10", "Hoechst", "6973", "Austria");
		String email = "YusufEmir@rhyta.com";
		String phoneNumber = "+436648795210";
		Gender gender = Gender.MALE;

		//when...then
		assertThrows(InvalidCustomerException.class, () -> IndividualCustomer.create(customerId, firstName, lastName, birthdate, address, email, phoneNumber, gender));
	}
}
