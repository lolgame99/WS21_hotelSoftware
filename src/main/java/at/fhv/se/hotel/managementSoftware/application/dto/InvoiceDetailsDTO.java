package at.fhv.se.hotel.managementSoftware.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;

public class InvoiceDetailsDTO {
	private InvoiceId invoiceId;
	private LocalDate date;
	private BigDecimal sum;
	private PaymentType advancePayment;
	private CustomerDetailsDTO customer;
    private List<InvoiceLineDetailsDTO> invoiceLine;
	
	private InvoiceDetailsDTO() {
	}
	
	public static InvoiceDetailsDTO createsFromInvoice(Invoice invoice, List<InvoiceLineDetailsDTO> invoiceLine, CustomerDetailsDTO customer) {
		InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
		dto.invoiceId = invoice.getInvoiceId();
        dto.date = invoice.getDate();
        dto.sum = invoice.getSum();
        dto.customer = customer;
        dto.advancePayment = invoice.getPaymentType();
        dto.invoiceLine = invoiceLine;
        
        return dto;
	}

	public InvoiceId getInvoiceId() {
		return invoiceId;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public PaymentType getAdvancePayment() {
		return advancePayment;
	}

	public List<InvoiceLineDetailsDTO> getInvoiceLine() {
		return invoiceLine;
	}

	public CustomerDetailsDTO getCustomer() {
		return customer;
	}

}
