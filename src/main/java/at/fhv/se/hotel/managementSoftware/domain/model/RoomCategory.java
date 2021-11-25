package at.fhv.se.hotel.managementSoftware.domain.model;

public class RoomCategory {
	private long id;
	private RoomCategoryId categoryId;
	private String categoryName;
	private String categoryDescription;
	private int bedNumber;
	
	private RoomCategory() {
	}
	
	public static RoomCategory createWithoutDescription(RoomCategoryId categoryID, String categoryName, int bedNumber) {
		RoomCategory category = new RoomCategory();
		category.categoryId = categoryID;
		category.categoryName = categoryName;
		category.bedNumber = bedNumber;
		return category;
	}
	
	public static RoomCategory createWithDescription(RoomCategoryId categoryID, String categoryName, int bedNumber, String desc) {
		RoomCategory category = new RoomCategory();
		category.categoryId = categoryID;
		category.categoryName = categoryName;
		category.bedNumber = bedNumber;
		category.categoryDescription = desc;
		return category;
	}

	public RoomCategoryId getCategoryID() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public int getBedNumber() {
		return bedNumber;
	}

}
