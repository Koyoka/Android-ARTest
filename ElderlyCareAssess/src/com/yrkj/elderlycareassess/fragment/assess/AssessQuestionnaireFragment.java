package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.QItemData;
import com.yrkj.elderlycareassess.bean.QItemTagData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnaire;
import com.yrkj.elderlycareassess.widget.UIAssessOptions_Base;
import com.yrkj.elderlycareassess.widget.UIAssessOptions_Base.ItemData;
import com.yrkj.elderlycareassess.widget.UIAssessOptions_CheckNoneDesc;
import com.yrkj.elderlycareassess.widget.UIAssessOptions_RadioNoneDesc;
import com.yrkj.elderlycareassess.widget.UIAssessOptions_RadioWithDesc;
import com.yrkj.util.log.DLog;

public class AssessQuestionnaireFragment extends AssessBaseFragment 
implements OnClickListener{
	
	private FragmentAssessQuestionnaire mLayout = null;
	private View mV = null;
	
	private String mTitle = "";
	private QSubcategoryData mSubcateData = null;
	
	public UIAssessOptions_Base vBase;
	
	private String mCateId = "";
	private Map mTaskDetailIndex;
	public AssessQuestionnaireFragment(QSubcategoryData d,String cateId,
			Map tdIndex,boolean e){
		mCateId = cateId;
		mSubcateData = d;
		mTaskDetailIndex = tdIndex;
		mNeedSync = e;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_questionnaire, container,
				false);
		setFrontBody(mV);
		initFragment();
		return mV;
	}
	
	private void initFragment(){
		mLayout = new FragmentAssessQuestionnaire(mV);
		
		mLayout.getTxtQuestionTitleView().setText(mSubcateData.SubcateName);
		mLayout.getBtnQuestionShorthandView().setOnClickListener(this);
		
		addItemList();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case FragmentAssessQuestionnaire.BtnQuestionShorthandViewId:
			mLayout.getLayoutSpecialItemView().setVisibility(
					View.VISIBLE == mLayout.getLayoutSpecialItemView().getVisibility() ? 
							View.GONE : View.VISIBLE);
			break;

		default:
			break;
		}
		
	}
	
	public ArrayList<AssessTaskDetailData> getSelectData(){
		if(mSubcateData == null){
			return null;
		}
		ArrayList<AssessTaskDetailData> itemList = new ArrayList<AssessTaskDetailData>();
		if(vBase == null){
			return itemList;
		}
		
		ItemData[] itemDataList = vBase.getSelectIds();
		if(itemDataList != null){
			for (ItemData selectItem : itemDataList) {
				AssessTaskDetailData item = new AssessTaskDetailData();
				item.SubcateId = mSubcateData.SubcateId;
				item.SubcateName = mSubcateData.SubcateName;
				
				item.ItemId = selectItem.Id;
//				item.ItemTagId = selectItem.Id;
				item.ItemName = selectItem.Name;
				item.ItemDesc = selectItem.Desc;
				item.TaskType = AssessTaskDetailData.TASK_TYPE_ITEM;
				
				item.Score = item.getScore();
				itemList.add(item);
			}
			
		}
		
		
		ArrayList<ItemTagData> itemTagDataList = getTagViewSelectIds();
		
		
		for(ItemTagData selectTagItem : itemTagDataList){
			AssessTaskDetailData item = new AssessTaskDetailData();
			item.SubcateId = mSubcateData.SubcateId;
			item.SubcateName = mSubcateData.SubcateName;
			item.ItemTagId = selectTagItem.Id;
//			item.ItemId = selectTagItem.Id;
//			item.ItemName = selectTagItem.Name;
			item.ItemTagName = selectTagItem.Name;
			item.ItemDesc = selectTagItem.Desc;
			item.TaskType = AssessTaskDetailData.TASK_TYPE_ITEMTAG;
//			DLog.LOG(SysMng.TAG_DB, 
//					item.ItemId + " " +
//							item.ItemName + " " +
//							item.ItemDesc + " " +
//							item.TaskType + " " +
//					
//					"");
			itemList.add(item);
		}
		
		return itemList;
		
		
	}
	
	private ArrayList<ItemTagData> getTagViewSelectIds(){
		ArrayList<ItemTagData> dList 
			= new ArrayList<ItemTagData>();
		
		if(mTagViewList != null){
			for (CheckBox v : mTagViewList) {
				if(v.isChecked()){
					ItemTagData d =
							(ItemTagData)v.getTag();
					dList.add(d);
				}
			}
		}
		
		return dList;
		
	}
	
	private ArrayList<CheckBox> mTagViewList;
	private void addItemList(){
		
		
		ViewGroup container = mLayout.getLayoutNormalContentView();
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		
		vBase=null;
		
		if(mSubcateData.UseType.equals(QSubcategoryData.USER_TYPE_ONLY_CHECK_NONE_DESC)
			|| mSubcateData.UseType.equals(QSubcategoryData.USER_TYPE_ONLY_CHECK)
				){
			vBase = new UIAssessOptions_CheckNoneDesc(inflater, container);
		}else if(
				mSubcateData.UseType.equals(
						QSubcategoryData.USER_TYPE_ONLY_RADIO_NONE_DESC)){
			vBase = new UIAssessOptions_RadioNoneDesc(inflater, container);	
		}else if(
			mSubcateData.UseType.equals(
					QSubcategoryData.USER_TYPE_ONLY_RADIO)
				){
			vBase = new UIAssessOptions_RadioWithDesc(inflater, container);	
		}
		if(vBase != null){
			for(int i = 0; i< mSubcateData.ItemList.size();i++){
				
				
				QItemData item = mSubcateData.ItemList.get(i);
				String key = AssessTaskDetailData.getIndexKey(
						mCateId, 
						mSubcateData.SubcateId, 
						item.ItemId+"",
						AssessTaskDetailData.TASK_TYPE_ITEM);
				
				boolean isSelected = false;
				if(mTaskDetailIndex.get(key)!=null){
					isSelected = true;
				}
				vBase.add(item.ItemId, item.ItemName,item.ItemDesc,isSelected);
			}
		}
		
		
		mTagViewList = new ArrayList<CheckBox>();
		if(mSubcateData.ItemTagList.size() == 0){
			mLayout.getBtnQuestionShorthandView().setVisibility(View.GONE);
		}else{
			mLayout.getBtnQuestionShorthandView().setVisibility(View.VISIBLE);
		
			for(int i = 0; i < mSubcateData.ItemTagList.size(); i++){
				QItemTagData item = mSubcateData.ItemTagList.get(i);
				CheckBox v = null;
				v = (CheckBox) inflater.inflate(R.layout.view_question_special_item, container,
						false);
				v.setText(item.ItemTagName);
				
				String key = AssessTaskDetailData.getIndexKey(
						mCateId, 
						mSubcateData.SubcateId, 
						item.ItemTagId+"",
						AssessTaskDetailData.TASK_TYPE_ITEMTAG);
	//			boolean isSelected = false;
				if(mTaskDetailIndex.get(key)!=null){
					DLog.LOG(SysMng.TAG_DB, "True-==="+key);
					mLayout.getLayoutSpecialItemView().setVisibility(View.VISIBLE);
					v.setChecked(true);
				}
				
				ItemTagData d = new ItemTagData();
				d.Id = ""+item.ItemTagId;
				d.Name = item.ItemTagName;
				d.Desc = item.ItemTagDesc;
				v.setTag(d);
				mTagViewList.add(v);
				switch (i%4) {
				case 0:
					v.setBackgroundResource(R.drawable.btn_special_item_1_bg_x);
					mLayout.getLayoutSpecialItem1View().addView(v);
					break;
				case 1:
	//				v.setBackgroundResource(R.drawable.btn_special_item_2_bg_x);
					v.setBackgroundResource(R.drawable.btn_special_item_1_bg_x);
					mLayout.getLayoutSpecialItem2View().addView(v);
					break;
				case 2:
	//				v.setBackgroundResource(R.drawable.btn_special_item_3_bg_x);
					v.setBackgroundResource(R.drawable.btn_special_item_1_bg_x);
					mLayout.getLayoutSpecialItem3View().addView(v);
					break;
				case 3:
	//				v.setBackgroundResource(R.drawable.btn_special_item_4_bg_x);
					v.setBackgroundResource(R.drawable.btn_special_item_1_bg_x);
					mLayout.getLayoutSpecialItem4View().addView(v);
					break;
	
				default:
					break;
				}
			}
		}
	}

	class ItemTagData{
		public String Id = "";
		public String Name = "";
		public String Desc = "";
	}
	
	public void saveAssessDetailData() {
		// TODO Auto-generated method stub
		
	}
	
	
//	<CheckBox
//    android:id="@+id/lChk"
//    style="@style/fragment_assess_question_btn_special_item_1"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/fragment_assess_questionnaire_special_item_margin"
//    android:button="@null"
//    android:text="xxxxx" />
	

	
}














