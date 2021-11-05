package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.util.HashMap;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;

public class Booking {
	private String bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String creditCardNumber;
	private Customer customer;
	private int guestCount;
	private BookingStatus bookingStatus;

	private HashMap<RoomCategory, Integer> categoryCount;

	public Booking(String bookingId, LocalDate checkInDate, LocalDate checkOutDate, String creditCardNumber,
			Customer customer, int guestCount, BookingStatus bookingStatus) throws InvalidBookingException {
		super();

		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.creditCardNumber = creditCardNumber;
		this.customer = customer;
		this.guestCount = guestCount;
		categoryCount = new HashMap<RoomCategory, Integer>();
		this.bookingStatus = bookingStatus;
	}

	public void FactorcyMethod() throws InvalidBookingException {
		
		// Pr�fen, ob Check-in-Date vor Check-out-Date liegt
		if (checkInDate.compareTo(checkOutDate) >= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Check-out-Date can't be before Check-in Date");
		}

		// Pr�fen, ob mind. ein Kunde
		if (guestCount <= 0) {
			throw new InvalidBookingException("Booking could not be created <br> Booking requires atleast 1 person");
		}

//			//Pr�fen, ob HashMap leer ist
//			if (categoryCount.isEmpty()) {
//				throw new InvalidBookingException("ERROR: HashMap is not supposed to be empty!");
//			}

		// Pr�fen, ob HashMap leer ist
		if (categoryCount.isEmpty()) {
			System.out.println();
		}

	}

	public Booking addCategory(RoomCategory roomCategory, Integer count) {
		categoryCount.put(roomCategory, count);
		return this;
	}

	public String getBookingId() {
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

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<RoomCategory, Integer> getCategoryCount() {
		return categoryCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

}
