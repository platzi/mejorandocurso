package com.mejorandola.ejemplo06;

import java.util.ArrayList;

import com.mejorandola.ejemplo06.data.CustomAdapter;
import com.mejorandola.ejemplo06.models.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

public class RoomListActivity extends ListActivity {
	/*
	private final static String STANDARD_ROOM = "standard";
	private final static String LUXURY_ROOM = "luxury";				
	private final static String ROOM_NUMBER = "number";
	private final static String ROOM_TYPE = "type";
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_list);

		/*
		List<Map<String, String>> rooms = new ArrayList<Map<String, String>>();
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			HashMap<String, String> one_room = new HashMap<String, String>(2);
			one_room.put("number",room);
			one_room.put("type",STANDARD_ROOM);
	        rooms.add(one_room);							
		}
		
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			HashMap<String, String> one_room = new HashMap<String, String>(2);
			one_room.put(ROOM_NUMBER,room);
			one_room.put(ROOM_TYPE,LUXURY_ROOM);
	        rooms.add(one_room);							
		}
		*/

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
		/*
		SimpleAdapter adapter = new SimpleAdapter(this, rooms, android.R.layout.simple_list_item_2, 
                								  new String[] {ROOM_NUMBER, ROOM_TYPE}, 
                								  new int[] {android.R.id.text1, android.R.id.text2 });
                								  */		
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Room clicked_room = (Room) l.getItemAtPosition(position);
		Intent intent = new Intent (this, RoomDetailActivity.class);
		intent.putExtra(RoomDetailActivity.ROOM_TYPE, clicked_room.getRoomType());
		intent.putExtra(RoomDetailActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
		startActivity(intent);
	}
}
