package at.fhv.se.hotel.managementSoftware.domain.model;

public class RoomCategory {
	private RoomCategoryId categoryID;
	private String categoryName;
	private String categoryDescription;
	private int bedNumber;
	
	
	public RoomCategory(RoomCategoryId categoryID, String categoryName, int bedNumber) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.bedNumber = bedNumber;
		
	}
	
	//wenn nicht optional, dann in Constructor
	public void addCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public RoomCategoryId getCategoryID() {
		return categoryID;
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
