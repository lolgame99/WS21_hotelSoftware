package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public interface PriceRepository {
	public List<Price> getAllPrices();
	public List<Price> getPricesByCategoryId(RoomCategoryId id);
	public Optional<Price> getCurrentPriceByCategoryId(RoomCategoryId id);
	public void addPrice(Price price);
}
