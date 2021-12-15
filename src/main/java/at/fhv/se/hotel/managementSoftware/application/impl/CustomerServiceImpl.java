package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<CustomerOverviewDTO> getAllCustomersOverview() {
		List<CustomerOverviewDTO> customerDTOs = new ArrayList<CustomerOverviewDTO>();
		List<Customer> customer = customerRepository.getAllCustomers();
		
		for (Customer cus : customer) {
			customerDTOs.add(CustomerOverviewDTO.createFromCustomer(cus));
		}
		
		
		return customerDTOs;
	}

	@Override
	public Optional<CustomerOverviewDTO> getCustomerOverviewById(String id) {
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(id));
		Optional<CustomerOverviewDTO> customerDTO = Optional.empty();
		
		if (customer.isPresent()) {
			customerDTO = Optional.of(CustomerOverviewDTO.createFromCustomer(customer.get()));
		}
		
		return customerDTO;
	}

	@Override
	public Optional<CustomerDetailsDTO> getCustomerDetailsById(String id) {
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(id));
		Optional<CustomerDetailsDTO> customerDTO = Optional.empty();
		
		if (customer.isPresent()) {
			customerDTO = Optional.of(CustomerDetailsDTO.createFromCustomer(customer.get()));
		}
		
		return customerDTO;
	}

}
