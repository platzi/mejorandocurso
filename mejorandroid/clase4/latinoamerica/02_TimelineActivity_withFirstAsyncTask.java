package com.mejorandola.android;

import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class TimelineActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		new TweetsSearchTask().execute();
	}
	
	private class TweetsSearchTask extends AsyncTask<Object, Void, Void>{

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progressDialog = new ProgressDialog(TimelineActivity.this);
			progressDialog.setMessage(getResources().getString(R.string.label_tweet_search_loader));
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Object... arg0) {
			TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			progressDialog.dismiss();
		}


	}
	
	


}
