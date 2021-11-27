package at.fhv.se.hotel.managementSoftware.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import at.fhv.se.hotel.managementSoftware.domain.model.Price;

public class PriceDetailsDTO {
	private BigDecimal cost;
	private LocalDate validFrom;
	private LocalDate validTo;
	
	private PriceDetailsDTO() {
	}
	
	public static PriceDetailsDTO createFromPrice(Price price) {
		PriceDetailsDTO dto = new PriceDetailsDTO();
		dto.cost = price.getCost();
		dto.validFrom = price.getValidFrom();
		dto.validTo = price.getValidTo();
		return dto;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
	
	
}
