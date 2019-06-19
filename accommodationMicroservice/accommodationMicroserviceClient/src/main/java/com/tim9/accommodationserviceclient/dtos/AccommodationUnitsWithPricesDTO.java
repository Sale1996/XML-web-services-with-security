package com.tim9.accommodationserviceclient.dtos;

import java.util.List;

public class AccommodationUnitsWithPricesDTO {

	private List<AccommodationUnitDTO> units;
	private List<Float> prices;

	public AccommodationUnitsWithPricesDTO() {
	}

	public List<AccommodationUnitDTO> getUnits() {
		return units;
	}

	public void setUnits(List<AccommodationUnitDTO> units) {
		this.units = units;
	}

	public List<Float> getPrices() {
		return prices;
	}

	public void setPrices(List<Float> prices) {
		this.prices = prices;
	}
}
