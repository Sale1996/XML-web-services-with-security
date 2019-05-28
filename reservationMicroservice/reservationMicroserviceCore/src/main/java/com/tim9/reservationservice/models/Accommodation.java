package com.tim9.reservationservice.models;

public class Accommodation {

	private long id;
	private String name;
	
	public Accommodation() {}

	public Accommodation(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
