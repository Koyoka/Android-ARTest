package com.yrkj.elderlycareassess.fragment.assess;

import java.text.ParseException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.layout.FragmentAssessNew;
import com.yrkj.util.date.DateHelper;

public class AssessNewFragment extends AssessBaseFragment 
implements OnClickListener {
	
	private AssessTaskHeaderData mTask;
	private OnBtnStratClickListener mOnBtnStratClickListener;
	private View mV;
	private FragmentAssessNew mLayout ;
	
	
	public AssessNewFragment(MainAssessActivity a, CustomerData c,
			AssessTaskHeaderData t) {
		super(a, c);
		mTask = t;
	}


	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_new, container,
				false);
		mLayout = new FragmentAssessNew(mV);
		initFragment();
		loadData();
		return mV;
	}
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_new));
		
		mLayout.getBtnStratView().setOnClickListener(this);
		
	}
	
	private void loadData(){
		if(mCust == null || mTask == null){
			return;
		}
		
		mLayout.getTxtAssessCustView().setText(mCust.customername);
		mLayout.getTxtAssessAddressView().setText(mCust.address);
		mLayout.getTxtAssessNumView().setText(mTask.AssessNum);
		mLayout.getTxtAssessTypeView().setText(AssessTaskHeaderData.getAssessTypeDesc(mTask.AssessType));
		mLayout.getTxtAssessCountView().setText(mCust.assessnum);
		mLayout.getTxtAssessLastDateView().setText(mTask.LastAssessDate);
//		mLayout.getTxtAssessEndDateView().setText(mTask.EndAssessDate);
		
		try {
			mLayout.getTxtAssessEndDateView().setText(DateHelper.getTodayStr());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mLayout.getTxtEmployNameView().setText(mCust.customername);
		
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


	@Override
	public ArrayList<AssessTaskDetailData> getSelectData() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
