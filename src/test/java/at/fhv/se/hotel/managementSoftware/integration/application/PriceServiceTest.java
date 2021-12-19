package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.PriceService;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.PriceRepository;

@ActiveProfiles("test")
@SpringBootTest
public class PriceServiceTest {

	@Autowired
	private PriceService priceService;
	
	@MockBean
	private PriceRepository priceRepository;
	
	@Test
	void when_getAll_prices_returns_all() {
		//given
		List<Price> allPrices = new ArrayList<Price>();
		allPrices.add(Price.create(new RoomCategoryId("1"), new BigDecimal(150.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		allPrices.add(Price.create(new RoomCategoryId("2"), new BigDecimal(250.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		Mockito.when(priceRepository.getAllPrices()).thenReturn(allPrices);
		
		//when
		List<PriceDetailsDTO> dtos = priceService.getAllPrices();
		
		//then
		assertEquals(2, dtos.size());
	}
	
	@Test
	void when_given_categoryId_returns_only_categoryId_prices() {
		//given
		List<Price> allCategoryPrices = new ArrayList<Price>();
		allCategoryPrices.add(Price.create(new RoomCategoryId("1"), new BigDecimal(250.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		allCategoryPrices.add(Price.create(new RoomCategoryId("1"), new BigDecimal(150.00), LocalDate.now().minusMonths(6), LocalDate.now().minusDays(1)));
		Mockito.when(priceRepository.getPricesByCategoryId(any(RoomCategoryId.class))).thenReturn(allCategoryPrices);
		
		//when
		List<PriceDetailsDTO> dtos = priceService.getPricesByCategoryId("1");
		
		//then
		assertEquals(2, dtos.size());
	}
	
	@Test
	void when_given_categoryId_returns_current_categoryId_prices() {
		//given
		Optional<Price> currentCategoryPrice = Optional.of(Price.create(new RoomCategoryId("1"), new BigDecimal(250.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		Mockito.when(priceRepository.getCurrentPriceByCategoryId(any(RoomCategoryId.class))).thenReturn(currentCategoryPrice);
		
		//when
		Optional<PriceDetailsDTO> dto = priceService.getCurrentPriceByCategoryId("1");
		
		//then
		assertTrue(dto.isPresent());
	}
	
	@Test
	void when_given_invalid_categoryId_return_empty() {
		//given
		Mockito.when(priceRepository.getCurrentPriceByCategoryId(any(RoomCategoryId.class))).thenReturn(Optional.empty());
		
		//when
		Optional<PriceDetailsDTO> dto = priceService.getCurrentPriceByCategoryId("2");
		
		//then
		assertTrue(dto.isEmpty());
	}
}
