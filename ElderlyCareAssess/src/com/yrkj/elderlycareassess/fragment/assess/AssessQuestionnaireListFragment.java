package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_questionnairellist, container,
				false);
		
		initFragment();
		return v;
	}
	
	private void initFragment(){
//		setTitle("test");
		
		FragmentManager fMng = getChildFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		AssessQuestionnaireFragment f1 = new AssessQuestionnaireFragment();
		AssessQuestionnaireFragment f2 = new AssessQuestionnaireFragment();
		AssessQuestionnaireFragment f3 = new AssessQuestionnaireFragment();
		f1.setQuestionTitle("1 - question");
		f2.setQuestionTitle("2 - question");
		f3.setQuestionTitle("3 - question");
		ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f1,"1");
		ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f2,"2");
		ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f3,"3");
		ft.commit();
		
	}
	
}
