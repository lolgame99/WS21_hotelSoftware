package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;

public class StayDetailsDTO {
	private String stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfGuests;
	private String creditCardNumber; 
	private Booking booking;
	private Customer customer;
	private Guest guest;
	
	private StayDetailsDTO() {
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private StayDetailsDTO instance;

        private Builder() {
            this.instance = new StayDetailsDTO();
        }

        public Builder withId(String id) {
            this.instance.stayId = id;
            return this;
        }

        public Builder withCheckInDate(LocalDate date) {
            this.instance.checkInDate = date;
            return this;
        } 
        
        public Builder withCheckOutDate(LocalDate date) {
            this.instance.checkOutDate = date;
            return this;
        } 
        
        public Builder withNumberOfGuests(int number) {
            this.instance.numberOfGuests = number;
            return this;
        } 
        
        public Builder withCreditCardNumber(String creditCardNumber) {
        	this.instance.creditCardNumber = creditCardNumber;
        	return this;
        }
        
        public Builder withBooking(Booking booking) {
        	this.instance.booking = booking;
        	return this;
        }
        
        public Builder withCustomer(Customer customer) {
        	this.instance.customer = customer;
        	return this;
        }

        public Builder withGuest(Guest guest) {
        	this.instance.guest = guest;
        	return this;
        }

        public StayDetailsDTO build() {
            Objects.requireNonNull(this.instance.stayId, "stayId must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.checkInDate, "checkInDate must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.checkOutDate, "checkOutDate must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.numberOfGuests, "numberOfGuests must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.creditCardNumber, "creditCardNumber must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.customer, "customer must be set in StayDetailsDTO");
            Objects.requireNonNull(this.instance.guest, "guest must be set in StayDetailsDTO");
            return this.instance;
        }
	}
}
