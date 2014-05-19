package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QItemTagData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.QuesDBCtrl;
import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment.OnBtnStratClickListener;
import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessQuestionnaireListFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
import com.yrkj.elderlycareassess.layout.ActivityMainAssess;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class MainAssessActivity extends 
//ActionBarActivity 
FragmentActivity 
implements 
OnClickListener, 
OnBtnStratClickListener
{

	public static final String INTENT_KEY_CUSTID = "custId";

	public static final String INTENT_KEY_ASSESSID = "assessId";
	
	MainAssessActivity mActy;

	private String[] mTitleList = null;
	
	private ActivityMainAssess mLayout;
	
	private String mCustId = null;
	private String mAssessId = null;
	private CustomerData mCust = null;
	private AssessTaskHeaderData mTask = null;
	private ArrayList<AssessBaseFragment> mAssessFragmentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_assess);
		mActy = this;
		mLayout = new ActivityMainAssess(this);
		if(getIntent()!=null){
			mCustId = getIntent().getStringExtra(INTENT_KEY_CUSTID);
			mAssessId = getIntent().getStringExtra(INTENT_KEY_ASSESSID);
		}
		
		initData();
		initActy();
		
		AssessNewFragment f = new AssessNewFragment(this,mCust,mTask);
		f.setOnBtnStratClickListener(this);
		
		getSupportFragmentManager().beginTransaction()
		.add(R.id.layoutBodyView,f, AssessNewFragment.class.getName())
		.commit();
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			finish();
			
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
//	ArrayList<AssessTaskDetailData> mTaskDetailList;
//	Map mTaskDetailIndex;
	private void initData(){
		
		if(mCustId != null){
			mCust = AssessDBCtrl.getCustomerByCustId(this, mCustId);
		}
		
		if(mAssessId != null){
			mTask = AssessDBCtrl.getAssessTaskById(this,mAssessId);
			
//			ArrayList<AssessTaskDetailData> mTaskDetailList
//			 = AssessDBCtrl.getAssessTaskDetailByTaskId(this, mAssessId);
			
//			mTaskDetailIndex = new HashMap();
//			mTaskDetailIndex.put(INTENT_KEY_ASSESSID, mAssessId);
//			int i=0;
//			for (AssessTaskDetailData tdItem : mTaskDetailList) {
//					mTaskDetailIndex.put(tdItem.getIndexKey(), i);
//					DLog.LOG(SysMng.TAG_DB,"tdItem = " + 
//					tdItem.TaskHeaderId + "  " +
//					tdItem.CateId + "  " +
//					tdItem.SubcateId + "  " +
//					tdItem.ItemId + "  " +
//					tdItem.ItemName + "  " +
//					tdItem.getIndexKey() + "  " +
////					tdItem.ItemId + "  "
//					""
//							);
//			}
		}
		
		ArrayList<AssessBaseFragment> fList = 
				new ArrayList<AssessBaseFragment>();
		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add("评估信息");//default first page name
		
		fList.add(new AssessPersonalInfoFragment(this,mCust));
		titleList.add(getResources().getString(R.string.assess_title_person));
		
		 
		fList.add(new AssessLivingFragment(this,mCust));
		titleList.add(getResources().getString(R.string.assess_title_living));
		
		ArrayList<QCategoryData> qitemList = 
				getQCategoryList();
		
		for (QCategoryData qCategoryData : qitemList) {
			fList.add(
					AssessQuestionnaireListFragment.getInstance(this,qCategoryData,mCust,
//							mTaskDetailList,
							mAssessId)
					);
			titleList.add(qCategoryData.CateName);
		}
		
		fList.add(new AssessServiceFragment(this,mCust,mAssessId));
		titleList.add(getResources().getString(R.string.assess_title_service));
		
		mTitleList =  titleList.toArray(new String[titleList.size()]);
		mAssessFragmentList = fList;
	}
	
	private ArrayList<QCategoryData> getQCategoryList(){
		ArrayList<QCategoryData> qitemList
			= QuesDBCtrl.getQCategoryList(this);
		
		for (QCategoryData item : qitemList) {
			item.SubcateList = 
					QuesDBCtrl.getQSubcategoryList(this,item.CateId);
			for (QSubcategoryData subitem : item.SubcateList) {
				setQSubcate(subitem);
			}
		}
		return qitemList;
	}
	private QSubcategoryData setQSubcate(QSubcategoryData item){

		item.ItemList =		
		QuesDBCtrl.getQItemListBySubcateId(this, item.SubcateId);
		
		item.ItemTagList = 
				QuesDBCtrl.getQItemTagListBySubcateId(this, item.SubcateId);
//		item.ItemTagList.add(getQItemLab(
//				1,"快速标签1"));
//		item.ItemTagList.add(getQItemLab(
//				2,"快速标签2"));
//		item.ItemTagList.add(getQItemLab(
//				3,"快速标签3"));
//		item.ItemTagList.add(getQItemLab(
//				4,"快速标签4"));
//		item.ItemTagList.add(getQItemLab(
//				5,"快速标签5"));
		
		
		return item;
	}
	
	private QItemTagData getQItemLab(int id,String name){
		QItemTagData item = new QItemTagData();
		item.ItemTagId = id;
		item.ItemTagName = name;
		return item;
	}

	private void initActy() {
		mLayout.getBtnGoView().setOnClickListener(this);
		mLayout.getBtnBackView().setOnClickListener(this);
		mLayout.getBtnFinishView().setOnClickListener(this);
		
		FragmentManager fMng = getSupportFragmentManager();
		
		fMng.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			@Override
			public void onBackStackChanged() {
//				DLog.LOG(SysMng.TAG_FRAGMENT,getSupportFragmentManager().getBackStackEntryCount()+" = addOnBackStackChangedListener");
				setNavBtn(getSupportFragmentManager().getBackStackEntryCount());
			}
		});
		
