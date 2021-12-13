package at.fhv.se.hotel.managementSoftware.domain.model;

import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public interface Customer {
	public CustomerId getCustomerId();
	public String getPhoneNumber();
	public String getEmail();
	public Address getAddress();
}
