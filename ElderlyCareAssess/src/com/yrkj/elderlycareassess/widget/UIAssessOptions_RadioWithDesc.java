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

	public ItemData[] getSelectIds(){
		if(mUIRadioGroup
				.getCheckedRadioButtonId() == -1){
			return new ItemData[0];
		}
		
		ItemData item = 
		(ItemData) mUIRadioGroup.findViewById(mUIRadioGroup
				.getCheckedRadioButtonId() ).getTag();
		return new ItemData[]{item};
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

	public void add(int id, String name, String desc,boolean isChecked) {
		View v = mInflater.inflate(R.layout.view_question_item_1,
				mUIRadioGroup, false);
		ViewQuestionItem1 holderView = new ViewQuestionItem1(v);
		holderView.getTxtQuestionItemView().setText(desc);
		holderView.getRdoQuestionItemView().setText(name);
		holderView.getRdoQuestionItemView().setId(id);
		holderView.getRdoQuestionItemView().setChecked(isChecked);
		
		
		ItemData item = new ItemData();
		item.Id = ""+id;
		item.Name = name;
		item.Desc = desc;
		holderView.getRdoQuestionItemView().setTag(item);
		mUIRadioGroup.addView(v);
	}


}
