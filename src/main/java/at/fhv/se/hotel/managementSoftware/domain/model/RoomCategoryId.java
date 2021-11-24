package at.fhv.se.hotel.managementSoftware.domain.model;

import java.io.Serializable;

public class RoomCategoryId implements Serializable {
	private String id;
	
	public RoomCategoryId() {
	}
	
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
