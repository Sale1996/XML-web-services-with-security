//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.tim9.accommodationservice.config.LocalDateTimeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accommodationUnit")
@XmlRootElement(name = "Accommodation_unit")
@Entity
@Table( name= "accomodation_units" )
public class AccommodationUnit {

    @XmlElement(name = "Accommodation_unit_id")
    @Id
   	@GeneratedValue ( strategy = GenerationType.IDENTITY )
   	@Column ( name = "id" )
    protected Long accommodationUnitId;
    
    
    @XmlElement(name = "Number_of_people")
    @Column ( name="number_of_people", nullable = false )  
    protected int numberOfPeople;
    
    
    @XmlElement(name = "Category", required = true)
    @ManyToOne ()
	@JoinColumn (name="unitCategory",nullable = false)
    protected Category unitCategory;
    
    
    @XmlElement(name = "Type", required = true)
    @ManyToOne ()
	@JoinColumn (name="unitType",nullable = false)
    protected Type unitType;
    
    @XmlElement(name="Accommodation", required = true)
    @ManyToOne ()
	@JoinColumn (name="accommodation",nullable = false)
    protected Accommodation accommodation;
    
    
    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accommodation_unit_extra_fields",
            joinColumns = { @JoinColumn( name = "accommodation_id" ) },
            inverseJoinColumns = { @JoinColumn( name = "extra_field_id" ) })
    protected List<ExtraField> extraFields;
    
    
    @XmlTransient
    @OneToMany (mappedBy="accommodationUnit")
    protected List<Price> price;

    @XmlElement(name = "LastUpdated")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	@Column
	private LocalDateTime lastUpdated;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    public Long getAccommodationUnitId() {
        return accommodationUnitId;
    }


    public void setAccommodationUnitId(Long value) {
        this.accommodationUnitId = value;
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(int value) {
        this.numberOfPeople = value;
    }


    public Category getCategory() {
        return unitCategory;
    }


    public void setCategory(Category value) {
        this.unitCategory = value;
    }


    public Type getType() {
        return unitType;
    }


    public void setType(Type value) {
        this.unitType = value;
    }


    public List<ExtraField> getExtraField() {
        if (extraFields == null) {
            extraFields = new ArrayList<ExtraField>();
        }
        return this.extraFields;
    }

 
    public List<Price> getPrice() {
        if (price == null) {
            price = new ArrayList<Price>();
        }
        return this.price;
    }

	public Category getUnitCategory() {
		return unitCategory;
	}

	public void setUnitCategory(Category unitCategory) {
		this.unitCategory = unitCategory;
	}

	public Type getUnitType() {
		return unitType;
	}

	public void setUnitType(Type unitType) {
		this.unitType = unitType;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public List<ExtraField> getExtraFields() {
		return extraFields;
	}

	public void setExtraFields(List<ExtraField> extraFields) {
		this.extraFields = extraFields;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}

}
