package com.mejorandola.ejemplo10.fragments;

import java.util.ArrayList;

import com.mejorandola.ejemplo10.R;
import com.mejorandola.ejemplo10.activities.MainActivity;
import com.mejorandola.ejemplo10.activities.RoomDetailActivity;
import com.mejorandola.ejemplo10.data.CustomAdapter;
import com.mejorandola.ejemplo10.models.Room;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RoomListFragment extends ListFragment implements OnRefreshListener {
	private PullToRefreshAttacher pull_to_refresh_attacher;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_room_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ListView list = getListView();
		pull_to_refresh_attacher = ((MainActivity)getActivity()).getPullToRefreshAttacher();
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
		
		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, true);		
		setListAdapter(adapter);		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Room clicked_room = (Room) l.getItemAtPosition(position);
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
