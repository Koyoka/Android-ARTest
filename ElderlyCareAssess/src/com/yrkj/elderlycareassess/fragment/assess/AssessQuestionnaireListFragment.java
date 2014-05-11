package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.dao.QCategoryData;
import com.yrkj.elderlycareassess.dao.QSubcategoryData;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;
import com.yrkj.util.log.DLog;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	public AssessQuestionnaireListFragment(MainAssessActivity a,QCategoryData d) {
		super(a,d);
		// TODO Auto-generated constructor stub
	}

	public static AssessQuestionnaireListFragment getInstance(MainAssessActivity a,QCategoryData d){
		return new AssessQuestionnaireListFragment(a,d);
	}
	
	View mV;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		if(mV == null){
			mV = inflater.inflate(R.layout.fragment_assess_questionnairellist, container,
					false);
//		}
		
//		initFragment();
		return mV;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			 initFragment();
		 }
		
	}
	
	private void initFragment(){
		if(mData == null){
			return;
		}
		
		FragmentManager fMng = getChildFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		for (QSubcategoryData item : mData.SubcateList) {
			AssessQuestionnaireFragment f = new AssessQuestionnaireFragment(item);
//			f.setQuestionTitle(item.SubcateName);
			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f,item.SubcateId+"");
			
		}
		
//		AssessQuestionnaireFragment f1 = new AssessQuestionnaireFragment();
//		AssessQuestionnaireFragment f2 = new AssessQuestionnaireFragment();
//		AssessQuestionnaireFragment f3 = new AssessQuestionnaireFragment();
//		f1.setQuestionTitle("1 - question");
//		f2.setQuestionTitle("2 - question");
//		f3.setQuestionTitle("3 - question");
////		if(fMng.findFragmentByTag("1") == null)
//			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f1,"1");
////		if(fMng.findFragmentByTag("2") == null)
//			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f2,"2");
////		if(fMng.findFragmentByTag("3") == null)
//			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f3,"3");
		ft.commit();
		
	}
	@Override
	public void onDetach() {
	    super.onDetach();

	    try {
	    	java.lang.reflect.Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
	        childFragmentManager.setAccessible(true);
	        childFragmentManager.set(this, null);

	    } catch (NoSuchFieldException e) {
	        throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
