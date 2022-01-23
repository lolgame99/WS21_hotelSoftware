package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

@RestController
@RequestMapping("api/booking")
public class BookingRestController {
	private static final String CREATE_BOOKING_URL = "/create";
	
	@Autowired
	private BookingService bookingService;
	
	@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8080"})
	@PostMapping(CREATE_BOOKING_URL)
	public Map<String,String> createBooking(@RequestBody BookingData form) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			bookingService.addBookingFromData(form, dateStringConverter(form.getCheckInDate()), dateStringConverter(form.getCheckOutDate()), dateStringConverter(form.getBirthdate()));
			map.put("status", "ok");
			map.put("message", "Booking created successfully!");
		} catch (Exception e) {
			map.put("status", "error");
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	/* Splits Date String into Array for further processing
	 * splitArray[0] = year
	 * splitArray[1] = month
	 * splitArray[2] = day
	 */
	private LocalDate dateStringConverter(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		if (date != null && date != "") {
			splitStringArray = date.split("-");
			for (int i = 0; i < splitStringArray.length; i++) {
				splitIntArray[i] = Integer.parseInt(splitStringArray[i]);
			}
			return LocalDate.of(splitIntArray[0], splitIntArray[1], splitIntArray[2]);
		}else {
			return null;
		}

	}
}
