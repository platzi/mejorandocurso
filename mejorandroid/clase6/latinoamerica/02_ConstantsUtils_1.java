package com.mejorandola.android.utils;

public class ConstantsUtils {
	
	public static final String URL_ROOT_TWITTER_API = "https://api.twitter.com";
	public static final String URL_SEARCH = URL_ROOT_TWITTER_API + "/1.1/search/tweets.json?q=";
	public static final String URL_AUTHENTICATION = URL_ROOT_TWITTER_API + "/oauth2/token";

	public static final String CONSUMER_KEY = "Your Consumer Key";
	public static final String CONSUMER_SECRET = "Your COnsumer Secret";
	
	public static final String MEJORANDROID_TERM = "mejorandroid";
	
	public static final String NEW_TWEETS_INTENT_FILTER = "com.mejorandroid.android.NEW_TWEETS";

}
