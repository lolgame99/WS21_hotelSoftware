package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentStatus;

public class BookingAssignment {
	private long id;
	private BookingAssignmentId bookingAssignmentId;
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomId roomNumber;
	private BookingId bookingId;
	
	private BookingAssignment() {
	}
	
	public static BookingAssignment create(BookingAssignmentId bookingAssignmentId, RoomId roomNumber, Booking booking) {
		BookingAssignment roomAssignment = new BookingAssignment();
		roomAssignment.bookingAssignmentId = bookingAssignmentId;
		roomAssignment.assignedFrom = booking.getCheckInDate();
		roomAssignment.assignedTo = booking.getCheckOutDate();
		roomAssignment.roomNumber = roomNumber;
		roomAssignment.bookingId = booking.getBookingId();
		
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

	public BookingId getBookingId() {
		return bookingId;
	}

	public long getId() {
		return id;
	}

	public BookingAssignmentId getBookingAssignmentId() {
		return bookingAssignmentId;
	}
	
}
