package com.mejorandola.ejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class NameDetailActivity extends
		FragmentActivity {

	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_detail);
		
		Intent i = getIntent();
		String name = i.getStringExtra(MainActivity.NAME_TAG);
		FragmentManager fm = getSupportFragmentManager();
		NameDetailFragment frag = (NameDetailFragment) 
							fm.findFragmentById(R.id.detail_fragment);
		frag.setName(name);
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
