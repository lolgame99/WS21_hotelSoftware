package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;

public interface InvoiceLineService {
	public List<InvoiceLineDetailsDTO> getAllInvoiceLines();
	public List<InvoiceLineDetailsDTO> getAllInvoiceLinesByInvoiceId(String id);
}
