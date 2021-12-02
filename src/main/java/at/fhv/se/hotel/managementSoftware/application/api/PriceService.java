package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;

public interface PriceService {
	public List<PriceDetailsDTO> getAllPrices();
	public List<PriceDetailsDTO> getPricesByCategoryId(String id);
	public Optional<PriceDetailsDTO> getCurrentPriceByCategoryId(String id);
}
