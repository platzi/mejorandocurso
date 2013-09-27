package com.mejorandola.ejemplo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentName extends
		Fragment {
	private TextView txt;
	@Override
	public void onActivityCreated(
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(
			LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_name, null);
		txt = (TextView)view.findViewById(R.id.txt_name);
		return view;
	}
	
	public void setName(String name){
		txt.setText(name);
	}
	

}
