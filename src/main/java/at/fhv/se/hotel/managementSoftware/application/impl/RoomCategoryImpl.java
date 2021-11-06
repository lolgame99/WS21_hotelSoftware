package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
public class RoomCategoryImpl implements RoomCategoryService{

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Override
	public List<RoomCategoryDTO> getAllRoomCategoriesDTO() {
		List<RoomCategory> roomCategories = roomCategoryRepository.getAllRoomCategories();
		List<RoomCategoryDTO> roomCategoryDTOs = new ArrayList<RoomCategoryDTO>();
		
		for (RoomCategory cat : roomCategories) {
			roomCategoryDTOs.add(RoomCategoryDTO.builder()
					.withId(cat.getCategoryID())
					.withName(cat.getCategoryName())
					.withDescription(cat.getCategoryDescription())
					.withBedNumber(cat.getBedNumber())
					.build());
		}
		return roomCategoryDTOs;
	}

	@Override
	public Optional<RoomCategoryDTO> getRoomCategoryById(String id) {
		Optional<RoomCategory> roomCategory = roomCategoryRepository.getRoomCategoryById(id);
		Optional<RoomCategoryDTO> roomCategoryDTO = Optional.empty();
		
		if (roomCategory.isPresent()) {
			roomCategoryDTO = Optional.of(RoomCategoryDTO.builder()
					.withId(roomCategory.get().getCategoryID())
					.withName(roomCategory.get().getCategoryName())
					.withDescription(roomCategory.get().getCategoryDescription())
					.withBedNumber(roomCategory.get().getBedNumber())
					.build());
		}
		return roomCategoryDTO;
	}

}
