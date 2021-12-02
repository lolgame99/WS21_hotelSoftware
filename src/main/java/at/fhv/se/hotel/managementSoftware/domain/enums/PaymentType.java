package at.fhv.se.hotel.managementSoftware.domain.enums;

public enum PaymentType {
	CASH("Cash"),
	CREDIT_CARD("Credit card"),
	INVOICE("Invoice");
	
	private final String displayPaymentType;
	
	private PaymentType(String displayPaymentType) {
		this.displayPaymentType = displayPaymentType;
	}

	public String getDisplayPaymentType() {
		return displayPaymentType;
	}
	
}
