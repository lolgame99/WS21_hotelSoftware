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

import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
@Transactional
public class HibernateBookingAssignmentRepository implements BookingAssignmentRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BookingAssignment> getAllBookingAssignmentsBetweenDates(LocalDate date1, LocalDate date2) {
		TypedQuery<BookingAssignment> query = em.createQuery("SELECT ba FROM BookingAssignment ba WHERE assignedFrom <= :toDate AND assignedTo >= :fromDate", BookingAssignment.class)
				.setParameter("fromDate", date1)
				.setParameter("toDate", date2);
        return query.getResultList();
	}

	@Override
	public void addBookingAssignment(BookingAssignment ba) {
		em.merge(ba);
	}

	@Override
	public BookingAssignmentId nextIdentity() {
		return new BookingAssignmentId(UUID.randomUUID().toString().toUpperCase());
	}
	
	

}
