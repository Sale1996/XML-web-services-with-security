package com.tim9.accommodationservice.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getPriceResponse")
public class GetPriceResponse {
	
	@XmlElement(required = true)
    protected List<Price> price;

	public List<Price> getPrice() {
		return price;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}
	
	
	
}
