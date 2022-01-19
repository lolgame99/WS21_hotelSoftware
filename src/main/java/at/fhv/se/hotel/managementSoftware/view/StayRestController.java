package at.fhv.se.hotel.managementSoftware.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;

@RestController
@RequestMapping("api/stay")
public class StayRestController {

	private static final String CHECKOUT_URL = "/checkout";
	
	@Autowired
	private StayService stayService;

	@PostMapping(CHECKOUT_URL)
	public Map<String,String> checkOutStay(@RequestParam(value = "stayId", required = true) String stayId) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			stayService.checkoutStay(stayId);
			map.put("status", "ok");
			map.put("message", "Stay checked out successfully!");
		} catch (InvalidStayException e) {
			map.put("status", "error");
			map.put("message", e.getMessage());
		}
		return map;
	}
}