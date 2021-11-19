package at.fhv.se.hotel.managementSoftware.domain.model;

public class RoomId {
	private String id;
	
	public RoomId(String id) {
		this.id = id;
	}
	
	public RoomId(RoomId roomId) {
		this.id = roomId.getId();
	}
	
	public String getId() {
		return this.id;
	}
}
