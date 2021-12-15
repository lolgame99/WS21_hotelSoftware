package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceService;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.InvoiceCustomer;

@ActiveProfiles("test")
@SpringBootTest
public class InvoiceServiceTest {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@MockBean
	private InvoiceRepository invoiceRepository;
	
	@MockBean
	private InvoiceLineService invoiceLineService;
	
	@Test
	void when_getAll_invoices_return_all() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getAllInvoices()).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
			
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getAllInvoices();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_customerId_return_customer_invoices() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoicesByCustomerId(any(CustomerId.class))).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getInvoicesByCustomerId(customer.getCustomerId().getId());
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_stayId_return_stay_invoices() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoicesByStayId(any(StayId.class))).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getInvoicesByStayId(allInvoices.get(0).getStayId().getId());
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_invoiceId_return_invoice() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		Optional<Invoice> invoice = Optional.of(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoiceByInvoiceId(any(InvoiceId.class))).thenReturn(invoice);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		Optional<InvoiceDetailsDTO> dto = invoiceService.getInvoiceByInvoiceId(invoice.get().getInvoiceId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(2, dto.get().getInvoiceLines().size());
		assertEquals(invoice.get().getCustomer().getCustomerId().getId(), dto.get().getCustomer().getCustomerId().getId());
		assertEquals(invoice.get().getDate(), dto.get().getDate());
		assertEquals(invoice.get().getInvoiceId().getId(), dto.get().getInvoiceId().getId());
		assertEquals(invoice.get().getStayId().getId(), dto.get().getStayId().getId());
		assertEquals(invoice.get().getPaymentType(), dto.get().getPaymentType());
		assertEquals(invoice.get().getSum(), dto.get().getSum());
	}
}
