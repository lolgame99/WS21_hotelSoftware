package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;

@Component
public class HibernateCustomerRepository implements CustomerRepository{
	
	List<Customer> customers = new ArrayList<Customer>();

	@Override
	public List<Customer> getAllCustomers() {
		return customers;
	}

	@Override
	public Optional<Customer> getCustomerById(String id) {
		Optional<Customer> customer = Optional.empty();
		for (Customer cus : customers) {
			if(cus.getCustomerId().equals(id)) {
				customer = Optional.of(cus);
			}
		}
		return customer;
	}

	@Override
	public void addCustomer(Customer customer) {
		customers.add(customer);
		
	}

	@Override
	public String nextIdentity() {
		return UUID.randomUUID().toString().toUpperCase();
	}

}
