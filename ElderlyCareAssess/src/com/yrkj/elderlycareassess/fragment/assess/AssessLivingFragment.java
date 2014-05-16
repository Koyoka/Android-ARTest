package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.R.integer;
import android.R.interpolator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
//import android.widget.RadioGroup.OnCheckedChangeListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
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
		loadData();
		return v;
	}

	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_living));
		mLayout.getRdoQ4ItemView().setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				if(
				mLayout.getRdoQ4ItemView().getCheckedRadioButtonId()
				== mLayout.ChkQ4ItemYViewId){
					mLayout.getChkQ4ItemLabView().setVisibility(View.VISIBLE);
					mLayout.getChkQ4ItemView().setVisibility(View.VISIBLE);
					if(mLayout.getChkQ4Item4View().isChecked()){
						mLayout.getTxtQ4ItemEditValView().setVisibility(View.VISIBLE);
					}else{
						mLayout.getTxtQ4ItemEditValView().setVisibility(View.GONE);
					}
				}else{
					mLayout.getChkQ4ItemLabView().setVisibility(View.GONE);
					mLayout.getChkQ4ItemView().setVisibility(View.GONE);
					mLayout.getTxtQ4ItemEditValView().setVisibility(View.GONE);
				}
			}
		});
		
		mLayout.getChkQ4Item4View().setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mLayout.getTxtQ4ItemEditValView().setVisibility(isChecked?View.VISIBLE:View.GONE);
			}
		});
		
	}
	
	private void loadData(){
		if(mCust == null)
			return;
		{
			int c = 0;
			c = Integer.parseInt(mCust.economicstatus, 10);
			bindQ1Data(c);
		}
		
		{
			int c = 0;
			c = Integer.parseInt(mCust.livestatus, 10);
			bindQ2Data(c);
		}
		
		{
			int c = 0;
			c = Integer.parseInt(mCust.housestatus, 10);
			bindQ3Data(c);
		}
		
		{
			int c = 0;
			c = Integer.parseInt(mCust.helper, 10);
			bindQ4Data(c);
			mLayout.getTxtQ4ItemEditValView().setText(
					mCust.otherhelper);
			mLayout.getChkQ4ItemYView().setChecked(mCust.helpstatus.equals("1"));
			mLayout.getChkQ4ItemNView().setChecked(!mLayout.getChkQ4ItemYView().isChecked());
		}
		
		{
			int c = 0;
			c = Integer.parseInt(mCust.medicalstatus, 10);
			bindQ5Data(c);
			mLayout.getTxtQ5ItemEditValView().setText(mCust.othermedical);
		}
	}
	
	private void setUpdateCustData(){
		if(mCust == null)
			return;
		mCust.economicstatus = getQ1Data()+"";
		
		mCust.livestatus = getQ2Data()+"";
		
		mCust.housestatus = getQ3Data()+"";
		
		mCust.helper = getQ4Data()+"";
		mCust.otherhelper = mLayout.getTxtQ4ItemEditValView().getText()+"";
		mCust.helpstatus = mLayout.getChkQ4ItemYView().isChecked()?"1":"0";
		
		mCust.medicalstatus = getQ5Data()+"";
		mCust.othermedical = mLayout.getTxtQ5ItemEditValView().getText()+"";
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
		mLayout.getChkQ1Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ1Item3View().setChecked((data&bit3)==0?false:true);
		mLayout.getChkQ1Item4View().setChecked((data&bit4)==0?false:true);
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
	
	private int getQ4Data(){
		return (mLayout.getChkQ4Item1View().isChecked() ? bit1 : 0)
				| (mLayout.getChkQ4Item2View().isChecked() ? bit2 : 0)
				| (mLayout.getChkQ4Item3View().isChecked() ? bit3 : 0)
				| (mLayout.getChkQ4Item4View().isChecked() ? bit4 : 0);
	}
	private void bindQ4Data(int data){
		mLayout.getChkQ4Item1View().setChecked((data&bit1)==0?false:true);
		mLayout.getChkQ4Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ4Item3View().setChecked((data&bit3)==0?false:true);
		mLayout.getChkQ4Item4View().setChecked((data&bit4)==0?false:true);
	}
	
	private int getQ5Data(){
		return (mLayout.getChkQ5Item1View().isChecked() ? bit1 : 0)
				| (mLayout.getChkQ5Item2View().isChecked() ? bit2 : 0)
				| (mLayout.getChkQ5Item3View().isChecked() ? bit3 : 0);
//				| (mLayout.getChkQ5Item4View().isChecked() ? bit4 : 0);
	}
	private void bindQ5Data(int data){
		mLayout.getChkQ5Item1View().setChecked((data&bit1)==0?false:true);
		mLayout.getChkQ5Item2View().setChecked((data&bit2)==0?false:true);
		mLayout.getChkQ5Item3View().setChecked((data&bit3)==0?false:true);
//		mLayout.getChkQ4Item4View().setChecked((data&bit4)==0?false:true);
	}
	
	public void saveData() {
		if(mCust == null)
			return;
		// TODO Auto-generated method stub
		setUpdateCustData();
		AssessDBCtrl.updateCustomerById(getActivity(), mCust);
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if(hidden){
			saveData();
		}
	}
	
	
}
