package com.yrkj.mwrmobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.mwrmobile.layout.ActivityLaunch;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.mwrmobile.util.scanner.MWRCaptureActivity;

public class LaunchActivity extends Activity implements OnClickListener {

	public final static String Tag = "com.yrkj.mwrmobile.LaunchActivity";
	
	private final static int SysStatus_NoInit = 1;
	private final static int SysStatus_NoLogin = 2;
//	private final static int SysStatus_
	
	private ActivityLaunch mLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		initData();
		initActy();
	}
	
	private void initData(){
		
	}
	
	private void initActy(){
		mLayout = new ActivityLaunch(this);
		
		mLayout.getBtnExit().setOnClickListener(this);
		mLayout.getBtnScan().setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == ActivityLaunch.BtnExitId){
			finish();
		}else if(v.getId() == ActivityLaunch.BtnScanId){
			boolean hasInit = false;
			CaptureHelper.ShowCapture(this,
					hasInit ? MWRCaptureActivity.SCANNERTYPE_KEY_Login :
							  MWRCaptureActivity.SCANNERTYPE_KEY_InitSystem);
		}
		
	}
	
	
}
