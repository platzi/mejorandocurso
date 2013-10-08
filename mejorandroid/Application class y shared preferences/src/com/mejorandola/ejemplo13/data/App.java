package com.mejorandola.ejemplo13.data;

import java.util.ArrayList;

import com.mejorandola.ejemplo13.R;
import com.mejorandola.ejemplo13.models.Room;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Room> favorite_rooms = new ArrayList<Room>();
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		rooms = new ArrayList<Room>();
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			Room one_room = new Room(room, Room.STANDARD_ROOM);
	        rooms.add(one_room);							
		}
		
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			Room one_room = new Room(room, Room.LUXURY_ROOM);
	        rooms.add(one_room);						
		}
		
		for (Room room : rooms) {
			SharedPreferences settings = getSharedPreferences(room.getRoomNumber(), 0);
			boolean favorite = settings.getBoolean("favorite", false);
			if (favorite) {
				addFavoriteRoom(room);
			}
		}
	}
		
	public ArrayList<Room> getRoomFulllist() {
		return rooms;
	}

	public ArrayList<Room> getFavoriteRooms() {
		return favorite_rooms;
	}

	public void setFavoriteRooms(ArrayList<Room> favorite_rooms) {
		this.favorite_rooms = favorite_rooms;
	}
 	
	public void addFavoriteRoom(Room room) {
		if (!this.favorite_rooms.contains(room)) {
			this.favorite_rooms.add(room);
		}
	}	

	public void removeFavoriteRoom(Room room) {
		for (Room r : favorite_rooms) {
			if (r.getRoomNumber().equals(room.getRoomNumber())) {
				this.favorite_rooms.remove(r);
				break;
			}
		}
	}		
}
