package com.yrkj.elderlycareassess.service;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast.UploadSyncListener;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.http.CustomMultipartEntity.HttpProgressListener;
import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.DLog;

public class SyncService extends Service {

	SyncBroadcast b1;
	
	Handler mTimeHandler=new Handler();
	
	Runnable timeRunnable = new Runnable() {
		@Override
		public void run() {
			timeHandlerEvent();
			mTimeHandler.postDelayed(this, time);
		}
	};
	
	
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
		startTimeHandler();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(b1!=null){
			 unregisterReceiver(b1);  
		}
		stopTimeHandler();
	}
	
	private void initService(){
		b1 = SyncBroadcast.registUploadSyncBroadcast(this, new UploadSyncListener() {
			
			@Override
			public void onListener(Bundle bundle, int taskHeaderId) {
				// TODO Auto-generated method stub
				DLog.LOG(SysMng.TAG_SERVICE, "taskHeaderId--"+taskHeaderId);
//				uploadTask(taskHeaderId);
				if(taskHeaderId == SYNC_ALL_TASK_KEY){
					mSyncAll = true;
				}else{
					
					iList.add(taskHeaderId);
				}
			}
		});
	}
	
	public static int SYNC_ALL_TASK_KEY = -1;
	
////	int i = 0;
//	private void uploadTask(final int id){
//		
////		SysSyncData data = new SysSyncData();
////		data.TaskHeaderId = id;
////		data.State = SysSyncData.SYNC_STATE_WAIT;
////		data.startTime = DateHelper.getTodayAndTime();
////		
////		SysDBCtrl.addWaitingSyncTask(this,data);
//		
//		
//	}
	
	private void sendUnSyncTaskCount(){
		int count = 
				AssessDBCtrl.getUnSyncAssessTaskList(this).size();
		SyncBroadcast.sendUnSyncCountBroadcast(this, count);
	}
	
	private void timeHandlerEvent(){
		DLog.LOG(SysMng.TAG_SERVICE,"0.-------- doCount="+ doCount);
		sendUnSyncTaskCount();
		
		if(NetHelper.getAPNType(this) != -1){
			if(mSyncAll
					&& doCount<threadCount){
				ArrayList<SysSyncData> items = SysDBCtrl.getWaitingSyncTask(this, threadCount);
				if(items.size()==0){
					mSyncAll = false;
				}else{
					for (SysSyncData item : items) {
						iList.add(item.TaskHeaderId);
					}
				}
			}
			
//			if(doCount<threadCount && iList.size()!=0){
			for(int i=0;i<threadCount && (doCount<threadCount && iList.size()!=0);i++){
				syncThread();
			}
//			}
		}
	}
	
	
	private boolean mSyncAll = false;
	private int doCount = 0;
	int ii=0;
	private synchronized void syncThread(){
		if(doCount>=threadCount
				|| iList.size()==0){
			return;
		}
		doCount++;
		DLog.LOG(SysMng.TAG_SERVICE,"1.-------- doCount="+ doCount);
		

		final int id = iList.get(0);
		iList.remove(0);
		
		
		
		final Handler mUploadTaskHandle = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				String assessNum = "";
				int processVal = 0;
				int taskHeaderId = 0;
				
				Bundle bundle = msg.getData();
				assessNum = bundle.getString(MESSAGE_KEY_ASSESSNUM);
				taskHeaderId = bundle.getInt(MESSAGE_KEY_TASKHEADERID);
				processVal = bundle.getInt(MESSAGE_KEY_PROCESSVAL);

				SyncBroadcast.sendUploadProcessSyncBroadcast(SyncService.this,
						taskHeaderId, assessNum, processVal);
				 
			}
		};


		if(!SysDBCtrl.doingSyncTaskState(SyncService.this, id)){
			return;
		}
		Thread t = new Thread(){
			public void run() {
				//do sync item
//				String assessNum = "";
//				int processVal = 0;
				
				AssessTaskHeaderData data = AssessDBCtrl.getAssessTaskById(SyncService.this, id+"");
				ArrayList<AssessTaskDetailData> dataDetailList = AssessDBCtrl.getAssessTaskDetailByTaskId(SyncService.this, id+"");
				ArrayList<AssessTaskServiceData> dataServiceList =  AssessDBCtrl.getAssessTaskServiceByTaskId(SyncService.this, id+"");
				CustomerData cust = AssessDBCtrl.getCustomerByCustId(SyncService.this, id+"");
				
				
				TaskData td = new TaskData();
				td.cust = cust;
				td.header = data;
				td.detail = dataDetailList;
				td.service = dataServiceList;
				Gson gson = new Gson();
				String s = gson.toJson(td);
				
				final String assessNum = data.AssessNum;
				HttpSync.uploadAssessTask(SyncService.this, s,new HttpProgressListener() {
					
					@Override
					public void transferred(long num, long contentLenth) {
						// TODO Auto-generated method stub
						int p = (int) ((num / (float) contentLenth) * 100);
						Message msg = new Message();
						Bundle bundle = new Bundle();
						bundle.putInt(MESSAGE_KEY_TASKHEADERID, id);
						bundle.putString(MESSAGE_KEY_ASSESSNUM, assessNum);
						bundle.putInt(MESSAGE_KEY_PROCESSVAL, p);
						msg.setData(bundle);
						mUploadTaskHandle.sendMessage(msg);
					}
				});
				
				
				
				DLog.LOG(SysMng.TAG_SERVICE,"2.--------id["+id+"] "+ doCount +" iList.size["+iList.size()+"]");
				SysDBCtrl.finishSyncTaskState(SyncService.this, id);
				doCount--;
			};
		};
		
		t.start();
		
		
	}

	class TaskData{
		CustomerData cust = null;
		AssessTaskHeaderData header = null;
		ArrayList<AssessTaskDetailData> detail = null;
		ArrayList<AssessTaskServiceData> service = null;
	}
