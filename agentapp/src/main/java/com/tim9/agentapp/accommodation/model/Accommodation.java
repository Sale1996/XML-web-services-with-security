//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.accommodation.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Accommodation")
@Entity
@Table( name= "acccomodations" )
public class Accommodation {

    @XmlElement(name = "Local_accommodation_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "localId" )
    protected Long localAccommodationId;
    
    
    @XmlElement(name = "Accommodation_id")
	@Column ( name = "id" )
    protected Long accommodationId;
    
    
    @XmlElement(name = "Name", required = true)
    @Column ( name="name", nullable = true )
    protected String accommodationName;
    

	@XmlElement(name = "Description", required = true)
    @Column ( name="description", nullable = false )
    protected String description;
    
    
    @XmlElement(name = "Number_of_days_before_cancelation")
    @Column ( name="number_of_days_before_cancelation", nullable = false )   
    protected int numberOfDaysBeforeCancelation;
    
    
    @XmlElement(name="City", required = true)
    @ManyToOne ()
	@JoinColumn (name="city",nullable = false)
    protected CityLocal city;
    
    
    @XmlElement(name = "Counted_number_of_beds")
    @Transient
    protected int countedNumberOfBeds;
    
    
    @XmlElement(name="AgentId",required = true)
    @Column(name="agent_id")
    protected Long agentId;

    
    @XmlElement(name = "Accommodation_units")
    @OneToMany (mappedBy="accommodation")
    protected List<AccommodationUnit> units;
    
    
	@XmlElement(name ="Accommodation_comments")
	@OneToMany (mappedBy="accommodation")
    protected List<Comment> comments;
	
	
	@XmlElement(name = "Accommodation_pictures")
	@OneToMany (mappedBy="accommodation")
	protected List<Picture> pictures;

	public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}

    
    public List<AccommodationUnit> getUnits() {
		return units;
	}

	public void setUnits(List<AccommodationUnit> units) {
		this.units = units;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
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


   
    public int getNumberOfDaysBeforeCancelation() {
        return numberOfDaysBeforeCancelation;
    }


    public void setNumberOfDaysBeforeCancelation(int value) {
        this.numberOfDaysBeforeCancelation = value;
    }

	public CityLocal getCity() {
		return city;
	}

	public void setCity(CityLocal city) {
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

	public Long getLocalAccommodationId() {
		return localAccommodationId;
	}

	public void setLocalAccommodationId(Long localAccommodationId) {
		this.localAccommodationId = localAccommodationId;
	}

}
