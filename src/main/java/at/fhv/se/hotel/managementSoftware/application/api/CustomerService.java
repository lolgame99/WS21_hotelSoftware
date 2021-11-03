package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;

public interface CustomerService {
	public List<CustomerOverviewDTO> getAllCustomersOverview();
	public Optional<CustomerOverviewDTO> getCustomerOverviewById(String id);
}
