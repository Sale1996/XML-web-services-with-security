package com.tim9.accommodationserviceclient.dtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accommodationUnitId",
    "description",
    "xCord",
    "yCord",
    "numberOfDaysBeforeCancelation"
})
@XmlRootElement(name = "AccommodationSearch")
public class AccommodationSearchDTO {

	@XmlElement(name = "Accommodation_id")
    protected Long accommodationId;
	
  
    @XmlElement(name = "Number_of_beds")
    protected int numberOfBeds;
    


    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long value) {
        this.accommodationId = value;
    }



}
