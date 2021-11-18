package at.fhv.se.hotel.managementSoftware.view.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class StayData {
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
	
	private String bookingId;
	private String checkInDate;
	private String checkOutDate;
	private int guestCount;
	private String creditCardNumber;
	private String creditCardValid;
	
	private String guest;
	private String guestFirstName;
	private String guestMiddleName;
	private String guestLastName;
	private String guestPhoneNumber;
	
	private List<String> categoryValues= new ArrayList<String>();
	private List<String> categoryCount= new ArrayList<String>();
	
	private List<String> roomCategorys = new ArrayList<String>();
	private List<String> roomNumbers = new ArrayList<String>();

	public StayData() {
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
	
	public void addExistingBooking(BookingDetailsDTO existingBooking) {
		this.bookingId = existingBooking.getBookingId().getId();
		this.addExistingCustomer(existingBooking.getCustomer());
		this.checkInDate = existingBooking.getCheckInDate().toString();
		this.checkOutDate = existingBooking.getCheckOutDate().toString();
		this.guestCount = existingBooking.getGuestCount();
		this.creditCardNumber = existingBooking.getCreditCardNumber();
		this.creditCardValid = existingBooking.getCreditCardValid();
		
		for (RoomCategory cat : existingBooking.getCategoryCount().keySet()) {
			categoryValues.add(cat.getCategoryID().getId());
		}
		for (Integer integer : existingBooking.getCategoryCount().values()) {
			categoryCount.add(integer.toString());
		}
	}
	
	
	
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getGuestFirstName() {
		return guestFirstName;
	}

	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}

	public String getGuestLastName() {
		return guestLastName;
	}

	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
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

	public String getGuestMiddleName() {
		return guestMiddleName;
	}

	public void setGuestMiddleName(String guestMiddleName) {
		this.guestMiddleName = guestMiddleName;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public List<String> getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(List<String> categoryCount) {
		this.categoryCount = categoryCount;
	}
	
	
	
}
