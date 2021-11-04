package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;


public interface CustomerRepository {
	public List<Customer> getAllCustomers();
	public Optional<Customer> getCustomerById(String id);
	public void addCustomer(Customer customer);
	public String nextIdentity();
}
