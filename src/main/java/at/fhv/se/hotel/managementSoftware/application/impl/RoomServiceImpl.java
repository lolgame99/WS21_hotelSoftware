package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Autowired
	private RoomCategoryRepository roomcategoryRepository;
	
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
		
		List<Room> rooms = roomRepository.getAllRoomsByRoomCategory(roomcategoryRepository.getRoomCategoryById(new RoomCategoryId(id)).get());
		
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
	public List<RoomDTO> getAllFreeRoomsBetween(String date1, String date2) {
		List<RoomAssignment> roomAssignments = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(dateStringConverter(date1), dateStringConverter(date2));
		List<Room> occupiedRooms  = new ArrayList<Room>();
		List<Room> rooms = roomRepository.getAllRooms();
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		
		for (RoomAssignment ra : roomAssignments) {		
			occupiedRooms.add(roomRepository.getRoomByNumber(ra.getRoomNumber()).get());
		}
		rooms.removeAll(occupiedRooms);
		for (Room r : rooms) {
			if (r.getRoomStatus() == RoomStatus.AVAILABLE) {
				dtos.add(RoomDTO.createFromRoom(r, roomCategoryService.getRoomCategoryById(r.getCategory().getCategoryId().getId()).get()));
			}
		}
		return dtos;
	}
	
	@Override
	public List<RoomDTO> getFreeRoomsBetweenByRoomCategoryId(String id,String date1, String date2) {
		List<RoomAssignment> roomAssignments = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(dateStringConverter(date1), dateStringConverter(date2));
		List<Room> occupiedRooms  = new ArrayList<Room>();
		List<Room> rooms = roomRepository.getAllRoomsByRoomCategory(roomcategoryRepository.getRoomCategoryById(new RoomCategoryId(id)).get());
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		
		for (RoomAssignment ra : roomAssignments) {		
			occupiedRooms.add(roomRepository.getRoomByNumber(ra.getRoomNumber()).get());
		}
		
		rooms.removeAll(occupiedRooms);
		for (Room r : rooms) {
			if (r.getRoomStatus() == RoomStatus.AVAILABLE) {
				dtos.add(RoomDTO.createFromRoom(r, roomCategoryService.getRoomCategoryById(r.getCategory().getCategoryId().getId()).get()));
			}
		}
		return dtos;
	}
	
	private LocalDate dateStringConverter(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		if (date != null && date != "") {
			splitStringArray = date.split("-");
			for (int i = 0; i < splitStringArray.length; i++) {
				splitIntArray[i] = Integer.parseInt(splitStringArray[i]);
			}
			return LocalDate.of(splitIntArray[0], splitIntArray[1], splitIntArray[2]);
		}else {
			return null;
		}
		
		
	}

	@Override
	@Transactional
	public void changeRoomStatus(String roomId, String status) throws InvalidRoomException {
		Optional<Room> room = roomRepository.getRoomByNumber(new RoomId(roomId));
		if (room.isEmpty()) {
			throw new InvalidRoomException("Status could'nt be changed <br> Room "+roomId+" does'nt exist");
		}else {
			room.get().setStatus(RoomStatus.valueOf(status));
		}
		
		return;
	}
	
}
