package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;
import java.util.HashMap;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;

public class Booking {
	private String bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private LocalDate cancelationDeadline;
	private String creditCardNumber;
	private Customer customer;
	private int guestCount;
	private BookingStatus bookingStatus;
	
	private HashMap<String, Integer> categoryCount;
	
	public Booking(String bookingId, LocalDate checkInDate,  LocalDate checkOutDate, LocalDate cancelationDeadline, String creditCardNumber, Customer customer, int guestCount, BookingStatus bookingStatus) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.cancelationDeadline = cancelationDeadline;
		this.creditCardNumber = creditCardNumber;
		this.customer = customer;
		this.guestCount = guestCount;
		categoryCount = new HashMap<String, Integer>();
		this.bookingStatus = bookingStatus;
		
		//Prüfen, ob Check-in-Date vor Check-out-Date liegt
		if (checkInDate.compareTo(checkOutDate) >= 0) {
			System.out.println("Fehler: Check-in-Date darf nicht hinter Check-out-Date liegen!");
		}
		
		//Prüfen, ob mind. ein Raum gebucht wurde
		if (guestCount <= 0) {
			System.out.println("Fehler: Es muss mind. ein Raum gebucht werden!");
		}
		
		//Prüfen, ob HashMap leer ist
		if (categoryCount.isEmpty()) {
			System.out.println();
		}
		
		//CancelationDeadline
		
	}
	
	public Booking addCategory(String category, Integer count) {
		categoryCount.put(category, count);
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

	public LocalDate getCancelationDeadline() {
		return cancelationDeadline;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<String, Integer> getCategoryCount() {
		return categoryCount;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	
	
	
}
