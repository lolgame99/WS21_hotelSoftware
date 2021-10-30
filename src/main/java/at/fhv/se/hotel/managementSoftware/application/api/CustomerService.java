package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;

public interface CustomerService {
	public List<CustomerOverviewDTO> getAllCustomersOverview();
	public CustomerOverviewDTO getCustomerOverviewById(String id);
}
