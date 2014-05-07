package com.yrkj.elderlycareassess.acty;

//import android.app.ActionBar;
//import android.app.ActionBar.Tab;
//import android.app.ActionBar.TabListener;
//import android.app.ActionBar;
//import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.BaseActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.fragment.AssessDoneListFragment;
import com.yrkj.elderlycareassess.fragment.AssessTaskListFragment;
import com.yrkj.elderlycareassess.fragment.HomeFragment;
import com.yrkj.elderlycareassess.fragment.ReportFragment;
import com.yrkj.util.log.DLog;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBar.TabListener;


public class MainHomeNoneActionBarActivity extends 
//ActionBarActivity 
FragmentActivity
//BaseActivity 
implements 
OnClickListener {
	final String TAG = "com.yrkj.elderlycareassess.acty.MainHomeActivity";
	final String SAVE_STATE_KEY_FRAGMENT = "curTag";
	final String SAVE_STATE_KEY_TAB = "tabIndex";
	final String TAB_TAG_TASK = "task";
	final String TAB_TAG_DONE = "done";
	
	MainHomeNoneActionBarActivity mActy;

	private String mCurClass = "";
	
	View mHomeView;
	View mAssessView;
	View mReportView;
	
	Button mBtnAssessTaskView;
	Button mBtnAssessDoneView;
	
	View mLayoutAssessTabView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_home_noneactionbar);
		mActy = this;
		
		initActy();
		
//		DLog.LOG(SysMng.TAG_FRAGMENT,"getActionBar() == " + getActionBar() + " " + getSupportActionBar());
		
		if (savedInstanceState == null) {
			addFragment(HomeFragment.class.getName(),null);
		}else{
			addFragment(savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT),null);
		}
		
		
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
		
		
		
		mBtnAssessTaskView = (Button) findViewById(R.id.btnAssessTaskView);
		mBtnAssessDoneView = (Button) findViewById(R.id.btnAssessDoneView);
		
		mLayoutAssessTabView = findViewById(R.id.layoutAssessTabView);
		
		mHomeView.setOnClickListener(this);
		mAssessView.setOnClickListener(this);
		mReportView.setOnClickListener(this);
		
		mBtnAssessTaskView.setOnClickListener(this);
		mBtnAssessDoneView.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHomeView:
			addFragment(HomeFragment.class.getName(),null);
			
			break;
		case R.id.btnAssessView:
			String defineClassName = 
			mLayoutAssessTabView.getTag() != null
					&& !mLayoutAssessTabView.getTag().toString().isEmpty() ? mLayoutAssessTabView
					.getTag().toString() : AssessTaskListFragment.class
					.getName();
			addFragment(defineClassName,null);
			break;
		case R.id.btnReportView:
			addFragment(ReportFragment.class.getName(),null);
			break;
		case R.id.btnAssessTaskView:
			addFragment(AssessTaskListFragment.class.getName(),null);
			break;
		case R.id.btnAssessDoneView:
			addFragment(AssessDoneListFragment.class.getName(),null);
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
		showAssessFragment(className);
	}
	
	private void showAssessFragment(String className){
		
		if(mCurClass.equals(AssessTaskListFragment.class.getName()) 
				|| mCurClass.equals(AssessDoneListFragment.class.getName()) ){
			mLayoutAssessTabView.setTag(className);
			mLayoutAssessTabView.setVisibility(View.VISIBLE);
		}else{
			mLayoutAssessTabView.setVisibility(View.GONE);
			
		}
	}
	

}
