//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.accommodation.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Category")

public class CategoryDTO {
    
    @XmlElement(name = "Category_id")
    protected Long categoryId;
    
    
    @XmlElement(name = "Category_name", required = true)
    protected String categoryName;


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
