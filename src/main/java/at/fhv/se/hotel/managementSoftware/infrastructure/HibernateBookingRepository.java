package at.fhv.se.hotel.managementSoftware.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;

@Component
public class HibernateBookingRepository implements BookingRepository{
	
	private List<Booking> bookings = new ArrayList<Booking>()
;
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

}
