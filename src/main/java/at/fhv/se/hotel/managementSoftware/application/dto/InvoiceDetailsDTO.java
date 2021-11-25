package at.fhv.se.hotel.managementSoftware.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;

public class InvoiceDetailsDTO {
	private InvoiceId invoiceId;
	private LocalDate date;
	private BigDecimal price;
	private PaymentType advancePayment;
    private List<InvoiceLine> invoiceLine;
	
	private InvoiceDetailsDTO() {
	}
	
	public static InvoiceDetailsDTO createsFromInvoice(Invoice invoice) {
		InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
		dto.invoiceId = invoice.getInvoiceId();
        dto.date = invoice.getDate();
        dto.price = invoice.getPrice();
        dto.advancePayment = invoice.getAdvancePayment();
        dto.invoiceLine = invoice.getInvoiceLine();
        
        return dto;
	}

	public InvoiceId getInvoiceId() {
		return invoiceId;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public PaymentType getAdvancePayment() {
		return advancePayment;
	}

	public List<InvoiceLine> getInvoiceLine() {
		return invoiceLine;
	}
}
