package at.fhv.se.hotel.managementSoftware.domain.model;

public class Customer {
	
	private String customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private enum Gender {
		MALE, FEMALE, DIVERSE;
	}
	private String streetName;
	private String streetNumber;
	private String city;
	private String postCode;
	private String country;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	private String billingStreetName;
	private String billingStreetNumber;
	private String billingCity;
	private String billingPostCode;
	private String billingCountry;
	
	
	public Customer(String customerId, String firstName, String lastName, String streetName,
			String streetNumber, String city, String postCode, String country, String email,
			String phoneNumber, Gender gender) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public void addBillingAddress(String billingStreetName, String billingStreetNumber, String billingCity, String billingPostCode, String billingCountry) {
		this.billingStreetName = billingStreetName;
		this.billingStreetNumber = billingStreetNumber;
		this.billingCity = billingCity;
		this.billingPostCode = billingPostCode;
		this.billingCountry = billingCountry;
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

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	
}
