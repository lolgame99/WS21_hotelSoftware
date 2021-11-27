package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@SpringBootTest
@Transactional
public class RoomCategoryRepositoryTest {
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Test
	void when_given_roomCategory_without_description_is_added() {
		//given
		RoomCategory categoryExpected = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Testcategory", 1);
		
		//when
		roomCategoryRepository.addRoomCategory(categoryExpected);
		Optional<RoomCategory> categoryActual = roomCategoryRepository.getRoomCategoryById(categoryExpected.getCategoryId());
		
		//then
		assertEquals(categoryExpected.getCategoryId().getId(), categoryActual.get().getCategoryId().getId());
		assertEquals(categoryExpected.getCategoryName(), categoryActual.get().getCategoryName());
		assertEquals(categoryExpected.getCategoryDescription(), categoryActual.get().getCategoryDescription());
		assertEquals(categoryExpected.getBedNumber(), categoryActual.get().getBedNumber());
	}
	
	@Test
	void when_given_roomCategory_with_description_is_added() {
		//given
		RoomCategory categoryExpected = RoomCategory.createWithDescription(new RoomCategoryId("1"), "Testcategory", 1, "Testbeschreibung");
		
		//when
		roomCategoryRepository.addRoomCategory(categoryExpected);
		Optional<RoomCategory> categoryActual = roomCategoryRepository.getRoomCategoryById(categoryExpected.getCategoryId());
		
		//then
		assertEquals(categoryExpected.getCategoryId().getId(), categoryActual.get().getCategoryId().getId());
		assertEquals(categoryExpected.getCategoryName(), categoryActual.get().getCategoryName());
		assertEquals(categoryExpected.getCategoryDescription(), categoryActual.get().getCategoryDescription());
		assertEquals(categoryExpected.getBedNumber(), categoryActual.get().getBedNumber());
	}
	
	@Test
	void when_all_test_roomCategories_loaded() {
		//given
		String[] expectedRoomCategoryNames = {"Single Room","Double Room","Family Suite"};
		
		//when
		List<RoomCategory> categories = roomCategoryRepository.getAllRoomCategories();
		String[] actualRoomCategoryNames = new String[3];
		for (int i = 0; i < categories.size(); i++) {
			actualRoomCategoryNames[i] = categories.get(i).getCategoryName();
		}
		
		//then
		assertArrayEquals(expectedRoomCategoryNames, actualRoomCategoryNames);
	}
	
	@Test
	void when_given_invalid_roomCategoryId_return_empty() {
		//given
		RoomCategoryId invalidRoomCategoryId = new RoomCategoryId("007");
		
		//when
		Optional<RoomCategory> actualRoomCategory = roomCategoryRepository.getRoomCategoryById(invalidRoomCategoryId);
		
		//then
		assertTrue(actualRoomCategory.isEmpty());
	}
	
	
}