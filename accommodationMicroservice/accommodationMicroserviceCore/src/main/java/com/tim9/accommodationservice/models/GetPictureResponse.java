package com.tim9.accommodationservice.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getPictureResponse")
public class GetPictureResponse {

	@XmlElement(required = true)
    protected List<Picture> picture;

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

	

}
