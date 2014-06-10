package com.yrkj.elderlycareassess.acty;

//import android.app.ActionBar;
//import android.app.ActionBar.Tab;
//import android.app.ActionBar.TabListener;
//import android.app.ActionBar;
//import android.app.ActionBar.TabListener;
import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.LoginActivity.LoginTask;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast.UnSyncCountListener;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.fragment.AssessDoneListFragment;
import com.yrkj.elderlycareassess.fragment.AssessTaskListFragment;
import com.yrkj.elderlycareassess.fragment.HomeFragment;
import com.yrkj.elderlycareassess.fragment.HomeFragment.OnAssessTaskClick;
import com.yrkj.elderlycareassess.fragment.ReportFragment;
import com.yrkj.elderlycareassess.layout.ActivityMainHomeNoneactionbar;
import com.yrkj.elderlycareassess.service.SyncService;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.ToastUtil;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBar.TabListener;


public class MainHomeNoneActionBarActivity extends 
//ActionBarActivity 
FragmentActivity
//BaseActivity 
implements 
OnClickListener {
	final String TAG = "com.yrkj.elderlycareassess.acty.MainHomeActivity";
	final String SAVE_STATE_KEY_FRAGMENT = "curTag";
	final String SAVE_STATE_KEY_TAB = "tabIndex";
	final String TAB_TAG_TASK = "task";
	final String TAB_TAG_DONE = "done";
	
	MainHomeNoneActionBarActivity mActy;

	private String mCurClass = "";
	
	View mHomeView;
	View mAssessView;
	View mReportView;
	
	Button mBtnAssessTaskView;
	Button mBtnAssessDoneView;
	
	View mLayoutAssessTabView;
	private ActivityMainHomeNoneactionbar mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_home_noneactionbar);
		mActy = this;
		mLayout
		 = new ActivityMainHomeNoneactionbar(this);
		
		Intent intent = new Intent(getBaseContext(), SyncService.class);
    	startService(intent);
    	
		initActy();
		
//		DLog.LOG(SysMng.TAG_FRAGMENT,"getActionBar() == " + getActionBar() + " " + getSupportActionBar());
		
		if (savedInstanceState == null) {
			addFragment(HomeFragment.class.getName(),null);
		}else{
			addFragment(savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT),null);
		}
		
		b1 = SyncBroadcast.registUnSyncCountBroadcast(this, new UnSyncCountListener() {
			
			@Override
			public void onListener(Bundle bundle, int count) {
				// TODO Auto-generated method stub
				mLayout.getTxtSyncCount().setVisibility(count==0?View.GONE:View.VISIBLE);
				mLayout.getTxtSyncCount().setText(count+"");
			}
		});
		
		
	}
	SyncBroadcast b1;
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(b1!=null){
			 unregisterReceiver(b1);  
		}
		Intent intent = new Intent(this, SyncService.class);
    	stopService(intent);
    	
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			DialogHelper.createConfirmDialog(MainHomeNoneActionBarActivity.this, "确认退出？",new ConfirmDialogListener() {
				
				@Override
				public void onClickListener(boolean result) {
					
					if(result){
//						SysMng.closeApp(mActy);
						Intent intent3 = new Intent(MainHomeNoneActionBarActivity.this, LoginActivity.class);
						intent3.putExtra("exit", 1);
						intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent3);
					}
				}
			});
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SAVE_STATE_KEY_FRAGMENT, mCurClass);
	}
	
	private void initActy(){
		mHomeView = findViewById(R.id.btnHomeView);
		mAssessView = findViewById(R.id.btnAssessView);
		mReportView = findViewById(R.id.btnReportView);
		
		
		
		mBtnAssessTaskView = (Button) findViewById(R.id.btnAssessTaskView);
		mBtnAssessDoneView = (Button) findViewById(R.id.btnAssessDoneView);
		
		mLayoutAssessTabView = findViewById(R.id.layoutAssessTabView);
		
		mHomeView.setOnClickListener(this);
		mAssessView.setOnClickListener(this);
		mReportView.setOnClickListener(this);
		
		mBtnAssessTaskView.setOnClickListener(this);
		mBtnAssessDoneView.setOnClickListener(this);
		
		
		mLayout.getBtnSettingView().setOnClickListener(this);
		mLayout.getBtnSyncView().setOnClickListener(this);
		
		
	}

	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHomeView:
			addFragment(HomeFragment.class.getName(),null);
			
			break;
		case R.id.btnAssessView:
			String defineClassName = 
			mLayoutAssessTabView.getTag() != null
					&& !mLayoutAssessTabView.getTag().toString().isEmpty() ? mLayoutAssessTabView
					.getTag().toString() : AssessTaskListFragment.class
					.getName();
			addFragment(defineClassName,null);
			break;
		case R.id.btnReportView:
			addFragment(ReportFragment.class.getName(),null);
			break;
		case R.id.btnAssessTaskView:
			addFragment(AssessTaskListFragment.class.getName(),null);
			break;
		case R.id.btnAssessDoneView:
			addFragment(AssessDoneListFragment.class.getName(),null);
			break;
		case ActivityMainHomeNoneactionbar.BtnSettingViewId:
			Intent intent = new Intent(this,SettingActivity.class);
			startActivity(intent);
			
			break;
		case ActivityMainHomeNoneactionbar .BtnSyncViewId:
			syncAll();
			
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
//			ToastUtil.show(this, "没有数据需要同步。");
			doDownloadTask();
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
	
	private void refAssessTaskList(){
		String tag = AssessTaskListFragment.class
		.getName();
		FragmentManager fMng = getSupportFragmentManager();
		Fragment f= fMng.findFragmentByTag(tag);
		
//		ToastUtil.show(mActy, f+"");
		
		
		if(f != null
				&& f instanceof AssessTaskListFragment){
//			ToastUtil.show(mActy, "111111111");
			((AssessTaskListFragment)f).reBind();
		}else{
			
//			ToastUtil.show(mActy, "2222222");
		}
	}
	
	private synchronized void addFragment(String className,Bundle args){
		if(className.equals(mCurClass)){
			return;
		}
		FragmentManager fMng = getSupportFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		Fragment f = fMng.findFragmentByTag(mCurClass);
		if(f != null){
			ft.detach(f);
//			ft.hide(f);
		}
		
		Fragment mFragment= fMng.findFragmentByTag(className);
		if(mFragment == null){
			mFragment = Fragment.instantiate(mActy, className, args);
			ft.add(R.id.layoutBodyView, mFragment, className);
			
			if(className.equals(HomeFragment.class.getName())){
				((HomeFragment)mFragment).setOnAssessTaskClick(mHomeTaskRowClick);
			}
			
		}else{
			ft.attach(mFragment);
//			ft.show(mFragment);
		}
		ft.commit();
		
		mCurClass = className;
		showAssessFragment(className);
	}
	
	private void showAssessFragment(String className){
		
		if(mCurClass.equals(AssessTaskListFragment.class.getName()) 
				|| mCurClass.equals(AssessDoneListFragment.class.getName()) ){
			mLayoutAssessTabView.setTag(className);
			mLayoutAssessTabView.setVisibility(View.VISIBLE);
		}else{
			mLayoutAssessTabView.setVisibility(View.GONE);
			
		}
	}
	private SyncAllTask mSyncAllTask;
	class SyncAllTask extends AsyncTask<Object, Object, Boolean>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ToastUtil.show(mActy, "已开始全部同步");
			mLayout.getTxtSyncCount().setVisibility(View.GONE);
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
			doDownloadTask();
