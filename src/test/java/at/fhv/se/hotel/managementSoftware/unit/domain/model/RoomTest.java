package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;

public class RoomTest {
	@Test
	void when_room_created() {
		//given
		RoomId roomNumber = new RoomId("103");
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("101"), "Single Room", 1);
		RoomStatus roomStatus = RoomStatus.AVAILABLE;
		
		//when
		Room room = Room.create(roomNumber, roomStatus, category);
		
		//then
		assertEquals(roomNumber, room.getRoomNumber());
		assertEquals(roomStatus, room.getRoomStatus());
		assertEquals(category, room.getCategory());
	}

}
