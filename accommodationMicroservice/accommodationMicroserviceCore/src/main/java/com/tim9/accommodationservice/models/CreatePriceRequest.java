package com.tim9.accommodationservice.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "createPriceRequest")
public class CreatePriceRequest {
	
	@XmlElement(required = true)
	protected Price price;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	
}
