package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;

public class InvoiceLine {
	private InvoiceId invoiceId;
	private int count;
	private String description;
	private String name;
	private BigDecimal price;
	
	
	private InvoiceLine() {
		
	}
	
	public static InvoiceLine createFromInvoiceLine(InvoiceId invoiceId, int count, String description, String name, BigDecimal price) {
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
	
}
