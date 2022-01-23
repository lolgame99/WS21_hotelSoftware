package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class IndividualCustomer implements Customer{
	private long id;
	private CustomerId customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private Address address;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	private IndividualCustomer() {
		
	}
	
	public static IndividualCustomer create(CustomerId customerId, String firstName, String lastName, LocalDate birthdate, Address address, String email,
			String phoneNumber, Gender gender) throws InvalidCustomerException {
		if (ChronoUnit.YEARS.between(birthdate, LocalDate.now()) < 18) {
			throw new InvalidCustomerException("Customer could not be created <br/> Customer has to be atleast 18 years old ");
		}
		
		IndividualCustomer customer = new IndividualCustomer();
		customer.customerId = customerId;
		customer.firstName = firstName;
		customer.lastName = lastName;
		customer.birthdate = birthdate;
		customer.address = address;
		customer.email = email;
		customer.phoneNumber = phoneNumber;
		customer.gender = gender;
		return customer;
	}	
	
	public IndividualCustomer addMiddleName(String middlename) {
		if (this.middleName == null) {
			this.middleName = middlename;
		}
		return this;
	}
	
	@Override
	public CustomerId getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Address getAddress() {
		return address;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}
	
}
