package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.PriceService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@ActiveProfiles("test")
@SpringBootTest
public class RoomCategoryServiceTest {

	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@MockBean
	private RoomCategoryRepository roomCategoryRepository;
	
	@MockBean
	private PriceService priceService;
	
	//With Description
	@Test
	void when_getAll_roomCategories_withoutDesc_returns_all() {
		
		//given
		List<RoomCategory> allCategories = new ArrayList<RoomCategory>();
		
		allCategories.add(RoomCategory.createWithoutDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4));
		allCategories.add(RoomCategory.createWithoutDescription(new RoomCategoryId("BB"), "Fünfbettzimmer", 5));
		allCategories.add(RoomCategory.createWithoutDescription(new RoomCategoryId("CC"), "Fussballmannschaftzimmer", 11));
		Optional<PriceDetailsDTO> price = Optional.of(PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3))));
		
		Mockito.when(priceService.getCurrentPriceByCategoryId(any(String.class))).thenReturn(price);
		Mockito.when(roomCategoryRepository.getAllRoomCategories()).thenReturn(allCategories);
		
		//when
		List<RoomCategoryDTO> dtos = roomCategoryService.getAllRoomCategoriesDTO();
		
		//then
		assertEquals(3, dtos.size());
		
		assertEquals(allCategories.get(0).getBedNumber(), dtos.get(0).getBedNumber());
		assertEquals(allCategories.get(0).getCategoryDescription(), dtos.get(0).getDescription());
		assertEquals(allCategories.get(0).getCategoryName(), dtos.get(0).getName());
		assertEquals(allCategories.get(0).getCategoryId(), dtos.get(0).getCategoryId());
		
		assertEquals(allCategories.get(1).getBedNumber(), dtos.get(1).getBedNumber());
		assertEquals(allCategories.get(1).getCategoryDescription(), dtos.get(1).getDescription());
		assertEquals(allCategories.get(1).getCategoryName(), dtos.get(1).getName());
		assertEquals(allCategories.get(1).getCategoryId(), dtos.get(1).getCategoryId());
		
		assertEquals(allCategories.get(2).getBedNumber(), dtos.get(2).getBedNumber());
		assertEquals(allCategories.get(2).getCategoryDescription(), dtos.get(2).getDescription());
		assertEquals(allCategories.get(2).getCategoryName(), dtos.get(2).getName());
		assertEquals(allCategories.get(2).getCategoryId(), dtos.get(2).getCategoryId());
		
		assertEquals(price.get().getCost(), dtos.get(0).getCurrentPrice().getCost());
		assertEquals(price.get().getCost(), dtos.get(1).getCurrentPrice().getCost());
		assertEquals(price.get().getCost(), dtos.get(2).getCurrentPrice().getCost());
	}
	
	@Test
	void when_given_roomCategoryId_withoutDesc_returns_roomCategoryId_roomCategories() {
		
		//given
		Optional<RoomCategory> expectedRoomCategory = Optional.of(RoomCategory.createWithoutDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4));
		Optional<PriceDetailsDTO> price = Optional.of(PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3))));
				
		Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(expectedRoomCategory);
		Mockito.when(priceService.getCurrentPriceByCategoryId(any(String.class))).thenReturn(price);
				
		//when
		Optional<RoomCategoryDTO> dto = roomCategoryService.getRoomCategoryById(expectedRoomCategory.get().getCategoryId().getId());
				
		//then
		assertEquals(expectedRoomCategory.get().getBedNumber(), dto.get().getBedNumber());
		assertEquals(expectedRoomCategory.get().getCategoryDescription(), dto.get().getDescription());
		assertEquals(expectedRoomCategory.get().getCategoryName(), dto.get().getName());
		assertEquals(expectedRoomCategory.get().getCategoryId(), dto.get().getCategoryId());
		assertEquals(price.get().getCost(), dto.get().getCurrentPrice().getCost());
	}
	
	
	//Without Description
	@Test
	void when_getAll_roomCategories_withDesc_returns_all() {
		
		//given
				List<RoomCategory> allRoomCategories = new ArrayList<RoomCategory>();
				
				allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4, "schoen"));
				allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("BB"), "Fünfbettzimmer", 5, "sehr schoen"));
				allRoomCategories.add(RoomCategory.createWithDescription(new RoomCategoryId("CC"), "Fussballmannschaftzimmer", 11, "bisschen eng"));
				Optional<PriceDetailsDTO> price = Optional.of(PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3))));
				
				Mockito.when(priceService.getCurrentPriceByCategoryId(any(String.class))).thenReturn(price);
				Mockito.when(roomCategoryRepository.getAllRoomCategories()).thenReturn(allRoomCategories);
				
				//when
				List<RoomCategoryDTO> dtos = roomCategoryService.getAllRoomCategoriesDTO();
				
				//then
				assertEquals(3, dtos.size());
				
				assertEquals(allRoomCategories.get(0).getBedNumber(), dtos.get(0).getBedNumber());
				assertEquals(allRoomCategories.get(0).getCategoryDescription(), dtos.get(0).getDescription());
				assertEquals(allRoomCategories.get(0).getCategoryName(), dtos.get(0).getName());
				assertEquals(allRoomCategories.get(0).getCategoryId(), dtos.get(0).getCategoryId());
				
				assertEquals(allRoomCategories.get(1).getBedNumber(), dtos.get(1).getBedNumber());
				assertEquals(allRoomCategories.get(1).getCategoryDescription(), dtos.get(1).getDescription());
				assertEquals(allRoomCategories.get(1).getCategoryName(), dtos.get(1).getName());
				assertEquals(allRoomCategories.get(1).getCategoryId(), dtos.get(1).getCategoryId());
				
				assertEquals(allRoomCategories.get(2).getBedNumber(), dtos.get(2).getBedNumber());
				assertEquals(allRoomCategories.get(2).getCategoryDescription(), dtos.get(2).getDescription());
				assertEquals(allRoomCategories.get(2).getCategoryName(), dtos.get(2).getName());
				assertEquals(allRoomCategories.get(2).getCategoryId(), dtos.get(2).getCategoryId());
				
				assertEquals(price.get().getCost(), dtos.get(0).getCurrentPrice().getCost());
				assertEquals(price.get().getCost(), dtos.get(1).getCurrentPrice().getCost());
				assertEquals(price.get().getCost(), dtos.get(2).getCurrentPrice().getCost());
			}
	
	@Test
	void when_given_roomCategoryId_withDesc_returns_roomCategoryId_roomCategories() {
		
		//given
		Optional<RoomCategory> expectedRoomCategory = Optional.of(RoomCategory.createWithDescription(new RoomCategoryId("AA"), "Vierbettzimmer", 4, "Zimmer mit 4 Betten"));
		Optional<PriceDetailsDTO> price = Optional.of(PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3))));
		
		Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(expectedRoomCategory);
		Mockito.when(priceService.getCurrentPriceByCategoryId(any(String.class))).thenReturn(price);
		
		//when
		Optional<RoomCategoryDTO> dto = roomCategoryService.getRoomCategoryById(expectedRoomCategory.get().getCategoryId().getId());
		
		//then
		assertEquals(expectedRoomCategory.get().getBedNumber(), dto.get().getBedNumber());
		assertEquals(expectedRoomCategory.get().getCategoryDescription(), dto.get().getDescription());
		assertEquals(expectedRoomCategory.get().getCategoryName(), dto.get().getName());
		assertEquals(expectedRoomCategory.get().getCategoryId(), dto.get().getCategoryId());
		assertEquals(price.get().getCost(), dto.get().getCurrentPrice().getCost());
	}
}
