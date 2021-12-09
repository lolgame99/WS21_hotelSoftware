package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceLineRepository;

@ActiveProfiles("test")
@SpringBootTest
public class InvoiceLineServiceTest {
	@Autowired
	private InvoiceLineService invoiceLineService;
	
	@MockBean
	private InvoiceLineRepository invoiceLineRepository;
	
	@Test
	void when_getAll_invoiceLines_returns_all() {
		//given
		List<InvoiceLine> allLines = new ArrayList<InvoiceLine>();
		allLines.add(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00)));
		allLines.add(InvoiceLine.create(new InvoiceId("2"), 2, "Double Room", "Two double rooms", new BigDecimal (250.00)));
		
		Mockito.when(invoiceLineRepository.getAllInvoiceLines()).thenReturn(allLines);
		
		//when
		List<InvoiceLineDetailsDTO> dtos = invoiceLineService.getAllInvoiceLines();
		
		//then
		assertEquals(2, dtos.size());
		assertEquals(allLines.get(0).getName(), dtos.get(0).getName());
		assertEquals(allLines.get(0).getDescription(), dtos.get(0).getDescription());
		assertEquals(allLines.get(0).getPrice(), dtos.get(0).getPrice());
		assertEquals(allLines.get(0).getCount(), dtos.get(0).getCount());
	}
	
	@Test
	void when_given_invoiceId_return_invoiceId_invoiceLines() {
		//given
		List<InvoiceLine> allInvoiceLines = new ArrayList<InvoiceLine>();
		allInvoiceLines.add(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00)));
		allInvoiceLines.add(InvoiceLine.create(new InvoiceId("1"), 2, "Double Room", "Two double rooms", new BigDecimal (250.00)));
		
		Mockito.when(invoiceLineRepository.getAllInvoiceLinesByInvoiceId(any(InvoiceId.class))).thenReturn(allInvoiceLines);
		
		//when
		List<InvoiceLineDetailsDTO> dtos = invoiceLineService.getAllInvoiceLinesByInvoiceId(allInvoiceLines.get(0).getInvoiceId().getId());
		
		//then
		assertEquals(2, allInvoiceLines.size());
		assertEquals(allInvoiceLines.get(0).getName(), dtos.get(0).getName());
		assertEquals(allInvoiceLines.get(0).getDescription(), dtos.get(0).getDescription());
		assertEquals(allInvoiceLines.get(0).getPrice(), dtos.get(0).getPrice());
		assertEquals(allInvoiceLines.get(0).getCount(), dtos.get(0).getCount());
	}
}
