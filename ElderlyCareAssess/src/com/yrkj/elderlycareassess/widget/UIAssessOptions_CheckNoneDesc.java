package com.yrkj.elderlycareassess.widget;

import java.util.ArrayList;

import com.yrkj.elderlycareassess.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class UIAssessOptions_CheckNoneDesc 
extends UIAssessOptions_Base {
	private LayoutInflater mInflater;

	private ViewGroup mVGroup;
	
	public UIAssessOptions_CheckNoneDesc(LayoutInflater inflater,
			ViewGroup container) {
		mInflater = inflater;
		View v = mInflater.inflate(R.layout.view_assess_options_checknonedesc,
				container, false);
		mVGroup = (ViewGroup) v.findViewById(R.id.layoutNormalContentView);
		container.addView(mVGroup);
	}
	
	private CheckBox mR1 = null;
	private CheckBox mR2 = null;
	
	public void add(int id, String name,String desc){
		if(mR2 == null){
			View v = mInflater.inflate(R.layout.view_question_item_row_check,
						mVGroup, false);
			mVGroup.addView(v);
			mR1 = (CheckBox) v.findViewById(R.id.chkQuestionItem1View);
			mR2 = (CheckBox) v.findViewById(R.id.chkQuestionItem2View);
		}
		
		CheckBox rv1 = mR1;
		CheckBox rv2 = mR2;
		if(rv1.getVisibility() == View.INVISIBLE){
			rv1.setText(name);
			rv1.setId(id);
			rv1.setVisibility(View.VISIBLE);
//			rv1.setOnCheckedChangeListener(this);
			mRvList.add(rv1);
		}else if(rv2.getVisibility() == View.INVISIBLE){
			rv2.setText(name);
			rv2.setId(id);
			rv2.setVisibility(View.VISIBLE);
//			rv2.setOnCheckedChangeListener(this);
			mRvList.add(rv2);
//			mR1 = null;
			mR2 = null;
		}
	}
	private ArrayList<CheckBox> mRvList = new ArrayList<CheckBox>();
	public Integer[] getSelectIds(){
		ArrayList<Integer> itenList = new ArrayList<Integer>();
		for (CheckBox v : mRvList) {
			if(v.isChecked()){
				itenList.add(v.getId());
			}
		}
		return itenList.toArray(new Integer[itenList.size()]);
	}
}
