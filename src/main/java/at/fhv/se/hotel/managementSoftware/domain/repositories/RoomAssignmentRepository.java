package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;


public interface RoomAssignmentRepository {
	public List<RoomAssignment> getAllRoomAssignments();
	public List<RoomAssignment> getAllRoomAssignmentsBetweenDates(LocalDate date1, LocalDate date2);
	public List<RoomAssignment> getRoomAssignmentsByStayId(StayId id);
	public Optional<RoomAssignment> getRoomAssignmentsById(RoomAssignmentId id);
	public void addRoomAssignment(RoomAssignment ra);
	public RoomAssignmentId nextIdentity();
 }
