package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
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
import com.yrkj.elderlycareassess.fragment.AttachmentFragment;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	private AssessQuestionnaireFragment[] mFList;
	private Map mTaskDetailIndex;
	private String mTaskHeaderId = "";
	public AssessQuestionnaireListFragment(MainAssessActivity a,QCategoryData d,CustomerData c,
			String AssessId) {
		super(a,d,c);
//		mTaskDetailIndex = tdIndex;
		mTaskHeaderId = AssessId;//(String) tdIndex.get(MainAssessActivity.INTENT_KEY_ASSESSID);
	}

	public static AssessQuestionnaireListFragment getInstance(
			MainAssessActivity a,QCategoryData d,CustomerData c,
			String AssessId){
		return new AssessQuestionnaireListFragment(a,d,c,
				AssessId);
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
				initData();
				initFragment();
//				initAttachment();
		 }
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		mAf.onActivityResult(requestCode, resultCode, data);
//		List<Fragment> fragments = getChildFragmentManager().getFragments();
//        if (fragments != null) {
//            for (Fragment fragment : fragments) {
//                fragment.onActivityResult(requestCode, resultCode, data);
//            }
//        }
		
	}
	
	private void initData(){
		if(mTaskHeaderId != null){
			ArrayList<AssessTaskDetailData> mTaskDetailList
			 = AssessDBCtrl.getAssessTaskDetailByTaskIdCateId(getActivity(), 
					 mTaskHeaderId,mData.CateId);
			mTaskDetailIndex = new HashMap();
			int i=0;
			for (AssessTaskDetailData tdItem : mTaskDetailList) {
					mTaskDetailIndex.put(tdItem.getIndexKey(), i);
					DLog.LOG(SysMng.TAG_DB,"tdItem = " + 
							tdItem.TaskHeaderId + "  " +
							tdItem.CateId + "  " +
							tdItem.SubcateId + "  " +
							tdItem.ItemId + "  " +
							tdItem.ItemName + "  " +
							tdItem.getIndexKey() + "  " +
//							tdItem.ItemId + "  "
							""
									);
			}
		}
	}
	
	AttachmentFragment mAf;
	private void initFragment(){
		if(mData == null){
			return;
		}
		
		FragmentManager fMng = getChildFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		int hId = Integer.parseInt(mTaskHeaderId, 10);
		int cId = Integer.parseInt(mData.CateId, 10);
		
		mAf = new AttachmentFragment(hId,cId);
		ft.add(FragmentAssessQuestionnairellist.LayoutBodyId,mAf, "attachment");
		
		
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
	
//	private void initAttachment(){
//		{
//			AttachmentFragment f = (AttachmentFragment)
//				getChildFragmentManager().findFragmentById(R.id.fragAttachmentView);
//		
//			DLog.LOG(SysMng.TAG_FRAGMENT, "1--------------"+f);
//		}
//		
//		{
//			AttachmentFragment f = (AttachmentFragment)
//					getFragmentManager().findFragmentById(R.id.fragAttachmentView);
//			DLog.LOG(SysMng.TAG_FRAGMENT, "2--------------"+f);
//		}
////		int hId = Integer.parseInt(mTaskHeaderId, 10);
////		int cId = Integer.parseInt(mData.CateId, 10);
////		f.setData(hId, cId);
//		
//		
//	}
	
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
