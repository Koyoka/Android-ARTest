package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.layout.FragmentAssessLiving;

public class AssessLivingFragment extends AssessBaseFragment {

	public AssessLivingFragment(MainAssessActivity a,CustomerData c) {
		super(a,c);
	}

	
	private FragmentAssessLiving mLayout = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_living, container,
				false);
		mLayout = new FragmentAssessLiving(v);
		
		initFragment();
		return v;
	}

	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_living));
		
		
		
		mLayout.getRdoQ4ItemView().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				if(
				mLayout.getRdoQ4ItemView().getCheckedRadioButtonId()
				== mLayout.ChkQ4ItemYViewId){
					mLayout.getChkQ4ItemLabView().setVisibility(View.VISIBLE);
					mLayout.getChkQ4ItemView().setVisibility(View.VISIBLE);
				}else{
					mLayout.getChkQ4ItemLabView().setVisibility(View.GONE);
					mLayout.getChkQ4ItemView().setVisibility(View.GONE);
				}
			}
		});
		
	}

	@Override
	public ArrayList<AssessTaskDetailData> getSelectData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveAssessDetailData() {
		// TODO Auto-generated method stub
		
	}
	
	int bit1 = 1;
	int bit2 = 2;
	int bit3 = 4;
	int bit4 = 8;
	private int getQ1Data(){
		return (mLayout.getChkQ1Item1View().isChecked() ? bit1 : 0)
				| (mLayout.getChkQ1Item2View().isChecked() ? bit2 : 0)
				| (mLayout.getChkQ1Item3View().isChecked() ? bit3 : 0)
				| (mLayout.getChkQ1Item4View().isChecked() ? bit4 : 0);
	}
	private void bindQ1Data(int data){
		mLayout.getChkQ1Item1View().setChecked((data&bit1)==0?false:true);
		mLayout.getChkQ2Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ3Item3View().setChecked((data&bit3)==0?false:true);
		mLayout.getChkQ4Item4View().setChecked((data&bit4)==0?false:true);
	}
	
	private int getQ2Data(){
		return (mLayout.getChkQ2Item1View().isChecked() ? bit1 : 0)
				| (mLayout.getChkQ2Item2View().isChecked() ? bit2 : 0)
				| (mLayout.getChkQ2Item3View().isChecked() ? bit3 : 0)
				| (mLayout.getChkQ2Item4View().isChecked() ? bit4 : 0);
	}
	private void bindQ2Data(int data){
		mLayout.getChkQ2Item1View().setChecked((data&bit1)==0?false:true);
		mLayout.getChkQ2Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ2Item3View().setChecked((data&bit3)==0?false:true);
		mLayout.getChkQ2Item4View().setChecked((data&bit4)==0?false:true);
	}
	
	private int getQ3Data(){
		return (mLayout.getChkQ3Item1View().isChecked() ? bit1 : 0)
				| (mLayout.getChkQ3Item2View().isChecked() ? bit2 : 0)
				| (mLayout.getChkQ3Item3View().isChecked() ? bit3 : 0)
				| (mLayout.getChkQ3Item4View().isChecked() ? bit4 : 0);
	}
	private void bindQ3Data(int data){
		mLayout.getChkQ3Item1View().setChecked((data&bit1)==0?false:true);
		mLayout.getChkQ3Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ3Item3View().setChecked((data&bit3)==0?false:true);
		mLayout.getChkQ3Item4View().setChecked((data&bit4)==0?false:true);
	}
	
	
	
}
