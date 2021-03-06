//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.accommodation.model;

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
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Accommodation_unit")
@Entity
@Table( name= "accomodation_units" )
public class AccommodationUnitLocal {

    @XmlElement(name = "LocalAccommodation_unit_id")
    @Id
   	@GeneratedValue ( strategy = GenerationType.IDENTITY )
   	@Column ( name = "localId" )
    protected Long localAccommodationUnitId;
    
    
    @XmlElement(name = "Accommodation_unit_id")
   	@Column ( name = "id" )
    protected Long accommodationUnitId;
    
    
    @XmlElement(name = "Number_of_people")
    @Column ( name="number_of_people", nullable = false )  
    protected int numberOfPeople;
    
    
    @XmlElement(name = "Category", required = true)
    @ManyToOne ()
	@JoinColumn (name="unitCategory",nullable = false)
    protected CategoryLocal unitCategory;
    
    
    @XmlElement(name = "Type", required = true)
    @ManyToOne ()
	@JoinColumn (name="unitType",nullable = false)
    protected TypeLocal unitType;
    
    @XmlElement(name="Accommodation", required = true)
    @ManyToOne ()
	@JoinColumn (name="accommodation",nullable = false)
    protected AccommodationLocal accommodation;

	@XmlElement(name = "Extra_field", required = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accommodation_unit_extra_fields",
            joinColumns = { @JoinColumn( name = "accommodation_id" ) },
            inverseJoinColumns = { @JoinColumn( name = "extra_field_id" ) })
    protected List<ExtraFieldLocal> extraFields;
    
    
    @XmlElement(name = "Price", required = true)
	@OneToMany (mappedBy="accommodationUnit")
    protected List<PriceLocal> price;

    
    
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


    public CategoryLocal getCategory() {
        return unitCategory;
    }


    public void setCategory(CategoryLocal value) {
        this.unitCategory = value;
    }


    public TypeLocal getType() {
        return unitType;
    }


    public void setType(TypeLocal value) {
        this.unitType = value;
    }


    public List<ExtraFieldLocal> getExtraField() {
        if (extraFields == null) {
            extraFields = new ArrayList<ExtraFieldLocal>();
        }
        return this.extraFields;
    }
 
    public List<PriceLocal> getPrice() {
        if (price == null) {
            price = new ArrayList<PriceLocal>();
        }
        return this.price;
    }
    
    public AccommodationLocal getAccommodation() {
		return this.accommodation;
	}


	public void setAccommodation(AccommodationLocal accommodation) {
		this.accommodation = accommodation;
	}

	public Long getLocalAccommodationUnitId() {
		return localAccommodationUnitId;
	}

	public void setLocalAccommodationUnitId(Long localAccommodationUnitId) {
		this.localAccommodationUnitId = localAccommodationUnitId;
	}

}
