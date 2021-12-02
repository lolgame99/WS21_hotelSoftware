package at.fhv.se.hotel.managementSoftware.domain.model;

public class StayId {
	private String id;
	
	private StayId() {
	}
	
	public StayId(String id) {
		this.id = id;
	}
	
	public StayId(StayId stayId) {
		this.id = stayId.getId();
	}
	
	public String getId() {
		return this.id;
	}

}
