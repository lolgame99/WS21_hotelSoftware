package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;

public class BookingAssignmentDTO {
	private BookingAssignmentId bookingAssignmentId;
	private LocalDate assignedFrom;
	private LocalDate assignedTo;
	private RoomCategory category;
	private int categoryCount;
	
	private BookingAssignmentDTO() {
		
	}
	
	public static BookingAssignmentDTO createFromBookingAssignment(BookingAssignment ba) {
		BookingAssignmentDTO dto = new BookingAssignmentDTO();
		dto.assignedFrom = ba.getAssignedFrom();
		dto.assignedTo = ba.getAssignedTo();
		dto.category = ba.getCategory();
		dto.categoryCount = ba.getCategoryCount();
		return dto;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
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
