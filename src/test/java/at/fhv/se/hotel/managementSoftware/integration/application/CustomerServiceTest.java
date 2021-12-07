package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	/*@Test
	void when_getAll_CustomerOverview_returns_all() throws InvalidCustomerException {
		//given
		List<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers.add(Customer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		
		Mockito.when(customerRepository.getAllCustomers()).thenReturn(allCustomers);
		
		//when
		List<CustomerOverviewDTO> dtos = customerService.getAllCustomersOverview();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(allCustomers.get(0).getCustomerId().getId(), dtos.get(0).getCustomerId().getId());
		assertEquals(allCustomers.get(0).getFirstName(), dtos.get(0).getFirstName());
		assertEquals(allCustomers.get(0).getLastName(), dtos.get(0).getLastName());
		assertEquals(allCustomers.get(0).getBirthdate(), dtos.get(0).getBirthdate());
	}*/
	
	@Test
	void when_given_customerId_return_Customer() throws InvalidCustomerException {
		//given;
		Optional<Customer> customer = Optional.of(Customer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		Mockito.when(customerRepository.getCustomerById(any(CustomerId.class))).thenReturn(customer);
		
		List<CustomerDetailsDTO> customerDTOs = new ArrayList<CustomerDetailsDTO>();
		customerDTOs.add(CustomerDetailsDTO.createFromCustomer(Customer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE)));
		//TODO:
		//Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(customerDTOs);
		
		//when
		Optional<CustomerDetailsDTO> dto = customerService.getCustomerDetailsById(customer.get().getCustomerId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(1, customerDTOs.size());
		assertEquals(customer.get().getCustomerId().getId(), customerDTOs.get(0).getCustomerId().getId());
		assertEquals(customer.get().getFirstName(), customerDTOs.get(0).getFirstName());
		assertEquals(customer.get().getLastName(), customerDTOs.get(0).getLastName());
		assertEquals(customer.get().getBirthdate(), customerDTOs.get(0).getBirthdate());
		assertEquals(customer.get().getAddress().getStreetName(), customerDTOs.get(0).getAddress().getStreetName());
		assertEquals(customer.get().getAddress().getStreetNumber(), customerDTOs.get(0).getAddress().getStreetNumber());
		assertEquals(customer.get().getAddress().getPostCode(), customerDTOs.get(0).getAddress().getPostCode());
		assertEquals(customer.get().getAddress().getCity(), customerDTOs.get(0).getAddress().getCity());
		assertEquals(customer.get().getAddress().getCountry(), customerDTOs.get(0).getAddress().getCountry());
		assertEquals(customer.get().getEmail(), customerDTOs.get(0).getEmail());
		assertEquals(customer.get().getPhoneNumber(), customerDTOs.get(0).getPhoneNumber());
		assertEquals(customer.get().getGender(), customerDTOs.get(0).getGender());
	}
	
	
	
}
