package com.mejorandola.ejemplo;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends
		FragmentActivity {
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
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(
					AdapterView<?> arg0,
					View arg1,
					int position, long arg3) {
				String name = adapter.getItem(position);
				
				if (getResources().getConfiguration().orientation == 
					Configuration.ORIENTATION_PORTRAIT) {
					Intent action = new Intent(getApplicationContext(),DetailActivity.class);
					action.putExtra(TAG, name);
					startActivity(action);
				} else {
					FragmentName f = (FragmentName) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
					f.setName(name);					
				}
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = input_name.getText().toString();
				input_name.setText("");
				if (!names.contains(input_name)){
					names.add(name);
					adapter.notifyDataSetChanged();
				}
				
			}
		});
	}

}
