package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.acty.qcar.QCARInitActivityActy;

public class LaunchLiuActivity extends Activity {
	
	final String TAG = "com.yrkj.artaskgame.cmobile.acty.LaunchLiuActivity";
	LaunchLiuActivity mActy = null;
	
	int curWelcomeGrilID = 0;
	int time = 800;
	
	ImageView mImgWelcomeGrilView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		goActy();
		
//		setContentView(R.layout.activity_launch);
//		mActy = this;
//		initData();
//		initActy();
//		loadData();
	}

	private void initData(){
	}

	private void initActy(){
		mImgWelcomeGrilView = (ImageView) findViewById(R.id.imgWelcomeGrilView);
	}
	
	
	
	private void loadData(){
		curWelcomeGrilID = R.drawable.welcome_girl_2;
		mImgWelcomeGrilView.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(curWelcomeGrilID == R.drawable.welcome_girl_2){
					curWelcomeGrilID = R.drawable.welcome_girl_1;
				}else{
					curWelcomeGrilID = R.drawable.welcome_girl_2;
					
				}
				mImgWelcomeGrilView.setImageResource(curWelcomeGrilID);
				
				mImgWelcomeGrilView.postDelayed(this,time);
			}
		}, time);
		
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
