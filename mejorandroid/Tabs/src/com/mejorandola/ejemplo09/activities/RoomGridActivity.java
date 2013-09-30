package com.mejorandola.ejemplo09.activities;

import java.util.ArrayList;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;

import com.mejorandola.ejemplo09.R;
import com.mejorandola.ejemplo09.data.CustomAdapter;
import com.mejorandola.ejemplo09.models.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.app.Activity;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;

public class RoomGridActivity extends Activity implements OnRefreshListener, OnItemClickListener {
	private GridView grid;
	private PullToRefreshAttacher pull_to_refresh_attacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_grid);

		grid = (GridView)findViewById(R.id.grid_rooms);
		pull_to_refresh_attacher = PullToRefreshAttacher.get(this);
		pull_to_refresh_attacher.addRefreshableView(grid, this);
		ArrayList<Room> rooms = new ArrayList<Room>();
				
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			Room one_room = new Room(room, Room.STANDARD_ROOM);
	        rooms.add(one_room);							
		}
		
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			Room one_room = new Room(room, Room.LUXURY_ROOM);
	        rooms.add(one_room);						
		}
				
		CustomAdapter adapter = new CustomAdapter(this, rooms, false);	
		grid.setOnItemClickListener(this);	
		grid.setAdapter(adapter);		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Room clicked_room = (Room) grid.getItemAtPosition(position);
		Intent intent = new Intent (this, RoomDetailActivity.class);
		intent.putExtra(RoomDetailActivity.ROOM_TYPE, clicked_room.getRoomType());
		intent.putExtra(RoomDetailActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
		startActivity(intent);
	}
	
	@Override
	public void onRefreshStarted(View view) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                pull_to_refresh_attacher.setRefreshComplete();
            }
        }.execute();
	}
}
