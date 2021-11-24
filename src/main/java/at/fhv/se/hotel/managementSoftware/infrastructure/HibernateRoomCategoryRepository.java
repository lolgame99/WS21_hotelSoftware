package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
@Transactional
public class HibernateRoomCategoryRepository implements RoomCategoryRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<RoomCategory> getAllRoomCategories() {
		TypedQuery<RoomCategory> query = em.createQuery("SELECT rc FROM RoomCategory rc", RoomCategory.class);
        return query.getResultList();
	}

	@Override
	public Optional<RoomCategory> getRoomCategoryById(RoomCategoryId id) {
		TypedQuery<RoomCategory> query = em.createQuery("SELECT rc FROM RoomCategory rc WHERE rc.id = :id", RoomCategory.class)
				.setParameter("id", id);
		List<RoomCategory> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public void addRoomCategory(RoomCategory category) {
		em.persist(category);	
	}

	@Override
	public RoomCategoryId nextIdentity() {
		return new RoomCategoryId(UUID.randomUUID().toString().toUpperCase());
	}

}
