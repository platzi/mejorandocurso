package com.mejorandola.ejemplo;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.mejorandola.ejemplo.data.CustomAdapter;
import com.mejorandola.ejemplo.models.Room;

public class RoomListActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_list);
		
		ListView list = getListView();
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			Room one_room = new Room(room, Room.STANDARD_ROOM);
			rooms.add(one_room);
		}
		
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			Room one_room = new Room(room, Room.LUXURY_ROOM);
			rooms.add(one_room);
		}
		
		CustomAdapter adapter = new CustomAdapter(this, rooms);
		setListAdapter(adapter);
	}
}
