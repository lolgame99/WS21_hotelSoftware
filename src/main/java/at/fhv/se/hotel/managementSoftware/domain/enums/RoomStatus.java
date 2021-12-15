package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum RoomStatus {
	AVAILABLE("Available"),
	OCCUPIED("Occupied"),
	CLEANING("Cleaning"),
	MAINTENANCE("Maintenance");
	
	private final String displayValue;
    
    private RoomStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
