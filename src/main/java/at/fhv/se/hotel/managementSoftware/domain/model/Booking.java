package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;

public class Booking {
	private long id;
	private BookingId bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String creditCardNumber;
	private String creditCardValid;
	private CustomerId customerId;
	private int guestCount;
	private BookingStatus bookingStatus;

	private Map<RoomCategory, Integer> categoryCount;
	
	private Booking() {
	}
	
	public static Booking create(BookingId bookingId, LocalDate checkInDate, LocalDate checkOutDate, String creditCardNumber, String creditCardValid,
			CustomerId customerId, int guestCount, BookingStatus bookingStatus, HashMap<RoomCategory, Integer> categoryCount) throws InvalidBookingException{
		// Pruefen, ob Check-in-Date vor Check-out-Date liegt
		if (checkInDate.compareTo(checkOutDate) >= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Check-out-Date can't be before Check-in Date");
		}
		
		//Pruefen, ob Check-in-Date vor heute liegt
		if (checkInDate.compareTo(LocalDate.now()) < 0) {
			throw new InvalidBookingException("Booking could not be created <br> Check-in-Date can't be in the past");
		}

		// Pruefen, ob mind. ein Kunde
		if (guestCount <= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Booking requires atleast 1 guest");
		}
		
		//Pruefen ob mind. eine Raumkategory in Hashmap
		if(categoryCount.isEmpty()) {
			throw new InvalidBookingException("Booking could not be created <br> Atleast 1 roomcategory has to be selected");
		}
		
		//Pruefen ob genug Betten fuer Anzahl von Gaesten
		Integer bedSum = 0;
		for (HashMap.Entry<RoomCategory, Integer> entry: categoryCount.entrySet()) {
			bedSum += entry.getKey().getBedNumber() * entry.getValue();
		}
		if (bedSum < guestCount) {
			throw new InvalidBookingException("Booking could not be created <br> Too many guests for the selected number of rooms");
		}
		
		Booking booking = new Booking();
		booking.bookingId = bookingId;
		booking.checkInDate = checkInDate;
		booking.checkOutDate = checkOutDate;
		booking.creditCardNumber = creditCardNumber;
		booking.customerId = customerId;
		booking.guestCount = guestCount;
		booking.bookingStatus = bookingStatus;
		booking.categoryCount = categoryCount;
		booking.creditCardValid = creditCardValid;
		
		return booking;
	}

	public BookingId getBookingId() {
		return bookingId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public Map<RoomCategory, Integer> getCategoryCount() {
		return categoryCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public String getCreditCardValid() {
		return creditCardValid;
	}
	
	public void checkedIn() {
		this.bookingStatus = BookingStatus.ARRIVED;
	}

	public long getId() {
		return id;
	}

	
}
