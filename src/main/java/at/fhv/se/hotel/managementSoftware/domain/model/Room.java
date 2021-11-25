package at.fhv.se.hotel.managementSoftware.domain.model;


import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;

public class Room {
	private long id;
	private RoomId roomNumber;
	private RoomStatus roomStatus;
	private RoomCategoryId categoryId;
	
	private Room() {
	}
	
	public static Room create(RoomId number, RoomStatus status, RoomCategoryId catId) {
		Room room = new Room();
		room.roomNumber = number;
		room.roomStatus = status;
		room.categoryId = catId;
		return room;
	}
	
	public static Room changeRoomStatus(RoomId roomNumber, RoomStatus roomStatus, RoomCategoryId categoryId){
		Room room = new Room();
		room.roomNumber = roomNumber;
		room.roomStatus = roomStatus;
		room.categoryId = categoryId;
		
		return room;
	}

	public RoomId getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomCategoryId getCategoryId() {
		return categoryId;
	}
	
}
