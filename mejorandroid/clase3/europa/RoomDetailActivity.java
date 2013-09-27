package com.mejorandola.ejemplo;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mejorandola.ejemplo.fragments.SendDataDialogFragment;
import com.mejorandola.ejemplo.models.Room;

public class RoomDetailActivity extends FragmentActivity implements SendDataDialogFragment.DialogListener {
	public final static String ROOM_TYPE = "tipo de habitaci—n";
	public final static String ROOM_NUMBER = "nœmero de habitaci—n";
	public final static String DIALOG_TAG = "dialogo";
	
	private Room room;	
	private boolean favorite = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Intent intent = getIntent();
		room = new Room(intent.getStringExtra(ROOM_NUMBER), 
					    intent.getStringExtra(ROOM_TYPE));
		ToggleButton toggle_recommendation = (ToggleButton)findViewById(R.id.toggle_recommendation);
		toggle_recommendation.setChecked(true);

		int resource = -1;
		if (room.getRoomType().equals(Room.STANDARD_ROOM)) {
			resource = R.drawable.hotel1; 
		} else {
			resource = R.drawable.hotel2;
		}
		
		ImageView img_header = (ImageView)findViewById(R.id.img_header);
		img_header.setImageResource(resource);
		setTitle(room.getRoomNumber());		
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
	        	i.putExtra(Intent.EXTRA_TEXT, "Me gust— la habitaci—n " + room.getRoomNumber() + " tipo " + room.getRoomType());	        	
	        	i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + getPackageName() + "/drawable/" + R.drawable.hotel1));
	        	i.setType("image/jpeg");
	        	startActivity(Intent.createChooser(i, getResources().getText(R.string.msg_share)));	        	
	            return true;
	        case R.id.action_dialog:
	        	SendDataDialogFragment frag = new SendDataDialogFragment();
	        	frag.show(getFragmentManager(),DIALOG_TAG);	        	
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}	
	
	public void toggleClicked(View v){
		Toast.makeText(getApplicationContext(), "Toggle", Toast.LENGTH_SHORT).show();
	}
	
	@Override
    public void onDialogPositiveClick(DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Click " + getResources().getString(R.string.msg_yes), Toast.LENGTH_SHORT).show();
    }
	
	@Override
    public void onDialogNegativeClick(DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Click " + getResources().getString(R.string.msg_no), Toast.LENGTH_SHORT).show();
    }	
}


