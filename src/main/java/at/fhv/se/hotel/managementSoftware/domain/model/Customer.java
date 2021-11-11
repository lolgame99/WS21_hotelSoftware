package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class Customer {
	
	private CustomerId customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private Address address;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	public Customer(CustomerId customerId, String firstName, String lastName, LocalDate birthdate, Address address, String email,
			String phoneNumber, Gender gender) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	
	public void addMiddleName(String middlename) {
		this.middleName = middlename;
	}

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

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}
	
	
}
