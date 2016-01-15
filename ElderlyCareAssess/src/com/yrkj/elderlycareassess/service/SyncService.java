package com.yrkj.elderlycareassess.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentDiseaseData;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentSoundData;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast.UploadSyncListener;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.util.http.CustomMultipartEntity.HttpProgressListener;
import com.yrkj.util.http.InputFileObj;
import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.DLog;

public class SyncService extends Service {

	public static int SYNC_ALL_TASK_KEY = -1;
	private boolean mSyncAll = false;
	private int doCount = 0;
	
	static String MESSAGE_KEY_TASKHEADERID = "taskheaderid";
	static String MESSAGE_KEY_ASSESSNUM = "assessnum";
	static String MESSAGE_KEY_PROCESSVAL = "processVal";
	
	
	final int threadCount = 5;
	ArrayList<Integer> iList = new ArrayList<Integer>();
	
	
	int time = 5000;
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
	
	
	
	
	private void sendUnSyncTaskCount(){
		int count = 
				AssessDBCtrl.getCanSyncAssessTaskCount(this);//AssessDBCtrl.getUnSyncAssessTaskList(this).size();
		int scount = AssessDBCtrl.getWaitingSyncAssessTaskCount(this);
		SyncBroadcast.sendUnSyncCountBroadcast(this, count+scount);
	}
	
