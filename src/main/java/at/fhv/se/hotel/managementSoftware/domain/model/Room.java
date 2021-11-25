package at.fhv.se.hotel.managementSoftware.domain.model;


import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;

public class Room {
	private long id;
	private RoomId roomNumber;
	private RoomStatus roomStatus;
	private RoomCategory category;
	
	private Room() {
	}
	
	public static Room create(RoomId number, RoomStatus status, RoomCategory cat) {
		Room room = new Room();
		room.roomNumber = number;
		room.roomStatus = status;
		room.category = cat;
		return room;
	}
	
	public static Room changeRoomStatus(RoomId roomNumber, RoomStatus roomStatus, RoomCategory category){
		Room room = new Room();
		room.roomNumber = roomNumber;
		room.roomStatus = roomStatus;
		room.category = category;
		
		return room;
	}

	public RoomId getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomCategory getCategory() {
		return category;
	}
	
}
