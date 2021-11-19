package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
public class HibernateRoomCategoryRepository implements RoomCategoryRepository{

	List<RoomCategory> roomCategories = new ArrayList<RoomCategory>();
	
	@Override
	public List<RoomCategory> getAllRoomCategories() {
		return roomCategories;
	}

	@Override
	public Optional<RoomCategory> getRoomCategoryById(RoomCategoryId id) {
		Optional<RoomCategory> category = Optional.empty();
		for (RoomCategory cat : roomCategories) {
			if(cat.getCategoryID().getId().equals(id.getId())) {
				category = Optional.of(cat);
			}
		}
		return category;
	}

	@Override
	public void addRoomCategory(RoomCategory category) {
		roomCategories.add(category);
	}

	@Override
	public RoomCategoryId nextIdentity() {
		return new RoomCategoryId(UUID.randomUUID().toString().toUpperCase());
	}

}
