package com.mejorandola.ejemplo14.fragments;

import java.util.ArrayList;

import com.mejorandola.ejemplo14.R;
import com.mejorandola.ejemplo14.activities.MainActivity;
import com.mejorandola.ejemplo14.activities.RoomDetailActivity;
import com.mejorandola.ejemplo14.data.App;
import com.mejorandola.ejemplo14.data.CustomAdapter;
import com.mejorandola.ejemplo14.models.Room;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class RoomGridFragment extends Fragment implements OnRefreshListener, OnItemClickListener {
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
		App app = (App)getActivity().getApplicationContext();		
		ArrayList<Room> rooms = app.getRoomFulllist();
				
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
