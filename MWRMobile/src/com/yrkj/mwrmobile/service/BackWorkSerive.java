package com.yrkj.mwrmobile.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.DLog;

public class BackWorkSerive extends Service {

	Context mContext;
	CommonBroadcast b;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = this;
//		b = CommonBroadcast.regist(this, new BroadcastListener() {
//			
//			@Override
//			public void onListener(Bundle extras) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		startTimeHandler();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopTimeHandler();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	int time = 5000;
	Handler mTimeHandler=new Handler();
	Runnable timeRunnable = new Runnable() {
		@Override
		public void run() {
			timeHandlerEvent();
			mTimeHandler.postDelayed(this, time);
		}
	};
	
	
	Thread t = new MyThread();
	private synchronized void timeHandlerEvent(){
		if(t.getState() == Thread.State.TERMINATED){
			t = new MyThread();
		}
		
		if(t.getState() == Thread.State.NEW){
			t.start();
		}
	}
	
	private void startTimeHandler(){
		mTimeHandler.post(timeRunnable);
	}
	private void stopTimeHandler(){
		mTimeHandler.removeCallbacks(timeRunnable);
	}
	
	public static final String INTENT_KEY_APNType = "APNType";
	class MyThread extends Thread{
		@Override
		public void run() {
			
			int state = NetHelper.getAPNType(mContext);
			DLog.LOG("test thread-------------------- " + state);
			Bundle extras = new Bundle();
			extras.putInt(INTENT_KEY_APNType, state);
			CommonBroadcast.send(mContext, extras);
		
		}
	}
}
