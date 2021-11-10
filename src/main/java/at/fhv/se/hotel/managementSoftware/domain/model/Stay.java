package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class Stay {
	
	private String stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfGuests;
	private String creditCardNumber; 
	private String bookingId;
	private String customerId;
	private String guestId;

	public Stay(String stayId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests,
			String creditCardNumber, String bookingId, String customerId, String guestId)
	{
		this.stayId = stayId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate; 
		this.creditCardNumber = creditCardNumber;
		this.bookingId = bookingId; 
		this.customerId = customerId;
		this.guestId = guestId;
		this.numberOfGuests = numberOfGuests;
	}
	
	public String getStayId() {
		return stayId;
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

	public String getBookingId() {
		return bookingId;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public String getGuestId() {
		return guestId;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	
}