	private void timeHandlerEvent(){
		DLog.LOG(SysMng.TAG_SERVICE,"0.-------- doCount="+ doCount);
		
		
		if(NetHelper.getAPNType(this) != -1){
			sendUnSyncTaskCount();
			
			if(mSyncAll
					&& 
					doCount<threadCount){
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
	
//	int ii=0;
	private synchronized void syncThread(){
		if(doCount>=threadCount
				|| iList.size()==0){
			return;
		}
		doCount++;
		DLog.LOG(SysMng.TAG_SERVICE,"1.-------- doCount="+ doCount);

		final int id = iList.get(0);
		iList.remove(0);

		if(!SysDBCtrl.doingSyncTaskState(SyncService.this, id)){
			return;
		}
		Thread t = new Thread(){
			public void run() {
				//do sync item
//				String assessNum = "";
//				int processVal = 0;
				DLog.LOG(SysMng.TAG_SERVICE,"2.-------- run thread  id="+ id);
				AssessTaskHeaderData data = AssessDBCtrl.getAssessTaskById(SyncService.this, id+"");
				ArrayList<AssessTaskDetailData> dataDetailList = AssessDBCtrl.getAssessTaskDetailByTaskId(SyncService.this, id+"");
				ArrayList<AssessTaskServiceData> dataServiceList =  AssessDBCtrl.getAssessTaskServiceByTaskId(SyncService.this, id+"");
				CustomerData cust = AssessDBCtrl.getCustomerByCustId(SyncService.this, data.CustId);
				
				TaskData td = new TaskData();
				td.cust = cust;
//				td.header = data;
				td.assessid = data.NetTaskHeaderId;
				td.detail = dataDetailList;
				td.service = dataServiceList;
				Gson gson = new Gson();
				String s = gson.toJson(td);
				
				
				
				final String assessNum = data.AssessNum;
				boolean result = false;
				result = HttpSync.uploadAssessTask(SyncService.this, s,new HttpProgressListener() {
					
					@Override
					public void transferred(long num, long contentLenth) {
						// TODO Auto-generated method stub
						int p = (int) ((num / (float) contentLenth) * 100);
						DLog.LOG(SysMng.TAG_SERVICE,"4.-------- process="+p);

						Message msg = new Message();
						Bundle bundle = new Bundle();
						bundle.putInt(MESSAGE_KEY_TASKHEADERID, id);
						bundle.putString(MESSAGE_KEY_ASSESSNUM, assessNum);
						bundle.putInt(MESSAGE_KEY_PROCESSVAL, p);
						msg.setData(bundle);
						mUploadTaskHandle.sendMessage(msg);
					}
				});
				
				if(result){
					DLog.LOG(SysMng.TAG_SERVICE,"2.1.1-------- upload ill ");
					ArrayList<AssessTaskAttachmentDiseaseData> dlist =
							AttachmentDBCtrl.getAttachmentDiseaseList(SyncService.this, id);
					
					if(dlist.size() != 0){
						ArrayList<IllData> illList  = new ArrayList<IllData>();
						for (AssessTaskAttachmentDiseaseData d : dlist) {
							IllData item = new IllData();
							item.name = d.DiseaseName;
							item.content = d.DiseaseDesc;
							item.cateid = d.CateId+"";
							item.assessid = data.NetTaskHeaderId;
							item.time = d.SickDate;
							item.medicate = d.IsMedication?"1":"0";
							illList.add(item);
						}
						String illJsonStr = gson.toJson(illList);
						DLog.LOG(SysMng.TAG_SERVICE,"2.1.2-------- illJsonStr " + illJsonStr);
						result = HttpSync.uploadAssessTaskIll(SyncService.this, illJsonStr, null);
					}
				}
				
				HttpProgressListener fileUploadListener = new HttpProgressListener() {
					
					@Override
					public void transferred(long num, long contentLenth) {
						// TODO Auto-generated method stub
						int p = (int) ((num / (float) contentLenth) * 100);
						DLog.LOG(SysMng.TAG_SERVICE,"4.-------- process="+p);

						Message msg = new Message();
						Bundle bundle = new Bundle();
						bundle.putInt(MESSAGE_KEY_TASKHEADERID, id);
						bundle.putString(MESSAGE_KEY_ASSESSNUM, "File");
						bundle.putInt(MESSAGE_KEY_PROCESSVAL, p);
						msg.setData(bundle);
						mUploadTaskHandle.sendMessage(msg);
					}
				};
				
				if(result){
					DLog.LOG(SysMng.TAG_SERVICE,"2.2.1-------- upload sound ");
					ArrayList<AssessTaskAttachmentSoundData> dlist =
							AttachmentDBCtrl.getAttachmentSoundList(SyncService.this, id);
					if(dlist.size() != 0){
						for (AssessTaskAttachmentSoundData d : dlist) {
							if(new File(d.SoundPath).exists()){
								FileInputStream fi;
								InputFileObj fileObj = null;
								File f = new File(d.SoundPath);
								DLog.LOG(SysMng.TAG_SERVICE,"2.2.2-------- upload sound "+d.SoundPath);
								try {
									fi = new FileInputStream(f);
									fileObj = new InputFileObj(f.getName(), fi);
									
									HttpSync.uploadAssessTaskFile(SyncService.this, "synaudio", data.NetTaskHeaderId, d.CateId+"", fileObj, fileUploadListener);
									
									fi.close();
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						};
					}
				}
				if(result){
					DLog.LOG(SysMng.TAG_SERVICE,"2.3.1-------- upload pic ");
					ArrayList<AssessTaskAttachmentImageData> dlist =
							AttachmentDBCtrl.getAttachmentImgList(SyncService.this, id);
					if(dlist.size() != 0){
						for (AssessTaskAttachmentImageData d : dlist) {
							if(new File(d.ImgPath).exists()){
								FileInputStream fi;
								InputFileObj fileObj = null;
								File f = new File(d.ImgPath);
								DLog.LOG(SysMng.TAG_SERVICE,"2.3.2-------- upload pic "+d.ImgPath);
								try {
									fi = new FileInputStream(f);
									fileObj = new InputFileObj(f.getName(), fi);
									
									HttpSync.uploadAssessTaskFile(SyncService.this, "synpic", data.NetTaskHeaderId, d.CateId+"", fileObj, fileUploadListener);
									
									fi.close();
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						};
					}
					
				}
				
				if(result){
					
					DLog.LOG(SysMng.TAG_SERVICE,"3.1-------- ok");
					doCount--;
					data.NeedSync = false;
					AssessDBCtrl.updateAssessTaskHeaderById(SyncService.this, data);
					SysDBCtrl.finishSyncTaskState(SyncService.this, id);
					SysDBCtrl.addSyncAssessLog(SyncService.this, assessNum);
				}else{
					DLog.LOG(SysMng.TAG_SERVICE,"3.2-------- err");
					doCount--;
					SysDBCtrl.finishSyncTaskState(SyncService.this, id);
				}
				
			};
		};
		
		t.start();
		
		
	}

	class TaskData{
		String assessid = "";
		CustomerData cust = null;
//		AssessTaskHeaderData header = null;
		ArrayList<AssessTaskDetailData> detail = null;
		ArrayList<AssessTaskServiceData> service = null;
	}
	class IllData{
//		{"name":"¹ÚÐÄ²¡","content":"bb","cateid":"2","assessid":"","time":"","medicate":"1"}
		String name = "";
		String content = "";
		String cateid = "";
		String assessid = "";
		String time = "";
		String medicate = "";
	}
	
	
	
	private void startTimeHandler(){
		mTimeHandler.post(timeRunnable);
//		mTimeHandler.postDelayed(timeRunnable, time);
	}
	private void stopTimeHandler(){
		mTimeHandler.removeCallbacks(timeRunnable);
	}
	
	
}
