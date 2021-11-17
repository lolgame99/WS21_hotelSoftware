package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;

public interface RoomRepository {
	public List<Room> getAllRooms();
	public List<Room> getAllRoomsOfOneCategory();
}
