package com.mejorandola.ejemplo04;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private int room_type = 0;
	public final static String[] rooms = new String[]{"regular","de lujo"};
	
	private boolean favorite = false; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ToggleButton toggle_recommendation = (ToggleButton)findViewById(R.id.toggle_recommendation);
		toggle_recommendation.setChecked(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
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
	        	Intent i = new Intent();
	        	i.setAction(Intent.ACTION_SEND);
	        	i.putExtra(Intent.EXTRA_TEXT, "Me gust— la habitaci—n tipo " + rooms[room_type] + " del hotel");	        	
	        	i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + getPackageName() + "/drawable/" + R.drawable.hotel1));
	        	i.setType("image/jpeg");
	        	startActivity(Intent.createChooser(i, getResources().getText(R.string.msg_share)));	        	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}	
	
	public void toggleClicked(View v){
		android.util.Log.i("TAG","toggle");
	}

}
