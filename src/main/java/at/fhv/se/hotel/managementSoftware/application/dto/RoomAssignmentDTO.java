package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;

public class RoomAssignmentDTO {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomDTO room;
	
	private RoomAssignmentDTO() {
		
	}
	
	public static RoomAssignmentDTO createFromRoomAssignment(RoomAssignment roomAssignment, RoomDTO room) {
		RoomAssignmentDTO dto = new RoomAssignmentDTO();
		dto.assignedFrom = roomAssignment.getAssignedFrom();
		dto.assignedTo = roomAssignment.getAssignedTo();
		dto.room = room;
		return dto;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
	}

	public RoomDTO getRoom() {
		return room;
	}

	
	
	

}
