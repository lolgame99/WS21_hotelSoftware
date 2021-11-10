package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;

@Component
public class HibernateBookingRepository implements BookingRepository{
	
	private List<Booking> bookings = new ArrayList<Booking>();
	
	@Override
	public List<Booking> getAllBookings() {
		return bookings;
	}

	@Override
	public List<Booking> getBookingsByCheckInDate(LocalDate date) {
		List<Booking> dateBookings = new ArrayList<Booking>();
		
		for (Booking b : bookings) {
			if(b.getCheckInDate().equals(date)) {
				dateBookings.add(b);
			}
		}
		return dateBookings;
	}

	@Override
	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

	@Override
	public String nextIdentity() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	@Override
	public Optional<Booking> getBookingById(String id) {
		Optional<Booking> booking = Optional.empty();
		for (Booking b : bookings) {
			if (b.getBookingId().equals(id)) {
				booking = Optional.of(b);
			}
		}
		
		return booking;
	}

}
