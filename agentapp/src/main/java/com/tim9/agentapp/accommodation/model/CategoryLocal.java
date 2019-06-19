//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.accommodation.model;

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
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Category")
@Entity
@Table( name= "categories" )
public class CategoryLocal {    
    
    @XmlElement(name = "Category_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long categoryId;
    
    
    @XmlElement(name = "Category_name", required = true)
    @Column ( name="category_name", nullable = false )
    protected String categoryName;
    
    
    @XmlElement(name ="Accommodation_units_of_category")
	@OneToMany (mappedBy="unitCategory")
    protected List<AccommodationUnitLocal> accommodationUnitsOfCategory;


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
