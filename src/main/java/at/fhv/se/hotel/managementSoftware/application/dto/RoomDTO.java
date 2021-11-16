package at.fhv.se.hotel.managementSoftware.application.dto;

import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;


public class RoomDTO {
	private int roomNumber;
	private RoomStatus roomStatus;
	private RoomCategoryDTO roomCategory;
	private RoomAssignmentDTO roomAssignment;
	
	private RoomDTO() {
		
	}
	
	public static RoomDTO createFromRoom(Room room, RoomCategoryDTO roomCategory, RoomAssignmentDTO roomAssignment) {
		RoomDTO dto = new RoomDTO();
		dto.roomNumber = room.getRoomNumber();
		dto.roomStatus = room.getRoomStatus();
		dto.roomCategory = roomCategory;
		dto.roomAssignment = roomAssignment;
		
		return dto;
		
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomCategoryDTO getRoomCategory() {
		return roomCategory;
	}

	public RoomAssignmentDTO getRoomAssignment() {
		return roomAssignment;
	}

	
	
	
	
	

}
