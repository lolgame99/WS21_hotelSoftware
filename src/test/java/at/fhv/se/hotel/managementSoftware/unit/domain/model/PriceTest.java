package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;

public class PriceTest {
	
	@Test
	void create_from_Price () {
		RoomCategoryId roomCategoryId = new RoomCategoryId("12");
		BigDecimal cost = new BigDecimal(130);
		LocalDate validFrom = LocalDate.of(2021, 5, 17);
		LocalDate validTo = LocalDate.of(2021, 5, 21);
		
		Price price = Price.create(roomCategoryId, cost, validFrom, validTo);
		
		assertEquals(roomCategoryId, price.getRoomCategoryId());
		assertEquals(cost, price.getCost());
		assertEquals(validFrom, price.getValidFrom());
		assertEquals(validTo, price.getValidTo());
	}
}
