package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidRoomAssignmentException extends Exception {
	
	public InvalidRoomAssignmentException(String errorMessage) {
		super(errorMessage);
	}
}
