package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public interface RoomRepository {
	public List<Room> getAllRooms();
	public List<Room> getAllRoomsByRoomCategory(RoomCategoryId id);
	public Optional<Room> getRoomByNumber(int number);
}