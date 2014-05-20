package com.yrkj.elderlycareassess.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast.UploadSyncListener;
import com.yrkj.util.log.DLog;

public class SyncService extends Service {

	SyncBroadcast b1;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initService();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(b1!=null){
			 unregisterReceiver(b1);  
		}
	}
	
	private void initService(){
		b1 = SyncBroadcast.registUploadSyncBroadcast(this, new UploadSyncListener() {
			
			@Override
			public void onListener(Bundle bundle, int taskHeaderId) {
				// TODO Auto-generated method stub
				DLog.LOG(SysMng.TAG_SERVICE, "1---"+taskHeaderId);
			}
		});
	}

}
