package com.tim9.reservationserviceClient.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "reservation_id",
    "rating",
    "comment"
})
@XmlRootElement(name = "Rating")
public class RatingDTO {
	
	@XmlElement(name = "id")
	protected Long id;
	  
    @XmlElement(name = "reservation_id", required = true)
    protected Long reservation_id;
    
    @XmlElement(name = "rating", required = true)
    protected int rating;
    
    @XmlElement(name = "comment")
    protected String comment;

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
  
}