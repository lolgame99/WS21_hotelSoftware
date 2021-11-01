package at.fhv.se.hotel.managementSoftware.domain.model;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;

public class Customer {
	
	private String customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private Address address;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	private Address billingAddress;
	
	public Customer(String customerId, String firstName, String lastName, Address address, String email,
			String phoneNumber, Gender gender) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public void addMiddleName(String middlename) {
		this.middleName = middlename;
	}

	public String getCustomerId() {
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

	public Address getAddress() {
		return address;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
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
