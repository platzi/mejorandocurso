package com.mejorandola.ejemplo07.models;

public class Room {	
	public final static String LUXURY_ROOM = "De lujo";
	public final static String STANDARD_ROOM = "Est‡ndar";
	
	private String room_type;
	private String room_number;
	
	public Room (String room_number, String room_type) {
		this.room_number = room_number;
		this.room_type = room_type;
	}
	
	public String getRoomNumber() {
		return room_number;
	}
	public void setRoomNumber(String room_number) {
		this.room_number = room_number;
	}
	public String getRoomType() {
		return room_type;
	}
	public void setRoomType(String room_type) {
		this.room_type = room_type;
	}
}
