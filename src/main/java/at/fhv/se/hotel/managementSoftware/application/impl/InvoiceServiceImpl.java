package at.fhv.se.hotel.managementSoftware.application.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceService;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidInvoiceException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceLineRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.InvoiceCustomer;
import at.fhv.se.hotel.managementSoftware.view.forms.InvoiceData;

@Component
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private InvoiceLineRepository invoiceLineRepository;
	
	@Autowired
	private InvoiceLineService	invoiceLineService;
	
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
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

	@Override
	public InvoiceId addInvoiceFromData(InvoiceData data) throws InvalidInvoiceException {
		if (data.getAssignmentIds().size() == 0) {
			throw new InvalidInvoiceException("Invoice could not be created <br> Atleast one position has to be selected");
		}
		InvoiceId invoiceId = new InvoiceId(invoiceRepository.nextIdentity());
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < data.getNames().size(); i++) {
			int count = Collections.frequency(data.getDescriptions(), data.getDescriptions().get(i));
			String description = data.getDescriptions().get(i);
			BigDecimal price = data.getPrices().get(i);
			String name = data.getNames().get(i);
			int index = i;
			
			int[] indexes = IntStream.range(0, data.getDescriptions().size())
				             .filter(j -> data.getDescriptions().get(j).equals(description))
				             .toArray();
			indexes[0] = -1;
			for (int j = 1; j < indexes.length; j++) {
				name += " & ";
				name += data.getNames().get(indexes[j]).split(" ")[1];
			}
			Boolean isDouble = IntStream.of(indexes).anyMatch(x -> x == index);
			if (!isDouble) {
				InvoiceLine line = InvoiceLine.create(invoiceId, count, name, description, price.multiply(BigDecimal.valueOf(count)));
				sum = sum.add(line.getPrice());
				invoiceLineRepository.addLine(line);
			}
			
		}
		for (int i = 0; i < data.getAssignmentIds().size(); i++) {
			Optional<RoomAssignment> ra = roomAssignmentRepository.getRoomAssignmentsById(new RoomAssignmentId(data.getAssignmentIds().get(i)));
			ra.get().paid();
		}
		
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(data.getCustomerId()));
		Invoice invoice = Invoice.create(invoiceId, LocalDate.now(), sum, data.getPaymentType(), new InvoiceCustomer(customer.get()), new StayId(data.getStayId()));
		invoiceRepository.addInvoice(invoice);
		return invoiceId;
	}

}
