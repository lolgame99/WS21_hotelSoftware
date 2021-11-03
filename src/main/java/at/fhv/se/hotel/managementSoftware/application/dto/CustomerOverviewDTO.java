package at.fhv.se.hotel.managementSoftware.application.dto;


import java.time.LocalDate;
import java.util.Objects;


public class CustomerOverviewDTO {
	private String customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	
	public static Builder builder() {
		return new Builder();
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
	
	private CustomerOverviewDTO() {
	}
	
	public static class Builder{
		private CustomerOverviewDTO instance;

        private Builder() {
            this.instance = new CustomerOverviewDTO();
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


        public CustomerOverviewDTO build() {
            Objects.requireNonNull(this.instance.customerId, "customerId must be set in CustomerOverviewDTO");
            Objects.requireNonNull(this.instance.firstName, "firstName must be set in CustomerOverviewDTO");
            Objects.requireNonNull(this.instance.lastName, "lastName must be set in CustomerOverviewDTO");
            Objects.requireNonNull(this.instance.birthdate, "birthdate must be set in CustomerOverviewDTO");
            return this.instance;
        }
	}
}
