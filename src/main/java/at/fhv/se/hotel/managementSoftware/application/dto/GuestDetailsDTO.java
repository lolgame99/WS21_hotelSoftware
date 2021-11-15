package at.fhv.se.hotel.managementSoftware.application.dto;

import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;

public class GuestDetailsDTO {
	private GuestId guestId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	
	private GuestDetailsDTO() {
	}
	
	public static GuestDetailsDTO createFromGuest(Guest guest) {
		GuestDetailsDTO dto = new GuestDetailsDTO();
		dto.guestId = guest.getGuestId();
		dto.firstName = guest.getFirstName();
		dto.middleName = guest.getMiddleName();
		dto.lastName = guest.getLastName();
		dto.phoneNumber = guest.getPhoneNumber();
		return dto;
	}

	public GuestId getGuestId() {
		return guestId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMiddleName() {
		return middleName;
	}
	
	
}
