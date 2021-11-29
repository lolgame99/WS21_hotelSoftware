package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;

@SpringBootTest
@Transactional
public class InvoiceRepositoryTest {
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Test
	void when_given_invoice_added_return_equal() {
		//given
		Invoice expectedInvoice = Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, new CustomerId("1"));
		
		//when
		invoiceRepository.addInvoice(expectedInvoice);
		Invoice actualInvoice = invoiceRepository.getAllInvoices().get(0);
		
		//then
		assertEquals(expectedInvoice.getInvoiceId().getId(), actualInvoice.getInvoiceId().getId());
		assertEquals(expectedInvoice.getDate(), actualInvoice.getDate());
		assertEquals(expectedInvoice.getPaymentType(), actualInvoice.getPaymentType());
		assertEquals(expectedInvoice.getSum(), actualInvoice.getSum());
		assertEquals(expectedInvoice.getCustomerId().getId(), actualInvoice.getCustomerId().getId());
	}
	
	@Test
	void when_given_customerId_return_equal() {
		//given
		Invoice expectedInvoice = Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, new CustomerId("1"));
		Invoice unexpectedInvoice = Invoice.create(new InvoiceId("2"), LocalDate.now(), new BigDecimal(300.00), PaymentType.CASH, new CustomerId("2"));
		
		//when
		invoiceRepository.addInvoice(expectedInvoice);
		invoiceRepository.addInvoice(unexpectedInvoice);
		List<Invoice> actualInvoices = invoiceRepository.getInvoicesByCustomerId(expectedInvoice.getCustomerId());
		
		//then
		assertEquals(1, actualInvoices.size());
		assertEquals(expectedInvoice.getInvoiceId().getId(), actualInvoices.get(0).getInvoiceId().getId());
		assertEquals(expectedInvoice.getDate(), actualInvoices.get(0).getDate());
		assertEquals(expectedInvoice.getPaymentType(), actualInvoices.get(0).getPaymentType());
		assertEquals(expectedInvoice.getSum(), actualInvoices.get(0).getSum());
		assertEquals(expectedInvoice.getCustomerId().getId(), actualInvoices.get(0).getCustomerId().getId());
	}
	
	
	@Test
	void when_given_invoiceId_return_equal() {
		//given
		Invoice expectedInvoice = Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, new CustomerId("1"));
		Invoice unexpectedInvoice = Invoice.create(new InvoiceId("2"), LocalDate.now(), new BigDecimal(300.00), PaymentType.CASH, new CustomerId("2"));
		
		//when
		invoiceRepository.addInvoice(expectedInvoice);
		invoiceRepository.addInvoice(unexpectedInvoice);
		Optional<Invoice> actualInvoice = invoiceRepository.getInvoiceByInvoiceId(expectedInvoice.getInvoiceId());
		
		//then
		assertTrue(actualInvoice.isPresent());
		assertEquals(expectedInvoice.getInvoiceId().getId(), actualInvoice.get().getInvoiceId().getId());
		assertEquals(expectedInvoice.getDate(), actualInvoice.get().getDate());
		assertEquals(expectedInvoice.getPaymentType(), actualInvoice.get().getPaymentType());
		assertEquals(expectedInvoice.getSum(), actualInvoice.get().getSum());
		assertEquals(expectedInvoice.getCustomerId().getId(), actualInvoice.get().getCustomerId().getId());
	}
	
	@Test
	void when_given_invalidInvoiceId_return_empty() {
		//given
		Invoice unexpectedInvoice = Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(300.00), PaymentType.CASH, new CustomerId("2"));
		InvoiceId invalidId = new InvoiceId("invalid");
		
		//when
		invoiceRepository.addInvoice(unexpectedInvoice);
		Optional<Invoice> actualInvoice = invoiceRepository.getInvoiceByInvoiceId(invalidId);
		
		//then
		assertTrue(actualInvoice.isEmpty());
	}
	
	@Test
	void when_nextIdentity_return_expected() {
		//given
		String[] expectedIds = {"21"+LocalDate.now().getMonthValue()+LocalDate.now().getDayOfMonth()+"01","21"+LocalDate.now().getMonthValue()+LocalDate.now().getDayOfMonth()+"02"};
		
		//when
		String[] actualIds = new String[2];
		actualIds[0] = invoiceRepository.nextIdentity();
		actualIds[1] = invoiceRepository.nextIdentity();
		
		//then
		assertArrayEquals(expectedIds, actualIds);
	}
}
