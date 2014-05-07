package com.yrkj.elderlycareassess.acty;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.fragment.assess.AssessCognitiveFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessEmotionalAndBehavioralFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSelfcareFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSocialLifeFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessVisualFragment;
import com.yrkj.util.log.DLog;

public class MainAssessActivity extends 
//ActionBarActivity 
FragmentActivity
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
	
	Button mBtnBackView;
	Button mBtnGoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_assess);
		mActy = this;
		initData();
		initActy();
		
		getSupportFragmentManager().beginTransaction()
		.add(R.id.layoutBodyView,new AssessNewFragment(), AssessNewFragment.class.getName())
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
				"back",
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
		
		mBtnGoView = (Button) findViewById(R.id.btnGoView);
		
		mBtnBackView = (Button) findViewById(R.id.btnBackView);
		
		mBtnGoView.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						addFragment();
					}
				});
		mBtnBackView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goBack();
			}
		});
		
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
		if(index+1 < mTitleList.length)
			mBtnGoView.setText(mTitleList[index+1]);
		else
			mBtnGoView.setText("");
		
		if(index-1 >= 0)
			mBtnBackView.setText(mTitleList[index-1]);
		else{
			mBtnBackView.setText("");
		}
	}

	public void goBack(){
		FragmentManager fMng = getSupportFragmentManager();
		fMng.popBackStack();
//		setNavBtn(fMng.getBackStackEntryCount());
//		fMng.addOnBackStackChangedListener(new OnBackStackChangedListener() {
//			
//			@Override
//			public void onBackStackChanged() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
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
		
		Fragment mFragment = Fragment.instantiate(mActy, className, args);
		FragmentTransaction ft = fMng.beginTransaction()
		.replace(R.id.layoutBodyView, mFragment, className)
		.addToBackStack(null);
		ft.commit();
//		setNavBtn(i);
		
	}

}
