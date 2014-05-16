package com.yrkj.elderlycareassess.widget;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.yrkj.elderlycareassess.R;

public class UIAssessOptions_RadioNoneDesc 
extends UIAssessOptions_Base  implements OnCheckedChangeListener {

	private LayoutInflater mInflater;
	
	private ViewGroup mVGroup;
	public UIAssessOptions_RadioNoneDesc(LayoutInflater inflater,
			ViewGroup container) {
		mInflater = inflater;
		View v = mInflater.inflate(R.layout.view_assess_options_radiononedesc,
				container, false);
		mVGroup = (ViewGroup) v.findViewById(R.id.layoutNormalContentView);
		container.addView(mVGroup);
	}
	
	
	private RadioButton mR1 = null;
	private RadioButton mR2 = null;
	
	public void add(int id, String name,String desc,boolean isChecked){
		if(mR2 == null){
			View v = mInflater.inflate(R.layout.view_question_item_row_radio,
						mVGroup, false);
			mVGroup.addView(v);
			mR1 = (RadioButton) v.findViewById(R.id.rdoQuestionItem1View);
			mR2 = (RadioButton) v.findViewById(R.id.rdoQuestionItem2View);
		}
		
		RadioButton rv1 = mR1;
		RadioButton rv2 = mR2;
		ItemData item = new ItemData();
		item.Id = id+"";
		item.Name = name;
		item.Desc = desc;
		if(rv1.getVisibility() == View.INVISIBLE){
			rv1.setChecked(isChecked);
			rv1.setText(name);
			rv1.setId(id);
			rv1.setVisibility(View.VISIBLE);
			rv1.setOnCheckedChangeListener(this);
			rv1.setTag(item);
			mRvList.add(rv1);
		}else if(rv2.getVisibility() == View.INVISIBLE){
			rv2.setChecked(isChecked);
			rv2.setText(name);
			rv2.setId(id);
			rv2.setVisibility(View.VISIBLE);
			rv2.setOnCheckedChangeListener(this);
			rv2.setTag(item);
			mRvList.add(rv2);
//			mR1 = null;
			mR2 = null;
		}
		
	}
	
	private ArrayList<RadioButton> mRvList = new ArrayList<RadioButton>();
	public ItemData[] getSelectIds(){
		for (RadioButton v : mRvList) {
			if(v.isChecked()){
				ItemData item = (ItemData) v.getTag();
				return new ItemData[]{item};
			}
		}
		return new ItemData[0];
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if(isChecked){
			for (RadioButton v : mRvList) {
				if(v.getId() != buttonView.getId()){
					v.setChecked(false);
				}
			}
		}
		
	}
	
}
