package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;

public interface RoomService {
	public List<RoomDTO> getAllRoomDTOs();
	public List<RoomDTO> getAllRoomsOfOneCategory();
}