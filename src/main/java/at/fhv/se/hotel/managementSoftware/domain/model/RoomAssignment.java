package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class RoomAssignment {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private Room roomNumber;
	private StayId stayId;
	
	private RoomAssignment() {
		
	}
	
	private RoomAssignment createRoom(Room roomNumber, Stay stay) {
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

	public Room getRoomNumber() {
		return roomNumber;
	}

	public StayId getStayId() {
		return stayId;
	}
	
}
