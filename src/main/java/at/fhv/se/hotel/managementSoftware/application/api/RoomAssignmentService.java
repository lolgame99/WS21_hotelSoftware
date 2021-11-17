package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;

public interface RoomAssignmentService {
	public List<RoomAssignmentDTO> getAllRoomAssignmentDTOs();
	public List<RoomAssignmentDTO> getAllFreeRoomsBetweenDates(LocalDate date1, LocalDate date2);
}
