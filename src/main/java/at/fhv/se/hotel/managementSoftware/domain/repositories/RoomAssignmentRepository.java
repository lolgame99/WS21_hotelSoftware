package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;


public interface RoomAssignmentRepository {
	public List<RoomAssignment> getAllRoomAssignments();
	public List<RoomAssignment> getAllFreeRoomsBetweenDates(LocalDate date1, LocalDate date2);
}
