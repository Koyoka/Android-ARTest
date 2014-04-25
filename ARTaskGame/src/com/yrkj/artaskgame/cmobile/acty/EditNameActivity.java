package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.artaskgame.cmobile.control.CheckUserTaskDao;
import com.yrkj.artaskgame.cmobile.control.CheckUserTaskDao.ResponseHeaderBean;
import com.yrkj.config.SysConfig;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.log.ToastUtil;

public class EditNameActivity extends Activity implements
OnClickListener,
CheckUserTaskDao.PostTaskListener,
CheckUserTaskDao.PreTaskListener
{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.EditNameActivity";
	EditNameActivity mActy = null;
	
	private Button mBtnReadyView = null;
	private TextView mTxtUserNameView = null;
	
	private CheckUserTaskDao mCheckUserTaskDao = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_name);
		mActy = this;
		initData();
		initActy();
		loadData();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		// DebugTrace.Print("MenuGroup---dispatchKeyEvent = " +
		// event.getKeyCode() + " action = " + event.getAction());
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			// showDialog(DIALOG_LOGOUT);
			DialogHelper.createConfirmDialog(EditNameActivity.this, "确认退出？",new ConfirmDialogListener() {
				
				@Override
				public void onClickListener(boolean result) {
					// TODO Auto-generated method stub
					
					if(result){
//						SysMng.sys_closeApp = true;
						SysMng.closeApp(mActy);
//						mActy.finish();
					}
				}
			});
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	private void initData() {
		// TODO Auto-generated method stub
		mCheckUserTaskDao = new CheckUserTaskDao(SysConfig.DEFAULT_INTERFACE_NAME);
		mCheckUserTaskDao.setTask(this, this);
	}

	private void initActy() {
		mTxtUserNameView = (TextView) findViewById(R.id.txtUserNameView);
		
		mBtnReadyView = (Button) findViewById(R.id.btnReadyView);
		mBtnReadyView.setOnClickListener(this);
	}

	private void loadData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnReadyView:
			setUserName();
			break;
		default:
			break;
		}
	}
	
	private void setUserName(){
		String defineUserName =  mTxtUserNameView.getText().toString();
		if(defineUserName.length() == 0){
			ToastUtil.show(mActy, "请输入您的名字");
		}else{
			doNetValidUserName();
		}
	}
	
	private void doNetValidUserName(){
		String defineUserName =  mTxtUserNameView.getText().toString();
		mCheckUserTaskDao
			.addPostParams("checkUser",
				defineUserName, 
				SysMng.sys_DriverId)
			.runTask();	
	}

	@Override
	public HttpRequestValue UpdateMyInfoDetailTaskDao_onPreTask(int taskId) {
		// TODO Auto-generated method stub
		DialogHelper.getProgressDialogInstance().show(this, "数据提交中");
		return null;
	}

	@Override
	public void UpdateMyInfoDetailTaskDao_onPostTask(int taskId,
			boolean isSuccess, String errMsg, ResponseHeaderBean result) {
		// TODO Auto-generated method stub
		DialogHelper.getProgressDialogInstance().close();
		
		if(!isSuccess){
			ToastUtil.show(this, errMsg);
			return;
		}
		
		if(result.bodyObj.status.equals("1")){
			String defineUserName =  mTxtUserNameView.getText().toString();
			SysMng.setUserName(defineUserName);
			Intent intent = new Intent(this, MainTaskActivity.class);
			this.startActivity(intent);
			this.finish();
		}else{
			ToastUtil.show(this, result.bodyObj.statusmessage);
		}
		
		
		
	}
	

}
