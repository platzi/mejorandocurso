package com.mejorandola.ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends
		Activity {
	private static final String TAG = "Aplicaci—n";
	
	@Override
	protected void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.btn_submit);
		btn.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				EditText edit_name = (EditText) findViewById(R.id.edit_name);
				TextView txt_name = (TextView) findViewById(R.id.txt_msg);
				txt_name.setText("Hola " + edit_name.getText());
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
