package com.yrkj.elderlycareassess.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.layout.ViewQuestionItem1;

public class UIAssessOptions_RadioWithDesc extends UIAssessOptions_Base {

	LayoutInflater mInflater;
//	private View mV;
	private UIRadioGroup mUIRadioGroup;

	public Integer[] getSelectIds(){
		if(mUIRadioGroup
				.getCheckedRadioButtonId() == -1){
			return null;
		}
		
		return new Integer[]{mUIRadioGroup
				.getCheckedRadioButtonId()};
	}
	
	public UIAssessOptions_RadioWithDesc(LayoutInflater inflater,
			ViewGroup container) {
		mInflater = inflater;
		View v = mInflater.inflate(R.layout.view_assess_options_radiowithdesc,
				container, false);
		mUIRadioGroup = (UIRadioGroup) v
				.findViewById(R.id.layoutNormalContentView);
		container.addView(mUIRadioGroup);
	}

	public void add(int id, String name, String desc) {
		View v = mInflater.inflate(R.layout.view_question_item_1,
				mUIRadioGroup, false);
		ViewQuestionItem1 holderView = new ViewQuestionItem1(v);
		holderView.getTxtQuestionItemView().setText(desc);
		holderView.getRdoQuestionItemView().setText(name);
		holderView.getRdoQuestionItemView().setId(id);
		mUIRadioGroup.addView(v);
	}


}
