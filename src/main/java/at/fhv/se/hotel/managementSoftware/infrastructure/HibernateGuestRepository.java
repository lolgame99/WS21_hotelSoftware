package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;

@Component
@Transactional
public class HibernateGuestRepository implements GuestRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Guest> getAllGuests() {
		TypedQuery<Guest> query = em.createQuery("SELECT g FROM Guest g", Guest.class);
        return query.getResultList();
	}

	@Override
	public Optional<Guest> getGuestById(GuestId id) {
		TypedQuery<Guest> query = em.createQuery("SELECT g FROM Guest g WHERE g.guestId = :id", Guest.class)
				.setParameter("id", id);
		List<Guest> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public void addGuest(Guest guest) {
		em.merge(guest);
		
	}

	@Override
	public GuestId nextIdentity() {
		return new GuestId(UUID.randomUUID().toString().toUpperCase());
	}
}
