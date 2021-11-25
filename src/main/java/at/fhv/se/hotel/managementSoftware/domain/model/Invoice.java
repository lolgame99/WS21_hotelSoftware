package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;


import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;

public class Invoice {
	 	private InvoiceId invoiceId;
	    private LocalDate date;
	    private BigDecimal price;
	    private PaymentType advancePayment;
	    private List<InvoiceLineDetailsDTO> invoiceLine;
	    
	    
	    private Invoice() {
	        
	    }
	    
	    
	    public static Invoice createFromInvoice(InvoiceId invoiceId, LocalDate date, BigDecimal price, PaymentType advancePayment, List<InvoiceLineDetailsDTO> invoiceLine) {
	        Invoice invoice = new Invoice();
	        invoice.invoiceId = invoiceId;
	        invoice.date = date;
	        invoice.price = price;
	        invoice.advancePayment = advancePayment;
	        invoice.invoiceLine = invoiceLine;
	        return invoice;
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


		public List<InvoiceLineDetailsDTO> getInvoiceLine() {
			return invoiceLine;
		}
	    
}
	

