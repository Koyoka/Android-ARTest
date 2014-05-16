package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;
import com.yrkj.util.log.DLog;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	public AssessQuestionnaireListFragment(MainAssessActivity a,QCategoryData d,CustomerData c) {
		super(a,d,c);
		// TODO Auto-generated constructor stub
	}

	public static AssessQuestionnaireListFragment getInstance(MainAssessActivity a,QCategoryData d,CustomerData c){
		return new AssessQuestionnaireListFragment(a,d,c);
	}
	
	View mV;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			mV = inflater.inflate(R.layout.fragment_assess_questionnairellist, container,
					false);
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
		mFList = new AssessQuestionnaireFragment[mData.SubcateList.size()];
		
		int i =0;
		for (QSubcategoryData item : mData.SubcateList) {
			AssessQuestionnaireFragment f = new AssessQuestionnaireFragment(item);
			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f,item.SubcateId+"");
			mFList[i] = f;
			i++;
			
		}
		
		ft.commit();
		
	}
	
	private AssessQuestionnaireFragment[] mFList;
	
	public ArrayList<AssessTaskDetailData> getSelectData(){
//		if(mData.CateName)
		if(mFList != null){
			ArrayList<AssessTaskDetailData> dlist = new ArrayList<AssessTaskDetailData>();
			for (AssessQuestionnaireFragment f : mFList) {
				
				ArrayList<AssessTaskDetailData> itemList = //new AssessTaskDetailData();
				f.getSelectData();
				for (AssessTaskDetailData item : itemList) {
					item.CateId = mData.CateId;
					item.CateName = mData.CateName;
					dlist.add(item);
				}
				
			}
			return dlist;
		}
		return null;
//		dlist.addAll(dlist);
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
