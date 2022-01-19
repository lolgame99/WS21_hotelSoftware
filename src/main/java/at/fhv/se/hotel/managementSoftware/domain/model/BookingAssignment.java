package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentStatus;

public class BookingAssignment {
	private long id;
	private BookingAssignmentId bookingAssignmentId;
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomCategory category;
	private int categoryCount;
	private BookingId bookingId;
	
	private BookingAssignment() {
	}
	
	public static BookingAssignment create(BookingAssignmentId bookingAssignmentId, RoomCategory category, int categoryCount, Booking booking) {
		BookingAssignment roomAssignment = new BookingAssignment();
		roomAssignment.bookingAssignmentId = bookingAssignmentId;
		roomAssignment.assignedFrom = booking.getCheckInDate();
		roomAssignment.assignedTo = booking.getCheckOutDate();
		roomAssignment.category = category;
		roomAssignment.categoryCount = categoryCount;
		roomAssignment.bookingId = booking.getBookingId();
		
		return roomAssignment;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
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

	public RoomCategory getCategory() {
		return category;
	}

	public int getCategoryCount() {
		return categoryCount;
	}
	
	
}
