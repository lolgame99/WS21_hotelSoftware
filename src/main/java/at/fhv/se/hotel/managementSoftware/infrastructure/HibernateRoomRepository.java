package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
@Transactional
public class HibernateRoomRepository implements RoomRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Room> getAllRooms() {
		TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r", Room.class);
        return query.getResultList();
	}

	@Override
	public List<Room> getAllRoomsByRoomCategory(RoomCategoryId id) {
		TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r WHERE r.categoryId = :id", Room.class)
				.setParameter("id", id);
        return query.getResultList();
	}

	@Override
	public Optional<Room> getRoomByNumber(RoomId number) {
		TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r WHERE r.roomNumber = :id", Room.class)
				.setParameter("id", number);
		List<Room> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public void addRoom(Room room) {
		em.merge(room);	
	}
	
}
