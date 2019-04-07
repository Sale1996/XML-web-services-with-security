package com.tim9.reservationservice.models;

public class Reservation {

	private long id;
	private long roomId;
	private String roomName;
	private float price;
	
	public Reservation() {}
	
	public Reservation(long id, long roomId, String roomName, float price) {
		this.id = id;
		this.roomId = roomId;
		this.roomName = roomName;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRoomId() {
		return roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
