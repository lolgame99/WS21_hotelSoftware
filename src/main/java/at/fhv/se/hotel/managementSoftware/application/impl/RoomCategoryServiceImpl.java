package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.PriceService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
public class RoomCategoryServiceImpl implements RoomCategoryService{

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Autowired
	private PriceService priceService;
	
	@Override
	public List<RoomCategoryDTO> getAllRoomCategoriesDTO() {
		List<RoomCategory> roomCategories = roomCategoryRepository.getAllRoomCategories();
		List<RoomCategoryDTO> roomCategoryDTOs = new ArrayList<RoomCategoryDTO>();
		
		for (RoomCategory cat : roomCategories) {
			PriceDetailsDTO price = priceService.getCurrentPriceByCategoryId(cat.getCategoryId().getId()).get();
			roomCategoryDTOs.add(RoomCategoryDTO.createFromCategory(cat, price));
		}
		return roomCategoryDTOs;
	}

	@Override
	public Optional<RoomCategoryDTO> getRoomCategoryById(String id) {
		Optional<RoomCategory> roomCategory = roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(id));
		Optional<RoomCategoryDTO> roomCategoryDTO = Optional.empty();
		
		if (roomCategory.isPresent()) {
			PriceDetailsDTO price = priceService.getCurrentPriceByCategoryId(roomCategory.get().getCategoryId().getId()).get();
			roomCategoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(roomCategory.get(), price));
		}
		return roomCategoryDTO;
	}

}
