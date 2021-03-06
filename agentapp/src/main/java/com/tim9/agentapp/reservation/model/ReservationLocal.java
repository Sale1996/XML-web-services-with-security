//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.agentapp.reservation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.tim9.agentapp.accommodation.model.AccommodationUnitLocal;

import ch.qos.logback.core.net.server.Client;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Reservation_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Date_from" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Date_to" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Final_price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Confirmation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.lukacvetinovic.com/backend-tim-9}Accommodation_unit"/>
 *         &lt;element name="Client" type="{http://www.lukacvetinovic.com/backend-tim-9-user}Client"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {})
@XmlRootElement(name = "Reservation")
@Entity
@Table( name= "reservations" )
public class ReservationLocal {

    @XmlElement(name = "Local_reservation_id")
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    protected Long localReservationId;
    @XmlElement(name = "Reservation_id")
	@Column
    protected Long reservationId;
    @XmlElement(name = "Date_from", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected LocalDateTime dateFrom;
    @XmlElement(name = "Date_to", required = true)
    @XmlSchemaType(name = "date")
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
    @XmlElement(name = "Accommodation")
    @Column
    protected long accommodation;
    @XmlElement(name = "Client", required = true)
    @Column
    protected Long client;

    public ReservationLocal() {}
    
    public ReservationLocal(Long id, Long roomId, String roomName, float price) {
		this.reservationId = id;
		this.accommodationUnit = roomId;
	//	this.roomName = roomName;			VIDECEMO ZA OVO DA LI JE NEOPHODNO
	//	this.price = price;					NISU SE BAS USAGLASILI LUKA I SALE
	}
    
    /**
     * Gets the value of the reservationId property.
     * 
     */
    public Long getReservationId() {
        return reservationId;
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
     *     {@link AccommodationUnitLocal }
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
     *     {@link AccommodationUnitLocal }
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

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getLocalReservationId() {
		return localReservationId;
	}

	public void setLocalReservationId(Long localReservationId) {
		this.localReservationId = localReservationId;
	}

	public long getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(long accommodation) {
		this.accommodation = accommodation;
	}

}
