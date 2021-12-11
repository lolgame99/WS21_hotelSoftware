package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
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
		StayId stayId1 = new StayId("123");
		LocalDate checkInDate1 = LocalDate.now();
		LocalDate checkOutDate1 = LocalDate.now().plusDays(7);
		int guestCount1 = 3;
		String creditCardNumber1 = "0201133211";
		BookingId bookingId1 = new BookingId("B12");
		CustomerId customerId1 = new CustomerId("122");
		GuestId guestId1 = new GuestId("133");
		Stay stay1 = Stay.createForWalkIn(stayId1, checkInDate1, checkOutDate1, guestCount1, creditCardNumber1, customerId1, guestId1);
		
		List<RoomAssignment> allAssignments = new ArrayList<RoomAssignment>();
		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("ABC"), new RoomId("123"), stay1));
		Mockito.when(roomAssignmentRepository.getAllRoomAssignments()).thenReturn(allAssignments);
		
		
		RoomCategory cat = RoomCategory.createWithoutDescription(new RoomCategoryId("AA"), "Testzimmer", 2);
		Price price = Price.create(new RoomCategoryId("test"), new BigDecimal("100"), LocalDate.now(), LocalDate.now().plusDays(7));
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO catDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		Room room = Room.create(new RoomId("BB"), RoomStatus.AVAILABLE, cat);
		Optional<RoomDTO> roomDTO = Optional.of(RoomDTO.createFromRoom(room, catDTO));
		Mockito.when(roomService.getRoomByRoomNumber(any(String.class))).thenReturn(roomDTO);
		
		//when
		List<RoomAssignmentDTO> dtos = roomAssignmentService.getAllRoomAssignmentDTOs();
		
		//then
		assertEquals(1, allAssignments.size());
		assertEquals(allAssignments.get(0).getAssignedFrom(), dtos.get(0).getAssignedFrom());
		assertEquals(allAssignments.get(0).getAssignedTo(), dtos.get(0).getAssignedTo());
		assertEquals(allAssignments.get(0).getRoomAssignmentId(), dtos.get(0).getRoomAssignmentId());
		assertEquals(allAssignments.get(0).getPaymentStatus(), dtos.get(0).getPaymentStatus());
//		assertEquals(allAssignments.get(0).getRoomNumber(), dtos.get(0).getRoom().getRoomNumber());
	}
	
	
	@Test
	void when_given_roomAssignmentId_returns_roomAssignmentId_roomAssignmentDTO() throws InvalidStayException {
		
		//given
		StayId stayId1 = new StayId("123");
		LocalDate checkInDate1 = LocalDate.now();
		LocalDate checkOutDate1 = LocalDate.now().plusDays(7);
		int guestCount1 = 3;
		String creditCardNumber1 = "0201133211";
		BookingId bookingId1 = new BookingId("B12");
		CustomerId customerId1 = new CustomerId("122");
		GuestId guestId1 = new GuestId("133");
		Stay stay1 = Stay.createForWalkIn(stayId1, checkInDate1, checkOutDate1, guestCount1, creditCardNumber1, customerId1, guestId1);
		
		List<RoomAssignment> allAssignments = new ArrayList<RoomAssignment>();
		allAssignments.add(RoomAssignment.create(new RoomAssignmentId("ABC"), new RoomId("123"), stay1));
		Mockito.when(roomAssignmentRepository.getRoomAssignmentsByStayId(any(StayId.class))).thenReturn(allAssignments);
		
		
		RoomCategory cat = RoomCategory.createWithoutDescription(new RoomCategoryId("AA"), "Testzimmer", 2);
		Price price = Price.create(new RoomCategoryId("test"), new BigDecimal("100"), LocalDate.now(), LocalDate.now().plusDays(7));
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO catDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		Room room = Room.create(new RoomId("BB"), RoomStatus.AVAILABLE, cat);
		Optional<RoomDTO> roomDTO = Optional.of(RoomDTO.createFromRoom(room, catDTO));
		Mockito.when(roomService.getRoomByRoomNumber(any(String.class))).thenReturn(roomDTO);
		
		//when
		List<RoomAssignmentDTO> dtos = roomAssignmentService.getRoomAssignmentsByStayId(stay1.getStayId().getId());
		
		//then
		assertEquals(1, allAssignments.size());
		assertEquals(allAssignments.get(0).getAssignedFrom(), dtos.get(0).getAssignedFrom());
		assertEquals(allAssignments.get(0).getAssignedTo(), dtos.get(0).getAssignedTo());
		assertEquals(allAssignments.get(0).getRoomAssignmentId(), dtos.get(0).getRoomAssignmentId());
		assertEquals(allAssignments.get(0).getPaymentStatus(), dtos.get(0).getPaymentStatus());
//		assertEquals(allAssignments.get(0).getRoomNumber(), dtos.get(0).getRoom().getRoomNumber());
	}
	
}
