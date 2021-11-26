package at.fhv.se.hotel.managementSoftware.application.dto;

import java.math.BigDecimal;

import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;

public class InvoiceLineDetailsDTO {
	private int count;
	private String description;
	private String name;
	private BigDecimal price;
	
	private InvoiceLineDetailsDTO() {
	}
	
	public static InvoiceLineDetailsDTO createsFromInvoiceLine (InvoiceLine invoiceLine) {
		InvoiceLineDetailsDTO dto = new InvoiceLineDetailsDTO();
		dto.count = invoiceLine.getCount();
		dto.description = invoiceLine.getDescription();
		dto.name = invoiceLine.getName();
		dto.price = invoiceLine.getPrice();
		
		return dto;
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
