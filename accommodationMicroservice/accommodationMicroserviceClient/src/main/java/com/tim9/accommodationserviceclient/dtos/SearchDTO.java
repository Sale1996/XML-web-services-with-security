package com.tim9.accommodationserviceclient.dtos;

import java.util.List;

public class SearchDTO {

	private Integer distance;
	private Long type;
	private Long category;
	private List<Long> extraFields;

	public SearchDTO() {}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public List<Long> getExtraFields() {
		return extraFields;
	}

	public void setExtraFields(List<Long> extraFields) {
		this.extraFields = extraFields;
	}
}
