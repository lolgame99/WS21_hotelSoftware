package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.Address;

public class CustomerDetailsDTO {
	private String customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	private Address address;

	private String email;
	private String phoneNumber;
	private Gender gender;
	
	private Address billingAddress;
	
	private CustomerDetailsDTO() {
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private CustomerDetailsDTO instance;

        private Builder() {
            this.instance = new CustomerDetailsDTO();
        }

        public Builder withId(String id) {
            this.instance.customerId = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.instance.firstName = firstName;
            return this;
        } 
        
        public Builder withLastName(String lastName) {
            this.instance.lastName = lastName;
            return this;
        } 
        
        public Builder withMiddleName(String middleName) {
            this.instance.middleName = middleName;
            return this;
        } 
        
        public Builder withBirthdate(LocalDate birthdate) {
        	this.instance.birthdate = birthdate;
        	return this;
        }
        
        public Builder withAddress(Address address) {
        	this.instance.address = address;
        	return this;
        }
        
        public Builder withEmail(String email) {
        	this.instance.email = email;
        	return this;
        }

        public Builder withPhoneNumber(String number) {
        	this.instance.phoneNumber = number;
        	return this;
        }
        
        public Builder withGender(Gender gender) {
        	this.instance.gender = gender;
        	return this;
        }
        
        public Builder withBillingAddress(Address address) {
        	this.instance.billingAddress = address;
        	return this;
        }

        public CustomerDetailsDTO build() {
            Objects.requireNonNull(this.instance.customerId, "customerId must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.firstName, "firstName must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.lastName, "lastName must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.birthdate, "birthdate must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.address, "address must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.email, "email must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.phoneNumber, "phoneNumber must be set in CustomerDetailsDTO");
            Objects.requireNonNull(this.instance.gender, "gender must be set in CustomerDetailsDTO");
            return this.instance;
        }
	}

	public String getCustomerId() {
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

	public Address getBillingAddress() {
		return billingAddress;
	}
	
	
}
