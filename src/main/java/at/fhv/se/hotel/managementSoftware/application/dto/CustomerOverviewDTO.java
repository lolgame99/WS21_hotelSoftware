package at.fhv.se.hotel.managementSoftware.application.dto;


import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;


public class CustomerOverviewDTO {
	private CustomerId customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private CustomerOverviewDTO() {
	}
	
	public static CustomerOverviewDTO createFromCustomer(Customer customer) {
		CustomerOverviewDTO dto = new CustomerOverviewDTO();
		dto.customerId = customer.getCustomerId();
		dto.firstName = customer.getFirstName();
		dto.lastName = customer.getLastName();
		dto.birthdate = customer.getBirthdate();
		dto.middleName = customer.getMiddleName();
		
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
	
}
