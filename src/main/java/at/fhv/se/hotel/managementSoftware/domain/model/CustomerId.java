package at.fhv.se.hotel.managementSoftware.domain.model;

public class CustomerId {
	private String id;
	
	public CustomerId(String id) {
		this.id = id;
	}
	
	public CustomerId(CustomerId customerId) {
		this.id = customerId.getId();
	}
	
	public String getId() {
		return this.id;
	}

}
