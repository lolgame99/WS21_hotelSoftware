package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

public class HibernateRoomAssignmentRepository implements RoomAssignmentRepository {
	
	List<RoomAssignment> roomAssignments = new ArrayList<RoomAssignment>();
	
	@Override
	public List<RoomAssignment> getAllRoomAssignments() {
		return roomAssignments;	
	}

	@Override
	public List<RoomAssignment> getAllRoomAssignmentsBetweenDates(LocalDate date1, LocalDate date2) {
		List<RoomAssignment> roomAssignments = new ArrayList<RoomAssignment>();
		for (RoomAssignment ra : getAllRoomAssignments()) {
			if (ra.getAssignedFrom().minusDays(1).isBefore(date1) && ra.getAssignedTo().plusDays(1).isAfter(date2)) {
				roomAssignments.add(ra);
			}
		}
		return roomAssignments;
	}

	@Override
	public Optional<RoomAssignment> getRoomAssignmentByStayId(StayId id) {
		Optional<RoomAssignment> roomAssignment = Optional.empty();
		for (RoomAssignment ra : getAllRoomAssignments()) {
			if (ra.getStayId().getId().equals(id.getId())) {
				roomAssignment = Optional.of(ra);
			}
		}
		return roomAssignment;
	}
	
	

}
