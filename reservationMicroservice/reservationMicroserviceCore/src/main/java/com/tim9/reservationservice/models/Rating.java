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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.tim9.reservationservice.config.LocalDateTimeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rating")
@XmlRootElement(name = "Rating")
@Entity
public class Rating {
	
    @XmlElement(name = "id")
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    protected Long id;
    
    @XmlElement(name = "reservation_id", required = true)
    @Column
    protected Long reservation_id;
    
    @XmlElement(name = "accommodation_id", required = true)
    @Column
    protected Long accommodation_id;
    
    @XmlElement(name = "rating", required = true)
    @Column
    protected int rating;
     
    @XmlElement(name = "verified", required = true)
    @Column
    protected Boolean verified;
    
    @XmlElement(name = "comment")
    @Column
    protected String comment;
    
    @XmlElement(name = "lastUpdated")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column
    protected LocalDateTime lastUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getAccommodation_id() {
		return accommodation_id;
	}

	public void setAccommodation_id(Long accommodation_id) {
		this.accommodation_id = accommodation_id;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	
}
