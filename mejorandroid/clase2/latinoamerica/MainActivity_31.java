package com.mejorandola.ejemplo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends
		FragmentActivity {
	private boolean favorite = false;
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_fav:
				Drawable icon = null;
				if (favorite) {
					icon = getResources().getDrawable(R.drawable.rating_not_important);
				} else {
					icon = getResources().getDrawable(R.drawable.rating_important);
				}
				favorite = !favorite;
				item.setIcon(icon);
				return true;
			case R.id.action_share:
				return true;
			case R.id.action_dialog:
				return true;
			default:
				return super
						.onOptionsItemSelected(item);				
		}
	}	
	
	
}
