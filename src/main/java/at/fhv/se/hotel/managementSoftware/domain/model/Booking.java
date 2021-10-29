package at.fhv.se.hotel.managementSoftware.domain.model;

import java.util.Date;

public class Booking {
	private String bookingId;
	private Date checkInDate;
	private Date checkOutDate;
	private String roomCategory;
	private String creditCardNumber;
	private int countPerCategory;
	
	public Booking(String bookingId, Date checkInDate, Date checkOutDate, String roomCategory, String creditCardNumber,
			int countPerCategory) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomCategory = roomCategory;
		this.creditCardNumber = creditCardNumber;
		this.countPerCategory = countPerCategory;
	}
	
	
}
