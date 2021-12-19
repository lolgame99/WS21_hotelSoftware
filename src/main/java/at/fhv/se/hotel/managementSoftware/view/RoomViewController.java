package at.fhv.se.hotel.managementSoftware.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;

@Controller
public class RoomViewController {
	private static final String OVERVIEW_ROOM_URL = "/room";
	private static final String ERROR_URL = "/error";
	
	private static final String OVERVIEW_ROOM_VIEW = "roomOverview";

	@Autowired
	private RoomService roomService;
	
	@GetMapping(OVERVIEW_ROOM_URL)
	public String roomOverview(Model model) {
		List<RoomDTO> allRooms = roomService.getAllRoomDTOs();
		model.addAttribute("allRooms",allRooms);
		
		return OVERVIEW_ROOM_VIEW;
	}
}
