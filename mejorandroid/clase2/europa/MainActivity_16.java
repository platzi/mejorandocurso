package com.mejorandola.ejemplo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends
		Activity {
	private static final ArrayList<String> names = new ArrayList<String>();
	public static final String NAME_TAG = "name";

	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText input_name = (EditText) findViewById(R.id.edit_name);
		ListView list = (ListView) findViewById(R.id.list_of_names);
		Button btn_submit = (Button) findViewById(R.id.btn_submit);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_1,
				names);

		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(
					AdapterView<?> arg0,
					View arg1,
					int arg2, long arg3) {
				String name = adapter.getItem(arg2);
				Intent action = new Intent(getApplicationContext(), NameDetailActivity.class);
				action.putExtra(NAME_TAG, name);
			}
		});
		btn_submit
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(
							View v) {
						String name = input_name
								.getText()
								.toString();
						if (!names
								.contains(name)) {
							names.add(name);
							adapter.notifyDataSetChanged();
						}

					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.main, menu);
		return true;
	}

}
