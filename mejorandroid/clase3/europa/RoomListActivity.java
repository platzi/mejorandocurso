package com.mejorandola.ejemplo;

import com.mejorandola.ejemplo.R;

import android.os.Bundle;
import android.app.ListActivity;

public class RoomListActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_list);
	}
}
