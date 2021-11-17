package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class RoomAssignment {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private int roomNumber;
	private StayId stayId;
	
	private RoomAssignment() {
		
	}
	
	public static RoomAssignment create(Room roomNumber, Stay stay) {
		RoomAssignment roomAssignment = new RoomAssignment();
		roomAssignment.assignedFrom = stay.getCheckInDate();
		roomAssignment.assignedTo = stay.getCheckOutDate();
		roomAssignment.roomNumber = roomNumber.getRoomNumber();
		roomAssignment.stayId = stay.getStayId();
		
		return roomAssignment;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public StayId getStayId() {
		return stayId;
	}
	
}
