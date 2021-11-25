package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class RoomCategoryTest {
	
	@Test
	void when_create_RoomCategory_withOutDescription() {
		//given
		RoomCategoryId categoryId = new RoomCategoryId("1");
		String categoryName = "Family Suite";
		int bedNumber = 2;
		
		
		//when
		RoomCategory roomCategory = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
		
		//then
		assertEquals(categoryId, roomCategory.getCategoryId());
		assertEquals(categoryName, roomCategory.getCategoryName());
		assertEquals(bedNumber, roomCategory.getBedNumber());
	}
	
	@Test
	void when_create_RoomCategory_withDescription() {
		//given
		RoomCategoryId categoryId = new RoomCategoryId("1");
		String categoryName = "Family Suite";
		String categoryDescription = "Test";
		int bedNumber = 2;
		
		//when
		RoomCategory roomCategory = RoomCategory.createWithDescription(categoryId, categoryName, bedNumber, categoryDescription);
		
		//then
		assertEquals(categoryId, roomCategory.getCategoryId());
		assertEquals(categoryName, roomCategory.getCategoryName());
		assertEquals(bedNumber, roomCategory.getBedNumber());
		assertEquals(categoryDescription, roomCategory.getCategoryDescription());
	}

}
