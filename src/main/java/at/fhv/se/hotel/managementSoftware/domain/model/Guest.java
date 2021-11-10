package at.fhv.se.hotel.managementSoftware.domain.model;

public class Guest {
	
	private String guestId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String stayId;
	
	public Guest(String guestId, String firstName, String lastName, String phoneNumber, String stayId)
	{
		this.guestId = guestId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.stayId = stayId;
	}

	public String getGuestId() {
		return guestId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getStayId() {
		return stayId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	
}
