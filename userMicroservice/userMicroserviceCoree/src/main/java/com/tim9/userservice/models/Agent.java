//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//

package com.tim9.userservice.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.tim9.userservice.config.LocalDateTimeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agent")
@XmlRootElement(name = "Agent")
@Entity
public class Agent extends AbstractUser {

	
    @XmlElement(name = "businessRegistrationNumber", required = true)
    @Column
    protected String businessRegistrationNumber;
    
    @XmlElement(name = "activated", required = true)
    @Column
    protected Boolean activated;
    
    @XmlElement(name = "lastSync", required = true)
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column
    protected LocalDateTime lastSync;

	public String getBusinessRegistrationNumber() {
		return businessRegistrationNumber;
	}

	public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
		this.businessRegistrationNumber = businessRegistrationNumber;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public LocalDateTime getLastSync() {
		return lastSync;
	}

	public void setLastSync(LocalDateTime lastSync) {
		this.lastSync = lastSync;
	}

}
