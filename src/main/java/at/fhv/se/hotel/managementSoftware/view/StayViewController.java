package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

@Controller
public class StayViewController {
	private static final String OVERVIEW_STAY_URL = "/stay";
	private static final String CREATE_STAY_URL = "/stay/create";
	private static final String ERROR_URL = "/error";
	
	private static final String OVERVIEW_STAY_VIEW ="stayOverview";
	private static final String CREATE_STAY_VIEW ="createStay";
	
	@Autowired
	private StayService stayService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@GetMapping(OVERVIEW_STAY_URL)
    public String currentStays(@RequestParam(value = "date", required = false) String date, Model model) {		
		List<StayDetailsDTO> stayOverviews = new ArrayList<>();
		if(date != null) {
			stayOverviews = stayService.getCurrentStays(dateStringConverter(date));
		}else {
			stayOverviews = stayService.getCurrentStays(LocalDate.now());
		}
		
        model.addAttribute("stays", stayOverviews);
        return OVERVIEW_STAY_VIEW;
    }
	
	@GetMapping(CREATE_STAY_URL)
	public String createStay(@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "bookingId", required = false) String bookingId, Model model) {
		final StayData form = new StayData();
		
		if(customerId != null) {
			Optional<CustomerDetailsDTO> existingCustomer = customerService.getCustomerDetailsById(customerId);
			if (existingCustomer.isPresent()) {
				form.addExistingCustomer(existingCustomer.get());
			}
		}
		
		if(bookingId != null) {
			Optional<BookingDetailsDTO> existingBooking = bookingService.getBookingDetailsById(bookingId);
			if (existingBooking.isPresent()) {
				form.addExistingBooking(existingBooking.get());
			}
		}
		model.addAttribute("form", form);
		
		List<RoomCategoryDTO> roomCategories = roomCategoryService.getAllRoomCategoriesDTO();
		model.addAttribute("roomCategories", roomCategories);
		
		List<CustomerOverviewDTO> customers = customerService.getAllCustomersOverview();
		model.addAttribute("customers", customers);
		
		
		
		return CREATE_STAY_VIEW;
	}
	
	
	@PostMapping(CREATE_STAY_URL)
	public ModelAndView createBookingPost(@ModelAttribute StayData form, Model model, HttpServletRequest request) {
		try {
			stayService.addStayFromData(form, dateStringConverter(form.getCheckInDate()), dateStringConverter(form.getCheckOutDate()), dateStringConverter(form.getBirthdate()));
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("forward:"+ERROR_URL);
		}
		return new ModelAndView("redirect:" + OVERVIEW_STAY_VIEW);
	}
	
	/* Splits Date String into Array for further processing
	 * splitArray[0] = year
	 * splitArray[1] = month
	 * splitArray[2] = day
	 */
	private LocalDate dateStringConverter(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		if (date != null) {
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
