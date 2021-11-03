package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidBookingException extends Exception {

	public InvalidBookingException(String errorMessage) {
		super(errorMessage);
	}

}
