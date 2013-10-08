package com.mejorandola.android;

import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {
	
	private final String TAG = UpdaterService.class.getSimpleName();
	
	static final int DELAY = 60000;
	private boolean runFlag = false;
	private Updater updater;
	
	private MejoandroidApplication application;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		application = (MejoandroidApplication) getApplication();
		updater = new Updater();
		Log.d(TAG, "onCreated");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		runFlag = false;
		application.setServiceRunningFlag(false);
		updater.interrupt();
		updater = null;
		
		Log.d(TAG, "onDestryed");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		runFlag = true;
		application.setServiceRunningFlag(true);
		updater.start();
		
		Log.d(TAG, "onStarted");
		return START_STICKY;
		
	}
	
	/**
	 * Thread that performs the actual update from twitter online service
	 */
	private class Updater extends Thread{
		
		public Updater(){
			super("UpdaterService-UpdaterThread");
		}
		
		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while (updaterService.runFlag) {
				Log.d(TAG, "UpdaterThread running");
				try{
					TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
					Thread.sleep(DELAY); 
				}catch(InterruptedException e){
					updaterService.runFlag = false;
					application.setServiceRunningFlag(false);
				}
				
			}
		}
	
	} 

}
