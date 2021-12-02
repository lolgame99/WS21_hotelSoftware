package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.PriceRepository;

@Component
@Transactional
public class HibernatePriceRepository implements PriceRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Price> getAllPrices() {
		TypedQuery<Price> query = em.createQuery("SELECT p FROM Price p", Price.class);
        return query.getResultList();
	}

	@Override
	public List<Price> getPricesByCategoryId(RoomCategoryId id) {
		TypedQuery<Price> query = em.createQuery("SELECT p FROM Price p WHERE roomCategoryId = :id", Price.class)
				.setParameter("id", id);
        return query.getResultList();
	}

	@Override
	public Optional<Price> getCurrentPriceByCategoryId(RoomCategoryId id) {
		TypedQuery<Price> query = em.createQuery("SELECT p FROM Price p WHERE p.roomCategoryId = :id AND p.validFrom <= :today AND p.validTo >= :today", Price.class)
				.setParameter("id", id)
				.setParameter("today", LocalDate.now());
		List<Price> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public void addPrice(Price price) {
		em.merge(price);
	}

}
