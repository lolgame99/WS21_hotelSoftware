package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
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

@ActiveProfiles("test")
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
		
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3)));
		Optional<RoomCategoryDTO> categoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(category, priceDTO));
		Mockito.when(roomCategoryService.getRoomCategoryById(any(String.class))).thenReturn(categoryDTO);
		
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
		
	//Hier ist noch ein Fehler
		Mockito.when(roomRepository.getAllRoomsByRoomCategory(allRooms.get(0).getCategory())).thenReturn(allRooms);
		
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3)));
		Optional<RoomCategoryDTO> categoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(category, priceDTO));
		Mockito.when(roomCategoryService.getRoomCategoryById(any(String.class))).thenReturn(categoryDTO);
		
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
	public void when_get_all_rooms_by_roomNumber() throws InvalidRoomException {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		
	//Hier ist noch ein Fehler
		Mockito.when(roomRepository.getRoomByNumber(allRooms.get(0).getRoomNumber())).thenReturn(Optional.of(allRooms.get(0)));
		
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3)));
		Optional<RoomCategoryDTO> categoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(category, priceDTO));
		Mockito.when(roomCategoryService.getRoomCategoryById(any(String.class))).thenReturn(categoryDTO);
		
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
	public void when_get_all_free_rooms_between() throws Exception {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusWeeks(1);
		
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.OCCUPIED, category));
		allRooms.add(Room.create(new RoomId("2"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRooms()).thenReturn(allRooms);
		
		Stay stay = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(7), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		List<RoomAssignment> roomAssignments =  new ArrayList<RoomAssignment>();
		roomAssignments.add(RoomAssignment.create(new RoomAssignmentId("1"), allRooms.get(0).getRoomNumber(), stay));
		Mockito.when(roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(fromDate,toDate)).thenReturn(roomAssignments);
		
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3)));
		Optional<RoomCategoryDTO> categoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(category, priceDTO));
		Mockito.when(roomCategoryService.getRoomCategoryById(any(String.class))).thenReturn(categoryDTO);
		
		Mockito.when(roomRepository.getRoomByNumber(roomAssignments.get(0).getRoomNumber())).thenReturn(Optional.of(allRooms.get(0)));
		
		//when
		List<RoomDTO> dtos = roomService.getAllFreeRoomsBetween(fromDate, toDate);
								
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
	}
	
	
	@Test
	public void when_get_all_free_rooms_between_by_roomCategoryId() throws Exception {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusWeeks(1);
				
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.OCCUPIED, category));
		allRooms.add(Room.create(new RoomId("2"), RoomStatus.AVAILABLE, category));
		
	//Hier ist noch ein Fehler
		Mockito.when(roomRepository.getAllRoomsByRoomCategory(any(RoomCategory.class))).thenReturn(allRooms);
				
		Stay stay = Stay.createForWalkIn(new StayId("123"), LocalDate.now(), LocalDate.now().plusDays(7), 2, "1234 1234 1234 1234", new CustomerId("C6"), new GuestId("G6"));
		List<RoomAssignment> roomAssignments =  new ArrayList<RoomAssignment>();
		roomAssignments.add(RoomAssignment.create(new RoomAssignmentId("1"), allRooms.get(0).getRoomNumber(), stay));
		Mockito.when(roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(fromDate,toDate)).thenReturn(roomAssignments);
				
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(Price.create(new RoomCategoryId("AA"), BigDecimal.valueOf(100), LocalDate.now().minusDays(1), LocalDate.now().plusMonths(3)));
		Optional<RoomCategoryDTO> categoryDTO = Optional.of(RoomCategoryDTO.createFromCategory(category, priceDTO));
		Mockito.when(roomCategoryService.getRoomCategoryById(any(String.class))).thenReturn(categoryDTO);
				
		Mockito.when(roomRepository.getRoomByNumber(roomAssignments.get(0).getRoomNumber())).thenReturn(Optional.of(allRooms.get(0)));
				
		//when
		List<RoomDTO> dtos = roomService.getAllFreeRoomsBetween(fromDate, toDate);
										
		//then
		assertEquals(allRooms.get(0).getRoomNumber().getId(), dtos.get(0).getRoomNumber().getId());
		assertEquals(allRooms.get(0).getRoomStatus(), dtos.get(0).getRoomStatus());
		assertEquals(allRooms.get(0).getCategory().getCategoryId().getId(), dtos.get(0).getRoomCategory().getCategoryId().getId());
		assertEquals(allRooms.get(0).getCategory().getCategoryName(), dtos.get(0).getRoomCategory().getName());
		assertEquals(allRooms.get(0).getCategory().getBedNumber(), dtos.get(0).getRoomCategory().getBedNumber());
			}
}
