package com.mejorandola.ejemplo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
				Uri image = Uri.parse("android.resource://" + getPackageName() 
									 + "/drawable/" + R.drawable.hotel1);
				String text_to_share = getResources().getString(R.string.msg_share);
				Intent i = new Intent();
				i.setAction(Intent.ACTION_SEND);
				
				i.putExtra(Intent.EXTRA_TEXT, text_to_share);
				i.putExtra(Intent.EXTRA_STREAM, image);
				i.setType("image/jpeg");
				startActivity(Intent.createChooser(i, "Compartir usando"));
				return true;
			case R.id.action_dialog:
				return true;
			default:
				return super
						.onOptionsItemSelected(item);				
		}
	}	
	
	public void toggleClicked(View v){
		Log.e("TAG","hizo click");
	}
}
