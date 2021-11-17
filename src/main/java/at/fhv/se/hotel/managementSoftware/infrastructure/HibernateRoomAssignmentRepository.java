package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;

public class HibernateRoomAssignmentRepository implements RoomAssignmentRepository {

	List<RoomAssignment> roomAssignments = new ArrayList<RoomAssignment>();
	
	@Override
	public List<RoomAssignment> getAllRoomAssignments() {
		return roomAssignments;	
	}

	@Override
	public List<RoomAssignment> getAllFreeRoomsBetweenDates(LocalDate date1, LocalDate date2) {
		List<RoomAssignment> freeRoomsBetweenDates = new ArrayList<RoomAssignment>();
		for (RoomAssignment ra : freeRoomsBetweenDates) {
			if(!(ra.getAssignedFrom().compareTo(date1) >= 0 && ra.getAssignedFrom().compareTo(date2) <= 0)) {
				if(!(ra.getAssignedTo().compareTo(date1) >= 0 && ra.getAssignedTo().compareTo(date2) <= 0)) {
					//TODO:
				}
			}
		}
		
		return freeRoomsBetweenDates;
	}

}
