package com.mejorandola.ejemplo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends FragmentActivity 
						  implements SendDataDialogFragment.DialogListener{
	private boolean favorite = false;
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
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

	@Override
	public void dialogPositive(
			DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Hizo click en s’", Toast.LENGTH_LONG).show();
	}

	@Override
	public void dialogNegative(
			DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Hizo click en no", Toast.LENGTH_LONG).show();
		
	}
}

class SendDataDialogFragment extends DialogFragment {
	public interface DialogListener {
		public void dialogPositive(DialogFragment dialog);
		public void dialogNegative(DialogFragment dialog);
	}
	
	DialogListener listener;

	@Override
	public void onAttach(
			Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			listener = (DialogListener)activity;
		} catch(ClassCastException e){
			e.printStackTrace();
		}
	}

	@Override
	public Dialog onCreateDialog(
			Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_title)
			   .setSingleChoiceItems(R.array.array_dialog_options, -1, null)
			   .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(
						DialogInterface dialog,
						int which) {
					listener.dialogPositive(SendDataDialogFragment.this);
					
				}
			} )
			   .setNegativeButton(R.string.msg_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(
						DialogInterface dialog,
						int which) {
					listener.dialogNegative(SendDataDialogFragment.this);
					
				}
			});
		return builder.create();
		
	}
	
	
}