package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@Override
	public List<RoomDTO> getAllRoomDTOs() {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomRepository.getAllRooms();
		
		for (Room ro : rooms) {		
			roomDTOs.add(RoomDTO.createFromRoom(ro,roomCategoryService.getRoomCategoryById(ro.getCategory().getCategoryId().getId()).get()));
		}
			
		return roomDTOs;
	}

	@Override
	public List<RoomDTO> getAllRoomsByRoomCategory(String id) {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomRepository.getAllRoomsByRoomCategory(new RoomCategoryId(id));
		
		for (Room ro : rooms) {		
			roomDTOs.add(RoomDTO.createFromRoom(ro,roomCategoryService.getRoomCategoryById(ro.getCategory().getCategoryId().getId()).get()));
		}
			
		return roomDTOs;
	}

	@Override
	public Optional<RoomDTO> getRoomByRoomNumber(String number) {
		Optional<RoomDTO> dto = Optional.empty();
		Room room = roomRepository.getRoomByNumber(new RoomId(number)).get();
		dto = Optional.of(RoomDTO.createFromRoom(room,roomCategoryService.getRoomCategoryById(room.getCategory().getCategoryId().getId()).get()));
		return dto;
	}

	@Override
	public List<RoomDTO> getAllFreeRoomsBetween(LocalDate date1, LocalDate date2) {
		List<RoomAssignment> roomAssignments = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(date1, date2);
		List<Room> occupiedRooms  = new ArrayList<Room>();
		List<Room> rooms = roomRepository.getAllRooms();
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		
		for (RoomAssignment ra : roomAssignments) {
			occupiedRooms.add(roomRepository.getRoomByNumber(ra.getRoomNumber()).get());
		}
		rooms.removeAll(occupiedRooms);
		for (Room r : rooms) {
			dtos.add(RoomDTO.createFromRoom(r, roomCategoryService.getRoomCategoryById(r.getCategory().getCategoryId().getId()).get()));
		}
		return dtos;
	}
	
	@Override
	public List<RoomDTO> getFreeRoomsBetweenByRoomCategoryId(String id,LocalDate date1, LocalDate date2) {
		List<RoomAssignment> roomAssignments = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(date1, date2);
		List<Room> occupiedRooms  = new ArrayList<Room>();
		List<Room> rooms = roomRepository.getAllRoomsByRoomCategory(new RoomCategoryId(id));
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		
		for (RoomAssignment ra : roomAssignments) {
			occupiedRooms.add(roomRepository.getRoomByNumber(ra.getRoomNumber()).get());
		}
		
		rooms.removeAll(occupiedRooms);
		for (Room r : rooms) {
			dtos.add(RoomDTO.createFromRoom(r, roomCategoryService.getRoomCategoryById(r.getCategory().getCategoryId().getId()).get()));
		}
		return dtos;
	}
	
	
	
}
