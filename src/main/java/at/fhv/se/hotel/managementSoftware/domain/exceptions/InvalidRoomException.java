package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidRoomException extends Exception {
		
	public InvalidRoomException(String errorMessage) {
		super(errorMessage);
	}
}
