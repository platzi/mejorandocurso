package com.mejorandola.ejemplo15.fragments;

import java.util.ArrayList;

import com.mejorandola.ejemplo15.R;
import com.mejorandola.ejemplo15.activities.MainActivity;
import com.mejorandola.ejemplo15.activities.RoomDetailActivity;
import com.mejorandola.ejemplo15.data.App;
import com.mejorandola.ejemplo15.data.CustomAdapter;
import com.mejorandola.ejemplo15.models.Room;

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
	public final static int FULL_LIST = 0;
	public final static int FAVORITE_LIST = 1;
	public final static String LIST_TYPE = "tipo";
	
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
		
		int list_type = FULL_LIST;
		Bundle args = getArguments();
		if (args != null) {
		 list_type = args.getInt(LIST_TYPE);
		}
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		App app = (App)getActivity().getApplicationContext();
		if (list_type == FULL_LIST) {
			rooms = app.getRoomFulllist();
		} else if (list_type == FAVORITE_LIST) {
			rooms = app.getFavoriteRooms();
		}
		
		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, true);		
		setListAdapter(adapter);
	}

	@Override
	public void onResume(){
		super.onResume();
		((CustomAdapter) getListAdapter()).notifyDataSetChanged();
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
