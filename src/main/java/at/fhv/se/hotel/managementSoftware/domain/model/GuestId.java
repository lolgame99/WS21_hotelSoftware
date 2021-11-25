package at.fhv.se.hotel.managementSoftware.domain.model;

public class GuestId {
	private String id;
	
	private GuestId() {
	}
	
	public GuestId(String id) {
		this.id = id;
	}
	
	public GuestId(GuestId guestId) {
		this.id = guestId.getId();
	}
	
	public String getId() {
		return this.id;
	}

}
