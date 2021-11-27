package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;


import java.time.LocalDate;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;

public class Invoice {
	private long id;
 	private InvoiceId invoiceId;
    private LocalDate date;
    private BigDecimal sum;
    private PaymentType paymentType;  
    
    private Invoice() {   
    }
    
    
    public static Invoice create(InvoiceId invoiceId, LocalDate date, BigDecimal sum, PaymentType advancePayment) {
        Invoice invoice = new Invoice();
        invoice.invoiceId = invoiceId;
        invoice.date = date;
        invoice.sum = sum;
        invoice.paymentType = advancePayment;
        return invoice;
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
		return paymentType;
	}


	public long getId() {
		return id;
	}
	
	
}	

