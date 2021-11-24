package at.fhv.se.hotel.managementSoftware.domain.model;

import java.io.Serializable;

public class RoomId implements Serializable{
	private String id;
	
	public RoomId() {
	}
	
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
