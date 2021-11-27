package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum ServicePayment {
	PAID("Paid"),
	UNPAID("Unpaid");
	
	private final String displayServicePayment;
	
	private ServicePayment(String displayServicePayment) {
		this.displayServicePayment = displayServicePayment;
	}

	public String getdisplayServicePayment() {
		return displayServicePayment;
	}
}
