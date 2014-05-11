package com.yrkj.elderlycareassess.acty;

//import android.app.ActionBar;
//import android.app.ActionBar.Tab;
//import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.fragment.AssessDoneListFragment;
import com.yrkj.elderlycareassess.fragment.AssessTaskListFragment;
import com.yrkj.elderlycareassess.fragment.HomeFragment;
import com.yrkj.elderlycareassess.fragment.ReportFragment;
import com.yrkj.util.log.DLog;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBar.TabListener;


public class MainHomeActivity extends ActionBarActivity implements 
OnClickListener, TabListener {
	final String TAG = "com.yrkj.elderlycareassess.acty.MainHomeActivity";
	final String SAVE_STATE_KEY_FRAGMENT = "curTag";
	final String SAVE_STATE_KEY_TAB = "tabIndex";
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
		
		initActy();
		
		if (savedInstanceState == null) {
			addFragment(HomeFragment.class.getName(),null);
//			DLog.LOG(SysMng.TAG_FRAGMENT,"MainHomeActivity ---- savedInstanceState == null");
		}else{
			addFragment(savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT),null);
			if(getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS){
				getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(SAVE_STATE_KEY_TAB, 0));
			}
//			DLog.LOG(SysMng.TAG_FRAGMENT,"MainHomeActivity ---- savedInstanceState == " + savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT));
		}
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SAVE_STATE_KEY_FRAGMENT, mCurClass);
		if(getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS){
			outState.putInt(SAVE_STATE_KEY_TAB, getActionBar().getSelectedNavigationIndex());
		}
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
			addFragment(HomeFragment.class.getName(),null);
			
			break;
		case R.id.btnAssessView:
//			add(AssessFragment.class.getName(),null);
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			break;
		case R.id.btnReportView:
			addFragment(ReportFragment.class.getName(),null);
			break;
		default:
			break;
		}
	}
	
	private synchronized void addFragment(String className,Bundle args){
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
		
		if(mCurClass.equals(AssessTaskListFragment.class.getName()) 
				|| mCurClass.equals(AssessDoneListFragment.class.getName()) ){
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}else{
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			
		}
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
	
	

//	@Override
//	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
//		
//	}
//
//	@Override
//	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//		if(tab.getTag().equals(TAB_TAG_TASK)){
//			addFragment(AssessTaskListFragment.class.getName(),null);
//		}else if(tab.getTag().equals(TAB_TAG_DONE)){
//			addFragment(AssessDoneListFragment.class.getName(),null);
//		}
//	}
//
//	@Override
//	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void onTabSelected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getTag().equals(TAB_TAG_TASK)){
			addFragment(AssessTaskListFragment.class.getName(),null);
		}else if(tab.getTag().equals(TAB_TAG_DONE)){
			addFragment(AssessDoneListFragment.class.getName(),null);
		}
	}

	@Override
	public void onTabUnselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
