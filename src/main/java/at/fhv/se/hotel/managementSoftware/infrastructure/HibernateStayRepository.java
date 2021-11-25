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

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;

@Component
@Transactional
public class HibernateStayRepository implements StayRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Stay> getAllStays() {
		TypedQuery<Stay> query = em.createQuery("SELECT s FROM Stay s", Stay.class);
        return query.getResultList();
	}

	@Override
	public List<Stay> getCurrentStays(LocalDate date) {
		TypedQuery<Stay> query = em.createQuery("SELECT s FROM Stay s WHERE checkInDate <= :date AND checkOutDate >= :date", Stay.class)
				.setParameter("date", date);
        return query.getResultList();
	}

	@Override
	public void addStay(Stay stay) {
		em.merge(stay);
	}

	@Override
	public StayId nextIdentity() {
		return new StayId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Optional<Stay> getStayById(StayId id) {
		TypedQuery<Stay> query = em.createQuery("SELECT s FROM Stay s WHERE s.stayId = :id", Stay.class)
				.setParameter("id", id);
		List<Stay> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}
	
}
