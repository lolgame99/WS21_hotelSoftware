package at.fhv.se.hotel.managementSoftware.domain.model;

public class BookingAssignmentId {
private String id;
	
	private BookingAssignmentId() {
	}
	
	public BookingAssignmentId(String id) {
		this.id = id;
	}
	
	public BookingAssignmentId(BookingAssignmentId guestId) {
		this.id = guestId.getId();
	}
	
	public String getId() {
		return this.id;
	}
}
