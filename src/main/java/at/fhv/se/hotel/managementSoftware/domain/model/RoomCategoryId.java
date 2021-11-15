package at.fhv.se.hotel.managementSoftware.domain.model;

public class RoomCategoryId {
	private String id;
	
	public RoomCategoryId(String id) {
		this.id = id;
	}
	
	public RoomCategoryId(RoomCategoryId categoryId) {
		this.id = categoryId.getId();
	}
	
	public String getId() {
		return this.id;
	}

}
