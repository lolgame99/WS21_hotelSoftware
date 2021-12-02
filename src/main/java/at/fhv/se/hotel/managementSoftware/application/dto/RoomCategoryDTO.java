package at.fhv.se.hotel.managementSoftware.application.dto;

import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class RoomCategoryDTO {
	private RoomCategoryId categoryId;
	private String name;
	private String description;
	private int bedNumber;
	private PriceDetailsDTO currentPrice;
	
	private RoomCategoryDTO() {
	}
	
	public static RoomCategoryDTO createFromCategory(RoomCategory category, PriceDetailsDTO price) {
		RoomCategoryDTO dto = new RoomCategoryDTO();
		dto.categoryId = category.getCategoryId();
		dto.name = category.getCategoryName();
		dto.bedNumber = category.getBedNumber();
		dto.description = category.getCategoryDescription();
		dto.currentPrice = price;
		return dto;
	}

	public RoomCategoryId getCategoryId() {
		return categoryId;
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

	public PriceDetailsDTO getCurrentPrice() {
		return currentPrice;
	}
	
	
}
