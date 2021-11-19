package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

public interface StayService {
	public List<StayDetailsDTO> getAllStays();
	public List<StayDetailsDTO> getCurrentStays(LocalDate date);
	public Optional<StayDetailsDTO> getStayById(String id);
	public void addStayFromData(StayData stayData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate, LocalDate convertedBirthDate) throws Exception;
}
