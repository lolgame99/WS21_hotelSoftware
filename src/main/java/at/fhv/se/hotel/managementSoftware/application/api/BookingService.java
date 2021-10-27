package at.fhv.se.hotel.managementSoftware.application.api;

import java.util.Date;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.BookingDTO;

public interface BookingService {
	public List<BookingDTO> getAllBookings();
	public List<BookingDTO> getBookingsByDate(Date date);
}
