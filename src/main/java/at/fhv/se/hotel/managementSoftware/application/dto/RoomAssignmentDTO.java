package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;

public class RoomAssignmentDTO {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomDTO room;
	private PaymentStatus paymentStatus;
	private RoomAssignmentId roomAssignmentId;
	
	private RoomAssignmentDTO() {
		
	}
	
	public static RoomAssignmentDTO createFromRoomAssignment(RoomAssignment roomAssignment, RoomDTO room) {
		RoomAssignmentDTO dto = new RoomAssignmentDTO();
		dto.assignedFrom = roomAssignment.getAssignedFrom();
		dto.assignedTo = roomAssignment.getAssignedTo();
		dto.room = room;
		dto.paymentStatus = roomAssignment.getPaymentStatus();
		dto.roomAssignmentId = roomAssignment.getRoomAssignmentId();
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

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public RoomAssignmentId getRoomAssignmentId() {
		return roomAssignmentId;
	}

	
}
