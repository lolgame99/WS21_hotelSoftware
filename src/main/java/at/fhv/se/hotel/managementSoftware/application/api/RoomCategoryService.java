package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;

public interface RoomCategoryService {
	public List<RoomCategoryDTO> getAllRoomCategoriesDTO();
	public List<RoomCategoryDTO> getRoomCategoryById(String id);
}
