package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum PaymentType {
	CASH("cash"),
	CREDIT_CARD("creditCard"),
	INVOICE("invoice");
	
	private final String displayPaymentType;
	
	private PaymentType(String displayPaymentType) {
		this.displayPaymentType = displayPaymentType;
	}

	public String getDisplayPaymentType() {
		return displayPaymentType;
	}
	
}
