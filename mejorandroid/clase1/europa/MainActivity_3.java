package com.mejorandola.ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
		super.onStop();
		status_message = "Estado: onCreate";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		super.onStop();
		status_message = "Estado: onDestroy";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		super.onStop();
		status_message = "Estado: onPause";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}



	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		super.onStop();
		status_message = "Estado: onRestart";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		super.onStop();
		status_message = "Estado: onResume";
		txt_msg.setText(status_message);
		Log.e(TAG, status_message);		
	}



	@Override
	protected void onStart() {
		super.onStart();
		super.onStop();
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

}
