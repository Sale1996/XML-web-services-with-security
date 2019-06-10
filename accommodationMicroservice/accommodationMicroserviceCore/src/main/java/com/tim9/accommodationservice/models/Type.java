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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@XmlType(name = "type")
@XmlRootElement(name = "Type")
@Entity
@Table( name= "types" )
public class Type {
	
    @XmlElement(name = "Type_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long typeId;
    
    
    @XmlElement(name = "Type_name", required = true)
	@Column ( name = "name" )
    protected String typeName;
    
    @XmlTransient
    @OneToMany (mappedBy="unitType")
    protected List<AccommodationUnit> units;

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
  
    public Long getTypeId() {
        return typeId;
    }

 
    public void setTypeId(Long value) {
        this.typeId = value;
    }

  
    public String getTypeName() {
        return typeName;
    }

  
    public void setTypeName(String value) {
        this.typeName = value;
    }

}
