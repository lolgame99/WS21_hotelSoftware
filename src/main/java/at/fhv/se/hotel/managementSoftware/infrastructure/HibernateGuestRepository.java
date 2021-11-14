package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;

@Component
public class HibernateGuestRepository implements GuestRepository{

	List<Guest> guests = new ArrayList<Guest>();
	
	@Override
	public List<Guest> getAllGuests() {
		return guests;
	}

	@Override
	public Optional<Guest> getGuestById(GuestId id) {
		Optional<Guest> guest = Optional.empty();
		for (Guest g : guests) {
			if (g.getGuestId().getId().equals(id.getId())) {
				guest = Optional.of(g);
			}
		}
		return guest;
	}

	@Override
	public void addGuest(Guest guest) {
		guests.add(guest);
		
	}

}
