package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;

@Component
public class HibernateStayRepository implements StayRepository{
	
	List<Stay> stays = new ArrayList<Stay>();
	
	@Override
	public List<Stay> getAllStays() {
		return stays;
	}

	@Override
	public List<Stay> getCurrentStays(LocalDate date) {
		List<Stay> currentStay = new ArrayList<Stay>();
		for (Stay s : stays) {
			if(s.getCheckInDate().compareTo(date) <= 0 && s.getCheckOutDate().compareTo(date) >= 0) {
				currentStay.add(s);
			}
		}
		
		return currentStay;
	}

	@Override
	public void addStay(Stay stay) {
		stays.add(stay);
	}

	@Override
	public StayId nextIdentity() {
		return new StayId(UUID.randomUUID().toString().toUpperCase());
	}
	
}
