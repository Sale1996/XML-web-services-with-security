//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationserviceclient.dtos;

import java.util.ArrayList;
import java.util.List;

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
@XmlRootElement(name = "Accommodation")
public class AccommodationDTO {




	@XmlElement(name = "Accommodation_id")
    protected Long accommodationId;
	
	
    @XmlElement(name = "Name", required = true)
    protected String accommodationName;
    
    
    @XmlElement(name = "Description", required = true)
    protected String description;
    
    
    @XmlElement(name="City", required = true)
    protected CityDTO city;
    
    @XmlElement(name = "Counted_number_of_beds")
    protected int countedNumberOfBeds;
    
    @XmlElement(name="AgentId",required = true)
    protected Long agentId;
    

	@XmlElement(name = "X_cord")
    protected double xCord;
    
    
    @XmlElement(name = "Y_cord")
    protected double yCord;
    
    
    @XmlElement(name = "Number_of_days_before_cancelation")
    protected int numberOfDaysBeforeCancelation;

   
    
    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long value) {
        this.accommodationId = value;
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }


    public double getXCord() {
        return xCord;
    }


    public void setXCord(double value) {
        this.xCord = value;
    }

    public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}


    public double getYCord() {
        return yCord;
    }


    public void setYCord(double value) {
        this.yCord = value;
    }


    public int getNumberOfDaysBeforeCancelation() {
        return numberOfDaysBeforeCancelation;
    }


    public void setNumberOfDaysBeforeCancelation(int value) {
        this.numberOfDaysBeforeCancelation = value;
    }

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public int getCountedNumberOfBeds() {
		return countedNumberOfBeds;
	}

	public void setCountedNumberOfBeds(int countedNumberOfBeds) {
		this.countedNumberOfBeds = countedNumberOfBeds;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

}
