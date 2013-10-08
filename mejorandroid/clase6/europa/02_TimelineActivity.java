package com.mejorandola.android;

import java.util.ArrayList;

import com.mejorandola.android.db.DBOperations;
import com.mejorandola.android.list.TweetAdapter;
import com.mejorandola.android.model.Tweet;
import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class TimelineActivity extends Activity {

	private ListView listView;
	private DBOperations dbOperations;
	
	private TweetAdapter adapter;
	
	private TimelineReceiver receiver;
	private IntentFilter filter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		listView = (ListView) findViewById(R.id.lv_timeline);
		
		dbOperations = new DBOperations(this);
		receiver = new TimelineReceiver();
		filter = new IntentFilter(ConstantsUtils.NEW_TWEETS_INTENT_FILTER);
		new TweetsSearchTask().execute();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(receiver);
	}
	
	private void updateListView(ArrayList<Tweet> tweets){
		adapter = new TweetAdapter(this, R.layout.row_tweet, tweets);
		listView.setAdapter(adapter);
	}
	
	private void updateListViewWithCache(){
		adapter = new TweetAdapter(this, R.layout.row_tweet, dbOperations.getStatusUpdates());
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private class TweetsSearchTask extends AsyncTask<Object, Void, ArrayList<Tweet>>{

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progressDialog = new ProgressDialog(TimelineActivity.this);
			progressDialog.setMessage(getResources().getString(R.string.label_tweet_search_loader));
			progressDialog.show();
		}
		
		@Override
		protected ArrayList<Tweet> doInBackground(Object... param) {
			return TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
		}
		
		@Override
		protected void onPostExecute(ArrayList<Tweet> tweets){
			progressDialog.dismiss();

			if (tweets.isEmpty()) {
				Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_not_found),
						Toast.LENGTH_SHORT).show();
			} else {
				updateListView(tweets);
				Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_downloaded),
						Toast.LENGTH_SHORT).show();
			}
		}


	}
	
	class TimelineReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			updateListViewWithCache();
		}
	}


}
