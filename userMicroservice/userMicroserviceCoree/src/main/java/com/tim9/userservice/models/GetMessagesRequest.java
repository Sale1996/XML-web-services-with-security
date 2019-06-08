package com.tim9.userservice.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getMessagesRequest")
public class GetMessagesRequest {

	@XmlElement(required = true)
    protected Long reservationId;

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long id) {
		this.reservationId = id;
	}
}
