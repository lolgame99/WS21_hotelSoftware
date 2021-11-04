package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
import java.util.List;

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
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

@Controller
public class BookingViewController {
	
	
	private static final String CREATE_BOOKING_URL = "/booking";
	private static final String DASHBOARD_URL ="/";
	private static final String ERROR_URL = "/error";
	
	private static final String DASHBOARD_VIEW ="dashboard";
	private static final String CREATE_BOOKING_VIEW ="createBooking";
	

	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@GetMapping(DASHBOARD_URL)
    public String customer(Model model) {
        
        // TODO: redirect to the error page in case of an error situation - use redirectError("SOME MESSAGE"); for that
        List<BookingOverviewDTO> bookingOverviews = bookingService.getBookingsByDate(LocalDate.now());
        model.addAttribute("bookings", bookingOverviews);
        return DASHBOARD_VIEW;
    }
	
	@GetMapping(CREATE_BOOKING_URL)
	public String createBooking( @RequestParam(value = "customerId", required = false) String customerId, Model model) {
		List<RoomCategoryDTO> roomCategories = roomCategoryService.getAllRoomCategoriesDTO();
		model.addAttribute("roomCategories", roomCategories);
		
		List<CustomerOverviewDTO> customers = customerService.getAllCustomersOverview();
		model.addAttribute("customers", customers);
		
		final BookingData form = new BookingData();
		model.addAttribute("form", form);
		
		return CREATE_BOOKING_VIEW;
	}
	
	
	@PostMapping(CREATE_BOOKING_URL)
	  public ModelAndView createBookingPost(@ModelAttribute BookingData form, Model model) {
		try {
			bookingService.addBookingFromData(form);
		} catch (InvalidBookingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return new ModelAndView("redirect:" + DASHBOARD_URL);
	  }
	  
}
