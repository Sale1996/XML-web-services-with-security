package com.tim9.accommodationservice.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "City")
@Entity
@Table( name= "cities" )
public class City {
	
    @XmlElement(name = "city_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long cityId;
    
    @XmlElement(name = "name")
    @Column (name="name", nullable = false)
    protected String name;
    
    @XmlElement(name = "X_cord")
    @Column ( name="x_cord", nullable = false )
    protected double xCord;
    
    
    @XmlElement(name = "Y_cord")
    @Column ( name="y_cord", nullable = false )
    protected double yCord;
    
    
    @XmlElement(name = "Accommodations")
    @OneToMany (mappedBy="city")
    protected List<Accommodation> accommodations;
       
    @XmlElement(name = "LastUpdated")
	@Column
	private LocalDateTime lastUpdated;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    public Long getCityId() {
		return cityId;
	}


	public void setCityId(Long cityId) {
		this.cityId = cityId;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


    
    

}
