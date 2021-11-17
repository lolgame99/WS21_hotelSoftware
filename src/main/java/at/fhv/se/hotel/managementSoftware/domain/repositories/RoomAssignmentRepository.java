package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;


public interface RoomAssignmentRepository {
	public List<RoomAssignment> getAllRoomAssignments();
	public List<RoomAssignment> getAllRoomAssignmentsBetweenDates(LocalDate date1, LocalDate date2);
	public Optional<RoomAssignment> getRoomAssignmentByStayId(StayId id);
 }
