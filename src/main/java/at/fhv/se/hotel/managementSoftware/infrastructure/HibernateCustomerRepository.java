package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;

@Component
@Transactional
public class HibernateCustomerRepository implements CustomerRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
	}

	@Override
	public Optional<Customer> getCustomerById(CustomerId id) {
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.customerId = :id", Customer.class)
				.setParameter("id", id);
		List<Customer> result = query.getResultList();
		if(result.size() != 1) {
			return Optional.empty();
		}
        return Optional.of(result.get(0));
	}

	@Override
	public void addCustomer(Customer customer) {
		em.merge(customer);
		
	}

	@Override
	public CustomerId nextIdentity() {
		return new CustomerId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public void deleteCustomerById(CustomerId id) {
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.customerId = :id", Customer.class)
				.setParameter("id", id);
		List<Customer> result = query.getResultList();
		if(result.size() == 1) {
			em.remove(result.get(0));
		}
	}

}
