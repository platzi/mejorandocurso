package com.mejorandola.ejemplo17.fragments;

import com.mejorandola.ejemplo17.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class SendDataDialogFragment extends DialogFragment {
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
