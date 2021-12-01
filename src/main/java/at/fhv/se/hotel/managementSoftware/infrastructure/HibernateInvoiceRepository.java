package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;

@Transactional
@Component
public class HibernateInvoiceRepository implements InvoiceRepository{

	@PersistenceContext
	private EntityManager em;
	
	private int counter = 1;
	private LocalDate lastInvoiceDate = LocalDate.now();
	
	@Override
	public List<Invoice> getAllInvoices() {
		TypedQuery<Invoice> query = em.createQuery("SELECT i FROM Invoice i", Invoice.class);
		return query.getResultList();
	}

	@Override
	public List<Invoice> getInvoicesByCustomerId(CustomerId id) {
		TypedQuery<Invoice> query = em.createQuery("SELECT i FROM Invoice i WHERE i.customer.customerId = :id", Invoice.class)
				.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Optional<Invoice> getInvoiceByInvoiceId(InvoiceId id) {
		TypedQuery<Invoice> query = em.createQuery("SELECT i FROM Invoice i WHERE i.invoiceId = :id", Invoice.class)
				.setParameter("id", id);
		List<Invoice> result = query.getResultList();
		if (result.size() != 1) {
			return Optional.empty();
		}
		return Optional.of(result.get(0));
	}

	@Override
	public String nextIdentity() {
		if (!lastInvoiceDate.equals(LocalDate.now())) {
			lastInvoiceDate = LocalDate.now();
			counter = 1;
		}
		String result = lastInvoiceDate.format(DateTimeFormatter.ofPattern("yyMMdd"));
		if (counter < 10) {
			result+= "0" + counter;
		}else {
			result+= counter;
		}
		counter++;
		return result;
	}

	@Override
	public void addInvoice(Invoice invoice) {
		em.merge(invoice);	
	}

	@Override
	public List<Invoice> getInvoicesByStayId(StayId id) {
		TypedQuery<Invoice> query = em.createQuery("SELECT i FROM Invoice i WHERE i.stayId = :id", Invoice.class)
				.setParameter("id", id);
		return query.getResultList();
	}

}
