package com.yrkj.elderlycareassess.acty;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessCognitiveFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessEmotionalAndBehavioralFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment.OnBtnStratClickListener;
import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSelfcareFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSocialLifeFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessVisualFragment;
import com.yrkj.elderlycareassess.layout.ActivityMainAssess;
import com.yrkj.util.log.DLog;

public class MainAssessActivity extends 
//ActionBarActivity 
FragmentActivity 
implements 
OnClickListener, 
OnBtnStratClickListener
{

	MainAssessActivity mActy;

	int mCurIndex = 0;
	String[] mFragmentList = new String[] { 
			AssessPersonalInfoFragment.class.getName(),
			AssessLivingFragment.class.getName(),
			AssessSelfcareFragment.class.getName(),
			AssessCognitiveFragment.class.getName(),
			AssessEmotionalAndBehavioralFragment.class.getName(),
			AssessVisualFragment.class.getName(),
			AssessSocialLifeFragment.class.getName(),
			AssessServiceFragment.class.getName()
	};
	String[] mTitleList = null;
	
	ActivityMainAssess mActivityMainAssess;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_assess);
		mActy = this;
		mActivityMainAssess = new ActivityMainAssess(this);
		initData();
		initActy();
		
		AssessNewFragment f = new AssessNewFragment();
		f.setOnBtnStratClickListener(this);
		f.setActy(this);
		getSupportFragmentManager().beginTransaction()
		.add(R.id.layoutBodyView,f, AssessNewFragment.class.getName())
		.commit();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			finish();
			
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
	
	private void initData(){
		mTitleList = new String[] {
				"评估信息",
				getResources().getString(R.string.assess_title_person),
				getResources().getString(R.string.assess_title_living),
				getResources().getString(R.string.assess_title_selfcare),
				getResources().getString(R.string.assess_title_cogn),
				getResources().getString(R.string.assess_title_emot),
				getResources().getString(R.string.assess_title_visual),
				getResources().getString(R.string.assess_title_social),
				getResources().getString(R.string.assess_title_service)
		};
	}

	private void initActy() {
		
		
		mActivityMainAssess.getBtnGoView().setOnClickListener(this);
		mActivityMainAssess.getBtnBackView().setOnClickListener(this);
		mActivityMainAssess.getBtnFinishView().setOnClickListener(this);
		
		FragmentManager fMng = getSupportFragmentManager();
		
		fMng.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			@Override
			public void onBackStackChanged() {
				DLog.LOG(SysMng.TAG_FRAGMENT,getSupportFragmentManager().getBackStackEntryCount()+" = addOnBackStackChangedListener");
				setNavBtn(getSupportFragmentManager().getBackStackEntryCount());
			}
		});
	}
	
	
	
	private void setNavBtn(int index){
		
		DLog.LOG(SysMng.TAG_FRAGMENT, index+" = index");
		if(index == 0){
			mActivityMainAssess.getLayoutFootView().setVisibility(View.GONE);
		}else{
			mActivityMainAssess.getLayoutFootView().setVisibility(View.VISIBLE);
		}
		if(index+1 < mTitleList.length)
			mActivityMainAssess.getBtnGoView().setText(mTitleList[index+1]);
		else
			mActivityMainAssess.getBtnGoView().setText("提交");
		
		if(index-1 >= 0)
			mActivityMainAssess.getBtnBackView().setText(mTitleList[index-1]);
		else{
			mActivityMainAssess.getBtnBackView().setText("");
		}
	}

	public void goBack(){
		FragmentManager fMng = getSupportFragmentManager();
		fMng.popBackStack();
	}
	
	public void addFragment() {
		FragmentManager fMng = getSupportFragmentManager();
		int i = fMng.getBackStackEntryCount();
				
		int count =		fMng.getFragments() == null?
				0 : fMng.getFragments().size();
		DLog.LOG(SysMng.TAG_FRAGMENT, i+" "+count);
		
		if(i >= mFragmentList.length){
			return;
		}
		
		String className = mFragmentList[i];
		Bundle args = null;
		
		Fragment f = fMng.findFragmentByTag(className);
		if(f != null){
			return;
		}
		
		AssessBaseFragment mFragment = (AssessBaseFragment)Fragment.instantiate(mActy, className, args);
		FragmentTransaction ft = fMng.beginTransaction()
		.replace(R.id.layoutBodyView, mFragment, className)
		.addToBackStack(null);
		ft.commit();
		mFragment.setActy(this);
//		setNavBtn(i);
		
	}
	
	public void setAssessTitle(String s){
		mActivityMainAssess.getTxtMainAssessTitleView().setText(s);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case ActivityMainAssess.BtnFinishViewId:
			finish();
			break;
		case ActivityMainAssess.BtnBackViewId:
			goBack();
			break;
		case ActivityMainAssess.BtnGoViewId:
			addFragment();
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onBtnStratClick() {
		addFragment();
	}

}
