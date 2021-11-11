package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;

public class Booking {
	private BookingId bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String creditCardNumber;
	private Customer customer;
	private int guestCount;
	private BookingStatus bookingStatus;

	private HashMap<RoomCategory, Integer> categoryCount;
	
	public Booking(BookingId bookingId, LocalDate checkInDate, LocalDate checkOutDate, String creditCardNumber,
			Customer customer, int guestCount, BookingStatus bookingStatus, HashMap<RoomCategory, Integer> categoryCount) throws InvalidBookingException{
		super();
		this.categoryCount = categoryCount;
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.creditCardNumber = creditCardNumber;
		this.customer = customer;
		this.guestCount = guestCount;
		this.bookingStatus = bookingStatus;
		
		validate();
	}
	
	private void validate() throws InvalidBookingException{
		// Pruefen, ob Check-in-Date vor Check-out-Date liegt
		if (checkInDate.compareTo(checkOutDate) >= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Check-out-Date can't be before Check-in Date");
		}
		
		//Pruefen, ob Check-in-Date vor heute liegt
		if (checkInDate.compareTo(LocalDate.now()) < 0) {
			throw new InvalidBookingException("Booking could not be created <br> Check-in-Date can't be in the past");
		}

		// Pruefen, ob mind. ein Kunde
		if (guestCount <= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Booking requires atleast 1 guest");
		}
		
		//Pruefen ob mind. eine Raumkategory in Hashmap
		if(categoryCount.isEmpty()) {
			throw new InvalidBookingException("Booking could not be created <br> Atleast 1 roomcategory has to be selected");
		}		
		
	}

	public BookingId getBookingId() {
		return bookingId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<RoomCategory, Integer> getCategoryCount() {
		return categoryCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

}
