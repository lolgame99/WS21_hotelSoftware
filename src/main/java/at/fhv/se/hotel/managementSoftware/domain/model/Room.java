package at.fhv.se.hotel.managementSoftware.domain.model;


import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;

public class Room {
	private int roomNumber;
	private RoomStatus roomStatus;
	private RoomCategoryId categoryId;
	
	public Room() {
	}
	
	public static Room changeRoomStatus(int roomNumber, RoomStatus roomStatus, RoomCategoryId categoryId){
		Room room = new Room();
		room.roomNumber = roomNumber;
		room.roomStatus = roomStatus;
		room.categoryId = categoryId;
		
		return room;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomCategoryId getCategoryId() {
		return categoryId;
	}
	
}
