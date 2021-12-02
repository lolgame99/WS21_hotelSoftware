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
    private Customer customer;
    private StayId stayId;
    
    private Invoice() {   
    }
    
    public static Invoice create(InvoiceId invoiceId, LocalDate date, BigDecimal sum, PaymentType advancePayment, Customer customer, StayId stayId) {
        Invoice invoice = new Invoice();
        invoice.invoiceId = invoiceId;
        invoice.date = date;
        invoice.sum = sum;
        invoice.paymentType = advancePayment;
        invoice.customer = customer;
        invoice.stayId = stayId;
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

	public long getId() {
		return id;
	}


	public PaymentType getPaymentType() {
		return paymentType;
	}


	public Customer getCustomer() {
		return customer;
	}


	public StayId getStayId() {
		return stayId;
	}
	
	
	
}	

