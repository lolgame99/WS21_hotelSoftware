package at.fhv.se.hotel.managementSoftware.domain.model;

import java.io.Serializable;

public class CustomerId implements Serializable{
	private String id;
	
	private CustomerId() {
	}
	
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
