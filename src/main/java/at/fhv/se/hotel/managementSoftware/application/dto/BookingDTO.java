package at.fhv.se.hotel.managementSoftware.application.dto;

import java.util.Date;
import java.util.Objects;


public class BookingDTO {
	private String id;
	private Date checkInDate;
	
	public static Builder builder() {
		return new Builder();
	}
		
	public Date checkInDate() {
	    return this.checkInDate;
	}
		
	public String id() {
	    return this.id;
	}
		
	private BookingDTO() {
	}
	
	public static class Builder{
		private BookingDTO instance;

        private Builder() {
            this.instance = new BookingDTO();
        }

        public Builder withId(String id) {
            this.instance.id = id;
            return this;
        }

        public Builder withCheckInDate(Date date) {
            this.instance.checkInDate = date;
            return this;
        } 

        public BookingDTO build() {
            Objects.requireNonNull(this.instance.checkInDate, "checkInDate must be set in BookingDTO");
            Objects.requireNonNull(this.instance.id, "id must be set in BookingDTO");

            return this.instance;
        }
	}
}
