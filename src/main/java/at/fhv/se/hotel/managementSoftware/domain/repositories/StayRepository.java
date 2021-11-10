package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.Stay;

public interface StayRepository {
	public List<Stay> getAllStays();
	public List<Stay> getCurrentStays(LocalDate date);
	public void addStay(Stay stay);
	public String nextIdentity();
}
