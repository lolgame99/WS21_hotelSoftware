package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.PriceService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.PriceRepository;

@Component
public class PriceServiceImpl implements PriceService{
	
	@Autowired
	private PriceRepository priceRepository;

	@Override
	public List<PriceDetailsDTO> getAllPrices() {
		List<Price> allPrices = priceRepository.getAllPrices();
		List<PriceDetailsDTO> dtos = new ArrayList<PriceDetailsDTO>();
		for (Price p : allPrices) {
			dtos.add(PriceDetailsDTO.createFromPrice(p));
		}
		return dtos;
	}

	@Override
	public List<PriceDetailsDTO> getPricesByCategoryId(String id) {
		List<Price> prices = priceRepository.getPricesByCategoryId(new RoomCategoryId(id));
		List<PriceDetailsDTO> dtos = new ArrayList<PriceDetailsDTO>();
		for (Price p : prices) {
			dtos.add(PriceDetailsDTO.createFromPrice(p));
		}
		return dtos;
	}

	@Override
	public Optional<PriceDetailsDTO> getCurrentPriceByCategoryId(String id) {
		Optional<Price> price = priceRepository.getCurrentPriceByCategoryId(new RoomCategoryId(id));
		if (price.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(PriceDetailsDTO.createFromPrice(price.get()));
	}

}
