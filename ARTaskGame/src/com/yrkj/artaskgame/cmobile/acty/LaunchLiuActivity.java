package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.acty.qcar.QCARInitActivityActy;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.util.log.ToastUtil;

public class LaunchLiuActivity extends Activity {
	
	final String TAG = "com.yrkj.artaskgame.cmobile.acty.LaunchLiuActivity";
	LaunchLiuActivity mActy = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		mActy = this;
		initData();
		initActy();
		loadData();
	}

	private void initData(){
	}

	private void initActy(){
	}
	
	private void loadData(){
		
		new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what == 1){
					goActy();
				}
			}
		}.sendEmptyMessageDelayed(1, 2000);
	}
	
	private void goActy(){
		Intent intent;
		intent = new Intent(this, QCARInitActivityActy.class);
		this.startActivity(intent);
		this.finish();
		
//		if (SysMng.hasSaveUserName()) {
//			intent = new Intent(this, MainTaskActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		} else {
//
//			intent = new Intent(this, EditNameActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		}
	}
	
	

}
