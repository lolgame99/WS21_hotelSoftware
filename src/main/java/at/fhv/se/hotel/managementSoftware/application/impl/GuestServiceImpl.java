package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.GuestService;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;

@Component
public class GuestServiceImpl implements GuestService{

	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public List<GuestDetailsDTO> getAllGuests() {
		List<Guest> guests = guestRepository.getAllGuests();
		List<GuestDetailsDTO> guestDTOs = new ArrayList<GuestDetailsDTO>();
		for (Guest g : guests) {
			guestDTOs.add(GuestDetailsDTO.createFromGuest(g));
		}
		return guestDTOs;
	}

	@Override
	public Optional<GuestDetailsDTO> getGuestById(String id) {
		Optional<Guest> guest = guestRepository.getGuestById(new GuestId(id));
		Optional<GuestDetailsDTO> dto = Optional.empty();
		if (guest.isPresent()) {
			dto = Optional.of(GuestDetailsDTO.createFromGuest(guest.get()));
		}
		
		return dto;
	}

}
