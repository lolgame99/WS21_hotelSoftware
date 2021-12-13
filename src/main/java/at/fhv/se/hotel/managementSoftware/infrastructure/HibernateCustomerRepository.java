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

import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
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
		List<Customer> allCustomers = new ArrayList<Customer>();
		TypedQuery<IndividualCustomer> individualQuery = em.createQuery("SELECT c FROM IndividualCustomer c", IndividualCustomer.class);
		allCustomers.addAll(individualQuery.getResultList());
		TypedQuery<CompanyCustomer> companyQuery = em.createQuery("SELECT c FROM CompanyCustomer c", CompanyCustomer.class);
		allCustomers.addAll(companyQuery.getResultList());
        return allCustomers;
	}

	@Override
	public Optional<Customer> getCustomerById(CustomerId id) {
		TypedQuery<IndividualCustomer> individualQuery = em.createQuery("SELECT c FROM IndividualCustomer c WHERE c.customerId = :id", IndividualCustomer.class)
				.setParameter("id", id);
		List<IndividualCustomer> result = individualQuery.getResultList();
		
		if(result.size() != 1) {
			TypedQuery<CompanyCustomer> companyQuery = em.createQuery("SELECT c FROM CompanyCustomer c WHERE c.customerId = :id", CompanyCustomer.class)
					.setParameter("id", id);
			List<CompanyCustomer> companyResult = companyQuery.getResultList();
			if (companyResult.size() != 1) {
				return Optional.empty();
			}
			return Optional.of(companyResult.get(0));
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
		TypedQuery<IndividualCustomer> individualQuery = em.createQuery("SELECT c FROM IndividualCustomer c WHERE c.customerId = :id", IndividualCustomer.class)
				.setParameter("id", id);
		List<IndividualCustomer> result = individualQuery.getResultList();
		if(result.size() == 1) {
			em.remove(result.get(0));
		}else {
			TypedQuery<CompanyCustomer> companyQuery = em.createQuery("SELECT c FROM CompanyCustomer c WHERE c.customerId = :id", CompanyCustomer.class)
					.setParameter("id", id);
			List<CompanyCustomer> companyResult = companyQuery.getResultList();
			em.remove(companyResult.get(0));
		}
	}
	
	

}
