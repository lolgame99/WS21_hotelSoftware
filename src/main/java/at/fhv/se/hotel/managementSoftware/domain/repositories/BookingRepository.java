package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;

public interface BookingRepository {
	public List<Booking> getAllBookings();
	public Optional<Booking> getBookingById(BookingId id);
	public List<Booking> getBookingsByCheckInDate(LocalDate date);
	public List<Booking> getReadyBookingsByCheckInDate(LocalDate date);
	public void addBooking(Booking booking);
	public void deleteBookingById(BookingId id);
	public BookingId nextIdentity();
}
