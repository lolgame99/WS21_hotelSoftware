package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;

@SpringBootTest
@Transactional
public class RoomAssignmentRepositoryTest {
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Test
	void when_given_roomAssignment_is_added_return_equal() throws InvalidStayException {
		//given
		Stay stay = Stay.createForWalkIn(new StayId("1"), LocalDate.now(), LocalDate.now().plusDays(2), 2, "44774477", new CustomerId("1"), new GuestId("1"));
		RoomAssignment expectedAssignment = RoomAssignment.create(new RoomId("106"), stay);
		
		//when
		roomAssignmentRepository.addRoomAssignment(expectedAssignment);
		RoomAssignment actualAssignment = roomAssignmentRepository.getRoomAssignmentsByStayId(expectedAssignment.getStayId()).get(0);
		
		//then
		assertEquals(expectedAssignment.getStayId().getId(), actualAssignment.getStayId().getId());
		assertEquals(expectedAssignment.getRoomNumber().getId(), actualAssignment.getRoomNumber().getId());
		assertEquals(expectedAssignment.getAssignedFrom(), actualAssignment.getAssignedFrom());
		assertEquals(expectedAssignment.getAssignedTo(), actualAssignment.getAssignedTo());
		
	}
	
	@Test
	void when_given_invalid_stayId_return_empty() {
		//given
		StayId invalidStayId = new StayId("1337");
		
		//when
		List<RoomAssignment> actualAssignments = roomAssignmentRepository.getRoomAssignmentsByStayId(invalidStayId);
		
		//then
		assertTrue(actualAssignments.isEmpty());
	}
	
	@Test
	void when_all_test_roomAssignments_are_loaded() {
		//given
		String[] expectedRooms = {"301","302","201"}; 
		
		//when
		List<RoomAssignment> actualAssignments = roomAssignmentRepository.getAllRoomAssignments();
		String[] actualRooms = new String[3];
		for (int i = 0; i < actualAssignments.size(); i++) {
			actualRooms[i] = actualAssignments.get(i).getRoomNumber().getId();
		}
		
		//then
		assertArrayEquals(expectedRooms, actualRooms);
		
	}
	
	@Test
	void when_given_dates_return_all_roomAssignments_between() {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusDays(7);
		String expectedRooms = "201"; 
		
		//when
		RoomAssignment actualAssignments = roomAssignmentRepository.getAllRoomAssignmentsBetweenDates(fromDate, toDate).get(0);
		String actualRooms = actualAssignments.getRoomNumber().getId();
		
		//then
		assertEquals(expectedRooms, actualRooms);
	
	}
}
