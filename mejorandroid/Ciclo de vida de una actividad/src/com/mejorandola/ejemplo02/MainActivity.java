package com.mejorandola.ejemplo02;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static String INFO_TAG = "INFO!";	
	private String status_message;
	private TextView txt_msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_open = (Button)findViewById(R.id.btn_open_activity);
		btn_open.setOnClickListener(new CustomOnClickListener());
		
		Button btn_close = (Button)findViewById(R.id.btn_finish);
		btn_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		status_message = "Estado: onCreate";
		txt_msg = (TextView)findViewById(R.id.txt_msg);
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		status_message = "Estado: onStart";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);		
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		status_message = "Estado: onRestart";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		status_message = "Estado: onResume";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);		
	}

	@Override
	protected void onPause(){
		super.onPause();
		status_message = "Estado: onPause";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);
	}

	@Override
	protected void onStop(){
		super.onStop();
		status_message = "Estado: onStop";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);		
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		status_message = "Estado: onDestroy";
		txt_msg.setText(status_message);
		android.util.Log.i(INFO_TAG,status_message);		
	}		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class CustomOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent starter = new Intent(getApplicationContext(), SecondActivity.class);
			startActivity(starter);
		}

	}
}
