package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
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
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

@Controller
public class BookingViewController {
	
	
	private static final String CREATE_BOOKING_URL = "/booking";
	private static final String DASHBOARD_URL ="/";
	private static final String ERROR_URL = "/error";
	
	private static final String DASHBOARD_VIEW ="dashboard";
	private static final String CREATE_BOOKING_VIEW ="createBooking";
	private static final String ERROR_VIEW ="error";
	

	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@GetMapping(DASHBOARD_URL)
    public String customer(Model model) {
        
        List<BookingOverviewDTO> bookingOverviews = bookingService.getBookingsByDate(LocalDate.now());
        model.addAttribute("bookings", bookingOverviews);
        return DASHBOARD_VIEW;
    }
	
	@GetMapping(CREATE_BOOKING_URL)
	public String createBooking(@RequestParam(value = "customerId", required = false) String customerId, Model model) {
		
		if(!customerId.isEmpty()) {
			Optional<CustomerDetailsDTO> customerDetailsDTO = customerService.getCustomerDetailsById(customerId);
			if (customerDetailsDTO.isPresent()) {
				model.addAttribute("existingCustomer", customerDetailsDTO);
			}
		}
		
		List<RoomCategoryDTO> roomCategories = roomCategoryService.getAllRoomCategoriesDTO();
		model.addAttribute("roomCategories", roomCategories);
		
		List<CustomerOverviewDTO> customers = customerService.getAllCustomersOverview();
		model.addAttribute("customers", customers);
		
		final BookingData form = new BookingData();
		model.addAttribute("form", form);
		
		return CREATE_BOOKING_VIEW;
	}
	
	
	@PostMapping(CREATE_BOOKING_URL)
	public ModelAndView createBookingPost(@ModelAttribute BookingData form, Model model, HttpServletRequest request) {
		try {
			bookingService.addBookingFromData(form);
		} catch (InvalidBookingException e) {
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("forward:"+ERROR_URL);
		}
		return new ModelAndView("forward:" + DASHBOARD_URL);
	}
	
	@GetMapping(ERROR_URL)
    public String displayError(HttpServletRequest request, Model model) {
		String msg = (String) request.getAttribute("msg");
		model.addAttribute("msg", msg);
        return ERROR_VIEW;
    }
	  
}
