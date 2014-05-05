package com.yrkj.elderlycareassess.acty;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.fragment.HomeFragment;

public class MainHomeActivity extends Activity implements 
OnClickListener {
	final String TAG = "com.yrkj.elderlycareassess.acty.MainHomeActivity";
	
	MainHomeActivity mActy;
	
	View mHomeView;
	View mAssessView;
	View mReportView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_home);
		mActy = this;
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.layoutBodyView, new HomeFragment()).commit();
		}
		
		initActy();
	}
	
	private void initActy(){
		
		
		mHomeView = findViewById(R.id.btnHomeView);
		mAssessView = findViewById(R.id.btnAssessView);
		mReportView = findViewById(R.id.btnReportView);
		
		mHomeView.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHomeView:
			
			break;
		case R.id.btnAssessView:
			
			break;
		case R.id.btnReportView:
			
			break;

		default:
			break;
		}
		
	}
	
	private void add(String tag,Class<Fragment> clz,Bundle args){
		Fragment mFragment;
		
//		mFragment = mActy.getFragmentManager().findFragmentByTag(tag);
//        if (mFragment != null && !mFragment.isDetached()) {
//            FragmentTransaction ft = mActy.getFragmentManager().beginTransaction();
//            ft.detach(mFragment);
//            ft.commit();
//        }
		
		mFragment = Fragment.instantiate(mActy, clz.getName(), args);
		
		getFragmentManager().
		beginTransaction().
		add(android.R.id.content, mFragment, tag).
//		detach(mFragment).
		commit();
	}

}
