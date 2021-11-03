package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
public class HibernateRoomCategoryRepository implements RoomCategoryRepository{

	List<RoomCategory> roomCategories = new ArrayList<RoomCategory>();
	
	@Override
	public List<RoomCategory> getAllRoomCategories() {
		return roomCategories;
	}

	@Override
	public Optional<RoomCategory> getRoomCategoryById(String id) {
		Optional<RoomCategory> category = Optional.empty();
		for (RoomCategory cat : roomCategories) {
			if(cat.getCategoryID().equals(id)) {
				category = Optional.of(cat);
			}
		}
		return category;
	}

	@Override
	public void addRoomCategory(RoomCategory category) {
		roomCategories.add(category);
	}

}
