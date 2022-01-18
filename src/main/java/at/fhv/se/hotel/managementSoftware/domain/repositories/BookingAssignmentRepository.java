package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;


public interface BookingAssignmentRepository {
	public List<BookingAssignment> getAllBookingAssignmentsBetweenDates(LocalDate date1, LocalDate date2);
	public void addBookingAssignment(BookingAssignment ba);
	public BookingAssignmentId nextIdentity();
 }
