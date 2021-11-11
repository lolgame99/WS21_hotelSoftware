package at.fhv.se.hotel.managementSoftware.domain.model;

import java.time.LocalDate;

public class Stay {
	
	private StayId stayId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfGuests;
	private String creditCardNumber; 
	private BookingId bookingId;
	private CustomerId customerId;
	private GuestId guestId;

	public static Stay createForBooking(StayId stayId, Booking booking, GuestId guestId) {
		Stay stay = new Stay(stayId, booking.getCheckInDate(), booking.getCheckOutDate(),
				booking.getGuestCount(), booking.getCreditCardNumber(), booking.getCustomer().getCustomerId(), guestId);
		stay.addBookingId(booking.getBookingId());
		return stay;
	}
	
	private Stay(StayId stayId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests,
			String creditCardNumber, CustomerId customerId, GuestId guestId){
		this.stayId = stayId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate; 
		this.creditCardNumber = creditCardNumber;
		this.customerId = customerId;
		this.guestId = guestId;
		this.numberOfGuests = numberOfGuests;
	}
	
	private void addBookingId(BookingId bookingId) {
		if(bookingId == null) {
			this.bookingId = bookingId;
		}
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
		return numberOfGuests;
	}
	
}

