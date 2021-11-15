package at.fhv.se.hotel.managementSoftware.domain.valueObjects;

public class Address {
	private String streetName;
	private String streetNumber;
	private String city;
	private String postCode;
	private String country;
	
	public Address(String streetName, String streetNumber, String city, String postCode, String country) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCountry() {
		return country;
	}
	
	
	
}
