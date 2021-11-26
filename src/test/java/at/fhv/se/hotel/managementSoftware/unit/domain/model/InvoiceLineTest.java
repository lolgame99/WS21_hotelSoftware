package at.fhv.se.hotel.managementSoftware.unit.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;

public class InvoiceLineTest {
	@Test
	void when_create_from_InvoiceLine() {
		InvoiceId invoiceId = new InvoiceId("3838");
		int count = 4;
		String description = "blabla";
		String name = "Batuhan Akkus";
		BigDecimal price = new BigDecimal(428);
		
	InvoiceLine invoiceLine = InvoiceLine.create(invoiceId, count, description, name, price);
	
	assertEquals(invoiceId, invoiceLine.getInvoiceId());
	assertEquals(count, invoiceLine.getCount());
	assertEquals(description, invoiceLine.getDescription());
	assertEquals(name, invoiceLine.getName());
	assertEquals(price, invoiceLine.getPrice());
		
			
	}
	
	
}
