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
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.ToastUtil;

public class EditNameActivity extends Activity implements
OnClickListener{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.EditNameActivity";
	EditNameActivity mActy = null;
	
	private Button mBtnReadyView = null;
	private TextView mTxtUserNameView = null;
	
	
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
						SysMng.sys_closeApp = true;
						mActy.finish();
					}
				}
			});
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	private void initData() {
		// TODO Auto-generated method stub
		
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
			SysMng.setUserName(defineUserName);
			
			Intent intent = new Intent(this, MainTaskActivity.class);
			this.startActivity(intent);
			this.finish();
		}
	}
	

}
