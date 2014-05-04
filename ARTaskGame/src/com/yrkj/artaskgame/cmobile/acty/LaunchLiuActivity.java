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
	
	public static final String INTENT_KEY_CLOSEAPP = "closeApp";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Intent intent = this.getIntent();
	      if(intent != null && intent.getBooleanExtra(INTENT_KEY_CLOSEAPP, false)){
	      	this.finish();
	      	return;
	      }else{
	    	  goActy();
	      }
		
//		setContentView(R.layout.activity_launch);
//		mActy = this;
//		initData();
//		initActy();
//		loadData();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void goActy(){
		Intent intent;
		intent = new Intent(this, QCARInitActivityActy.class);
		this.startActivity(intent);
//		this.finish();
		
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
