package com.yrkj.mwrmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.service.BackWorkSerive;
import com.yrkj.util.log.DLog;

public class EmptyActivity extends Activity {
	private Context mContext = null;
	private boolean mHasBeenInit = false;
	
	private static Activity mActy = null;
	public static Activity getInstance(){
			return mActy;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empy);
		DLog.LOG("EmptyActivity--------[onCreate]");
		mActy = this;
		mContext = this;
		
		initActy();

		WSInfo ws = SysMng.getWSInfo();
		if(ws.WSCode.length() == 0
				|| ws.AccessKey.length() == 0
				|| ws.SecretKey.length() == 0
				|| ws.CrateMask.length() == 0){
			mHasBeenInit = false;
		}else{
			mHasBeenInit = true;
		}
		
		TxnInfo txnInfo = SysMng.getTxnInfo();
		if(mHasBeenInit && txnInfo.CarCode.length() != 0
				&& txnInfo.DriverCode.length() != 0
				&& txnInfo.InspectorCode.length() != 0)
		{
			Intent intent = new Intent(mContext, MainActivity.class);
			startActivity(intent);
		}else{
			
			Intent intent = new Intent(mContext, LaunchActivity.class);
			startActivity(intent);
		}
		
//		Intent intent = new Intent(this, LaunchActivity.class);
//		startActivity(intent);
//		finish();
		
		
	}
	
	private void initActy(){
		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
    	startService(intent);
    	
    	BaseApplication.getInstance().mLocationClient.start();
    	TextView tv = (TextView) findViewById(R.id.txtTextView);
    	BaseApplication.getInstance().locationTextView = tv;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		DLog.LOG("EmptyActivity--------[onDestroy]");
		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
		stopService(intent);
		BaseApplication.getInstance().mLocationClient.stop();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DLog.LOG("EmptyActivity--------[onResume]");
	}
	
}
