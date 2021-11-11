package at.fhv.se.hotel.managementSoftware.domain.model;

public class Guest {
	
	private GuestId guestId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private StayId stayId;
	
	public Guest(GuestId guestId, String firstName, String lastName, String phoneNumber, StayId stayId)
	{
		this.guestId = guestId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.stayId = stayId;
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
