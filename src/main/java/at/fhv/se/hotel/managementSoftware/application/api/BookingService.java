package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;

public interface BookingService {
	public List<BookingOverviewDTO> getAllBookings();
	public List<BookingOverviewDTO> getBookingsByDate(LocalDate date);
}
