package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;


public interface BookingAssignmentService {
	public Map<String,Integer> getFreeRoomCountBetweenDates(String fromDate, String toDate);
	public void addBookingAssignment(BookingAssignment ba);
}
