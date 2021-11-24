package at.fhv.se.hotel.managementSoftware.domain.model;

public class InvoiceId {
	private String id;
	
	public InvoiceId(String id) {
		this.id = id;
	}
	
	public InvoiceId(InvoiceId invoiceId) {
		this.id = invoiceId.getId();
	}

	public String getId() {
		return id;
	}

	
	
	
}
