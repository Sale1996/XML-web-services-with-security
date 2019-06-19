//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.tim9.accommodationservice.config.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "picture")
@XmlRootElement(name = "Picture")
@Entity
@Table( name= "pictures" )
public class Picture {

    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long pictureId;
    
    @Lob
	@Column ( name = "pic_url" )
    protected String picUrl;
    
    
    @ManyToOne ()
	@JoinColumn (name="accommodation",nullable = false)
    protected Accommodation accommodation;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	@Column
	private LocalDateTime lastUpdated;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
  
    public Long getPictureId() {
        return pictureId;
    }

 
    public void setPictureId(Long value) {
        this.pictureId = value;
    }

 
    public String getPicUrl() {
        return picUrl;
    }

 
    public void setPicUrl(String value) {
        this.picUrl = value;
    }

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

}
