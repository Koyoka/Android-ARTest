package com.yrkj.elderlycareassess.acty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.gusturelock.LockActivity;
import com.yrkj.elderlycareassess.layout.ActivitySetting;

public class SettingActivity extends FragmentActivity implements OnClickListener {

	SettingActivity mActy;
	ActivitySetting mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mActy = this;
		mLayout = new ActivitySetting(this);
		initActy();
		
	}
	
	private void initActy(){
//		UserHeaderInfoFragment f
//		= new UserHeaderInfoFragment();
//
//		FragmentManager fMng = getSupportFragmentManager();
//		FragmentTransaction ft = fMng.beginTransaction();
//		ft.add(ActivitySetting.LayoutUserHeaderInfoContainerViewId, 
//				f, 
//				UserHeaderInfoFragment.class.getName());
//		ft.commit();
		
		mLayout.getBtnBackView().setOnClickListener(this);
		mLayout.getBtnLogoutView().setOnClickListener(this);
		mLayout.getBtnAysnView().setOnClickListener(this);
		mLayout.getBtnSetNetPwdView().setOnClickListener(this);
		mLayout.getBtnSetLocPwdView().setOnClickListener(this);
		mLayout.getBtnLogView().setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case ActivitySetting.BtnBackViewId:
			this.finish();
			break;
		case ActivitySetting.BtnLogoutViewId:
			Intent intent3 = new Intent(SettingActivity.this, LoginActivity.class);
			
			intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
//			android.os.Process.killProcess(android.os.Process.myPid()) ;   //获取PID 
//			  System.exit(0); 
//			this.finish();
			return;
		case ActivitySetting.BtnAysnViewId:
			Intent intent = new Intent(this, SyncActivity.class);
			startActivity(intent);
			break;
		case ActivitySetting.BtnSetLocPwdViewId:
			Intent intent11 = new Intent(this, LockActivity.class);
			intent11.putExtra("Login", true);
			startActivity(intent11);
			break;
		case ActivitySetting.BtnSetNetPwdViewId:
			Intent intent1 = new Intent(this, EditActivity.class);
			startActivity(intent1);
			break;
		case ActivitySetting.BtnLogViewId:
			Intent intent2 = new Intent(this, SettingContentActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
		
	}
	

//	private void loadData(){
//		
//		String s = SysDBCtrl.getLastLoginDate(this);
//		
//		mLayout.getTxtLastLoginDateView().setText(s.isEmpty()?"首次登陆":s);
//		
//		AssessUserData u = SysMng.getUserInfo();
//		DLog.LOG(SysMng.TAG_DB, u+" 1 ===["+u.UserId);
//		u = AssessUserDBCtrl.getUserData(this, u.UserId);
//		DLog.LOG(SysMng.TAG_DB, u+" 2 ===[");
//		if(u != null){
//			mLayout.getTxtUserNameView().setText(u.UserName);
//			mLayout.getTxtAddressView().setText(u.OfficeAddress);
//			mLayout.getTxtOfficeView().setText(u.Office);
//		}
//		
//		
//	}
}
