package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@SpringBootTest
@Transactional
public class BookingAssignmentRepositoryTest {
	@Autowired
	private BookingAssignmentRepository bookingAssignmentRepository;
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void when_given_roomAssignment_is_added_return_equal() throws InvalidStayException {
		//given
		BookingId bookingId = new BookingId("asdf");
		LocalDate checkInDate =  LocalDate.now().plusDays(20);
		LocalDate checkOutDate = LocalDate.now().plusDays(30);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = customerRepository.getAllCustomers().get(2).getCustomerId();
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		RoomCategory category = roomCategoryRepository.getAllRoomCategories().get(2);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        
        categoryCount.put(category, 1);
		
		Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
		BookingAssignment expectedAssignment = BookingAssignment.create(new BookingAssignmentId(bookingAssignmentRepository.nextIdentity()), , booking)
		
		//when
		bookingAssignmentRepository.addBookingAssignment(expectedAssignment);
		RoomAssignment actualAssignment = bookingAssignmentRepository.getRoomAssignmentsByStayId(expectedAssignment.getStayId()).get(0);
		
		//then
		assertEquals(expectedAssignment.getStayId().getId(), actualAssignment.getStayId().getId());
		assertEquals(expectedAssignment.getRoomNumber().getId(), actualAssignment.getRoomNumber().getId());
		assertEquals(expectedAssignment.getAssignedFrom(), actualAssignment.getAssignedFrom());
		assertEquals(expectedAssignment.getAssignedTo(), actualAssignment.getAssignedTo());
		
	}

}
