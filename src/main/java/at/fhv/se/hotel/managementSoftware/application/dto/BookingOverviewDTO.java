package at.fhv.se.hotel.managementSoftware.application.dto;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;


public class BookingOverviewDTO {
	private BookingId bookingId;
	private int roomCount;
	private int guestCount;
	private CustomerOverviewDTO customer;
	private BookingStatus bookingStatus;
	
	private BookingOverviewDTO() {
	}
	
	public static BookingOverviewDTO createFromBooking(Booking booking, CustomerOverviewDTO customer) {
		int totalRoomCount = 0;
		for(int categoryCount : booking.getCategoryCount().values()) {
			totalRoomCount += categoryCount;
		}
		
		BookingOverviewDTO dto = new BookingOverviewDTO();
		dto.bookingId = booking.getBookingId();
		dto.roomCount = totalRoomCount;
		dto.guestCount = booking.getGuestCount();
		dto.customer = customer;
		dto.bookingStatus = booking.getBookingStatus();
		return dto;
	}
			
	public BookingId getBookingId() {
		return bookingId;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public CustomerOverviewDTO getCustomer() {
		return customer;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	
	
}
