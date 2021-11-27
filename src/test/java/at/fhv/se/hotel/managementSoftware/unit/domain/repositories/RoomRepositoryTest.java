package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@SpringBootTest
@Transactional
public class RoomRepositoryTest {
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Test
	void when_given_room_is_added_return_equal() {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		Room expectedRoom = Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category);
		
		//when
		roomRepository.addRoom(expectedRoom);
		Optional<Room> actualRoom = roomRepository.getRoomByNumber(expectedRoom.getRoomNumber());
		
		//then
		assertEquals(expectedRoom.getRoomNumber().getId(), actualRoom.get().getRoomNumber().getId());
		assertEquals(expectedRoom.getRoomStatus(), actualRoom.get().getRoomStatus());
		assertEquals(expectedRoom.getCategory().getCategoryId().getId(), actualRoom.get().getCategory().getCategoryId().getId());
	}
	
	@Test
	void when_given_invalid_roomId_return_empty() {
		//given
		RoomId invalidRoomId = new RoomId("12333");
		
		//when
		Optional<Room> actualRoom = roomRepository.getRoomByNumber(invalidRoomId);
		
		//then
		assertTrue(actualRoom.isEmpty());
	}
	
	@Test
	void when_all_test_rooms_are_loaded() {
		//given
		String[] expectedRoomNumbers = {"101","102","103","104","105","106","201","202","203","204","205","206","301","302","303","304","305","306"};
		
		//when
		String[] actualRoomNumbers = new String[18];
		List<Room> actualRooms = roomRepository.getAllRooms();
		for (int i = 0; i < actualRooms.size(); i++) {
			actualRoomNumbers[i] = actualRooms.get(i).getRoomNumber().getId();
		}
		
		//then
		assertArrayEquals(expectedRoomNumbers, actualRoomNumbers);
	}
	
	@Test
	void when_given_roomcategory_return_all_rooms_of_category() {
		//given
		RoomCategory expectedCategory = roomCategoryRepository.getAllRoomCategories().get(0);
		
		//when
		List<Room> actualRooms = roomRepository.getAllRoomsByRoomCategory(expectedCategory);
		
		//then
		assertEquals(expectedCategory, actualRooms.get(0).getCategory());
		assertEquals(expectedCategory, actualRooms.get(1).getCategory());
		assertEquals(expectedCategory, actualRooms.get(2).getCategory());
		assertEquals(expectedCategory, actualRooms.get(3).getCategory());
		assertEquals(expectedCategory, actualRooms.get(4).getCategory());
		assertEquals(expectedCategory, actualRooms.get(5).getCategory());		
	}
}
