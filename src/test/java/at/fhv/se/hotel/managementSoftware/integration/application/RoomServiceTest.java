package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@SpringBootTest
public class RoomServiceTest {
	
	@Autowired
	private RoomService roomService;
	
	@MockBean
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private RoomCategoryRepository roomCategoryRepository;
	
	@MockBean
	private RoomCategoryService roomCategoryService;
	
	
	
	
	@Test
	public void when_get_all_rooms() throws InvalidRoomException {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRooms()).thenReturn(allRooms);
		
		//when
		List<RoomDTO> dtos = roomService.getAllRoomDTOs();
		
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
	}
	
	
	@Test
	public void when_get_all_rooms_by_roomCategory()  throws InvalidRoomException {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRoomsByRoomCategory(any(RoomCategory.class))).thenReturn(allRooms);
				
		//when
		List<RoomDTO> dtos = roomService.getAllRoomsByRoomCategory(allRooms.get(0).getCategory().getCategoryId().getId());
				
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
	}

	@Test
	public void when_get_all_rooms_by_roomNumber() throws InvalidRoomException {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRooms()).thenReturn(allRooms);
						
		//when
		Optional<RoomDTO> dtos = roomService.getRoomByRoomNumber(allRooms.get(0).getRoomNumber().getId());
						
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get().getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get().getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get().getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get().getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get().getRoomCategory().getBedNumber());
	}
	
	
	@Test
	public void when_get_all_free_rooms_between() throws Exception {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRooms()).thenReturn(allRooms);
		Mockito.when(roomAssignmentRepository.getAllRoomAssignments().get(0).getAssignedFrom());
		Mockito.when(roomAssignmentRepository.getAllRoomAssignments().get(0).getAssignedTo());
		
		//when
		List<RoomDTO> dtos = roomService.getAllFreeRoomsBetween(roomAssignmentRepository.getAllRoomAssignments().get(0).getAssignedFrom(), roomAssignmentRepository.getAllRoomAssignments().get(0).getAssignedTo());
								
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
	}
	
	/*
	@Test
	public void when_get_all_free_rooms_between_by_roomCategoryId() throws Exception {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRoomsByRoomCategory(any(RoomCategory.class)));
		
		List<RoomAssignment> assignment = new ArrayList<RoomAssignment>();
		Stay stay = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(14), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		assignment.add(roomAssignmentRepository.create(new RoomAssignmentId("1"), new RoomId("1"), stay));
		Mockito.when(roomAssignmentRepository.getAssignedFrom());
		Mockito.when(roomAssignmentRepository.getAssignedTo());
		
		//when
		List<RoomDTO> dtos = roomService.getAllFreeRoomsBetween(assignment.get(0).getAssignedFrom(), assignment.get(0).getAssignedTo());
						
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
	} */
}
