package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.view.forms.InvoiceData;

public interface InvoiceService {
	public List<InvoiceDetailsDTO> getAllInvoices();
	public List<InvoiceDetailsDTO> getInvoicesByCustomerId(String id);
	public List<InvoiceDetailsDTO> getInvoicesByStayId(String id);
	public Optional<InvoiceDetailsDTO> getInvoiceByInvoiceId(String id);
	public void addInvoiceFromData(InvoiceData data);
}
