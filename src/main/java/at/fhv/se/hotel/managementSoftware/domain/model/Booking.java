package at.fhv.se.hotel.managementSoftware.domain.model;

import java.util.Date;
import java.util.HashMap;

public class Booking {
	private String bookingId;
	private Date checkInDate;
	private Date checkOutDate;
	private Date cancelationDate;
	private String creditCardNumber;
	private String customerId;
	
	private HashMap<String, Integer> categoryCount;
	
	public Booking(String bookingId, Date checkInDate, Date checkOutDate, String creditCardNumber, String customerId) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.creditCardNumber = creditCardNumber;
		this.customerId = customerId;
		categoryCount = new HashMap<String, Integer>();
	}
	
	public void addCategory(String category, Integer count) {
		categoryCount.put(category, count);
	}

	public String getBookingId() {
		return bookingId;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public Date getCancelationDate() {
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
	
	
	
}
