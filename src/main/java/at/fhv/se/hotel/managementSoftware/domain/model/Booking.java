package at.fhv.se.hotel.managementSoftware.domain.model;

import java.util.Date;

public class Booking {
	private String id;
	private Date checkInDate;
	
	public Booking(String idString, Date checkInDate) {
		super();
		this.id = idString;
		this.checkInDate = checkInDate;
	}
}
