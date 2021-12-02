package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.PriceRepository;

@SpringBootTest
@Transactional
public class PriceRepositoryTest {
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Test
	void when_given_price_is_added_return_equal() {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusMonths(3);
		Price expectedPrice = Price.create(new RoomCategoryId("11"), new BigDecimal(10.00), fromDate, toDate);
		
		//when
		priceRepository.addPrice(expectedPrice);
		List<Price> allPrices = priceRepository.getAllPrices();
		Price actualPrice = null;
		for (Price p : allPrices) {
			if (p.getRoomCategoryId().getId().equals(expectedPrice.getRoomCategoryId().getId()) && p.getValidFrom().equals(expectedPrice.getValidFrom()) && p.getValidTo().equals(expectedPrice.getValidTo())) {
				actualPrice = p;
			}
		}
		
		//then
		assertEquals(expectedPrice.getRoomCategoryId().getId(), actualPrice.getRoomCategoryId().getId());
		assertEquals(expectedPrice.getValidFrom(), actualPrice.getValidFrom());
		assertEquals(expectedPrice.getValidTo(), actualPrice.getValidTo());
		assertEquals(expectedPrice.getCost(), actualPrice.getCost());
	}
	
	@Test
	void when_given_categoryId_return_all_prices() {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusMonths(3);
		BigDecimal cost = new BigDecimal(50.00);
		Price expectedPrice = Price.create(new RoomCategoryId("exp"), cost, fromDate, toDate);
		Price unexpectedPrice = Price.create(new RoomCategoryId("AA"), cost, fromDate, toDate);
		
		//when
		priceRepository.addPrice(expectedPrice);
		priceRepository.addPrice(unexpectedPrice);
		List<Price> actualPrices = priceRepository.getPricesByCategoryId(new RoomCategoryId("exp"));
		
		//then
		assertEquals(1, actualPrices.size());
	}
	
	@Test
	void when_given_categoryId_return_current_price() {
		//given
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now().plusMonths(3);
		BigDecimal cost = new BigDecimal(50.00);
		Price expectedPrice = Price.create(new RoomCategoryId("AA"), cost, fromDate, toDate);
		Price unexpectedPrice = Price.create(new RoomCategoryId("AA"), cost, fromDate.minusMonths(6), fromDate.minusDays(1));
		
		//when
		priceRepository.addPrice(expectedPrice);
		priceRepository.addPrice(unexpectedPrice);
		Optional<Price> actualPrice = priceRepository.getCurrentPriceByCategoryId(new RoomCategoryId("AA"));
		
		//then
		assertEquals(expectedPrice.getRoomCategoryId().getId(), actualPrice.get().getRoomCategoryId().getId());;
		assertEquals(expectedPrice.getValidFrom(), actualPrice.get().getValidFrom());
		assertEquals(expectedPrice.getValidTo(), actualPrice.get().getValidTo());
		assertEquals(expectedPrice.getCost(), actualPrice.get().getCost());
	}
}
