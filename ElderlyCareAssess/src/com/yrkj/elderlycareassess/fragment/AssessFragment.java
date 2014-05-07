package com.yrkj.elderlycareassess.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;


public class AssessFragment extends Fragment implements OnClickListener {

	final String SAVE_STATE_KEY_FRAGMENT = "curTag1";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_assess, container,
				false);
		initFrag(v);
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState == null){
			add(AssessTaskListFragment.class.getName(),null);
			DLog.LOG(SysMng.TAG_FRAGMENT,"AssessFragment ---- savedInstanceState == null");
		}else{
			DLog.LOG(SysMng.TAG_FRAGMENT,"AssessFragment ---- savedInstanceState == " + savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT));
			add(savedInstanceState.getString(SAVE_STATE_KEY_FRAGMENT),null);
		}
	}
	
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(SAVE_STATE_KEY_FRAGMENT, mCurClass);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAssessTaskView:
			add(AssessTaskListFragment.class.getName(),null);
			break;
		case R.id.btnAssessDoneView:
			add(AssessDoneListFragment.class.getName(),null);
			break;
		default:
			break;
		}
		
	}
	
	private void initFrag(View v){
//		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		
		v.findViewById(R.id.btnAssessTaskView).setOnClickListener(this);
		v.findViewById(R.id.btnAssessDoneView).setOnClickListener(this);
	}
	
	private String mCurClass = "";
	
	private void add(String className,Bundle args){
		if(className.equals(mCurClass)){
			return;
		}
		
//		FragmentManager fMng = getFragmentManager();
		FragmentManager fMng = getChildFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		Fragment f = fMng.findFragmentByTag(mCurClass);
		if(f != null){
			ft.detach(f);
		}
		
		
		Fragment mFragment= fMng.findFragmentByTag(className);
		if(mFragment == null){
			mFragment = Fragment.instantiate(getActivity(), className, args);
			ft.add(R.id.layoutAssessBodyView, mFragment, className);
		}else{
			ft.attach(mFragment);
		}
		ft.commit();
		
		mCurClass = className;
	}

	
}
