package com.mejorandola.ejemplo12.fragments;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;
import com.mejorandola.ejemplo12.R;
import com.mejorandola.ejemplo12.activities.MainActivity;
import com.mejorandola.ejemplo12.activities.RoomDetailActivity;
import com.mejorandola.ejemplo12.data.CustomAdapter;
import com.mejorandola.ejemplo12.models.Room;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarsherlock.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class RoomGridFragment extends SherlockFragment implements OnRefreshListener, OnItemClickListener {
	private GridView grid;
	private PullToRefreshAttacher pull_to_refresh_attacher;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.fragment_room_grid, null);
		 grid = (GridView)content.findViewById(R.id.grid_rooms);
		return content;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		pull_to_refresh_attacher = ((MainActivity)getActivity()).getPullToRefreshAttacher();
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
				
		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, false);
		grid.setOnItemClickListener(this);
		grid.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Room clicked_room = (Room) grid.getItemAtPosition(position);
		Intent intent = new Intent (getActivity(), RoomDetailActivity.class);
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
