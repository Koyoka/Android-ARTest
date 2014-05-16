package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;
import com.yrkj.util.log.DLog;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	private AssessQuestionnaireFragment[] mFList;
//	private ArrayList<AssessTaskDetailData> mTaskDetailList;
	private Map mTaskDetailIndex;
	private String mTaskHeaderId = "";
	public AssessQuestionnaireListFragment(MainAssessActivity a,QCategoryData d,CustomerData c,
//			ArrayList<AssessTaskDetailData> dList,
			Map tdIndex) {
		super(a,d,c);
//		mTaskDetailList = dList;
		mTaskDetailIndex = tdIndex;
		mTaskHeaderId = (String) tdIndex.get(MainAssessActivity.INTENT_KEY_ASSESSID);
	}

	public static AssessQuestionnaireListFragment getInstance(
			MainAssessActivity a,QCategoryData d,CustomerData c,
//			ArrayList<AssessTaskDetailData> dList,
			Map tdIndex){
		return new AssessQuestionnaireListFragment(a,d,c,
//				dList,
				tdIndex);
	}
	
	View mV;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			mV = inflater.inflate(R.layout.fragment_assess_questionnairellist, container,
					false);
		return mV;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
			
			AssessQuestionnaireFragment f = new AssessQuestionnaireFragment(item,
					mData.
					CateId,
//					mTaskDetailList,
					mTaskDetailIndex
					);
			
			ft.add(FragmentAssessQuestionnairellist.LayoutBodyId, f,item.SubcateId+"");
			mFList[i] = f;
			i++;
		}
		
		ft.commit();
	}
	
	private void loadData(){
		
	}
	
	
	public ArrayList<AssessTaskDetailData> getSelectData(){
		ArrayList<AssessTaskDetailData> dlist = new ArrayList<AssessTaskDetailData>();
		if(mFList != null){
			
			for (AssessQuestionnaireFragment f : mFList) {
				
				ArrayList<AssessTaskDetailData> itemList = //new AssessTaskDetailData();
				f.getSelectData();
				for (AssessTaskDetailData item : itemList) {
					item.TaskHeaderId = mTaskHeaderId;
					item.CateId = mData.CateId;
					item.CateName = mData.CateName;
					dlist.add(item);
				}
			}
//			return dlist;
		}
		return dlist;
	}
	
	public void saveData() {
		if(mCust == null)
			return;
		// TODO Auto-generated method stub
//		setUpdateCustData();
		AssessDBCtrl.deleteAssessTaskDetail(getActivity(),
				mTaskHeaderId,
				mData.CateId
				);
		
		ArrayList<AssessTaskDetailData> itemList = getSelectData();
		for (AssessTaskDetailData item : itemList) {
			AssessDBCtrl.insertAssessTaskDetail(getActivity(), item);
		}
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if(hidden){
			saveData();
		}
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
