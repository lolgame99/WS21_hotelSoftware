package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class Stay {
	
	private String stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfAdults;
	private int numberOfChildren;
	private String creditCardNumber; 
	private String bookingId;
	private String customerId;
	private String guestId;

	public Stay(String stayId, LocalDate checkInDate, LocalDate checkOutDate,
			String creditCardNumber, String bookingId, String customerId, String guestId)
	{
		this.stayId = stayId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate; 
		this.creditCardNumber = creditCardNumber;
		this.bookingId = bookingId; 
		this.customerId = customerId;
		this.guestId = guestId;
	}

	public void addNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	
	public void addNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
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

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	
	public String getGuestId() {
		return guestId;
	}
	
	
	
	
}

