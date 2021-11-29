package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;

public interface InvoiceRepository {
	public List<Invoice> getAllInvoices();
	public List<Invoice> getInvoicesByCustomerId(CustomerId id);
	public Optional<Invoice> getInvoiceByInvoiceId(InvoiceId id);
	public String nextIdentity();
	public void addInvoice(Invoice invoice);
}
