package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class RoomAssignment {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomId roomNumber;
	private StayId stayId;
	
	private RoomAssignment() {
		
	}
	
	public static RoomAssignment create(RoomId roomNumber, Stay stay) {
		RoomAssignment roomAssignment = new RoomAssignment();
		roomAssignment.assignedFrom = stay.getCheckInDate();
		roomAssignment.assignedTo = stay.getCheckOutDate();
		roomAssignment.roomNumber = roomNumber;
		roomAssignment.stayId = stay.getStayId();
		
		return roomAssignment;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
	}

	public RoomId getRoomNumber() {
		return roomNumber;
	}

	public StayId getStayId() {
		return stayId;
	}
	
}
