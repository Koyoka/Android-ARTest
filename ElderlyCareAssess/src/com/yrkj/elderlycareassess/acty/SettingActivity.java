package com.yrkj.elderlycareassess.acty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.fragment.UserHeaderInfoFragment;
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
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case ActivitySetting.BtnBackViewId:
			this.finish();
			break;
		case ActivitySetting.BtnLogoutViewId:
			this.finish();
			break;
		default:
			break;
		}
		
	}
	

//	private void loadData(){
//		
//		String s = SysDBCtrl.getLastLoginDate(this);
//		
//		mLayout.getTxtLastLoginDateView().setText(s.isEmpty()?"Ê×´ÎµÇÂ½":s);
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
