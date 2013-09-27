package com.mejorandola.ejemplo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends
		Activity {
	private static final String TAG = "DEBUG";
	private String status_message = "";
	private TextView txt_msg;
	
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt_msg = (TextView)findViewById(R.id.txt_msg);
		status_message = "Estado: onCreate";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);	
		
		Button btn_open = (Button)findViewById(R.id.btn_open_activity);
		btn_open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent start_intent = new Intent(getApplicationContext(), 
										        SecondActivity.class);
				startActivity(start_intent);
				
					
				
			}
		});
		Button btn_close = (Button)findViewById(R.id.btn_close_activity);
		btn_close.setOnClickListener(new CustomOnClickListener());
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		status_message = "Estado: onDestroy";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		status_message = "Estado: onPause";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}



	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		status_message = "Estado: onRestart";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		status_message = "Estado: onResume";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}



	@Override
	protected void onStart() {
		super.onStart();
		status_message = "Estado: onStart";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}



	@Override
	protected void onStop() {
		super.onStop();
		status_message = "Estado: onStop";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);
	}



	@Override
	public boolean onCreateOptionsMenu(
			Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.main, menu);
		return true;
	}

	class CustomOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			finish();
			
		}
		
	}
}
