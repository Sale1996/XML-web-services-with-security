package com.tim9.accommodationservice.models;

public class Accommodation {

	private long id;
	private String name;
	private String owner;
	
	public Accommodation() {}

	public Accommodation(long id, String name, String owner) {
		this.id = id;
		this.name = name;
		this.owner = owner;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
