package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

@SpringBootTest
@Transactional
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void when_given_customer_is_added_return_equal() throws InvalidCustomerException {
		//given
		IndividualCustomer expectedCustomer = IndividualCustomer.create(new CustomerId("C2"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
		
		//when
		customerRepository.addCustomer(expectedCustomer);
		IndividualCustomer actualCustomer = (IndividualCustomer) customerRepository.getCustomerById(expectedCustomer.getCustomerId()).get();
		
		//then
		assertEquals(expectedCustomer.getCustomerId().getId(), actualCustomer.getCustomerId().getId());
		assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
		assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
		assertEquals(expectedCustomer.getBirthdate(), actualCustomer.getBirthdate());
		assertEquals(expectedCustomer.getAddress().getStreetName(), actualCustomer.getAddress().getStreetName());
		assertEquals(expectedCustomer.getAddress().getStreetNumber(), actualCustomer.getAddress().getStreetNumber());
		assertEquals(expectedCustomer.getAddress().getCity(), actualCustomer.getAddress().getCity());
		assertEquals(expectedCustomer.getAddress().getPostCode(), actualCustomer.getAddress().getPostCode());
		assertEquals(expectedCustomer.getAddress().getCountry(), actualCustomer.getAddress().getCountry());
		assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
		assertEquals(expectedCustomer.getPhoneNumber(), actualCustomer.getPhoneNumber());
		assertEquals(expectedCustomer.getGender(), actualCustomer.getGender());
		
	}
	
	@Test
	void when_given_invalid_customerId_return_empty() {
		//given
		CustomerId invalCustomerId = new CustomerId("007");
		
		//when
		Optional<Customer> actualCustomer = customerRepository.getCustomerById(invalCustomerId);
		
		//then
		assertTrue(actualCustomer.isEmpty());
	}
	
	 @Test
	 void delete_customer_does_not_return_anything() throws InvalidCustomerException {
		// given
	    IndividualCustomer customerToDelete = IndividualCustomer.create(new CustomerId("C2"), "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
	        
	    //when
	    customerRepository.addCustomer(customerToDelete);
	    customerRepository.deleteCustomerById(customerToDelete.getCustomerId());
	    List<Customer> allCustomers  = customerRepository.getAllCustomers();
	    boolean isDeleted = true;
	    
	    for (int i = 0; i < allCustomers.size(); i++) {
			if (allCustomers.get(i).getCustomerId() == customerToDelete.getCustomerId()) {
				isDeleted = false;
			}
		}
	    
	    //then
	    assertTrue(isDeleted);
	      
	        
	  }   
	
}
