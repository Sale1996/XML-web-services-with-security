//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.reservationservice.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.tim9.reservationservice.config.LocalDateTimeAdapter;

import ch.qos.logback.core.net.server.Client;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservation")
@XmlRootElement(name = "Reservation")
@Entity
public class Reservation {

    @XmlElement(name = "Reservation_id")
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    protected Long reservationId;
    @XmlElement(name = "Date_from", required = true)
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column
    protected LocalDateTime dateFrom;
    @XmlElement(name = "Date_to", required = true)
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column
    protected LocalDateTime dateTo;
    @XmlElement(name = "Final_price")
    @Column
    protected float finalPrice;
    @XmlElement(name = "Confirmation")
    @Column
    protected boolean confirmation;
    @XmlElement(name = "Accommodation_unit", required = true)
    @Column
    protected Long accommodationUnit;
    @XmlElement(name = "Client", required = true)
    @Column
    protected Long client;
    @XmlTransient
    @Column
    protected LocalDateTime LastUpdated;

    public Reservation() {}
    
    public Reservation(long id, long roomId, String roomName, float price) {
		this.reservationId = id;
		this.accommodationUnit = roomId;
	//	this.roomName = roomName;			VIDECEMO ZA OVO DA LI JE NEOPHODNO
	//	this.price = price;					NISU SE BAS USAGLASILI LUKA I SALE
	}
    
    /**
     * Gets the value of the reservationId property.
     * 
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     */
    public void setReservationId(long value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFrom(LocalDateTime value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDateTime getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTo(LocalDateTime value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the finalPrice property.
     * 
     */
    public float getFinalPrice() {
        return finalPrice;
    }

    /**
     * Sets the value of the finalPrice property.
     * 
     */
    public void setFinalPrice(float value) {
        this.finalPrice = value;
    }

    /**
     * Gets the value of the confirmation property.
     * 
     */
    public boolean isConfirmation() {
        return confirmation;
    }

    /**
     * Sets the value of the confirmation property.
     * 
     */
    public void setConfirmation(boolean value) {
        this.confirmation = value;
    }

    /**
     * Gets the value of the accommodationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnit }
     *     
     */
    public Long getAccommodationUnit() {
        return accommodationUnit;
    }

    /**
     * Sets the value of the accommodationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnit }
     *     
     */
    public void setAccommodationUnit(Long value) {
        this.accommodationUnit = value;
    }

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Long getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClient(Long value) {
        this.client = value;
    }

	public LocalDateTime getLastUpdated() {
		return LastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		LastUpdated = lastUpdated;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

}
