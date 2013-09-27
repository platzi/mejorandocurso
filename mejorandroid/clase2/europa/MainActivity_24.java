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




public class MainActivity extends FragmentActivity implements SendDataDialogFragment.DialogListener {
	private boolean favorite = false;
	
	public void toggleClicked(View v) {
		Log.e("TAG","toggle");
	}
	
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getSupportMenuInflater().inflate(R.menu.main,  menu);
		getMenuInflater().inflate(R.menu.main,  menu);
		return true;
	}	

	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		switch(item.getItemId()){
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
				Intent share = new Intent();
				share.setAction(Intent.ACTION_SEND);
				String msg = getResources().getString(R.string.msg_share);
				share.putExtra(Intent.EXTRA_TEXT, msg);
				Uri img_res = Uri.parse("android.resource://" + getPackageName() +
										   "/drawable/" + R.drawable.hotel1);	
				share.putExtra(Intent.EXTRA_STREAM, img_res);
				share.setType("image/jpeg");
				startActivity(Intent.createChooser(share, "Compartir"));
				return true;
			case R.id.action_dialog:
				SendDataDialogFragment f = new SendDataDialogFragment();
				f.show(getSupportFragmentManager(),"dialogo");
				return true;
			default:
				return super
						.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onDialogPositiveClick(
			DialogFragment dialog) {
		// TODO Auto-generated method stub
		Log.e("TAG","dijo que s’");
	}

	@Override
	public void onDialogNegativeClick(
			DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}	

}


class SendDataDialogFragment extends DialogFragment {
	public interface DialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	
	DialogListener listener;

	@Override
	public void onAttach(
			Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			listener = (DialogListener)activity;
		} catch (ClassCastException e){}
	}

	@Override
	public Dialog onCreateDialog(
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Titulo")
			.setSingleChoiceItems(R.array.dialog_options,-1, null)
			.setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(
						DialogInterface arg0,
						int arg1) {
						listener.onDialogPositiveClick(SendDataDialogFragment.this);
					
				}
				
			});
		
		return builder.create();
	}
	
	
}
