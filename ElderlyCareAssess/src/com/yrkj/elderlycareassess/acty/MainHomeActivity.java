package com.yrkj.elderlycareassess.acty;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.fragment.AssessDoneFragment;
import com.yrkj.elderlycareassess.fragment.AssessFragment;
import com.yrkj.elderlycareassess.fragment.AssessTaskFragment;
import com.yrkj.elderlycareassess.fragment.HomeFragment;
import com.yrkj.elderlycareassess.fragment.ReportFragment;
import com.yrkj.util.log.DLog;


public class MainHomeActivity extends ActionBarActivity implements 
OnClickListener, TabListener {
	final String TAG = "com.yrkj.elderlycareassess.acty.MainHomeActivity";
	final String SAVE_STATE_KEY_FRAGMENT = "curTag";
	final String TAB_TAG_TASK = "task";
	final String TAB_TAG_DONE = "done";
	
	MainHomeActivity mActy;

	private String mCurClass = "";
	
	View mHomeView;
	View mAssessView;
	View mReportView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_home);
		mActy = this;
		
		if (savedInstanceState == null) {
			add(HomeFragment.class.getName(),null);
			DLog.LOG(SysMng.TAG_FRAGMENT,"MainHomeActivity ---- savedInstanceState == null");
		}else{
			add(savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT),null);
			DLog.LOG(SysMng.TAG_FRAGMENT,"MainHomeActivity ---- savedInstanceState == " + savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT));
		}
		
		initActy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SAVE_STATE_KEY_FRAGMENT, mCurClass);
	}
	
	private void initActy(){
		mHomeView = findViewById(R.id.btnHomeView);
		mAssessView = findViewById(R.id.btnAssessView);
		mReportView = findViewById(R.id.btnReportView);
		
		mHomeView.setOnClickListener(this);
		mAssessView.setOnClickListener(this);
		mReportView.setOnClickListener(this);
		
		createTabs();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHomeView:
			add(HomeFragment.class.getName(),null);
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		case R.id.btnAssessView:
//			add(AssessFragment.class.getName(),null);
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			break;
		case R.id.btnReportView:
			add(ReportFragment.class.getName(),null);
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		default:
			break;
		}
	}
	
	private synchronized void add(String className,Bundle args){
		if(className.equals(mCurClass)){
			return;
		}
		FragmentManager fMng = getSupportFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		Fragment f = fMng.findFragmentByTag(mCurClass);
		if(f != null){
			ft.detach(f);
		}
		
		Fragment mFragment= fMng.findFragmentByTag(className);
		if(mFragment == null){
			mFragment = Fragment.instantiate(mActy, className, args);
			ft.add(R.id.layoutBodyView, mFragment, className);
		}else{
			ft.attach(mFragment);
		}
		ft.commit();
		
		mCurClass = className;
	}
	
	private void createTabs(){
		ActionBar bar = getActionBar();
		bar.addTab(bar.newTab()
				.setTag(TAB_TAG_TASK)
                .setText("Task")
                .setTabListener(this));
		bar.addTab(bar.newTab()
				.setTag(TAB_TAG_DONE)
				.setText("Done")
				.setTabListener(this));
	}
	
	

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getTag().equals(TAB_TAG_TASK)){
			add(AssessTaskFragment.class.getName(),null);
		}else if(tab.getTag().equals(TAB_TAG_DONE)){
			add(AssessDoneFragment.class.getName(),null);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
