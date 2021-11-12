package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public interface RoomCategoryService {
	public List<RoomCategoryDTO> getAllRoomCategoriesDTO();
	public Optional<RoomCategoryDTO> getRoomCategoryById(String id);
}
