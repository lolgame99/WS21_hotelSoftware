package at.fhv.se.hotel.managementSoftware.domain.model;

public class RoomAssignmentId {
private String id;
	
	private RoomAssignmentId() {
	}
	
	public RoomAssignmentId(String id) {
		this.id = id;
	}
	
	public RoomAssignmentId(RoomAssignmentId guestId) {
		this.id = guestId.getId();
	}
	
	public String getId() {
		return this.id;
	}
}
