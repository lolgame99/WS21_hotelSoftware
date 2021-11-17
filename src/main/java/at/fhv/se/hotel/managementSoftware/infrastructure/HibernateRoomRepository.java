package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
public class HibernateRoomRepository implements RoomRepository {
	
	List<Room> rooms = new ArrayList<Room>();
	
	@Override
	public List<Room> getAllRooms() {
		return rooms;
	}

	@Override
	public List<Room> getAllRoomsByRoomCategory(RoomCategoryId id) {
		List<Room> categoryRooms = new ArrayList<Room>();
		
		for (Room room : getAllRooms()) {
			if (room.getCategoryId().getId().equals(id.getId())) {
				categoryRooms.add(room);
			}
		}
		return categoryRooms;
	}

	@Override
	public Optional<Room> getRoomByNumber(int number) {
		Optional<Room> room = Optional.empty();
		for (Room r : getAllRooms()) {
			if (r.getRoomNumber() == number) {
				room = Optional.of(r);
			}
		}
		return room;
	}

	@Override
	public void addRoom(Room room) {
		rooms.add(room);		
	}
	
}
