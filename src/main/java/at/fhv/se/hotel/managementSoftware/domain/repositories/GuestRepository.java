package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;

public interface GuestRepository {
	public List<Guest> getAllGuests();
	public Optional<Guest> getGuestByStayId(String id);
	public void addGuest(Guest guest);
}
