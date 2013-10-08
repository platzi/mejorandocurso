package com.mejorandola.android;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class MejoandroidApplication extends Application {
	
	private static final String TAG = MejoandroidApplication.class.getSimpleName();
	private boolean serviceRunningFlag;
	
	public boolean isServiceRunning(){
		return serviceRunningFlag;
	}
	
	public void setServiceRunningFlag(boolean serviceRunningFlag){
		this.serviceRunningFlag = serviceRunningFlag;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreated");
		startService(new Intent(this, UpdaterService.class));
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminated");
		stopService(new Intent(this, UpdaterService.class));
	}

}
