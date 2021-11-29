package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceLineRepository;

@Component
public class HibernateInvoiceLineRepository implements InvoiceLineRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<InvoiceLine> getAllInvoiceLines() {
		TypedQuery<InvoiceLine> query = em.createQuery("SELECT il FROM InvoiceLine il", InvoiceLine.class);
		return query.getResultList();
	}

	@Override
	public List<InvoiceLine> getAllInvoiceLinesByInvoiceId(InvoiceId id) {
		TypedQuery<InvoiceLine> query = em.createQuery("SELECT il FROM InvoiceLine il WHERE il.invoiceId = :id", InvoiceLine.class)
				.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void addLine(InvoiceLine line) {
		em.merge(line);
	}

}
