package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.BookingAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidRoomException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@ActiveProfiles("test")
@SpringBootTest
public class BookingAssignmentServiceTest {
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private BookingAssignmentRepository bookingAssignmentRepository;
	
	@Autowired
	private BookingAssignmentService bookingAssignmentService;
	
	@Test
	public void when_getFreeRoomCount_return() throws Exception {
		//given
		RoomCategory category = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Test Category", 2);
		List<Room> allRooms = new ArrayList<Room>();
		allRooms.add(Room.create(new RoomId("1"), RoomStatus.AVAILABLE, category));
		allRooms.add(Room.create(new RoomId("2"), RoomStatus.AVAILABLE, category));
		allRooms.add(Room.create(new RoomId("3"), RoomStatus.AVAILABLE, category));
		Mockito.when(roomRepository.getAllRooms()).thenReturn(allRooms);
		
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 1;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(category, 1);
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		
        List<BookingAssignment> bookingAssignments = new ArrayList<BookingAssignment>();
		bookingAssignments.add(BookingAssignment.create(new BookingAssignmentId("11"), category, 1, booking));
		Mockito.when(bookingAssignmentRepository.getAllBookingAssignmentsBetweenDates(checkInDate, checkOutDate)).thenReturn(bookingAssignments);
		
		//when
		//Map<String,Integer> result = bookingAssignmentService.getFreeRoomCountBetweenDates(checkInDate.toString(), checkOutDate.toString());
		
		//then
		//assertEquals(2, result.get(category.getCategoryId().getId()));
	}
}
