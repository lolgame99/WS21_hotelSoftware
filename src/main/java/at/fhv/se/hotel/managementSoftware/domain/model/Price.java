package at.fhv.se.hotel.managementSoftware.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Price {
	private RoomCategoryId roomCategoryId;
	private BigDecimal cost;
	private LocalDate validFrom;
	private LocalDate validTo;
	
	
	private Price() {
		
	}
	
	public static Price create(RoomCategoryId roomCategoryId, BigDecimal cost, LocalDate validFrom, LocalDate validTo) {
		Price price = new Price();
		price.roomCategoryId = roomCategoryId;
		price.cost = cost;
		price.validFrom = validFrom;
		price.validTo = validTo;
		
		
		return price;
	}

	public RoomCategoryId getRoomCategoryId() {
		return roomCategoryId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}
	
}
