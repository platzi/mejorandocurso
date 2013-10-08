package com.mejorandola.android;

import android.app.Application;
import android.util.Log;

public class MejoandroidApplication extends Application {
	
	private static final String TAG = MejoandroidApplication.class.getSimpleName();
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreated");
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminated");
	}

}