//	void a(){
//		
//		
//		AssessTaskHeaderData data = AssessDBCtrl.getAssessTaskById(this, mAssessId);
//		ArrayList<AssessTaskDetailData> dataDetailList = AssessDBCtrl.getAssessTaskDetailByTaskId(this, mAssessId);
//		TaskData td = new TaskData();
//		td.header = data;
//		td.detail = dataDetailList;
//		Gson gson = new Gson();
////		AssessTaskHeaderData d = new AssessTaskHeaderData();
////		d.AssessNum = "111";
////		d.AssessState = "dd";
//		 String s = gson.toJson(td);
//		 DLog.LOG(SysMng.TAG_DB,s);
////		 autoRun = gson.fromJson(s, AutoRun.class);
//
//
//	}
//	
	static String MESSAGE_KEY_TASKHEADERID = "taskheaderid";
	static String MESSAGE_KEY_ASSESSNUM = "assessnum";
	static String MESSAGE_KEY_PROCESSVAL = "processVal";
	
	
	final int threadCount = 5;
//	private void doAllSycn(){
//		ArrayList<SysSyncData> items = SysDBCtrl.getWaitingSyncTask(this, threadCount);
//		
//		syncThread();
//	}
	ArrayList<Integer> iList = new ArrayList<Integer>();
	
	int time = 10000;
	
	private void startTimeHandler(){
		mTimeHandler.postDelayed(timeRunnable, time);
	}
	private void stopTimeHandler(){
		mTimeHandler.removeCallbacks(timeRunnable);
	}
	
//	
//	final Handler h = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			
//			if(i==100)
//				return;
//			i++;
//			SyncBroadcast.sendUploadProcessSyncBroadcast(SyncService.this, id, i);
//			this.sendEmptyMessageDelayed(0, 1000);
//		}
//	};
//	
//	h.sendEmptyMessageDelayed(0, 1000);
	
}
