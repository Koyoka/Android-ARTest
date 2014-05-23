package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainHomeNoneActionBarActivity.SyncAllTask;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.gusturelock.LockActivity;
import com.yrkj.elderlycareassess.layout.ActivitySetting;
import com.yrkj.elderlycareassess.service.SyncService;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.ToastUtil;

public class SettingActivity extends FragmentActivity implements OnClickListener {

	SettingActivity mActy;
	ActivitySetting mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mActy = this;
		mLayout = new ActivitySetting(this);
		initActy();
		
	}
	
	private void initActy(){
//		UserHeaderInfoFragment f
//		= new UserHeaderInfoFragment();
//
//		FragmentManager fMng = getSupportFragmentManager();
//		FragmentTransaction ft = fMng.beginTransaction();
//		ft.add(ActivitySetting.LayoutUserHeaderInfoContainerViewId, 
//				f, 
//				UserHeaderInfoFragment.class.getName());
//		ft.commit();
		
		mLayout.getBtnBackView().setOnClickListener(this);
		mLayout.getBtnLogoutView().setOnClickListener(this);
		mLayout.getBtnAysnView().setOnClickListener(this);
		mLayout.getBtnSetNetPwdView().setOnClickListener(this);
		mLayout.getBtnSetLocPwdView().setOnClickListener(this);
		mLayout.getBtnLogView().setOnClickListener(this);
		mLayout.getBtnClearView().setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case ActivitySetting.BtnClearViewId:
			ToastUtil.show(this, "清理缓存。");
			break;
		case ActivitySetting.BtnBackViewId:
			this.finish();
			break;
		case ActivitySetting.BtnLogoutViewId:
			Intent intent3 = new Intent(SettingActivity.this, LoginActivity.class);
			
			intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
//			android.os.Process.killProcess(android.os.Process.myPid()) ;   //获取PID 
//			  System.exit(0); 
//			this.finish();
			return;
		case ActivitySetting.BtnAysnViewId:
//			Intent intent = new Intent(this, SyncActivity.class);
//			startActivity(intent);
			syncAll();
			break;
		case ActivitySetting.BtnSetLocPwdViewId:
			Intent intent11 = new Intent(this, LockActivity.class);
			intent11.putExtra("Login", true);
			startActivity(intent11);
			break;
		case ActivitySetting.BtnSetNetPwdViewId:
			Intent intent1 = new Intent(this, EditActivity.class);
			startActivity(intent1);
			break;
		case ActivitySetting.BtnLogViewId:
			Intent intent2 = new Intent(this, SettingContentActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
		
	}
	
	public synchronized void syncAll(){
		if(NetHelper.getAPNType(this) == -1){
			DialogHelper.createTextDialog(this, "消息", "请确保在通畅的网络环境下，进行同步操作。");
			return;
		}
		
		
		int scount = AssessDBCtrl.getWaitingSyncAssessTaskCount(mActy);
		if((AssessDBCtrl.getCanSyncAssessTaskCount(this)+scount) == 0){
			ToastUtil.show(this, "没有数据需要同步。");
			return;
		}
		
		if(mSyncAllTask == null){
			mSyncAllTask = new SyncAllTask();
		}
		
		if(mSyncAllTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mSyncAllTask.getStatus() == Status.FINISHED){
			mSyncAllTask = new SyncAllTask();
		}
		
		if(mSyncAllTask.getStatus() != Status.RUNNING){
			mSyncAllTask.execute();
		}
		
	}
	private SyncAllTask mSyncAllTask;
	class SyncAllTask extends AsyncTask<Object, Object, Boolean>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ToastUtil.show(mActy, "已开始全部同步");
//			mLayout.getTxtSyncCount().setVisibility(View.GONE);
//			DialogHelper.getProgressDialogInstance().show(mActy, "正在准备数据");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			ArrayList<AssessTaskHeaderData> itemList = 
					AssessDBCtrl.getCanSyncAssessTaskList(mActy);
			
			String time =  DateHelper.getTodayAndTime();
			for (AssessTaskHeaderData item : itemList) {
				try{
					int taskHeaderId = Integer.parseInt(item.Id, 10);
					SysSyncData data = new SysSyncData();
					data.TaskHeaderId = taskHeaderId;
					data.State = SysSyncData.SYNC_STATE_WAIT;
					data.startTime = time;
					SysDBCtrl.addWaitingSyncTask(mActy, data);
				}
				catch(Exception ex){
					
				}
			}
			
			int scount = AssessDBCtrl.getWaitingSyncAssessTaskCount(mActy);
			
			if((itemList.size()+scount) != 0){
				return true;
			}else{
				return false;
			}
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				SyncBroadcast.sendUploadSyncBroadcast(mActy,SyncService.SYNC_ALL_TASK_KEY);
			}
//			DialogHelper.getProgressDialogInstance().close();
			
		}
		
	}

//	private void loadData(){
//		
//		String s = SysDBCtrl.getLastLoginDate(this);
//		
//		mLayout.getTxtLastLoginDateView().setText(s.isEmpty()?"首次登陆":s);
//		
//		AssessUserData u = SysMng.getUserInfo();
//		DLog.LOG(SysMng.TAG_DB, u+" 1 ===["+u.UserId);
//		u = AssessUserDBCtrl.getUserData(this, u.UserId);
//		DLog.LOG(SysMng.TAG_DB, u+" 2 ===[");
//		if(u != null){
//			mLayout.getTxtUserNameView().setText(u.UserName);
//			mLayout.getTxtAddressView().setText(u.OfficeAddress);
//			mLayout.getTxtOfficeView().setText(u.Office);
//		}
//		
//		
//	}
}
