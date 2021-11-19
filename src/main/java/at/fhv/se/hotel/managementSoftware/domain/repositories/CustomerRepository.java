package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;


public interface CustomerRepository {
	public List<Customer> getAllCustomers();
	public Optional<Customer> getCustomerById(CustomerId id);
	public void addCustomer(Customer customer);
	public CustomerId nextIdentity();
	public void deleteCustomerById(CustomerId id);
}
