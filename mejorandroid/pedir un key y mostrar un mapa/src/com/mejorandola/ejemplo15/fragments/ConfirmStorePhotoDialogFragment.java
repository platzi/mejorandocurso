package com.mejorandola.ejemplo15.fragments;

import com.mejorandola.ejemplo15.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ConfirmStorePhotoDialogFragment extends DialogFragment {
    public interface DialogListener {
        public void confirmStorePhotoDialogPositiveClick(DialogFragment dialog);
        public void confirmStorePhotoDialogNegativeClick(DialogFragment dialog);
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
        builder.setTitle(R.string.dialog_store_title)
               .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   listener.confirmStorePhotoDialogPositiveClick(ConfirmStorePhotoDialogFragment.this);
                   }
               })
               .setNegativeButton(R.string.msg_no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {	
                	   listener.confirmStorePhotoDialogNegativeClick(ConfirmStorePhotoDialogFragment.this);
                	   
                   }
               });
        return builder.create();
    }
}
