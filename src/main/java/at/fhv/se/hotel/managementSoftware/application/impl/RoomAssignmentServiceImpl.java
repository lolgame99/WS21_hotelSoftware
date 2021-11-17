package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;

public class RoomAssignmentServiceImpl implements RoomAssignmentService {

	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Override
	public List<RoomAssignmentDTO> getAllRoomAssignmentDTOs() {
		List<RoomAssignmentDTO> roomAssignmentDTOs = new ArrayList<RoomAssignmentDTO>();
		List<RoomAssignment> roomAssignment = roomAssignmentRepository.getAllRoomAssignments();
		
		for (RoomAssignment ra : roomAssignment) {
			
			//roomAssignmentDTOs.add(RoomAssignmentDTO.createFromRoomAssignment());
		}
		
		
		return roomAssignmentDTOs;
	}

	@Override
	public List<RoomAssignmentDTO> getAllFreeRoomsBetweenDates(LocalDate date1, LocalDate date2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
