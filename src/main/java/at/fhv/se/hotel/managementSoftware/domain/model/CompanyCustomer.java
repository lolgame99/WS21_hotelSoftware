package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;

import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class CompanyCustomer implements Customer{
	private long id;
	private CustomerId customerId;
	private String name;
	private Address address;

	private String email;
	private String phoneNumber;
	private BigDecimal discountRate;
	
	private CompanyCustomer() {
	}
	
	public static CompanyCustomer create(CustomerId customerId, String name, Address address, String email,
			String phoneNumber, BigDecimal discountRate) {
		
		CompanyCustomer customer = new CompanyCustomer();
		customer.customerId = customerId;
		customer.name = name;
		customer.address = address;
		customer.email = email;
		customer.phoneNumber = phoneNumber;
		customer.discountRate = discountRate;
		return customer;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	
	
	
}
