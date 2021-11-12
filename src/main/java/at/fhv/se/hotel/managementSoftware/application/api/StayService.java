package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;

public interface StayService {
	public List<StayDetailsDTO> getAllStays();
	public List<StayDetailsDTO> getCurrentStays(LocalDate date);
}
