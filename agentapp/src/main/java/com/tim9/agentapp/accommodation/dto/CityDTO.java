package com.tim9.agentapp.accommodation.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "City")
public class CityDTO {
	
	@XmlElement(name = "Local_City_id")
    protected Long localCityId;
	
	@XmlElement(name = "city_id")
    protected Long cityId;
	
    @XmlElement(name = "name")
    protected String name;
    
    @XmlElement(name = "X_cord")
    protected double xCord;
    
    
    @XmlElement(name = "Y_cord")
    protected double yCord;


	public Long getCityId() {
		return cityId;
	}


	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}


	public double getxCord() {
		return xCord;
	}


	public void setxCord(double xCord) {
		this.xCord = xCord;
	}


	public double getyCord() {
		return yCord;
	}


	public void setyCord(double yCord) {
		this.yCord = yCord;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Long getLocalCityId() {
		return localCityId;
	}

	public void setLocalCityId(Long localCityId) {
		this.localCityId = localCityId;
	}    
       
}
