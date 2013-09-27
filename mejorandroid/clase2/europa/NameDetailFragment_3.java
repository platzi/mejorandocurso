package com.mejorandola.ejemplo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NameDetailFragment extends
		Fragment {

	private TextView txt_name;

	public void setName(String name) {
		if (name != null) {
			txt_name.setText(name);
		}
	}

	@Override
	public void onActivityCreated(
			Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			String name = (String) arguments
				.get(MainActivity.NAME_TAG);
			setName(name);
		}
	}

	@Override
	public View onCreateView(
			LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(
						R.layout.fragment_name_detail,
						null);
		txt_name = (TextView) view
				.findViewById(R.id.txt_name);
		return view;
	}

}
