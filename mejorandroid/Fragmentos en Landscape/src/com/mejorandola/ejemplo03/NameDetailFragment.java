package com.mejorandola.ejemplo03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NameDetailFragment extends Fragment {
	TextView txt_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.fragment_name_detail, container, false);
    	txt_name = (TextView) view.findViewById(R.id.txt_name_detail);    	
		return view;
    }
    
    public void onActivityCreated(){
    	Bundle args = getArguments();
    	String name = (String) args.get(MainActivity.NAME_TAG);
    	if (name != null) {
    		txt_name.setText(name);
    	}
    }
    
    public void setName(String name){
    	txt_name.setText(name);
    }
}
