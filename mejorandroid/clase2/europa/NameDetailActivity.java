package com.mejorandola.ejemplo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class NameDetailActivity extends
		Activity {

	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_detail);
		
		Intent i = getIntent();
		String name = i.getStringExtra(MainActivity.NAME_TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.name_detail,
				menu);
		return true;
	}

}
