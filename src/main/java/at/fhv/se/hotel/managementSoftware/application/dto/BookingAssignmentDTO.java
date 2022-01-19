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
	private String category;
	private int count;
	
	private BookingAssignmentDTO() {
		
	}
	
	public static BookingAssignmentDTO create(String categoryId, Integer categoryCount) {
		BookingAssignmentDTO dto = new BookingAssignmentDTO();
		dto.category = categoryId;
		dto.count = categoryCount;
		return dto;
	}

	public String getCategory() {
		return category;
	}

	public int getCategoryCount() {
		return count;
	}

	
}
