package at.fhv.se.hotel.managementSoftware.application.dto;

import java.time.LocalDate;
import java.util.HashMap;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;

public class BookingDetailsDTO {
	private BookingId bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String creditCardNumber;
	private String creditCardValid;
	private CustomerDetailsDTO customer;
	private int guestCount;
	private BookingStatus bookingStatus;
	private int roomCount;

	private HashMap<RoomCategory, Integer> categoryCount;
	
	private BookingDetailsDTO(){
	}
	
	public static BookingDetailsDTO createFromBooking(Booking booking, CustomerDetailsDTO customer) {
		int totalRoomCount = 0;
		for(int categoryCount : booking.getCategoryCount().values()) {
			totalRoomCount += categoryCount;
		}
		
		BookingDetailsDTO dto = new BookingDetailsDTO();
		dto.roomCount = totalRoomCount;
		dto.bookingId = booking.getBookingId();
		dto.checkInDate = booking.getCheckInDate();
		dto.checkOutDate = booking.getCheckOutDate();
		dto.creditCardNumber = booking.getCreditCardNumber();
		dto.customer = customer;
		dto.guestCount = booking.getGuestCount();
		dto.bookingStatus = booking.getBookingStatus();
		dto.categoryCount = booking.getCategoryCount();
		dto.creditCardValid = booking.getCreditCardValid();
		return dto;
	}

	public BookingId getBookingId() {
		return bookingId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public CustomerDetailsDTO getCustomer() {
		return customer;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public HashMap<RoomCategory, Integer> getCategoryCount() {
		return categoryCount;
	}

	public String getCreditCardValid() {
		return creditCardValid;
	}

	public int getRoomCount() {
		return roomCount;
	}
	
	
}
