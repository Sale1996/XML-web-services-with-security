//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Extra_field")
@Entity
@Table( name= "extra_fields" )
public class ExtraField {

    @XmlElement(name = "Extra_field_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long extraFieldId;
    
    
    @XmlElement(name = "Extra_field_name", required = true)
	@Column ( name = "name" )
    protected String extraFieldName;
    
    
    @XmlElement(name = "Extra_price")
	@Column ( name = "extra_price" )
    protected float extraPrice;
    
    
    @XmlElement(name = "Optional")
	@Column ( name = "optional" )
    protected boolean optional;

    
    @XmlElement(name= "Accommodation_Units")
	@ManyToMany ( fetch = FetchType.LAZY, mappedBy = "extraFields" )
    protected List<AccommodationUnit> accommodationUnits;
    
    @XmlElement(name = "LastUpdated")
	@Column
	private LocalDateTime lastUpdated;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    public Long getExtraFieldId() {
        return extraFieldId;
    }

    public void setExtraFieldId(Long value) {
        this.extraFieldId = value;
    }

 
    public String getExtraFieldName() {
        return extraFieldName;
    }

 
    public void setExtraFieldName(String value) {
        this.extraFieldName = value;
    }

  
    public float getExtraPrice() {
        return extraPrice;
    }

  
    public void setExtraPrice(float value) {
        this.extraPrice = value;
    }

 
    public boolean isOptional() {
        return optional;
    }

  
    public void setOptional(boolean value) {
        this.optional = value;
    }

}
