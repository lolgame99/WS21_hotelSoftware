package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceLineRepository;

@Component
public class InvoiceLineServiceImpl implements InvoiceLineService{
	
	@Autowired
	private InvoiceLineRepository invoiceLineRepository;
	
	@Override
	public List<InvoiceLineDetailsDTO> getAllInvoiceLines() {
		List<InvoiceLine> allLines = invoiceLineRepository.getAllInvoiceLines();
		List<InvoiceLineDetailsDTO> dtos = new ArrayList<InvoiceLineDetailsDTO>();
		for (InvoiceLine line : allLines) {
			dtos.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(line));
		}
		return dtos;
	}

	@Override
	public List<InvoiceLineDetailsDTO> getAllInvoiceLinesByInvoiceId(String id) {
		List<InvoiceLine> allLines = invoiceLineRepository.getAllInvoiceLinesByInvoiceId(new InvoiceId(id));
		List<InvoiceLineDetailsDTO> dtos = new ArrayList<InvoiceLineDetailsDTO>();
		for (InvoiceLine line : allLines) {
			dtos.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(line));
		}
		return dtos;
	}

}
