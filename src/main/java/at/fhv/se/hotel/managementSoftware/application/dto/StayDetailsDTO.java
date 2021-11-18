package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public class StayDetailsDTO {
	private StayId stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int guestCount;
	private String creditCardNumber; 
	private BookingDetailsDTO booking;
	private CustomerDetailsDTO customer;
	private GuestDetailsDTO guest;
	private List<RoomAssignmentDTO> roomAssignments;
	
	private StayDetailsDTO() {
	}
	
	public static StayDetailsDTO createFromStay(Stay stay, BookingDetailsDTO booking, CustomerDetailsDTO customer, GuestDetailsDTO guest, List<RoomAssignmentDTO> assignments) {
		StayDetailsDTO dto = new StayDetailsDTO();
		dto.stayId = stay.getStayId();
		dto.checkInDate = stay.getCheckInDate();
		dto.checkOutDate = stay.getCheckOutDate();
		dto.guestCount = stay.getGuestCount();
		dto.creditCardNumber = stay.getCreditCardNumber();
		dto.booking = booking;
		dto.customer = customer;
		dto.guest = guest;
		dto.roomAssignments = assignments;
		return dto;
	}

	public StayId getStayId() {
		return stayId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public int getNumberOfGuests() {
		return guestCount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public BookingDetailsDTO getBooking() {
		return booking;
	}

	public CustomerDetailsDTO getCustomer() {
		return customer;
	}

	public GuestDetailsDTO getGuest() {
		return guest;
	}
}