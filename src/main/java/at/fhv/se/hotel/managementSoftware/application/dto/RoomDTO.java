package at.fhv.se.hotel.managementSoftware.application.dto;

import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;


public class RoomDTO {
	private RoomId roomNumber;
	private RoomStatus roomStatus;
	private RoomCategoryDTO roomCategory;
	
	private RoomDTO() {	
	}
	
	public static RoomDTO createFromRoom(Room room, RoomCategoryDTO roomCategory) {
		RoomDTO dto = new RoomDTO();
		dto.roomNumber = room.getRoomNumber();
		dto.roomStatus = room.getRoomStatus();
		dto.roomCategory = roomCategory;
		
		return dto;
		
	}

	public RoomId getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomCategoryDTO getRoomCategory() {
		return roomCategory;
	}		

}
