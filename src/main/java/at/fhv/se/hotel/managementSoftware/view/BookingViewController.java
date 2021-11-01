package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;

public class BookingViewController {
	
	
	private static final String POST_BOOKING_URL = "/booking";
	private static final String DAILY_CHECKINS_URL ="/booking";
	private static final String ERROR_URL = "/error";
	
	//TODO: change name of daily checkin view
	private static final String DAILY_CHECKINS_VIEW ="dailyBookings";
	

	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(DAILY_CHECKINS_URL)
    public ModelAndView customer(Model model) {
        
        // TODO: redirect to the error page in case of an error situation - use redirectError("SOME MESSAGE"); for that
        List<BookingOverviewDTO> bookingOverviews = bookingService.getBookingsByDate(LocalDate.now());
        model.addAttribute("bookings", bookingOverviews);
        return new ModelAndView(DAILY_CHECKINS_VIEW);
    }
	
	@PostMapping(POST_BOOKING_URL)
	  public ModelAndView greetingSubmit(@ModelAttribute Booking booking, @ModelAttribute Customer customer,Model model) {
		bookingService.addBooking(booking);
	    return new ModelAndView("redirect:" + DAILY_CHECKINS_URL);
	  }
}
