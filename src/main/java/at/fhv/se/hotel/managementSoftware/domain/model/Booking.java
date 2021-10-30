package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.util.HashMap;

public class Booking {
	private String bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private LocalDate cancelationDate;
	private String creditCardNumber;
	private String customerId;
	private int guestCount;
	
	private HashMap<String, Integer> categoryCount;
	
	public Booking(String bookingId, LocalDate checkInDate, LocalDate checkOutDate, String creditCardNumber, String customerId, int guestCount) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.creditCardNumber = creditCardNumber;
		this.customerId = customerId;
		this.guestCount = guestCount;
		categoryCount = new HashMap<String, Integer>();
	}
	
	public Booking addCategory(String category, Integer count) {
		categoryCount.put(category, count);
		return this;
	}

	public String getBookingId() {
		return bookingId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public LocalDate getCancelationDate() {
		return cancelationDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public HashMap<String, Integer> getCategoryCount() {
		return categoryCount;
	}

	public int getGuestCount() {
		return guestCount;
	}
	
	
	
}
