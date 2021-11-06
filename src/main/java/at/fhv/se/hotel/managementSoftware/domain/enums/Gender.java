package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum Gender {
	MALE("Male"), 
	FEMALE("Female"),
	DIVERSE("Divers");
	
	private final String displayValue;
    
    private Gender(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}