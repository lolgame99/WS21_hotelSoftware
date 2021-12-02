package at.fhv.se.hotel.managementSoftware.domain.exceptions;

public class InvalidInvoiceException extends Exception{

	public InvalidInvoiceException(String errorMessage) {
		super(errorMessage);
	}
}
