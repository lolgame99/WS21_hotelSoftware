package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;

public class InvoiceLine {
	private long id;
	private InvoiceId invoiceId;
	private int count;
	private String description;
	private String name;
	private BigDecimal price;
	
	
	private InvoiceLine() {	
	}
	
	public static InvoiceLine create(InvoiceId invoiceId, int count, String name, String description, BigDecimal price) {
		InvoiceLine invoiceLine = new InvoiceLine();
		invoiceLine.invoiceId = invoiceId;
		invoiceLine.count = count;
		invoiceLine.description = description;
		invoiceLine.name = name;
		invoiceLine.price = price;
		return invoiceLine;
	}

	public InvoiceId getInvoiceId() {
		return invoiceId;
	}

	public int getCount() {
		return count;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getId() {
		return id;
	}
	
	
}
