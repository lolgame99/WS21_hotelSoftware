package at.fhv.se.hotel.managementSoftware.unit.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceLineRepository;

@SpringBootTest
@Transactional
public class InvoiceLineRepositoryTest {
	
	@Autowired
	private InvoiceLineRepository invoiceLineRepository;
	
	@Test
	void when_given_invoiceLine_added_return_equal() {
		//given
		InvoiceLine expectedLine = InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00));
		
		//when
		invoiceLineRepository.addLine(expectedLine);
		InvoiceLine actualLine = invoiceLineRepository.getAllInvoiceLinesByInvoiceId(expectedLine.getInvoiceId()).get(0);
		
		//then
		assertEquals(expectedLine.getInvoiceId().getId(), actualLine.getInvoiceId().getId());
		assertEquals(expectedLine.getCount(), actualLine.getCount());
		assertEquals(expectedLine.getName(), actualLine.getName());
		assertEquals(expectedLine.getDescription(), actualLine.getDescription());
		assertEquals(expectedLine.getPrice(), actualLine.getPrice());
	}
	
	@Test
	void when_given_invoiceLines_added_return_all() {
		//given
		List<InvoiceLine> expectedLines = new ArrayList<InvoiceLine>();
		expectedLines.add(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00)));
		expectedLines.add(InvoiceLine.create(new InvoiceId("2"), 2, "Double Room", "Two double room", new BigDecimal (250.00)));
		
		//when
		for (InvoiceLine invoiceLine : expectedLines) {
			invoiceLineRepository.addLine(invoiceLine);
		}
		
		List<InvoiceLine> actualLines = invoiceLineRepository.getAllInvoiceLines();
		
		//then
		assertEquals(expectedLines.get(0).getInvoiceId().getId(), actualLines.get(0).getInvoiceId().getId());
		assertEquals(expectedLines.get(0).getCount(), actualLines.get(0).getCount());
		assertEquals(expectedLines.get(0).getName(), actualLines.get(0).getName());
		assertEquals(expectedLines.get(0).getDescription(), actualLines.get(0).getDescription());
		assertEquals(expectedLines.get(0).getPrice(), actualLines.get(0).getPrice());
		
		assertEquals(expectedLines.get(1).getInvoiceId().getId(), actualLines.get(1).getInvoiceId().getId());
		assertEquals(expectedLines.get(1).getCount(), actualLines.get(1).getCount());
		assertEquals(expectedLines.get(1).getName(), actualLines.get(1).getName());
		assertEquals(expectedLines.get(1).getDescription(), actualLines.get(1).getDescription());
		assertEquals(expectedLines.get(1).getPrice(), actualLines.get(1).getPrice());
	}
	
	@Test
	void when_given_invalidInvoiceId_return_empty() {
		//given
		InvoiceId id = new InvoiceId("invalid");
		
		//when
		List<InvoiceLine> actualLine = invoiceLineRepository.getAllInvoiceLinesByInvoiceId(id);
		
		//then
		assertTrue(actualLine.isEmpty());
	}
}
