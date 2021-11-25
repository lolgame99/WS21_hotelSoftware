package at.fhv.se.hotel.managementSoftware.domain.model;

public class BookingId {
	private String id;
	
	private BookingId() {
	}
	
	public BookingId(String id) {
		this.id = id;
	}
	
	public BookingId(BookingId bookingId) {
		this.id = bookingId.getId();
	}
	
	public String getId() {
		return this.id;
	}

}
