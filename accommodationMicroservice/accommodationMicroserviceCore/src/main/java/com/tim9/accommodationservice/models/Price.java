//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Price")
@Entity
@Table( name= "prices" )
public class Price {

    @XmlElement(name = "Price_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long priceId;
    
    
    @XmlElement(name = "Date_from", required = true)
    @XmlSchemaType(name = "date")
	@Column ( name = "date_from" )
    protected LocalDate dateFrom;
    
    
    @XmlElement(name = "Amount")
    @Column ( name = "amount" )
    protected float amount;
    
    @XmlElement(name = "Date_to", required = true)
    @XmlSchemaType(name = "date")
	@Column ( name = "date_to" )
    protected LocalDate dateTo;
    
    
    @XmlElement(name ="Accommodation_Unit")
    @ManyToOne ()
	@JoinColumn (name="accommodationUnit",nullable = false)
    protected AccommodationUnit accommodationUnit;

    @XmlElement(name = "LastUpdated")
	@Column
	private LocalDateTime lastUpdated;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
  
    public Long getPriceId() {
        return priceId;
    }

 
    public void setPriceId(Long value) {
        this.priceId = value;
    }

 
    public LocalDate getDateFrom() {
        return dateFrom;
    }

 
    public void setDateFrom(LocalDate value) {
        this.dateFrom = value;
    }

  
    public float getAmount() {
        return amount;
    }

  
    public void setAmount(float value) {
        this.amount = value;
    }

  
    public LocalDate getDateTo() {
        return dateTo;
    }

  
    public void setDateTo(LocalDate value) {
        this.dateTo = value;
    }

}
