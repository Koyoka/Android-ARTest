package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.fragment.AttachmentFragment;
import com.yrkj.elderlycareassess.fragment.AttachmentFragment.OnRecorLisenter;
import com.yrkj.elderlycareassess.layout.FragmentAssessQuestionnairellist;
import com.yrkj.elderlycareassess.util.RecordHelper;
import com.yrkj.elderlycareassess.util.RecordHelper.OnHFinishedRecordListener;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.DLog;

public class AssessQuestionnaireListFragment extends AssessBaseFragment {
	
	private AssessQuestionnaireFragment[] mFList;
	private Map mTaskDetailIndex;
	private String mTaskHeaderId = "";
	private AttachmentFragment mAf;
	
	
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
	FragmentAssessQuestionnairellist mLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mV = inflater.inflate(R.layout.fragment_assess_questionnairellist, container,
				false);
		mLayout
		= new FragmentAssessQuestionnairellist(mV);
		
		ScrollView scroll = mLayout.getContainer();
	    scroll.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
	    scroll.setFocusable(true);
	    scroll.setFocusableInTouchMode(true);
	    scroll.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            v.requestFocusFromTouch();
	            return false;
	        }
	    });
		
		if (savedInstanceState == null) {
				initData();
				initFragment();
//				initAttachment();
		 }
		
			
		return mV;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	
	private RecordHelper mRecordHelper;
	private void initFragment(){
		if(mData == null){
			return;
		}
		
		
		mRecordHelper = new RecordHelper(getActivity(),mLayout.getTxtRecordDescView());
		mRecordHelper.setOnFinishedRecordListener(new OnHFinishedRecordListener() {
			
			@Override
			public void onFinishedRecord(String audioPath) {
				// TODO Auto-generated method stub
//				ToastUtil.show(getActivity(), audioPath);
				mAf.saveSound(audioPath);
				mLayout.getBtnStopRecordView().setVisibility(View.GONE);
			}
		});
		
		
		mLayout.getBtnStopRecordView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mRecordHelper.finishRecord();
			}
		});
		
		FragmentManager fMng = getChildFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		int hId = Integer.parseInt(mTaskHeaderId, 10);
		int cId = Integer.parseInt(mData.CateId, 10);
		
		mAf = new AttachmentFragment(hId,cId);
		mAf.setOnRecorLisenter(new OnRecorLisenter() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				mRecordHelper.startRecording(mAf.getSaveSoundFileName());
				mLayout.getBtnStopRecordView().setVisibility(View.VISIBLE);
			}
		});
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
	
//	private boolean mCheckData = true;
	
	@Override
	public boolean checkData(final int page) {
		// TODO Auto-generated method stub
		if(!mRecordHelper.getHasBeenStart())
			return true;
		else{
			if(mOnCheckDataLisenter != null){
				DialogHelper.createConfirmDialog(getActivity(), "Äú½«ÇÐ»»Ò³Ãæ£¬ÊÇ·ñ±£´æÂ¼Òô£¿",new ConfirmDialogListener() {
					
					@Override
					public void onClickListener(boolean result) {
						if(result){
							mRecordHelper.finishRecord();
						}else{
							mLayout.getBtnStopRecordView().setVisibility(View.GONE);
							mRecordHelper.cancelRecord();
						}
//						ToastUtil.show(getActivity(), result+"");
						mOnCheckDataLisenter.onCheck(page,true);
					}
				});
				
			}
			return false;
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
