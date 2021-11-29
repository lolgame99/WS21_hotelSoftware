package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;

public class InvoiceTest {
	@Test
	void when_create_from_Invoice () {
	InvoiceId invoiceId = new InvoiceId("123");
	LocalDate date = LocalDate.now();
	BigDecimal sum = new BigDecimal(4444);
	PaymentType paymentType = PaymentType.CASH;
	CustomerId customerId = new CustomerId("1");
		
	Invoice invoice = Invoice.create(invoiceId, date, sum, paymentType, customerId);
	
	assertEquals(invoiceId, invoice.getInvoiceId());
	assertEquals(date, invoice.getDate());
	assertEquals(sum, invoice.getSum());
	assertEquals(paymentType, invoice.getPaymentType());
	assertEquals(customerId.getId(), invoice.getCustomerId().getId());
	
	
	
	}
	
	
	

}
