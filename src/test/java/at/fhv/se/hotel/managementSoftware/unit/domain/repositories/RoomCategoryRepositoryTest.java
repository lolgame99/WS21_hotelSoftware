package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.infrastructure.HibernateRoomCategoryRepository;

@SpringBootTest
@Transactional
public class RoomCategoryRepositoryTest {
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Test
	void add_roomCategory_without_description__equals_return_roomCategory() {
		//given
		RoomCategory categoryExpected = RoomCategory.createWithoutDescription(new RoomCategoryId("1"), "Testcategory", 1);
		
		//when
		roomCategoryRepository.addRoomCategory(categoryExpected);
		Optional<RoomCategory> categoryActual = roomCategoryRepository.getRoomCategoryById(categoryExpected.getCategoryId());
		
		assertEquals(categoryExpected.getCategoryId().getId(), categoryActual.get().getCategoryId().getId());
		assertEquals(categoryExpected.getCategoryName(), categoryActual.get().getCategoryName());
		assertEquals(categoryExpected.getCategoryDescription(), categoryActual.get().getCategoryDescription());
		assertEquals(categoryExpected.getBedNumber(), categoryActual.get().getBedNumber());
	}
}