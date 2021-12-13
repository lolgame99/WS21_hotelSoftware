package at.fhv.se.hotel.managementSoftware.domain.model;

import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidGuestException;

public class Guest {
	private long id;
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
	
	public static Guest createFromCustomer(GuestId guestId, Customer customer) throws InvalidGuestException {
		if (customer instanceof CompanyCustomer) {
			throw new InvalidGuestException("Guest could not be created <br> Must fill guest information if stay is booked by a company");
		}
		IndividualCustomer convertedCustomer = (IndividualCustomer) customer;
		Guest guest = new Guest();
		guest.guestId = guestId;
		guest.firstName = convertedCustomer.getFirstName();
		guest.lastName = convertedCustomer.getLastName();
		guest.phoneNumber = convertedCustomer.getPhoneNumber();
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

	public long getId() {
		return id;
	}

	
	
}
