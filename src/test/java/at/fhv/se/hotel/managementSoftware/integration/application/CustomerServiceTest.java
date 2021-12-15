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
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	void when_getAll_CustomerOverview_returns_all() throws InvalidCustomerException {
		//given
		List<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers.add(IndividualCustomer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		
		Mockito.when(customerRepository.getAllCustomers()).thenReturn(allCustomers);
		IndividualCustomer convertedCustomer = (IndividualCustomer) allCustomers.get(0);
		
		//when
		List<CustomerOverviewDTO> dtos = customerService.getAllCustomersOverview();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(convertedCustomer.getCustomerId().getId(), dtos.get(0).getCustomerId().getId());
		assertEquals(convertedCustomer.getFirstName(), dtos.get(0).getFirstName());
		assertEquals(convertedCustomer.getLastName(), dtos.get(0).getLastName());
		assertEquals(convertedCustomer.getBirthdate(), dtos.get(0).getBirthdate());
	}
	
	@Test
	void when_given_customerId_return_CustomerOverview() throws InvalidCustomerException {
		//given;
		Optional<Customer> customer = Optional.of(IndividualCustomer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		Mockito.when(customerRepository.getCustomerById(any(CustomerId.class))).thenReturn(customer);
		IndividualCustomer convertedCustomer = (IndividualCustomer) customer.get();
		
		//when
		Optional<CustomerOverviewDTO> dto = customerService.getCustomerOverviewById(customer.get().getCustomerId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(convertedCustomer.getCustomerId().getId(), dto.get().getCustomerId().getId());
		assertEquals(convertedCustomer.getFirstName(), dto.get().getFirstName());
		assertEquals(convertedCustomer.getLastName(), dto.get().getLastName());
		assertEquals(convertedCustomer.getBirthdate(), dto.get().getBirthdate());
	}
	
	@Test
	void when_given_customerId_return_CustomerDetails() throws InvalidCustomerException {
		//given;
		Optional<Customer> customer = Optional.of(IndividualCustomer.create(new CustomerId("C1"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		Mockito.when(customerRepository.getCustomerById(any(CustomerId.class))).thenReturn(customer);
		IndividualCustomer convertedCustomer = (IndividualCustomer) customer.get();
		
		//when
		Optional<CustomerDetailsDTO> dto = customerService.getCustomerDetailsById(customer.get().getCustomerId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(convertedCustomer.getCustomerId().getId(), dto.get().getCustomerId().getId());
		assertEquals(convertedCustomer.getFirstName(), dto.get().getFirstName());
		assertEquals(convertedCustomer.getLastName(), dto.get().getLastName());
		assertEquals(convertedCustomer.getBirthdate(), dto.get().getBirthdate());
		assertEquals(convertedCustomer.getAddress().getStreetName(), dto.get().getAddress().getStreetName());
		assertEquals(convertedCustomer.getAddress().getStreetNumber(), dto.get().getAddress().getStreetNumber());
		assertEquals(convertedCustomer.getAddress().getPostCode(), dto.get().getAddress().getPostCode());
		assertEquals(convertedCustomer.getAddress().getCity(), dto.get().getAddress().getCity());
		assertEquals(convertedCustomer.getAddress().getCountry(), dto.get().getAddress().getCountry());
		assertEquals(convertedCustomer.getEmail(), dto.get().getEmail());
		assertEquals(convertedCustomer.getPhoneNumber(), dto.get().getPhoneNumber());
		assertEquals(convertedCustomer.getGender(), dto.get().getGender());
	}
	
	
	
}
