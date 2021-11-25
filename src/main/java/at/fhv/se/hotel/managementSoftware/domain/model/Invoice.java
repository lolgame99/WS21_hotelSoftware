package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;

public class Invoice {
	 	private InvoiceId invoiceId;
	    private LocalDate date;
	    private BigDecimal price;
	    private PaymentType advancePayment;
	    List<InvoiceLine> invoiceLine = new ArrayList<InvoiceLine>();
	    
	    
	    private Invoice() {
	        
	    }
	    
	    
	    public static Invoice createFromInvoice(InvoiceId invoiceId, LocalDate date, BigDecimal price, PaymentType advancePayment) {
	        Invoice invoice = new Invoice();
	        invoice.invoiceId = invoiceId;
	        invoice.date = date;
	        invoice.price = price;
	        invoice.advancePayment = advancePayment;
	       
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

		

		
	    
	    
	    
}
	

