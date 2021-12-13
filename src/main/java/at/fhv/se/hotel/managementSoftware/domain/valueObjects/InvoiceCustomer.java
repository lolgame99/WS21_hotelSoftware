package at.fhv.se.hotel.managementSoftware.domain.valueObjects;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;

public class InvoiceCustomer {
	private long id;
	private CustomerId customerId;
	private String name;
	private Address address;

	private String email;
	private String phoneNumber;
	private BigDecimal discountRate;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	private Gender gender;
	
	private InvoiceCustomer() {
	}
	
	public InvoiceCustomer(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.address = customer.getAddress();
		this.email = customer.getEmail();
		this.phoneNumber = customer.getPhoneNumber();
		
		if (customer instanceof IndividualCustomer) {
			IndividualCustomer typeCustomer = (IndividualCustomer) customer;
			this.firstName = typeCustomer.getFirstName();
			this.middleName = typeCustomer.getMiddleName();
			this.lastName = typeCustomer.getLastName();
			this.birthdate = typeCustomer.getBirthdate();
			this.gender = typeCustomer.getGender();
		}else {
			CompanyCustomer typeCustomer = (CompanyCustomer) customer;
			this.name = typeCustomer.getName();
			this.discountRate = typeCustomer.getDiscountRate();
		}

		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
