package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum PaymentStatus {
	PAID("Paid"),
	UNPAID("Unpaid");
	
	private final String displayServicePayment;
	
	private PaymentStatus(String displayServicePayment) {
		this.displayServicePayment = displayServicePayment;
	}

	public String getdisplayServicePayment() {
		return displayServicePayment;
	}
}
