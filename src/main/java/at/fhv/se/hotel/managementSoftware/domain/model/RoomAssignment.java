package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentStatus;

public class RoomAssignment {
	private long id;
	private RoomAssignmentId roomAssignmentId;
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomId roomNumber;
	private StayId stayId;
	private PaymentStatus paymentStatus;
	
	private RoomAssignment() {
	}
	
	public static RoomAssignment create(RoomAssignmentId roomAssignmentId, RoomId roomNumber, Stay stay) {
		RoomAssignment roomAssignment = new RoomAssignment();
		roomAssignment.roomAssignmentId = roomAssignmentId;
		roomAssignment.assignedFrom = stay.getCheckInDate();
		roomAssignment.assignedTo = stay.getCheckOutDate();
		roomAssignment.roomNumber = roomNumber;
		roomAssignment.stayId = stay.getStayId();
		roomAssignment.paymentStatus = PaymentStatus.UNPAID;
		
		return roomAssignment;
	}
	
	public void paid() {
		this.paymentStatus = PaymentStatus.PAID;
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

	public long getId() {
		return id;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public RoomAssignmentId getRoomAssignmentId() {
		return roomAssignmentId;
	}
	
}
