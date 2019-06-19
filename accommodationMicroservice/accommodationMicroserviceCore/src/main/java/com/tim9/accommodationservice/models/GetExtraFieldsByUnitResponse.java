package com.tim9.accommodationservice.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getExtraFieldsByUnitResponse")
public class GetExtraFieldsByUnitResponse {

	@XmlElement(required = true)
    protected List<ExtraField> extraFields;

	public List<ExtraField> getExtraFields() {
		return extraFields;
	}

	public void setExtraFields(List<ExtraField> extraFields) {
		this.extraFields = extraFields;
	}
}
