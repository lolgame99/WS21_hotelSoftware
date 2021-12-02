package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.enums.StayStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;

public class Stay {
	private long id;
	private StayId stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int guestCount;
	private String creditCardNumber; 
	private BookingId bookingId;
	private CustomerId customerId;
	private GuestId guestId;
	private StayStatus status;

	private Stay() {		
	}
	
	public static Stay createFromBooking(StayId stayId, Booking booking, GuestId guestId) {

		Stay stay = new Stay();
		stay.status = StayStatus.CHECKEDIN;
		stay.stayId = stayId;
		stay.checkInDate = booking.getCheckInDate();
		stay.checkOutDate = booking.getCheckOutDate();
		stay.guestCount = booking.getGuestCount();
		stay.creditCardNumber = booking.getCreditCardNumber();
		stay.bookingId = booking.getBookingId();
		stay.customerId = booking.getCustomerId();
		stay.guestId = guestId;
		return stay;
	}
	
	public static Stay createForWalkIn(StayId stayId, LocalDate checkInDate, LocalDate checkOutDate, int guestCount, String creditCardNumber, CustomerId customerId, GuestId guestId) throws InvalidStayException {
		
		
		// Pruefen, ob Check-in-Date vor Check-out-Date liegt
		if (checkInDate.compareTo(checkOutDate) >= 0) {
			throw new InvalidStayException("Stay could not be created <br> Check-out-Date can't be before Check-in Date");
		}
				
		//Pruefen, ob Check-in-Date vor heute liegt
		if (checkInDate.compareTo(LocalDate.now()) < 0) {
			throw new InvalidStayException("Stay could not be created <br> Check-in-Date can't be in the past");
		}

		// Pruefen, ob mind. ein Kunde
		if (guestCount <= 0) {
			throw new InvalidStayException("Stay could not be created <br> Booking requires atleast 1 guest");
		}
				
		
		Stay stay = new Stay();
		stay.status = StayStatus.CHECKEDIN;
		stay.stayId = stayId;
		stay.checkInDate = checkInDate;
		stay.checkOutDate = checkOutDate;
		stay.guestCount = guestCount;
		stay.creditCardNumber = creditCardNumber;
		stay.customerId = customerId;
		stay.guestId = guestId;
		return stay;
	}
	
	public StayId getStayId() {
		return stayId;
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

	public BookingId getBookingId() {
		return bookingId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}
	
	public GuestId getGuestId() {
		return guestId;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public StayStatus getStatus() {
		return status;
	}
	
	
}

