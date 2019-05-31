package com.tim9.agentapp.reservation.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Reservation")

public class ReservationDTO {

	@XmlElement(name = "Reservation_id")
	protected Long reservationId;
	@XmlElement(name = "Date_from", required = true)
    @XmlSchemaType(name = "date")
    protected String dateFrom;
    @XmlElement(name = "Date_to", required = true)
    @XmlSchemaType(name = "date")
    protected String dateTo;
    @XmlElement(name = "Final_price")
    protected float finalPrice;
    @XmlElement(name = "Confirmation")
    protected boolean confirmation;
    @XmlElement(name = "Accommodation_unit", required = true)
    protected Long accommodationUnit;
    @XmlElement(name = "Client", required = true)
    protected Long client;


	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public float getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}
	public boolean isConfirmation() {
		return confirmation;
	}
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	public Long getAccommodationUnit() {
		return accommodationUnit;
	}
	public void setAccommodationUnit(Long accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}
	public Long getClient() {
		return client;
	}
	public void setClient(Long client) {
		this.client = client;
	}
    
    
}
