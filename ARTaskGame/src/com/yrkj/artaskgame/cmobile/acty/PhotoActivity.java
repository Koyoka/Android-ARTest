package com.yrkj.artaskgame.cmobile.acty;


import android.app.Activity;
import android.os.Bundle;

import com.yrkj.artaskgame.R;

public class PhotoActivity extends Activity{
	
	final String TAG = "com.yrkj.artaskgame.cmobile.acty.EndTaskActivity";
	
	PhotoActivity mActy = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		mActy = this;
		initData();
		initActy();
		loadData();
	}

	private void initData() {
		
	}

	private void initActy() {
		
	}

	private void loadData() {
		
	}
}

