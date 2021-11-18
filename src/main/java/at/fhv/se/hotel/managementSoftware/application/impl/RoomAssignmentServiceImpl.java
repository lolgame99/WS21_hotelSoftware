package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Autowired
	private StayService stayService;
	
	@Autowired
	private RoomService roomService;
	
	@Override
	public List<RoomAssignmentDTO> getAllRoomAssignmentDTOs() {
		List<RoomAssignmentDTO> roomAssignmentDTOs = new ArrayList<RoomAssignmentDTO>();
		List<RoomAssignment> roomAssignment = roomAssignmentRepository.getAllRoomAssignments();
		
		for (RoomAssignment ra : roomAssignment) {		
			roomAssignmentDTOs.add(RoomAssignmentDTO.createFromRoomAssignment(ra, stayService.getStayById(ra.getStayId().getId()).get(), roomService.getRoomByRoomNumber(ra.getRoomNumber().getId()).get()));
		}
		
		return roomAssignmentDTOs;
	}

	@Override
	public List<RoomAssignmentDTO> getRoomAssignmentsByStayId(StayId id) {
		List<RoomAssignmentDTO> roomAssignmentDTOs = new ArrayList<RoomAssignmentDTO>();
		List<RoomAssignment> roomAssignment = roomAssignmentRepository.getRoomAssignmentsByStayId(new StayId(id));
		
		for (RoomAssignment ra : roomAssignment) {		
			roomAssignmentDTOs.add(RoomAssignmentDTO.createFromRoomAssignment(ra, stayService.getStayById(ra.getStayId().getId()).get(), roomService.getRoomByRoomNumber(ra.getRoomNumber().getId()).get()));
		}
		
		return roomAssignmentDTOs;
	}
	
}
