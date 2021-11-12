package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidStayException extends Exception {

	public InvalidStayException(String errorMessage) {
		super(errorMessage);
	}
}
