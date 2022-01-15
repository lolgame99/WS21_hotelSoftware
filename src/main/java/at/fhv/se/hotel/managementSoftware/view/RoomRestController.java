package at.fhv.se.hotel.managementSoftware.view;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;


@RestController
@RequestMapping("api/room")
public class RoomRestController {
	private static final String GETAVAILABLE_URL = "/getAvailableCount";
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomCategoryService roomCategoryService;

	@GetMapping(GETAVAILABLE_URL)
	public HashMap<String, Integer> getAvailableRoomCount(@RequestParam(value = "from", required = true) String from, @RequestParam(value = "to", required = true) String to) {
		List<RoomDTO> freeRooms = roomService.getAllFreeRoomsBetween(to, from);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<RoomCategoryDTO> categories =  roomCategoryService.getAllRoomCategoriesDTO();

		for (RoomCategoryDTO cat : categories) {
			Integer counter = 0;
			for (RoomDTO r : freeRooms) {
				if (r.getRoomCategory().getCategoryId().getId().equals(cat.getCategoryId().getId())) {
					counter++;
				}
			}
			map.put(cat.getName(),counter);
		}
		
		return map;
	}
}
