package com.mejorandola.ejemplo19.fragments.dialogs;

import com.mejorandola.ejemplo19.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SelectMapTypeDialog extends DialogFragment {
    public static final int TIPO_NORMAL = 0;
    public static final int TIPO_HIBRIDO = 1;
    public static final int TIPO_SATELITE = 2;
    public static final int TIPO_TERRENO = 3;
    
    public interface DialogListener {
    	public int getCurrentMapType();
    	public void onSelectMapTypeDialogSelected(int type);
    }
    
    DialogListener listener;
    int current_map_type = 0;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            listener = (DialogListener) activity;
            current_map_type = listener.getCurrentMapType();
        } catch (ClassCastException e) {}
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_select_map_type_title)
        	   .setSingleChoiceItems(R.array.map_types, current_map_type, new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                listener.onSelectMapTypeDialogSelected(which);
		                dialog.dismiss();
		            }
        });
        return builder.create();
    }    
}
