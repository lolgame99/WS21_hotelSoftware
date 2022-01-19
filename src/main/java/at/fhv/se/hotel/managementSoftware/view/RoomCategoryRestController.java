package at.fhv.se.hotel.managementSoftware.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;

@RestController
@RequestMapping("api/category")
public class RoomCategoryRestController {
	private static final String GETALL_URL = "/getAll";
	
	@Autowired
	private RoomCategoryService roomCategoryService;

	@GetMapping(GETALL_URL)
	public List<RoomCategoryDTO> getAllCategoriesDTO() {
		return roomCategoryService.getAllRoomCategoriesDTO();
	}
	

}
