package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public class RoomAssignmentTest {
	
	
	@Test
	void when_create_RoomAssignment_for_WalkIn_Guest() throws InvalidStayException {
		//given RoomAssignment
		LocalDate assignedFrom = LocalDate.now();
		LocalDate assignedTo = LocalDate.now().plusDays(7);
		RoomId roomNumber = new RoomId("101");
		StayId stayId = new StayId("C1");
		//given Stay
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "220201001020";
		//BookingId bookingId = new BookingId("B2"); forCreateFromBookingGuest
		CustomerId customerId = new CustomerId("C2");
		GuestId guestId = new GuestId("G2");
		Stay stay = Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId);
				
		
		
		//when
		RoomAssignment roomAssignment = RoomAssignment.create(roomNumber, stay);
		
		//then
		assertEquals(assignedFrom, roomAssignment.getAssignedFrom());
		assertEquals(assignedTo, roomAssignment.getAssignedTo());
		assertEquals(roomNumber, roomAssignment.getRoomNumber());
		assertEquals(stayId, roomAssignment.getStayId());
	}
	

}
