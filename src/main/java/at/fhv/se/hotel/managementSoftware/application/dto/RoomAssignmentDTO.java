package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;

public class RoomAssignmentDTO {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private StayDetailsDTO stay;
	private RoomDTO room;
	
	private RoomAssignmentDTO() {
		
	}
	
	public static RoomAssignmentDTO createFromRoomAssignment(RoomAssignment roomAssignment, StayDetailsDTO stay, RoomDTO room) {
		RoomAssignmentDTO dto = new RoomAssignmentDTO();
		dto.assignedFrom = roomAssignment.getAssignedFrom();
		dto.assignedTo = roomAssignment.getAssignedTo();
		dto.stay = stay;
		dto.room = room;
		return dto;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
	}

	public StayDetailsDTO getStay() {
		return stay;
	}

	public RoomDTO getRoom() {
		return room;
	}

	
	
	

}
