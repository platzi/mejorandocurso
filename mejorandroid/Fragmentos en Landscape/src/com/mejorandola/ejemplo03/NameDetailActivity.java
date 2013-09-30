package com.mejorandola.ejemplo03;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;

public class NameDetailActivity extends FragmentActivity {
	private String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_detail);
		
		Intent i = getIntent();
		name = i.getStringExtra(MainActivity.NAME_TAG);
		NameDetailFragment frag = (NameDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
		frag.setName(name);
	}

}
