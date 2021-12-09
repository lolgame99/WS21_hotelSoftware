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
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
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

@ActiveProfiles("test")
@SpringBootTest
public class RoomAssignmentServiceTest {
	
	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	@MockBean
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@MockBean
	private StayService stayService;
	
	@MockBean
	private RoomService roomService;
	
	@Test
	void when_getAll_roomAssignment_returns_all() throws InvalidStayException {
		
		//given
		List<RoomAssignment> allAssignments = new ArrayList<RoomAssignment>();
		
		StayId stayId1 = new StayId("123");
		LocalDate checkInDate1 = LocalDate.now();
		LocalDate checkOutDate1 = LocalDate.now().plusDays(7);
		int guestCount1 = 3;
		String creditCardNumber1 = "0201133211";
		BookingId bookingId1 = new BookingId("B12");
		CustomerId customerId1 = new CustomerId("122");
		GuestId guestId1 = new GuestId("133");
		
		StayId stayId2 = new StayId("1234");
		LocalDate checkInDate2 = LocalDate.now();
		LocalDate checkOutDate2 = LocalDate.now().plusDays(7);
		int guestCount2 = 3;
		String creditCardNumber2 = "0201133211";
		BookingId bookingId2 = new BookingId("B123");
		CustomerId customerId2 = new CustomerId("1222");
		GuestId guestId2 = new GuestId("1333");
		
		Stay stay1 = Stay.createForWalkIn(stayId1, checkInDate1, checkOutDate1, guestCount1, creditCardNumber1, customerId1, guestId1);
		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("101"), new RoomId("112"), stay1));
		
		Stay stay2 = Stay.createForWalkIn(stayId2, checkInDate2, checkOutDate2, guestCount2, creditCardNumber2, customerId2, guestId2);
		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("102"), new RoomId("113"), stay2));
		
		Mockito.when(roomAssignmentRepository.getAllRoomAssignments()).thenReturn(allAssignments);
		
		
		//when
		List<RoomAssignmentDTO> dtos = roomAssignmentService.getAllRoomAssignmentDTOs();
		
		//then
		assertEquals(2, dtos.size());
		assertEquals(allAssignments.get(0).getAssignedFrom(), dtos.get(0).getAssignedFrom());
		assertEquals(allAssignments.get(0).getAssignedTo(), dtos.get(0).getAssignedTo());
		assertEquals(allAssignments.get(0).getRoomAssignmentId(), dtos.get(0).getRoomAssignmentId());
		assertEquals(allAssignments.get(0).getPaymentStatus(), dtos.get(0).getPaymentStatus());
		assertEquals(allAssignments.get(0).getRoomNumber(), dtos.get(0).getRoom());
		//getStayID		nicht bei dem dto vorhanden
		//getId 		nicht bei dem dto vorhanden
	}
	
	
//	@Test
//	void when_given_roomAssignmentId_return_roomAssignmentId_roomAssignments() throws InvalidStayException {
//		
//		//given
//		List<RoomAssignment> allAssignments = new ArrayList<RoomAssignment>();
//		
//		StayId stayId1 = new StayId("123");
//		LocalDate checkInDate1 = LocalDate.now();
//		LocalDate checkOutDate1 = LocalDate.now().plusDays(7);
//		int guestCount1 = 3;
//		String creditCardNumber1 = "0201133211";
//		BookingId bookingId1 = new BookingId("B12");
//		CustomerId customerId1 = new CustomerId("122");
//		GuestId guestId1 = new GuestId("133");
//		
//		StayId stayId2 = new StayId("1234");
//		LocalDate checkInDate2 = LocalDate.now();
//		LocalDate checkOutDate2 = LocalDate.now().plusDays(7);
//		int guestCount2 = 3;
//		String creditCardNumber2 = "0201133211";
//		BookingId bookingId2 = new BookingId("B123");
//		CustomerId customerId2 = new CustomerId("1222");
//		GuestId guestId2 = new GuestId("1333");
//		
//		Stay stay1 = Stay.createForWalkIn(stayId1, checkInDate1, checkOutDate1, guestCount1, creditCardNumber1, customerId1, guestId1);
//		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("101"), new RoomId("112"), stay1));
//		
//		Stay stay2 = Stay.createForWalkIn(stayId2, checkInDate2, checkOutDate2, guestCount2, creditCardNumber2, customerId2, guestId2);
//		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("102"), new RoomId("113"), stay2));
//		
//		//when
//		List<RoomAssignmentDTO> dtos = roomAssignmentService.getRoomAssignmentsByStayId(allRoomAssignments.get(0).getRoomAssignmentId().getId());
//		
//		//then
//		assertEquals(2, allRoomAssignments.size());
//		assertEquals(allRoomAssignments.get(0).getAssignedFrom(), dtos.get(0).getAssignedFrom());
//		assertEquals(allRoomAssignments.get(0).getAssignedTo(), dtos.get(0).getAssignedTo());
//		assertEquals(allRoomAssignments.get(0).getRoomAssignmentId(), dtos.get(0).getRoomAssignmentId());
//		assertEquals(allRoomAssignments.get(0).getPaymentStatus(), dtos.get(0).getPaymentStatus());
//		assertEquals(allRoomAssignments.get(0).getRoomNumber(), dtos.get(0).getRoom());
//		//getStayID		nicht bei dem dto vorhanden
//		//getId 		nicht bei dem dto vorhanden
//	}
	
}
