package at.fhv.se.hotel.managementSoftware.domain.repositories;

import java.util.Date;
import java.util.List;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;

public interface BookingRepository {
	public List<Booking> getAllBookings();
	public List<Booking> getBookingsByDate(Date date);
	public void addBooking(Booking booking);
}
