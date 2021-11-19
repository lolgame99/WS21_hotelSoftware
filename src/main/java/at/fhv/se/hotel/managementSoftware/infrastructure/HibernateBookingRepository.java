package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
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
	public BookingId nextIdentity() {
		return new BookingId(UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public Optional<Booking> getBookingById(BookingId id) {
		Optional<Booking> booking = Optional.empty();
		for (Booking b : bookings) {
			if (b.getBookingId().getId().equals(id.getId())) {
				booking = Optional.of(b);
			}
		}
		
		return booking;
	}

	@Override
	public List<Booking> getReadyBookingsByCheckInDate(LocalDate date) {
		List<Booking> readyBookings = getBookingsByCheckInDate(date);
		for (Booking b : readyBookings) {
			if (b.getBookingStatus() == BookingStatus.PENDING || b.getBookingStatus() == BookingStatus.CONFIRMED || b.getBookingStatus() == BookingStatus.CANCELLED) {
				readyBookings.remove(b);
			}
		}
		return readyBookings;
	}

	@Override
	public void deleteBookingById(BookingId id) {
		Booking toDelete = null;
		for (Booking b : bookings) {
			if (b.getBookingId().getId().equals(id.getId())) {
				toDelete = b;
			}
		}
		bookings.remove(toDelete);
	}

}
