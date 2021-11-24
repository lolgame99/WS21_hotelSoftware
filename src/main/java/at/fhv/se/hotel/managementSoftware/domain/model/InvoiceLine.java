package at.fhv.se.hotel.managementSoftware.domain.model;

public class InvoiceLine {
	private InvoiceId invoiceId;
	private String description;
	private String name;
	private String line;
	
	
	
	private InvoiceLine() {
		
	}
	
	public static InvoiceLine createFromInvoiceLine(InvoiceId invoiceId, String description, String name, String line) {
		InvoiceLine invoiceLine = new InvoiceLine();
		invoiceLine.invoiceId = invoiceId;
		invoiceLine.description = description;
		invoiceLine.name = name;
		invoiceLine.line = line;
		return invoiceLine;
	}
	
	public InvoiceId getInvoiceId() {
		return invoiceId;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public String getLine() {
		return line;
	}
	
	
	
	
	
	

}
