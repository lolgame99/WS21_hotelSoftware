package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@SpringBootTest
public class RoomCategoryServiceTest {

	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@MockBean
	private RoomCategoryRepository roomCategoryRepository;
	
	@Test
	void when_getAll_roomCategories_returns_all() {
		
		//given
		List<RoomCategory> allCategories = new ArrayList<RoomCategory>();
		
		allCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4, "schön"));
		allCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("BB"), "Fünfbettzimmer", 5, "sehr schön"));
		allCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("CC"), "Fussballmannschaftzimmer", 11, "bisschen fraglich"));
		
		Mockito.when(roomCategoryRepository.getAllRoomCategories()).thenReturn(allCategories);
		
		//when
		List<RoomCategoryDTO> dtos = roomCategoryService.getAllRoomCategoriesDTO();
		
		//then
		assertEquals(3, dtos.size());
		assertEquals(allCategories.get(0).getBedNumber(), dtos.get(0).getBedNumber());
		assertEquals(allCategories.get(0).getCategoryDescription(), dtos.get(0).getDescription());
		assertEquals(allCategories.get(0).getCategoryName(), dtos.get(0).getName());
		assertEquals(allCategories.get(0).getCategoryId(), dtos.get(0).getCategoryId());
		//allcategories -> id übrig
		//dtos -> price übrig
	}
	
	@Test
	void when_given_roomCategoryId_returns_roomCategoryId_roomCategories() {
		
		//given
		List<RoomCategory> allRoomCategories = new ArrayList<RoomCategory>();
		
		allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4, "schön"));
		allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("BB"), "Fünfbettzimmer", 5, "sehr schön"));
		allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("CC"), "Fussballmannschaftzimmer", 11, "bisschen fraglich"));
		
		Mockito.doReturn(allRoomCategories).when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class)));
		
		//when
		List<RoomCategoryDTO> dtos = roomCategoryService.getRoomCategoryById(allRoomCategories.get(0).getCategoryId().getId());
		
		//then
		assertEquals(3, dtos.size());
		assertEquals(allRoomCategories.get(0).getBedNumber(), dtos.get(0).getBedNumber());
		assertEquals(allRoomCategories.get(0).getCategoryDescription(), dtos.get(0).getDescription());
		assertEquals(allRoomCategories.get(0).getCategoryName(), dtos.get(0).getName());
		assertEquals(allRoomCategories.get(0).getCategoryId(), dtos.get(0).getCategoryId());
		//allcategories -> id übrig
		//dtos -> price übrig
	}
}
