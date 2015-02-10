package com.yrkj.mwrmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yrkj.util.log.ToastUtil;

public class RecoverDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover_detail);
		
		initActy();
	}
	
	private void initActy(){
	
  		Intent intent = getIntent();
		String result = intent.getStringExtra("result");
		ToastUtil.show(this, result +"   crate code");
	}
}
