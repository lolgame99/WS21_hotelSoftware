package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;

public class RoomTest {
	@Test
	void when_room_set_status() {
		//given
		RoomId roomNumber = new RoomId("103");
		RoomCategoryId categoryId = new RoomCategoryId("2");
		RoomStatus roomStatus = RoomStatus.CLEANING;
		
		//when
		Room room = Room.create(roomNumber, roomStatus, categoryId);
		
		//then
		assertEquals(roomNumber, room.getRoomNumber());
		assertEquals(roomStatus, room.getRoomStatus());
		assertEquals(categoryId, room.getCategoryId());
	}
	
	@Test
	void when_room_doesnt_exist() throws InvalidRoomException {
		//given
		RoomId roomNumber = new RoomId("asf");
		RoomCategoryId categoryId = new RoomCategoryId("2");
		RoomStatus roomStatus = RoomStatus.CLEANING;
		
		//when
		Room room = Room.create(roomNumber, roomStatus, categoryId);
		
		//then
		assertEquals(roomNumber, room.getRoomNumber());
		assertEquals(roomStatus, room.getRoomStatus());
		assertEquals(categoryId, room.getCategoryId());
	}
}
