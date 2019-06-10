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
@XmlType(name = "category")
@XmlRootElement(name = "Category")
@Entity
@Table( name= "categories" )
public class Category {

    @XmlElement(name = "Category_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long categoryId;
    
    
    @XmlElement(name = "Category_name", required = true)
    @Column ( name="category_name", nullable = false )
    protected String categoryName;
    
    
    @XmlTransient
    @OneToMany (mappedBy="unitCategory")
    protected List<AccommodationUnit> accommodationUnitsOfCategory;

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

    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long value) {
        this.categoryId = value;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String value) {
        this.categoryName = value;
    }

}
