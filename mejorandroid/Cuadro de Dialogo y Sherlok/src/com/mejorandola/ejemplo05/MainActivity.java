package com.mejorandola.ejemplo05;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends SherlockFragmentActivity implements SendDataDialogFragment.DialogListener {
	private int room_type = 0;
	public final static String[] rooms = new String[]{"regular","de lujo"};
	public final static String DIALOG_TAG = "dialogo";
	
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
		getSupportMenuInflater().inflate(R.menu.main, menu);
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
	        case R.id.action_dialog:
	        	SendDataDialogFragment frag = new SendDataDialogFragment();
	        	frag.show(getSupportFragmentManager(),DIALOG_TAG);	        	
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

class SendDataDialogFragment extends DialogFragment {
    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    
    DialogListener listener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (ClassCastException e) {}
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title)
        	   .setSingleChoiceItems(R.array.array_disclaimer, -1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), "Click " + which, Toast.LENGTH_SHORT).show();
					}
				
        	   })
               .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   listener.onDialogPositiveClick(SendDataDialogFragment.this);
                   }
               })
               .setNegativeButton(R.string.msg_no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {	
                	   listener.onDialogPositiveClick(SendDataDialogFragment.this);
                	   
                   }
               });
        return builder.create();
    }
}

