package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;

public interface RoomService {
	public List<RoomDTO> getAllRoomDTOs();
	public List<RoomDTO> getAllRoomsByRoomCategory(String id);
	public Optional<RoomDTO> getRoomByRoomNumber(String number);
	public List<RoomDTO> getAllFreeRoomsBetween(String date1, String date2);
	public List<RoomDTO> getFreeRoomsBetweenByRoomCategoryId(String id,String date1, String date2);
	public void changeRoomStatus(String roomId, String status) throws InvalidRoomException;
}