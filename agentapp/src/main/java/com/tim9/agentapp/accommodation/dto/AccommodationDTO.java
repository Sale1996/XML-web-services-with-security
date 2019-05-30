//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.accommodation.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Accommodation")
public class AccommodationDTO {

	@XmlElement(name = "LocalAccommodation_id")
    protected Long localAccommodationId;

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
    
    
    public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}


	@XmlElement(name = "X_cord")
    protected double xCord;
    
    
    @XmlElement(name = "Y_cord")
    protected double yCord;
    
    
    @XmlElement(name = "Number_of_days_before_cancelation")
    protected int numberOfDaysBeforeCancelation;

    
	@XmlElement(name = "Accommodation_pictures")
	protected List<PictureDTO> pictures = new ArrayList<PictureDTO>();

	
    public List<PictureDTO> getPictures() {
		return pictures;
	}

	public void setPictures(List<PictureDTO> pictures) {
		this.pictures = pictures;
	}
	
	public Long getLocalAccommodationId() {
        return localAccommodationId;
    }

    public void setLocalAccommodationId(Long value) {
        this.localAccommodationId = value;
    }
    
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

}
