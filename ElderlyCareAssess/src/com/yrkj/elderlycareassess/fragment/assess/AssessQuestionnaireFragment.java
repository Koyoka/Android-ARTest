package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnaire;

public class AssessQuestionnaireFragment extends AssessBaseFragment {
	
	FragmentAssessQuestionnaire mLayout = null;
	View mV = null;
	
	String mTitle = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_questionnaire, container,
				false);
		
		initFragment();
		return mV;
	}
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_service));
		mLayout = new FragmentAssessQuestionnaire(mV);
		mLayout.getTxtQuestionTitleView().setText(mTitle);
	}
	
	public void setQuestionTitle(String s){
		mTitle = s;
	}
	
}
