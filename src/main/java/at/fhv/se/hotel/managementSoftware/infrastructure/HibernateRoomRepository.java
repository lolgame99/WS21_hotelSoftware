package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

public class HibernateRoomRepository implements RoomRepository {

	List<Room> rooms = new ArrayList<Room>();
	
	@Override
	public List<Room> getAllRooms() {
		return rooms;
	}

	@Override
	public List<Room> getAllRoomsOfOneCategory() {
		//TODO:
		return null;
	}
	
}
