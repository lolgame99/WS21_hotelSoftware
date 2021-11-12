package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

public class CustomerDetailsDTO {
	private CustomerId customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private Address address;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	private CustomerDetailsDTO() {
	}
	
	public static CustomerDetailsDTO createFromCustomer(Customer customer) {
		CustomerDetailsDTO dto = new CustomerDetailsDTO();
		dto.customerId = customer.getCustomerId();
		dto.firstName = customer.getFirstName();
		dto.lastName = customer.getLastName();
		dto.birthdate = customer.getBirthdate();
		dto.address = customer.getAddress();
		dto.email = customer.getEmail();
		dto.phoneNumber = customer.getPhoneNumber();
		dto.gender = customer.getGender();
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

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}
	
}
