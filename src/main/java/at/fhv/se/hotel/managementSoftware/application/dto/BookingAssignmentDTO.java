package at.fhv.se.hotel.managementSoftware.application.dto;

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
