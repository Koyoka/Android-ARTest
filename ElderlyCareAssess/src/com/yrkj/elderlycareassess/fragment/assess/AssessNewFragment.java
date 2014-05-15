package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.layout.FragmentAssessNew;

public class AssessNewFragment extends AssessBaseFragment 
implements OnClickListener {
	
	
	public AssessNewFragment(MainAssessActivity a,CustomerData c) {
		super(a,c);
		// TODO Auto-generated constructor stub
	}


	OnBtnStratClickListener mOnBtnStratClickListener;
	View mV;
	FragmentAssessNew mFragmentAssessNew ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_new, container,
				false);
		
		initFragment();
		return mV;
	}
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_new));
		mFragmentAssessNew = new FragmentAssessNew(mV);
		mFragmentAssessNew.getBtnStratView().setOnClickListener(this);
		
	}
	
	public void setOnBtnStratClickListener(OnBtnStratClickListener l){
		mOnBtnStratClickListener = l;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case FragmentAssessNew.BtnStratViewId:
//			getActivity().
			if(mOnBtnStratClickListener!=null){
				mOnBtnStratClickListener.onBtnStratClick();
			}
			break;

		default:
			break;
		}
		
	}
	
	
	public interface OnBtnStratClickListener{
		public void onBtnStratClick();
	}
	
}
