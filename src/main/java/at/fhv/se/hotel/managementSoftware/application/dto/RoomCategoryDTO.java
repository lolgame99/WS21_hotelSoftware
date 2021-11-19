package at.fhv.se.hotel.managementSoftware.application.dto;

import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class RoomCategoryDTO {
	private RoomCategoryId categoryID;
	private String name;
	private String description;
	private int bedNumber;
	
	private RoomCategoryDTO() {
	}
	
	public static RoomCategoryDTO createFromCategory(RoomCategory category) {
		RoomCategoryDTO dto = new RoomCategoryDTO();
		dto.categoryID = category.getCategoryID();
		dto.name = category.getCategoryName();
		dto.bedNumber = category.getBedNumber();
		dto.description = category.getCategoryDescription();
		return dto;
	}

	public RoomCategoryId getCategoryID() {
		return categoryID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getBedNumber() {
		return bedNumber;
	}
	
}
