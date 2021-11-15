package at.fhv.se.hotel.managementSoftware.domain.model;

public class Guest {
	
	private GuestId guestId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	
	private Guest() {
	}

	public static Guest create(GuestId guestId, String firstName, String lastName, String number) {
		Guest guest = new Guest();
		guest.guestId = guestId;
		guest.firstName = firstName;
		guest.lastName = lastName;
		guest.phoneNumber = number;
		return guest;
	}
	
	public static Guest createFromCustomer(GuestId guestId, Customer customer) {
		Guest guest = new Guest();
		guest.guestId = guestId;
		guest.firstName = customer.getFirstName();
		guest.lastName = customer.getLastName();
		guest.phoneNumber = customer.getPhoneNumber();
		return guest;
	}
	
	public Guest addMiddleName(String name) {
		if(this.middleName == null) {
			this.middleName = name;
		}
		return this;
	}

	public GuestId getGuestId() {
		return guestId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	
	
}
