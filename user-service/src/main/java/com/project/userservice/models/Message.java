//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//

package com.project.userservice.models;

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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Message")
@Entity
public class Message {

    @XmlElement(name = "Message_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    protected Long messageId;
    
    @XmlElement(name = "Message_body", required = true)
    @Column
    protected String messageBody;
    
    @XmlElement(name = "Message_time", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected LocalDateTime messageTime;
     
    @XmlElement(name = "sender", required = true)
    @Column
    protected Long senderId;
    
    @XmlElement(name = "reciever", required = true)
    @Column
    protected Long recieverId;
    
    @XmlElement(name = "reservationId")
    @Column
    protected Long reservationId;

    /**
     * Gets the value of the mesasgeId property.
     * 
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the mesasgeId property.
     * 
     */
    public void setMessageId(Long value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the messageBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * Sets the value of the messageBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageBody(String value) {
        this.messageBody = value;
    }

    /**
     * Gets the value of the messageTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    /**
     * Sets the value of the messageTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMessageTime(LocalDateTime value) {
        this.messageTime = value;
    }

    
    public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
}
