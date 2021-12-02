package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;

public interface InvoiceLineRepository {
	public List<InvoiceLine> getAllInvoiceLines();
	public List<InvoiceLine> getAllInvoiceLinesByInvoiceId(InvoiceId id);
	public void addLine(InvoiceLine line);
}
