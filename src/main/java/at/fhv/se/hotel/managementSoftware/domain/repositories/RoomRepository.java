package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;

public interface RoomRepository {
	public List<Room> getAllRooms();
	public List<Room> getAllRoomsByRoomCategory(RoomCategory cat);
	public Optional<Room> getRoomByNumber(RoomId number);
	public void addRoom(Room room);
}
