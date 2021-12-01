package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
@Transactional
public class HibernateRoomAssignmentRepository implements RoomAssignmentRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<RoomAssignment> getAllRoomAssignments() {
		TypedQuery<RoomAssignment> query = em.createQuery("SELECT ra FROM RoomAssignment ra", RoomAssignment.class);
        return query.getResultList();
	}

	@Override
	public List<RoomAssignment> getAllRoomAssignmentsBetweenDates(LocalDate fromDate, LocalDate toDate) {
		TypedQuery<RoomAssignment> query = em.createQuery("SELECT ra FROM RoomAssignment ra WHERE assignedFrom >= :fromDate AND assignedTo <= :toDate", RoomAssignment.class)
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate);
        return query.getResultList();
	}

	@Override
	public List<RoomAssignment> getRoomAssignmentsByStayId(StayId id) {
		TypedQuery<RoomAssignment> query = em.createQuery("SELECT ra FROM RoomAssignment ra WHERE ra.stayId = :id", RoomAssignment.class)
				.setParameter("id", id);
        return query.getResultList();
	}

	@Override
	public void addRoomAssignment(RoomAssignment ra) {
		em.merge(ra);	
	}

	@Override
	public RoomAssignmentId nextIdentity() {
		return new RoomAssignmentId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Optional<RoomAssignment> getRoomAssignmentsById(RoomAssignmentId id) {
		TypedQuery<RoomAssignment> query = em.createQuery("SELECT ra FROM RoomAssignment ra WHERE ra.roomAssignmentId = :id", RoomAssignment.class)
				.setParameter("id", id);
		List<RoomAssignment> result = query.getResultList();
		if (result.size() != 1) {
			return Optional.empty();
		}
		return Optional.of(result.get(0));
	}
	
	

}