//			DialogHelper.getProgressDialogInstance().close();
			
		}
		
	}
	
	private DownloadTask mDownloadTask;
	private void doDownloadTask(){
		String userId = SysMng.getUserInfo().UserId;
		if(userId.isEmpty()){
			return;
		}
		if(mDownloadTask == null){
			mDownloadTask = new DownloadTask(userId);
		}
		
		if(mDownloadTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mDownloadTask.getStatus() == Status.FINISHED){
			mDownloadTask = new DownloadTask(userId);
		}
		
		
		if(mDownloadTask.getStatus() != Status.RUNNING){
			mDownloadTask.execute();
		}
	}
	
	class DownloadTask extends AsyncTask<Object, Object, Integer>{
		String mUserId = "";
		public DownloadTask(String userId){
			mUserId = userId;
			
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			DialogHelper.getProgressDialogInstance().show(mActy, "获取网络数据");
		}
		
		@Override
		protected Integer doInBackground(Object... params) {
			
			return HttpSync.downLoadAssessTask(mActy,mUserId);
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			
			DialogHelper.getProgressDialogInstance().close();
			if(result != -1){
				if(result != 0){
					ToastUtil.show(mActy, "获取"+result+"条新的评估任务！");
					refAssessTaskList();
				}
				else{
					ToastUtil.show(mActy, "没有新的评估任务！");
				}
				
			}else{
				ToastUtil.show(mActy, "同步数据出错，联系管理员。");
				
			}
		}
		
	}

	
	OnAssessTaskClick mHomeTaskRowClick = new OnAssessTaskClick() {
		
		@Override
		public void onClick(String name) {
			addFragment(name,null);
			
			mLayout.getRdoTabsView().check(mLayout.getBtnAssessView().getId());
			if(name.equals(AssessTaskListFragment.class.getName())){
				mLayout.getLayoutAssessTabView().check(mLayout.getBtnAssessTaskView().getId());
			}else{
				mLayout.getLayoutAssessTabView().check(mLayout.getBtnAssessDoneView().getId());
			}
		}
	};
	

}
