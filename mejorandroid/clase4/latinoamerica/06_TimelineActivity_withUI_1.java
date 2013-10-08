package com.mejorandola.android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mejorandola.android.list.TweetAdapter;
import com.mejorandola.android.model.Tweet;
import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class TimelineActivity extends Activity {


	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		listView = (ListView) findViewById(R.id.lv_timeline);

		new TweetsSearchTask().execute();
	}
	
	private void updateListView(ArrayList<Tweet> tweets){
		listView.setAdapter(new TweetAdapter(this, R.layout.row_tweet, tweets));
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

			ArrayList<Tweet> tweets = new ArrayList<Tweet>();

			try {
				
				String timeline = TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
				JSONObject jsonResponse = new JSONObject(timeline);
				
				JSONArray jsonArray = jsonResponse.getJSONArray("statuses");
				JSONObject jsonObject;

				for (int i = 0; i < jsonArray.length(); i++) {
					
					jsonObject = (JSONObject) jsonArray.get(i);
					Tweet tweet = new Tweet();

					tweet.setName(jsonObject.getJSONObject("user").getString("name"));
					tweet.setScreenName(jsonObject.getJSONObject("user").getString("screen_name"));
					tweet.setProfileImageUrl(jsonObject.getJSONObject("user").getString("profile_image_url"));
					tweet.setText(jsonObject.getString("text"));
					tweet.setCreatedAt(jsonObject.getString("created_at"));

					tweets.add(i, tweet);
				}

			} catch (Exception e) {
				Log.e("JSON error: ", Log.getStackTraceString(e));        

			}
			return tweets;
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
	
	


}
