package com.mejorandola.ejemplo07;

import java.util.ArrayList;


import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;
//import uk.co.senab.actionbarpulltorefresh.extras.actionbarsherlock.PullToRefreshAttacher;

//import com.actionbarsherlock.app.SherlockListActivity;
import com.mejorandola.ejemplo07.R;
import com.mejorandola.ejemplo07.data.CustomAdapter;
import com.mejorandola.ejemplo07.models.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

//public class RoomListActivity extends SherlockListActivity implements OnRefreshListener {
public class RoomListActivity extends ListActivity implements OnRefreshListener {
	private PullToRefreshAttacher pull_to_refresh_attacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_list);

		ListView list = getListView();
		pull_to_refresh_attacher = PullToRefreshAttacher.get(this);
		pull_to_refresh_attacher.addRefreshableView(list, this);		
		
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
	public void onListItemClick(ListView l, View v, int position, long id){
		Room clicked_room = (Room) l.getItemAtPosition(position);
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