//		mLayout.getTxtMainAssessTitleView().setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				DLog.LOG(SysMng.TAG_ASSESS, "=============================");
//				for (AssessBaseFragment item : mAssessFragmentList) {
//					
//					ArrayList<AssessTaskDetailData> itemList = item.getSelectData();
//					if(itemList != null){
//						
//						for (AssessTaskDetailData itemData : itemList) {
//							DLog.LOG(SysMng.TAG_ASSESS, 
//									itemData.CateName + " " +
//											itemData.SubcateName + " " +
//											itemData.ItemName + " " +
//											itemData.ItemDesc + " " +
//											""
//									);
//						}
//					}
//				}
//			}
//		});
		
		setAssessTitle(0);
	}
	
	private void setNavBtn(int index){
		
		setAssessTitle(index);
		
		if(index == 0){
			mLayout.getLayoutFootView().setVisibility(View.GONE);
		}else{
			mLayout.getLayoutFootView().setVisibility(View.VISIBLE);
		}
		if(index+1 < mTitleList.length)
			mLayout.getBtnGoView().setText(mTitleList[index+1]);
		else{
			
			mLayout.getBtnGoView().setText("提交");
				
		}
		
		if(index-1 >= 0)
			mLayout.getBtnBackView().setText(mTitleList[index-1]);
		else{
			mLayout.getBtnBackView().setText("");
		}
	}

	public void goBack(){
		FragmentManager fMng = getSupportFragmentManager();
		fMng.popBackStack();
	}
	
	public synchronized void addFragment() {
		
		FragmentManager fMng = getSupportFragmentManager();
		int i = fMng.getBackStackEntryCount();
				
		if(i >= mAssessFragmentList.size()){
			return;
		}
		String tag = (i+1)+"";
//		DLog.LOG(SysMng.TAG_FRAGMENT, "begin "+tag
//				+" size["+mAssessFragmentList.size()+"] haveSize["+fMng.getFragments().size()+"] index["+i+"]=================================");
		
		Fragment lastFragment = fMng.getFragments().get(i);//fMng.findFragmentById(R.id.layoutBodyView);
		AssessBaseFragment mFragment = mAssessFragmentList.get(i);
		{
			
			if(!mFragment.isAdded() /*&& fMng.findFragmentByTag(tag) == null*/){
				FragmentTransaction ft = fMng.beginTransaction();
				ft.add(R.id.layoutBodyView, mFragment, tag);
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.commit();
				fMng.executePendingTransactions();
			}
		}
		
		if(fMng.getFragments().contains(mFragment))
		{
			FragmentTransaction ft = fMng.beginTransaction();
//			ft.setCustomAnimations(R.anim.fragment_slide_left_enter,
//		                R.anim.fragment_slide_left_exit,
//		                R.anim.fragment_slide_right_enter,
//		                R.anim.fragment_slide_right_exit);
			ft.hide(lastFragment);
//			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.show(mFragment);
			ft.addToBackStack(null);
//			ft.commitAllowingStateLoss();
			ft.commit();
			fMng.executePendingTransactions();
			
		}
//		DLog.LOG(SysMng.TAG_FRAGMENT, "end "+tag+" "+ mFragment.isAdded()+ " =================================");
		
		
	}
	
	
	public void setAssessTitle(int i){
		if(i<mTitleList.length){
			String s = mTitleList[i];
			mLayout.getTxtMainAssessTitleView().setText(s);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case ActivityMainAssess.BtnFinishViewId:
			finish();
			break;
		case ActivityMainAssess.BtnBackViewId:
			goBack();
			break;
		case ActivityMainAssess.BtnGoViewId:
			if(mLayout.getBtnGoView().getText().equals("提交")){
				mAssessFragmentList.get(mAssessFragmentList.size()-1).saveData();

				if(mTask != null){
					mTask.AssessState = AssessTaskHeaderData.ASSESS_STATE_DONE;
					mTask.NeedSync = true;
					AssessDBCtrl.updateAssessTaskHeaderById(this, mTask);
					ToastUtil.show(mActy, "提交成功。");
					setResult(RESULT_SUBMIT);
					this.finish();
				}
			}
			addFragment();
			break;

		default:
			break;
		}
	}
	public static final int RESULT_SUBMIT = 101; 

	@Override
	public void onBtnStratClick() {
		addFragment();
	}

}
