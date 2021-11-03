package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.Customer;


public class BookingOverviewDTO {
	private String bookingId;
	private LocalDate checkInDate;
	private int roomCount;
	private int guestCount;
	private Customer customer;
	
	
	public static Builder builder() {
		return new Builder();
	}
			
	public String getBookingId() {
		return bookingId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	private BookingOverviewDTO() {
	}
	
	public static class Builder{
		private BookingOverviewDTO instance;

        private Builder() {
            this.instance = new BookingOverviewDTO();
        }

        public Builder withId(String id) {
            this.instance.bookingId = id;
            return this;
        }

        public Builder withCheckInDate(LocalDate date) {
            this.instance.checkInDate = date;
            return this;
        } 
        
        public Builder withGuestCount(int count) {
        	this.instance.guestCount  = count;
        	return this;
        }
        
        public Builder withRoomCount(int count) {
        	this.instance.roomCount  = count;
        	return this;
        }
        
        public Builder withCustomer(Customer customer) {
        	this.instance.customer = customer;
        	return this;
        }

        public BookingOverviewDTO build() {
            Objects.requireNonNull(this.instance.checkInDate, "checkInDate must be set in BookingOverviewDTO");
            Objects.requireNonNull(this.instance.bookingId, "bookingId must be set in BookingOverviewDTO");
            Objects.requireNonNull(this.instance.customer, "customer must be set in BookingOverviewDTO");
            Objects.requireNonNull(this.instance.guestCount, "guestCount must be set in BookingOverviewDTO");
            return this.instance;
        }
	}
}
