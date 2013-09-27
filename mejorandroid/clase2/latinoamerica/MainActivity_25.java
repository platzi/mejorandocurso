package com.mejorandola.ejemplo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends
		Activity {
	private final static ArrayList<String> names = new ArrayList<String>();
	public final static String TAG = "name";
	
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
											android.R.layout.simple_list_item_1, names);
		final EditText input_name = (EditText)findViewById(R.id.edit_name);
		Button submit = (Button)findViewById(R.id.btn_submit);
		ListView list = (ListView)findViewById(R.id.list_view);
	}

}
