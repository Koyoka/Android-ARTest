package com.yrkj.elderlycareassess.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class SyncBroadcast extends BroadcastReceiver {

	public final static String ACTION_NAME = "com.yrkj.elderlycareassess.sync";
	
	public final static String INTENT_KEY_BROADCASTTYPE = "BroadcastType";
	
	final static String BROADCAST_UPLOAD = "upload";
	
	public final static String INTENT_KEY_TASKHEADERID = "taskheaderid";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent == null){
			return;
		}
		
		String BroadcastType = intent.getStringExtra(INTENT_KEY_BROADCASTTYPE);
		if(BroadcastType == null){
			return;
		}
		
		if(BroadcastType.equals(BROADCAST_UPLOAD)
				&& mUploadSyncListener != null){
			Bundle bundle = intent.getExtras();
			int taskHeaderId = intent.getIntExtra(INTENT_KEY_TASKHEADERID,0);
			mUploadSyncListener.onListener(bundle,taskHeaderId);
		}
	}
	
	public static SyncBroadcast registUploadSyncBroadcast(Context context,UploadSyncListener l){
		SyncBroadcast bc = new SyncBroadcast();
		bc.mUploadSyncListener = l;
		IntentFilter filter = new IntentFilter(); 
	    filter.addAction(ACTION_NAME);  
	    context.registerReceiver(bc, filter); 
	    return bc;
	}
	
	public static void sendUploadSyncBroadcast(Context context,int taskHeaderId){
		Intent intent = new Intent(ACTION_NAME); 
		intent.putExtra(INTENT_KEY_BROADCASTTYPE, BROADCAST_UPLOAD);
		intent.putExtra(INTENT_KEY_TASKHEADERID, taskHeaderId);
	    context.sendBroadcast(intent); 
	}
	
	public interface UploadSyncListener{
		public void onListener(Bundle bundle,int taskHeaderId);
	}
	public UploadSyncListener mUploadSyncListener;

}
