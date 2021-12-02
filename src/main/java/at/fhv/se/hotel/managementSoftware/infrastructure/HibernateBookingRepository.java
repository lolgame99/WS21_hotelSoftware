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

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;

@Component
@Transactional
public class HibernateBookingRepository implements BookingRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Booking> getAllBookings() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
        return query.getResultList();
	}

	@Override
	public List<Booking> getBookingsByCheckInDate(LocalDate date) {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.checkInDate = :date", Booking.class)
				.setParameter("date", date);
        return query.getResultList();
	}

	@Override
	public void addBooking(Booking booking) {
		em.merge(booking);
	}

	@Override
	public BookingId nextIdentity() {
		return new BookingId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Optional<Booking> getBookingById(BookingId id) {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.bookingId = :id", Booking.class)
				.setParameter("id", id);
		List<Booking> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public List<Booking> getReadyBookingsByCheckInDate(LocalDate date) {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE checkInDate = :date AND (b.bookingStatus = 'PAID' OR b.bookingStatus = 'ARRIVED')", Booking.class)
				.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	public void deleteBookingById(BookingId id) {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.bookingId = :id", Booking.class)
				.setParameter("id", id);
		List<Booking> result = query.getResultList();
		if(result.size() == 1) {
			em.remove(result.get(0));
		}
	}

}
