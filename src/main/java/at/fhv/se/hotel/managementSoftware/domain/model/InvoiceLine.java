package at.fhv.se.hotel.managementSoftware.domain.model;

public class InvoiceLine {
	private InvoiceId invoiceId;
	private int count;
	private String description;
	private String name;
	private String line;
	
	
	private InvoiceLine() {
		
	}
	
	public static InvoiceLine createFromInvoiceLine(InvoiceId invoiceId, int count, String description, String name, String line) {
		InvoiceLine invoiceLine = new InvoiceLine();
		invoiceLine.invoiceId = invoiceId;
		invoiceLine.count = count;
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

	public int getCount() {
		return count;
	}
}
