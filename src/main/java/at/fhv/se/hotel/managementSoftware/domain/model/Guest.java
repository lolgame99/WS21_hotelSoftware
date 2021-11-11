package at.fhv.se.hotel.managementSoftware.domain.model;

public class Guest {
	
	private GuestId guestId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private StayId stayId;
	
	private Guest() {
	}

	public static Guest create(GuestId guestId, String firstName, String lastName, String number, StayId stayId) {
		Guest guest = new Guest();
		guest.guestId = guestId;
		guest.firstName = firstName;
		guest.lastName = lastName;
		guest.phoneNumber = number;
		guest.stayId = stayId;
		return guest;
	}

	public GuestId getGuestId() {
		return guestId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public StayId getStayId() {
		return stayId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	
}
