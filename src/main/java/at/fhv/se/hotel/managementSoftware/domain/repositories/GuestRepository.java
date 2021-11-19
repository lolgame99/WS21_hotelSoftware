package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public interface GuestRepository {
	public List<Guest> getAllGuests();
	public Optional<Guest> getGuestById(GuestId id);
	public void addGuest(Guest guest);
	public GuestId nextIdentity();
}
