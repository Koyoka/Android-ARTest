package com.yrkj.elderlycareassess.widget;

import java.util.ArrayList;

import com.yrkj.elderlycareassess.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

public abstract class UIAssessOptions_Base {
//	private LayoutInflater mInflater;
//
//	private ViewGroup mVGroup;
//	
//	public UIAssessOptions_Base(LayoutInflater inflater,
//			ViewGroup container) {
//		mInflater = inflater;
//		View v = mInflater.inflate(R.layout.view_assess_options_checknonedesc,
//				container, false);
//		mVGroup = (ViewGroup) v.findViewById(R.id.layoutNormalContentView);
//		container.addView(mVGroup);
//	}
	
//	private CheckBox mR1 = null;
//	private CheckBox mR2 = null;
	
	public abstract void add(int id, String name,String desc,boolean isChecked);
		
//	private ArrayList<CheckBox> mRvList = new ArrayList<CheckBox>();
	public abstract ItemData[] getSelectIds();
	
	public class ItemData{
		public String Id = "";
		public String Name = "";
		public String Desc = "";
		
	}
}
