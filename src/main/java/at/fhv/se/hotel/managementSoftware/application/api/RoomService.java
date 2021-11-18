package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;

public interface RoomService {
	public List<RoomDTO> getAllRoomDTOs();
	public List<RoomDTO> getAllRoomsByRoomCategory(String id);
	public Optional<RoomDTO> getRoomByRoomNumber(String number);
	public List<RoomDTO> getAllFreeRoomsBetween(LocalDate date1, LocalDate date2);
	public List<RoomDTO> getFreeRoomsBetweenByRoomCategoryId(String id,LocalDate date1, LocalDate date2);
}