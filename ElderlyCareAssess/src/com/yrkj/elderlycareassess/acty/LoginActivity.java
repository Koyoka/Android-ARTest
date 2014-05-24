package com.yrkj.elderlycareassess.acty;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.gusturelock.LockActivity;
import com.yrkj.elderlycareassess.layout.ActivityLogin;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.http.NetHelper;

public class LoginActivity extends FragmentActivity {

	LoginActivity mActy;
	ActivityLogin mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		mActy = this;
		mLayout = new ActivityLogin(this);
		manager  = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
		initActy();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(getIntent() != null && getIntent().getIntExtra("exit",0) != 0){
			this.finish();
		}
	};
	
	InputMethodManager manager;//  = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
	  
	@Override  
	 public boolean onTouchEvent(MotionEvent event) {  
	  // TODO Auto-generated method stub  
	  if(event.getAction() == MotionEvent.ACTION_DOWN){  
	     if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){  
	    	 if(manager != null)
	    		 manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
	     }  
	  }  
	  return super.onTouchEvent(event);  
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
		
		boolean isLogin = false;
		if(AssessUserDBCtrl.userLogin(this, userId.trim(), pwd)){
			isLogin = true;
//			return;
		}
		
		int a = NetHelper.getAPNType(this);
//		ToastUtil.show(this, a+" net");
		
		if(a == -1){
			if(isLogin){
				go();
			}else{
				
				DialogHelper.createTextDialog(mActy, "消息", "用户名或密码错误,请重新输入。");
				mLayout.getTxtUserNameView().setFocusable(true);
			}
			
		}else{
			if(mLoginTask == null){
				mLoginTask = new LoginTask(userId, pwd,isLogin);
			}
			
			if(mLoginTask.getStatus() == Status.RUNNING){
				return;
			}
			
			if(mLoginTask.getStatus() == Status.FINISHED){
				mLoginTask = new LoginTask(userId, pwd,isLogin);
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
//		Intent intent = new Intent(this,MainHomeNoneActionBarActivity.class);
		Intent intent = new Intent(this,LockActivity.class);
//		Intent intent = new Intent(this,CddMainActivity.class);
		startActivity(intent);
//		this.finish();
	}
	
	
	
	class LoginTask extends AsyncTask<Object, Object, Boolean>{
		String mUserId = "";
		String mPassword = "";
		boolean mIsLogin = false;
		public LoginTask(String userId, String password,boolean isLogin){
			mUserId = userId;
			mPassword = password;
			mIsLogin = isLogin;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			DialogHelper.getProgressDialogInstance().show(mActy, "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			if(!mIsLogin){
				 if(!HttpSync.userNetLogin(mActy, mUserId, mPassword)){
					 return false;
				 }
			}
			
			HttpSync.downLoadAssessTask(mActy,mUserId);
			return true;
			 
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
