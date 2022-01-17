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
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;


@RestController
@RequestMapping("api/room")
public class RoomRestController {
	private static final String GETAVAILABLE_URL = "/getAvailableCount";
	private static final String SETROOMSTATUS_URL ="/setStatus";
	
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
	
	@PostMapping(SETROOMSTATUS_URL)
	public Map<String,String> setRoomStatus(@RequestParam(value = "roomId", required = true) String roomId, @RequestParam(value = "status", required = true) String status) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			roomService.changeRoomStatus(roomId, status);
			map.put("status", "ok");
			map.put("message", "Room status changed successfully!");
		} catch (Exception e) {
			map.put("status", "error");
			map.put("message", e.getMessage());
		}
		return map;
	}
}
