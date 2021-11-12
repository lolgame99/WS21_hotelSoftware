package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;

public interface GuestService {
	public List<GuestDetailsDTO> getAllGuests();
	public Optional<GuestDetailsDTO> getGuestByStayId(String id);
	
}
