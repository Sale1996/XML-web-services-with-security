package com.tim9.accommodationservice.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "unitId",
    "extraFieldId"
})
@XmlRootElement(name = "addExtraFieldToUnitRequest")
public class AddExtraFieldToUnitRequest {

	@XmlElement(required = true)
    protected Long unitId;
	
	@XmlElement(required = true)
	protected Long extraFieldId;
	

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getExtraFieldId() {
		return extraFieldId;
	}

	public void setExtraFieldId(Long extraFieldId) {
		this.extraFieldId = extraFieldId;
	}


}
