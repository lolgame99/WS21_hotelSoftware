package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum BookingStatus {
	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	CANCELLED("Canceled"),
	PAID("Paid"),
	ARRIVED("Arrived");
	
	private final String displayValue;
    
    private BookingStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
