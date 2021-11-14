package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class RoomAssigment {
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private Room roomNumber;
	private StayId stayId;
	
	private RoomAssigment() {
		
	}
	
	private RoomAssigment checkRoomAvailability(Room roomNumber, Stay stay) {
		RoomAssigment roomAssigment = new RoomAssigment();
		roomAssigment.assignedFrom = stay.getCheckInDate();
		roomAssigment.assignedTo = stay.getCheckOutDate();
		roomAssigment.roomNumber = roomNumber;
		roomAssigment.stayId = stay.getStayId();
		
		return roomAssigment;
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
