package at.fhv.se.hotel.managementSoftware.domain.model;

public class Guest {
	
	private String guestId;
	private String name;
	private String phoneNumber;
	private String stayId;
	
	public Guest(String guestId, String name, String phoneNumber, String stayId)
	{
		this.guestId = guestId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.stayId = stayId;
	}

	public String getGuestId() {
		return guestId;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getStayId() {
		return stayId;
	}

	
}
