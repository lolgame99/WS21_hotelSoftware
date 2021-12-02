package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum StayStatus {
	CHECKEDIN("Checked-in"),
	CHECKEDOUT("Checked-out");
	
	private final String displayValue;
    
    private StayStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
