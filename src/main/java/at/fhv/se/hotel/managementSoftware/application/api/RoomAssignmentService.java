package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public interface RoomAssignmentService {
	public List<RoomAssignmentDTO> getAllRoomAssignmentDTOs();
	public List<RoomAssignmentDTO> getRoomAssignmentsByStayId(String id);
}
