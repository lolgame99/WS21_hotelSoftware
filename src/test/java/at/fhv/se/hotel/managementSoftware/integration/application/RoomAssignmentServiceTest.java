package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;

@SpringBootTest
public class RoomAssignmentServiceTest {
	
	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	@MockBean
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Test
	void when_getAll_roomAssignment_returns_all() throws InvalidStayException {
		
		//given
		List<RoomAssignment> allLines = new ArrayList<RoomAssignment>();
		
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		Stay stay = Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId);
		
		
		allLines.add(RoomAssignment.create(new RoomAssignmentId("101"), new RoomId("112"), stay));
		
		Mockito.when(roomAssignmentRepository.getAllRoomAssignments()).thenReturn(allLines);
		
		//when
		List<RoomAssignmentDTO> dtos = roomAssignmentService.getAllRoomAssignmentDTOs();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(allLines.get(0).getAssignedFrom(), dtos.get(0).getAssignedFrom());
		assertEquals(allLines.get(0).getAssignedTo(), dtos.get(0).getAssignedTo());
		assertEquals(allLines.get(0).getRoomAssignmentId(), dtos.get(0).getRoomAssignmentId());
		assertEquals(allLines.get(0).getPaymentStatus(), dtos.get(0).getPaymentStatus());
		assertEquals(allLines.get(0).getRoomNumber(), dtos.get(0).getRoom());
		//getStayID		nicht bei dem dto vorhanden
		//getId 		nicht bei dem dto vorhanden
	}
	
	
}
