package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public List<RoomDTO> getAllRoomDTOs() {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> room = roomRepository.getAllRooms();
		
		for (Room ro : room) {
			
			//roomDTOs.add(RoomDTO.createFromRoom(ro, roomCategory.get()));
		}
		
		
		return roomDTOs;
	}

	@Override
	public List<RoomDTO> getAllRoomsOfOneCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
