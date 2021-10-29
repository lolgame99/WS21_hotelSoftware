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
	private String nationality;
	private String email;
	private String phoneNumber;
	private String billingStreetName;
	private String billingStreetNumber;
	private String billingCity;
	private String billingPostCode;
	private String billingCountry;
	
	
	public Customer(String customerId, String firstName, String middleName, String lastName, String streetName,
			String streetNumber, String city, String postCode, String country, String nationality, String email,
			String phoneNumber, String billingStreetName, String billingStreetNumber, String billingCity,
			String billingPostCode, String billingCountry) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.nationality = nationality;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.billingStreetName = billingStreetName;
		this.billingStreetNumber = billingStreetNumber;
		this.billingCity = billingCity;
		this.billingPostCode = billingPostCode;
		this.billingCountry = billingCountry;
	}
	
	
	
}
