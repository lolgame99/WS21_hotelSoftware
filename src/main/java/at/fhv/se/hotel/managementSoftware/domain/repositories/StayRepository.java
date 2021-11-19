package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public interface StayRepository {
	public List<Stay> getAllStays();
	public List<Stay> getCurrentStays(LocalDate date);
	public Optional<Stay> getStayById(StayId id);
	public void addStay(Stay stay);
	public StayId nextIdentity();
}
