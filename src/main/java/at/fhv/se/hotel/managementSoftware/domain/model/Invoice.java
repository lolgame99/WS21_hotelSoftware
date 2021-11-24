package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;

public class Invoice {
	 	private InvoiceId invoiceId;
	    private LocalDate date;
	    private BigDecimal price;
	    private PaymentType advancePayment;
	    private InvoiceLine description;
		private InvoiceLine name;
		private InvoiceLine line;
	    
	    private Invoice() {
	        
	    }
	    
	    public static Invoice createFromInvoice(InvoiceId invoiceId, LocalDate date, BigDecimal price, PaymentType advancePayment, InvoiceLine description, InvoiceLine name, InvoiceLine line) {
	        Invoice invoice = new Invoice();
	        invoice.invoiceId = invoiceId;
	        invoice.date = date;
	        invoice.price = price;
	        invoice.advancePayment = advancePayment;
	        invoice.description = description;
	        invoice.name = name;
	        invoice.line = line;
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

		public InvoiceLine getDescription() {
			return description;
		}

		public InvoiceLine getName() {
			return name;
		}

		public InvoiceLine getLine() {
			return line;
		}

		
	    
	    
	    
}
	

