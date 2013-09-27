package com.mejorandola.ejemplo;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

	@Override
	protected void onListItemClick(
			ListView l, View v,
			int position, long id) {
		Room clicked_room = (Room)l.getItemAtPosition(position);
		Intent i = new Intent(this, RoomDetailActivity.class);
		i.putExtra(RoomDetailActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
		i.putExtra(RoomDetailActivity.ROOM_TYPE, clicked_room.getRoomType());
		startActivity(i);
	}
	
	
}
