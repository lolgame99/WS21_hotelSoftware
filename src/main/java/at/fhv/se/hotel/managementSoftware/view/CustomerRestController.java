package at.fhv.se.hotel.managementSoftware.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomCategoryService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;

@RestController
@RequestMapping("api/customer")
public class CustomerRestController {
	private static final String GETALL_URL = "/getAll";
	private static final String GETDETAILS_URL = "/getDetails";
	
	@Autowired
	private CustomerService customerService;

	@GetMapping(GETALL_URL)
	public List<CustomerOverviewDTO> getAllIndividualCustomers() {
		List<CustomerOverviewDTO> customers = customerService.getAllCustomersOverview();
		List<CustomerOverviewDTO> result = new ArrayList<CustomerOverviewDTO>();
		for (CustomerOverviewDTO c : customers) {
			if (c.getFirstName() != null) {
				result.add(c);
			}
		}
		
		return result;
	}
	
	@GetMapping(GETDETAILS_URL)
	public Optional<CustomerDetailsDTO> getCustomerDetails(@RequestParam(value = "customerId", required = true) String customerId) {		
		return customerService.getCustomerDetailsById(customerId);
	}
}
