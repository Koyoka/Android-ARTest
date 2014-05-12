package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.QItemData;
import com.yrkj.elderlycareassess.bean.QItemTagData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnaire;
import com.yrkj.elderlycareassess.layout.ViewQuestionItem1;
import com.yrkj.elderlycareassess.layout.ViewQuestionItem2;
import com.yrkj.elderlycareassess.widget.UIRadioGroup;
import com.yrkj.elderlycareassess.widget.UIRadioGroup.OnCheckedChangeListener;
import com.yrkj.util.log.ToastUtil;

public class AssessQuestionnaireFragment extends AssessBaseFragment 
implements OnClickListener{
	
	FragmentAssessQuestionnaire mLayout = null;
	View mV = null;
	
	String mTitle = "";
	QSubcategoryData mSubcateData = null;
	
	public AssessQuestionnaireFragment(QSubcategoryData d){
		mSubcateData = d;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_questionnaire, container,
				false);
		
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
	
	private View getOnlyRadioView(LayoutInflater inflater,ViewGroup container,QItemData item){
		View v = inflater.inflate(R.layout.view_question_item_1, container,
				false);
		ViewQuestionItem1 holderView = new ViewQuestionItem1(v);
		holderView.getTxtQuestionItemView().setText(item.ItemDesc);
		holderView.getRdoQuestionItemView().setText(item.ItemName);
		holderView.getRdoQuestionItemView().setId(item.ItemId);
		return v;
	}
	private View getOnlyCheckView(LayoutInflater inflater,ViewGroup container,QItemData item1,QItemData item2){
		View v = inflater.inflate(R.layout.view_question_item_2, container,
				false);
		ViewQuestionItem2 holderView = new ViewQuestionItem2(v);
		holderView.getRdoQuestionItem1View().setText(item1.ItemName);
		holderView.getRdoQuestionItem1View().setId(item1.ItemId);
		
		if(item2 != null){
			holderView.getRdoQuestionItem2View().setVisibility(View.VISIBLE);
			holderView.getRdoQuestionItem2View().setText(item2.ItemName);
			holderView.getRdoQuestionItem2View().setId(item2.ItemId);
		}else{
			holderView.getRdoQuestionItem2View().setVisibility(View.INVISIBLE);
			
		}
//		holderView.getTxtQuestionItemView().setText(item.ItemDesc);
//		holderView.getRdoQuestionItemView().setText(item.ItemName);
//		holderView.getRdoQuestionItemView().setId(item.ItemId);
		return v;
	}
	
	private void addItemList(){
		
		mLayout.getLayoutNormalContentView().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(UIRadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				ToastUtil.show(getActivity(), mLayout.getLayoutNormalContentView()
						.getCheckedRadioButtonId() + " checked");
			}
		});
		
		ViewGroup container = mLayout.getLayoutNormalContentView();
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		
//		for (QItemData item : mSubcateData.ItemList) {
////			View v = inflater.inflate(R.layout.view_question_item_1, container,
////					false);
////			ViewQuestionItem1 holderView = new ViewQuestionItem1(v);
////			holderView.getTxtQuestionItemView().setText(item.ItemDesc);
////			holderView.getRdoQuestionItemView().setText(item.ItemName);
////			holderView.getRdoQuestionItemView().setId(item.ItemId);
//			View v = null;
//			v = getOnlyRadioView(inflater,container,item);
//			mLayout.getLayoutNormalContentView().addView(v);
//		}
		
		for(int i = 0; i< mSubcateData.ItemList.size();i++){
			
			View v = null;
			if(mSubcateData.UseType.equals(QSubcategoryData.USER_TYPE_ONLY_RADIO)){
				QItemData item = mSubcateData.ItemList.get(i);
				v = getOnlyRadioView(inflater,container,item);
			}else if(mSubcateData.UseType.equals(QSubcategoryData.USER_TYPE_ONLY_CHECK)){
				QItemData item1 = null;
				item1 = mSubcateData.ItemList.get(i);
				
				i++;
				QItemData item2 = null;
				if(i<mSubcateData.ItemList.size()){
					item2 = mSubcateData.ItemList.get(i);
				}
				v = getOnlyCheckView(inflater,container,item1,item2);
			}
			if(v!=null)
				mLayout.getLayoutNormalContentView().addView(v);
		}
		
		
		for (QItemTagData item : mSubcateData.ItemLabList) {
			
			
		}
		
		for(int i = 0; i < mSubcateData.ItemLabList.size(); i++){
			QItemTagData item = mSubcateData.ItemLabList.get(i);
			CheckBox v = null;
			v = (CheckBox) inflater.inflate(R.layout.view_question_special_item, container,
					false);
			v.setText(item.ItemLabName);
			switch (i%4) {
			case 0:
				v.setBackgroundResource(R.drawable.btn_special_item_1_bg_x);
				mLayout.getLayoutSpecialItem1View().addView(v);
				break;
			case 1:
				v.setBackgroundResource(R.drawable.btn_special_item_2_bg_x);
				mLayout.getLayoutSpecialItem2View().addView(v);
				break;
			case 2:
				v.setBackgroundResource(R.drawable.btn_special_item_3_bg_x);
				mLayout.getLayoutSpecialItem3View().addView(v);
				break;
			case 3:
				v.setBackgroundResource(R.drawable.btn_special_item_4_bg_x);
				mLayout.getLayoutSpecialItem4View().addView(v);
				break;

			default:
				break;
			}
			
			
			
			
			
		}
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














