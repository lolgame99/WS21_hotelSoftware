package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidGuestException extends Exception {
	public InvalidGuestException(String errorMessage) {
		super(errorMessage);
	}
}
