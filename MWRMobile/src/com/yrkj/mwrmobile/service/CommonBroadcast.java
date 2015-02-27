package com.yrkj.mwrmobile.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class CommonBroadcast extends BroadcastReceiver {
	public final static String ACTION_NAME = "com.yrkj.mwrmobile.service.commonBroadcast";
	
	public final static String INTENT_KEY_BROADCASTTYPE = "BroadcastType";
	
//	final static String BROADCAST_UPLOAD = "broadcateupload";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		if(mBroadcastListener != null){
			Bundle extras = intent.getExtras();
			mBroadcastListener.onListener(extras);
		}
		
	}

	public static CommonBroadcast regist(Context context,BroadcastListener l){
		CommonBroadcast bc = new CommonBroadcast();
		bc.mBroadcastListener = l;
		
		IntentFilter filter = new IntentFilter(); 
		filter.addAction(ACTION_NAME);  
		context.registerReceiver(bc, filter); 
		return bc;
	}
	public static void send(Context context,Bundle extras){
		Intent intent = new Intent(ACTION_NAME); 
		intent.putExtras(extras);
		context.sendBroadcast(intent); 
	}
	
	public interface BroadcastListener{
		public void onListener(Bundle extras);
	}
	
	public BroadcastListener mBroadcastListener;
}
