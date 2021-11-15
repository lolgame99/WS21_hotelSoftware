package at.fhv.se.hotel.managementSoftware.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

public interface BookingService {
	public List<BookingOverviewDTO> getAllBookings();
	public List<BookingOverviewDTO> getBookingsByDate(LocalDate date);
	public List<BookingOverviewDTO> getReadyBookingsByDate(LocalDate date);
	public Optional<BookingDetailsDTO> getBookingDetailsById(String id);
	public void addBooking(Booking booking);
	public void addBookingFromData(BookingData bookingData, LocalDate convertedCheckInDate, LocalDate convertedCheckOutDate, LocalDate convertedBirthDate) throws Exception;
}
