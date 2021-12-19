package at.fhv.se.hotel.managementSoftware.view.forms;

import java.math.BigDecimal;
import java.util.ArrayList;

import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;

public class InvoiceData {
	private ArrayList<Boolean> toPay = new ArrayList<Boolean>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> descriptions = new ArrayList<String>();
	private ArrayList<BigDecimal> prices = new ArrayList<BigDecimal>();
	private ArrayList<String> assignmentIds = new ArrayList<String>();
	private PaymentType paymentType;
	private String stayId;
	private String customerId;
	private String discountRate;
	
	public InvoiceData() {
	}
	
	public void addInfo(StayDetailsDTO stay) {
		this.stayId = stay.getStayId().getId();
		this.customerId = stay.getCustomer().getCustomerId().getId();
		if (stay.getCustomer().getDiscountRate() != null) {
			this.discountRate = stay.getCustomer().getDiscountRate().toString();
		}
	}
	
	public void validate() {
		ArrayList<String> validatedNames = new ArrayList<String>();
		ArrayList<String> validatedDescriptions = new ArrayList<String>();
		ArrayList<BigDecimal> validatedPrices = new ArrayList<BigDecimal>();
		ArrayList<String> validatedAssignmentIds = new ArrayList<String>();
		
		for (int i = 0; i < toPay.size(); i++) {
			if (toPay.get(i) != null) {
				validatedNames.add(names.get(i));
				validatedDescriptions.add(descriptions.get(i));
				validatedPrices.add(prices.get(i));
				validatedAssignmentIds.add(assignmentIds.get(i));
			}
		}
		
		this.names = validatedNames;
		this.descriptions = validatedDescriptions;
		this.prices = validatedPrices;
		this.assignmentIds = validatedAssignmentIds;
		if (this.discountRate == null) {
			this.discountRate = "0";
		}
	}

	public ArrayList<Boolean> getToPay() {
		return toPay;
	}

	public void setToPay(ArrayList<Boolean> toPay) {
		this.toPay = toPay;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public ArrayList<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(ArrayList<String> descriptions) {
		this.descriptions = descriptions;
	}

	public ArrayList<BigDecimal> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<BigDecimal> prices) {
		this.prices = prices;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getStayId() {
		return stayId;
	}

	public void setStayId(String stayId) {
		this.stayId = stayId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public ArrayList<String> getAssignmentIds() {
		return assignmentIds;
	}

	public void setAssignmentIds(ArrayList<String> assignmentIds) {
		this.assignmentIds = assignmentIds;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	
	
}
