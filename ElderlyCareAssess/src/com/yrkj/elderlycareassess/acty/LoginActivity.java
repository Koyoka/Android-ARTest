package com.yrkj.elderlycareassess.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.layout.ActivityLogin;

public class LoginActivity extends Activity {

	LoginActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mActy = this;
		initActy();
	}
	
	private void initActy(){
		ActivityLogin mActyCtrl = new ActivityLogin(this);
		mActyCtrl.getBtnLoginView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});
	}
	
	private void login(){
		Intent intent = new Intent(this,MainHomeNoneActionBarActivity.class);
		startActivity(intent);
		this.finish();
		
//		ArrayList<CustomerAssessTask> itemList =  AssessDBCtrl.getDoingAssessTaskList(this);
//		
//		DLog.LOG(SysMng.TAG_DB, itemList.size()+"  test===========");
		
	}

}
