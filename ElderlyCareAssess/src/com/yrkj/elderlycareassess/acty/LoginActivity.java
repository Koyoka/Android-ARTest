package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.layout.ActivityLogin;
import com.yrkj.elderlycareassess.service.SyncService;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.http.NetHelper;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class LoginActivity extends FragmentActivity {

	LoginActivity mActy;
	ActivityLogin mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mActy = this;
		mLayout = new ActivityLogin(this);
		
		initActy();
		a();
	}
	
	void a(){
		Gson gson = new Gson();
		
//		AssessTaskHeaderData data = AssessDBCtrl.getAssessTaskById(this, id+"");
//		ArrayList<AssessTaskDetailData> dataDetailList = AssessDBCtrl.getAssessTaskDetailByTaskId(this, id+"");
		
//		AssessTaskHeaderData d = new AssessTaskHeaderData();
//		d.AssessNum = "111";
//		d.AssessState = "dd";
//		 String s = gson.toJson(d);
//		 DLog.LOG(SysMng.TAG_DB,s);
//		 autoRun = gson.fromJson(s, AutoRun.class);


	}
	
	private void initActy(){
		AssessUserData uData = SysMng.getUserInfo();
		if(uData != null){
			mLayout.getTxtUserNameView().setText(uData.UserId);
			mLayout.getTxtPasswordView().setText(uData.LocPassword);
		}
		
		mLayout.getBtnLoginView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});
	}
	
	private LoginTask mLoginTask;
	private void login(){
		String userId = mLayout.getTxtUserNameView().getText().toString();
		String pwd = mLayout.getTxtPasswordView().getText().toString();
		
		if(AssessUserDBCtrl.userLogin(this, userId.trim(), pwd)){
			go();
			return;
		}
		
		if(NetHelper.getAPNType(this) == -1){
			DialogHelper.createTextDialog(mActy, "消息", "用户名或密码错误,请重新输入。");
			mLayout.getTxtUserNameView().setFocusable(true);
			
		}else{
			if(mLoginTask == null){
				mLoginTask = new LoginTask(userId, pwd);
			}
			
			if(mLoginTask.getStatus() == Status.RUNNING){
				return;
			}
			
			if(mLoginTask.getStatus() == Status.FINISHED){
				mLoginTask = new LoginTask(userId, pwd);
			}
			
			
			if(mLoginTask.getStatus() != Status.RUNNING){
				mLoginTask.execute();
			}
			
			
		}
		
	}
	
	
	public void showDialog()
    {
    	//根据传进的参数来实例化DialogFragment.
//    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALTER_DIALOG);
    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.DATE_PICKER_DIALOG);
//    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.TIME_PICKER_DiALOG);
//    	getSupportFragmentManager()
    	newDialog.show(getSupportFragmentManager(), "alert msg");
    }
	

	
	
	
	
	
	private void go(){
		String userId = mLayout.getTxtUserNameView().getText().toString();
		String pwd = mLayout.getTxtPasswordView().getText().toString();
		SysMng.saveUserInfo(userId, pwd);
		SysDBCtrl.addSysLoginLog(this, userId);
		Intent intent = new Intent(this,MainHomeNoneActionBarActivity.class);
		startActivity(intent);
		this.finish();
	}
	
	
	
	class LoginTask extends AsyncTask<Object, Object, Boolean>{
		String mUserId = "";
		String mPassword = "";
		
		public LoginTask(String userId, String password){
			mUserId = userId;
			mPassword = password;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			DialogHelper.getProgressDialogInstance().show(mActy, "数据提交中");
			
			
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			 if(HttpSync.userNetLogin(mActy, mUserId, mPassword)){
				 
				 HttpSync.downLoadAssessTask(mActy,mUserId);
				 return true;
			 }else{
				 return false;
			 }
			 
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			DialogHelper.getProgressDialogInstance().close();
			if(!result){
				DialogHelper.createTextDialog(mActy, "消息", "用户名或密码错误,请重新输入。");
				mLayout.getTxtUserNameView().setFocusable(true);
			}else{
				go();
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
}
