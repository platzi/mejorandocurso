package com.mejorandola.ejemplo20.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.mejorandola.ejemplo20.R;
import com.mejorandola.ejemplo20.models.Place;
import com.mejorandola.ejemplo20.models.Room;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {	
	private ArrayList<Room> rooms;
	private ArrayList<Place> places;
	private ArrayList<Room> favorite_rooms;
	private final static String PLACES_FILE_NAME = "hotels.json";
	
	@Override
	public void onCreate() {
		super.onCreate();
		loadRoomsAndFavoriteRooms();
		loadPlacesFromAssets();
	}
		
	private void loadPlacesFromAssets() {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(getAssets().open(PLACES_FILE_NAME)));
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line); 
			}
			reader.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}

		String json = builder.toString();
        if (!json.equals("")) {
        	Gson gson = new Gson();
        	setPlaces(new ArrayList<Place>(Arrays.asList(gson.fromJson(json, Place[].class))));
        }		
	}
	
	private void loadRoomsAndFavoriteRooms() {
		rooms = new ArrayList<Room>();
		favorite_rooms = new ArrayList<Room>();
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

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}	

}
