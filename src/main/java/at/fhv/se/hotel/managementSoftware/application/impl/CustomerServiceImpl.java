package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
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
			customerDTOs.add(CustomerOverviewDTO.builder()
					.withFirstName(cus.getFirstName())
					.withLastName(cus.getLastName())
					.withMiddleName(cus.getMiddleName())
					.withId(cus.getCustomerId())
					.withBirthdate(cus.getBirthdate())
					.build());
		}
		
		
		return customerDTOs;
	}

	@Override
	public Optional<CustomerOverviewDTO> getCustomerOverviewById(String id) {
		Optional<Customer> customer = customerRepository.getCustomerById(id);
		Optional<CustomerOverviewDTO> customerDTO = Optional.empty();
		
		if (customer.isPresent()) {
			customerDTO = Optional.of(CustomerOverviewDTO.builder()
					.withFirstName(customer.get().getFirstName())
					.withLastName(customer.get().getLastName())
					.withMiddleName(customer.get().getMiddleName())
					.withId(customer.get().getCustomerId())
					.withBirthdate(customer.get().getBirthdate())
					.build());
		}
		
		return customerDTO;
	}

	@Override
	public Optional<CustomerDetailsDTO> getCustomerDetailsById(String id) {
		Optional<Customer> customer = customerRepository.getCustomerById(id);
		Optional<CustomerDetailsDTO> customerDTO = Optional.empty();
		
		if (customer.isPresent()) {
			customerDTO = Optional.of(CustomerDetailsDTO.builder()
					.withFirstName(customer.get().getFirstName())
					.withLastName(customer.get().getLastName())
					.withMiddleName(customer.get().getMiddleName())
					.withId(customer.get().getCustomerId())
					.withBirthdate(customer.get().getBirthdate())
					.withAddress(customer.get().getAddress())
					.withEmail(customer.get().getEmail())
					.withPhoneNumber(customer.get().getPhoneNumber())
					.withGender(customer.get().getGender())
					.withBillingAddress(customer.get().getBillingAddress())
					.build());
		}
		
		return customerDTO;
	}

}
