package at.fhv.se.hotel.managementSoftware.application.dto;


import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;


public class CustomerOverviewDTO {
	private CustomerId customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private String name;
	
	private CustomerOverviewDTO() {
	}
	
	public static CustomerOverviewDTO createFromCustomer(Customer customer) {
		CustomerOverviewDTO dto = new CustomerOverviewDTO();
		dto.customerId = customer.getCustomerId();
		
		if (customer instanceof IndividualCustomer) {
			IndividualCustomer typeCustomer = (IndividualCustomer) customer;
			dto.firstName = typeCustomer.getFirstName();
			dto.middleName = typeCustomer.getMiddleName();
			dto.lastName = typeCustomer.getLastName();
			dto.birthdate = typeCustomer.getBirthdate();
		}else {
			CompanyCustomer typeCustomer = (CompanyCustomer) customer;
			dto.name = typeCustomer.getName();
		}
		
		return dto;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getName() {
		return name;
	}
	
	
}
