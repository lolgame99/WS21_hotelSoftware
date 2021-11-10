package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;

public interface BookingRepository {
	public List<Booking> getAllBookings();
	public Optional<Booking> getBookingById(String id);
	public List<Booking> getBookingsByCheckInDate(LocalDate date);
	public void addBooking(Booking booking);
	public String nextIdentity();
}
