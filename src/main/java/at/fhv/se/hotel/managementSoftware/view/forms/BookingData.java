package at.fhv.se.hotel.managementSoftware.view.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class BookingData {
	private String customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Gender gender;
	private String birthdate;
	
	private String email;
	private String phoneNumber;
	
	private String streetName;
	private String streetNumber;
	private String city;
	private String postcode;
	private String country;
	
	private String checkInDate;
	private String checkOutDate;
	private int guestCount;
	private String creditCardNumber;
	private String creditCardValid;
	
	private List<String> categoryValues= new ArrayList<String>();
	private List<Integer> categoryAmounts= new ArrayList<Integer>();

	public BookingData() {
	}
	
	public void addExistingCustomer(CustomerDetailsDTO existingCustomer) {
		this.customerId = existingCustomer.getCustomerId().getId();
		this.firstName = existingCustomer.getFirstName();
		this.middleName = existingCustomer.getMiddleName();
		this.lastName = existingCustomer.getLastName();
		this.gender = existingCustomer.getGender();
		this.birthdate = existingCustomer.getBirthdate().toString();
		this.email = existingCustomer.getEmail();
		this.phoneNumber = existingCustomer.getPhoneNumber();
		this.streetName = existingCustomer.getAddress().getStreetName();
		this.streetNumber = existingCustomer.getAddress().getStreetNumber();
		this.city = existingCustomer.getAddress().getCity();
		this.postcode = existingCustomer.getAddress().getPostCode();
		this.country = existingCustomer.getAddress().getCountry();
	}
	
	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public List<String> getCategoryValues() {
		return categoryValues;
	}


	public void setCategoryValues(List<String> categoryValues) {
		this.categoryValues = categoryValues;
	}


	public List<Integer> getCategoryAmounts() {
		return categoryAmounts;
	}


	public void setCategoryAmounts(List<Integer> categoryAmounts) {
		this.categoryAmounts = categoryAmounts;
	}


	public String getCreditCardValid() {
		return creditCardValid;
	}

	public void setCreditCardValid(String creditCardValid) {
		this.creditCardValid = creditCardValid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}	

}
