package com.tim9.accommodationservice.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accommodationUnits"
})
@XmlRootElement(name = "getAccommodationUnitsResponse")
public class GetAccommodationUnitsResponse {

	@XmlElement(required = true)
    protected List<AccommodationUnit> accommodationUnits;

	
	
	public List<AccommodationUnit> getAccommodationUnits() {
		return accommodationUnits;
	}

	public void setAccommodationUnits(List<AccommodationUnit> accommodationUnits) {
		this.accommodationUnits = accommodationUnits;
	}


}