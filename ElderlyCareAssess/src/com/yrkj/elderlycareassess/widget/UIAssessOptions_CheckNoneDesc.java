package com.yrkj.elderlycareassess.widget;

import java.util.ArrayList;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.util.log.DLog;

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
	private boolean needCreate  = false;
	public void add(int id, String name,String desc){
		if(mR2 == null){
			View v = mInflater.inflate(R.layout.view_question_item_row_check,
						mVGroup, false);
			mVGroup.addView(v);
			mR1 = (CheckBox) v.findViewById(R.id.chkQuestionItem1View);
			mR2 = (CheckBox) v.findViewById(R.id.chkQuestionItem2View);
		}
		ItemData item = new ItemData();
		item.Id = ""+id;
		item.Name = name;
		item.Desc = desc;
		CheckBox rv1 = mR1;
		CheckBox rv2 = mR2;
		if(rv1.getVisibility() == View.INVISIBLE){
			rv1.setText(name);
			rv1.setId(id);
			rv1.setVisibility(View.VISIBLE);
			rv1.setTag(item);
			mRvList.add(rv1);
//			mRvList.get(mRvList.size()).setTag(item);
		}else if(rv2.getVisibility() == View.INVISIBLE){
			rv2.setText(name);
			rv2.setId(id);
			rv2.setVisibility(View.VISIBLE);
			rv2.setTag(item);
			mRvList.add(rv2);
			mR1 = null;
			mR2 = null;
		}
	}
	private ArrayList<CheckBox> mRvList = new ArrayList<CheckBox>();
	public ItemData[] getSelectIds(){
		ArrayList<ItemData> itenList = new ArrayList<ItemData>();
		for (CheckBox v : mRvList) {
			if(v.isChecked()){
				ItemData item = (ItemData) v.getTag();
//				DLog.LOG(SysMng.TAG_ASSESS, v.getText() + " " + v.getId() + " " + item.Name);
				itenList.add(item);
			}
		}
//		return new ItemData[0];
		return itenList.toArray(new ItemData[itenList.size()]);
	}
}
