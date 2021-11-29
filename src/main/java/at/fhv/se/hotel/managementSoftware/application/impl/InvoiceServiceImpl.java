package at.fhv.se.hotel.managementSoftware.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;

@Component
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceLineService	invoiceLineService;
	
	@Override
	public List<InvoiceDetailsDTO> getAllInvoices() {
		List<Invoice> allInvoices = invoiceRepository.getAllInvoices();
		List<InvoiceDetailsDTO> dtos = new ArrayList<InvoiceDetailsDTO>();
		for (Invoice invoice : allInvoices) {
			CustomerDetailsDTO customer = CustomerDetailsDTO.createFromCustomer(invoice.getCustomer());
			List<InvoiceLineDetailsDTO> lines = invoiceLineService.getAllInvoiceLinesByInvoiceId(invoice.getInvoiceId().getId());
			dtos.add(InvoiceDetailsDTO.createsFromInvoice(invoice, lines, customer));
		}
		return dtos;
	}

	@Override
	public List<InvoiceDetailsDTO> getInvoicesByCustomerId(String id) {
		List<Invoice> allInvoices = invoiceRepository.getInvoicesByCustomerId(new CustomerId(id));
		List<InvoiceDetailsDTO> dtos = new ArrayList<InvoiceDetailsDTO>();
		for (Invoice invoice : allInvoices) {
			CustomerDetailsDTO customer = CustomerDetailsDTO.createFromCustomer(invoice.getCustomer());
			List<InvoiceLineDetailsDTO> lines = invoiceLineService.getAllInvoiceLinesByInvoiceId(invoice.getInvoiceId().getId());
			dtos.add(InvoiceDetailsDTO.createsFromInvoice(invoice, lines, customer));
		}
		return dtos;
	}

	@Override
	public List<InvoiceDetailsDTO> getInvoicesByStayId(String id) {
		List<Invoice> allInvoices = invoiceRepository.getInvoicesByStayId(new StayId(id));
		List<InvoiceDetailsDTO> dtos = new ArrayList<InvoiceDetailsDTO>();
		for (Invoice invoice : allInvoices) {
			CustomerDetailsDTO customer = CustomerDetailsDTO.createFromCustomer(invoice.getCustomer());
			List<InvoiceLineDetailsDTO> lines = invoiceLineService.getAllInvoiceLinesByInvoiceId(invoice.getInvoiceId().getId());
			dtos.add(InvoiceDetailsDTO.createsFromInvoice(invoice, lines, customer));
		}
		return dtos;
	}

	@Override
	public Optional<InvoiceDetailsDTO> getInvoiceByInvoiceId(String id) {
		Optional<Invoice> invoice = invoiceRepository.getInvoiceByInvoiceId(new InvoiceId(id));
		Optional<InvoiceDetailsDTO> dto = Optional.empty();
		if (invoice.isPresent()) {
			CustomerDetailsDTO customer = CustomerDetailsDTO.createFromCustomer(invoice.get().getCustomer());
			List<InvoiceLineDetailsDTO> lines = invoiceLineService.getAllInvoiceLinesByInvoiceId(invoice.get().getInvoiceId().getId());
			dto = Optional.of(InvoiceDetailsDTO.createsFromInvoice(invoice.get(), lines, customer));
		}
		return dto;
	}

}
