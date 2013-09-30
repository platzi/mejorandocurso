package com.mejorandola.ejemplo03;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.content.Intent;
import android.content.res.Configuration;

public class MainActivity extends FragmentActivity {
	final static ArrayList<String> names = new ArrayList<String>();
	public final static String NAME_TAG = "name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_list_item_1, names);
		
		final EditText input_name = (EditText)findViewById(R.id.input_name);
		ListView list_of_names = (ListView)findViewById(R.id.list_of_names);
		Button button_submit_name = (Button)findViewById(R.id.btn_submit_name);		
		list_of_names.setAdapter(adapter);
		
		list_of_names.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				String name = adapter.getItem(position);

				if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
					Intent action = new Intent(getApplicationContext(),NameDetailActivity.class);
					action.putExtra(NAME_TAG, name);
					startActivity(action);
				} else {
					NameDetailFragment frag = (NameDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);					
					frag.setName(name);					
				}

			}
		});
		
		input_name.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String name = input_name.getText().toString();
				input_name.setText("");
				if (!names.contains(name)) {
					names.add(name);
					adapter.notifyDataSetChanged();
				}
				return false;
			}
		});
		
		button_submit_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = input_name.getText().toString();
				input_name.setText("");
				if (!names.contains(name)) {
					names.add(name);
					adapter.notifyDataSetChanged();
				}
			}
		});

		
	}
}
