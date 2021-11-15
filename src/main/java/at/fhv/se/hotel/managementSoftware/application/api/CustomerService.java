package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;

public interface CustomerService {
	public List<CustomerOverviewDTO> getAllCustomersOverview();
	public Optional<CustomerOverviewDTO> getCustomerOverviewById(String id);
	public Optional<CustomerDetailsDTO> getCustomerDetailsById(String id);
}
