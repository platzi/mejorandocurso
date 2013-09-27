package com.mejorandola.ejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class DetailActivity extends
		FragmentActivity {

	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Intent i = getIntent();
		String name = i.getStringExtra(MainActivity.TAG);
		FragmentName f = (FragmentName) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
		f.setName(name);
	}

	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.detail, menu);
		return true;
	}

}
