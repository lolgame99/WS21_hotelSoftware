package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public interface RoomCategoryRepository {
	public List<RoomCategory> getAllRoomCategories();
	public Optional<RoomCategory> getRoomCategoryById(RoomCategoryId id);
	public void addRoomCategory(RoomCategory category);
	public RoomCategoryId nextIdentity();
}
