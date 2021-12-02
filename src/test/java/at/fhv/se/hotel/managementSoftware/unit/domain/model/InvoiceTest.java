package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class InvoiceTest {
	@Test
	void when_create_from_Invoice () throws InvalidCustomerException {
	InvoiceId invoiceId = new InvoiceId("123");
	LocalDate date = LocalDate.now();
	BigDecimal sum = new BigDecimal(4444);
	PaymentType paymentType = PaymentType.CASH;
	Customer customer = Customer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		
	Invoice invoice = Invoice.create(invoiceId, date, sum, paymentType, customer, new StayId("1"));
	
	assertEquals(invoiceId, invoice.getInvoiceId());
	assertEquals(date, invoice.getDate());
	assertEquals(sum, invoice.getSum());
	assertEquals(paymentType, invoice.getPaymentType());
	assertEquals(customer.getCustomerId().getId(), invoice.getCustomer().getCustomerId().getId());
	
	
	
	}
	
	
	

}
