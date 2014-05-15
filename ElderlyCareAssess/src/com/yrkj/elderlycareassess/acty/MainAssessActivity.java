package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

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
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QItemData;
import com.yrkj.elderlycareassess.bean.QItemTagData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.QuesDBCtrl;
import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessCognitiveFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessEmotionalAndBehavioralFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessQuestionnaireListFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment.OnBtnStratClickListener;
import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSelfcareFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessSocialLifeFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessVisualFragment;
import com.yrkj.elderlycareassess.layout.ActivityMainAssess;
import com.yrkj.util.log.DLog;

public class MainAssessActivity extends 
//ActionBarActivity 
FragmentActivity 
implements 
OnClickListener, 
OnBtnStratClickListener
{

	public static final String INTENT_KEY_CUSTID = "custId";
	
	MainAssessActivity mActy;

//	private int mCurIndex = 0;
	private String[] mTitleList = null;
	
	private ActivityMainAssess mLayout;
	
	private String mCustId = null;
	private CustomerData mCust = null;
	private ArrayList<AssessBaseFragment> mAssessFragmentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_assess);
		mActy = this;
		mLayout = new ActivityMainAssess(this);
		if(getIntent()!=null){
			mCustId = getIntent().getStringExtra(INTENT_KEY_CUSTID);
		}
		
		initData();
		initActy();
		
		AssessNewFragment f = new AssessNewFragment(this,mCust);
		f.setOnBtnStratClickListener(this);
		
		getSupportFragmentManager().beginTransaction()
		.add(R.id.layoutBodyView,f, AssessNewFragment.class.getName())
		.commit();
	}
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.fragment_main);
//		
//		AssessQuestionnaireListFragment f
//		 = new AssessQuestionnaireListFragment();
//		getSupportFragmentManager().beginTransaction()
//		.add(R.id.container,f, AssessQuestionnaireListFragment.class.getName())
//		.commit();
//	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			finish();
			
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
	
	
	private void initData(){
		
		if(mCustId != null){
			mCust = AssessDBCtrl.getCustomerByCustId(this, mCustId);
			DLog.LOG(SysMng.TAG_DB, "custId=" + mCustId + " mCust=" +mCust);
			
		}
		
		
		ArrayList<AssessBaseFragment> fList = 
				new ArrayList<AssessBaseFragment>();
		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add("评估信息");//default first page name
		
		fList.add(new AssessPersonalInfoFragment(this,mCust));
		titleList.add(getResources().getString(R.string.assess_title_person));
		
//		fList.add(new AssessLivingFragment(this));
//		titleList.add(getResources().getString(R.string.assess_title_living));
		
		ArrayList<QCategoryData> qitemList = 
				getQCategoryList();
//				new ArrayList<QCategoryData>();
//		qitemList.add(getQcategory(1,"评估分类-1"));
//		qitemList.add(getQcategory(2,"评估分类-2"));
//		qitemList.add(getQcategory(3,"评估分类-3"));
//		qitemList.add(getQcategory(4,"评估分类-4"));
//		qitemList.add(getQcategory(5,"评估分类-5"));
		
		for (QCategoryData qCategoryData : qitemList) {
			fList.add(
					AssessQuestionnaireListFragment.getInstance(this,qCategoryData,mCust)
					);
			titleList.add(qCategoryData.CateName);
		}
		
		mTitleList =  titleList.toArray(new String[titleList.size()]);
		mAssessFragmentList = fList;
		
		
//		mFragmentList = new String[] { 
//				AssessPersonalInfoFragment.class.getName(),
//				AssessLivingFragment.class.getName(),
//				AssessSelfcareFragment.class.getName(),
//				AssessCognitiveFragment.class.getName(),
//				AssessEmotionalAndBehavioralFragment.class.getName(),
//				AssessVisualFragment.class.getName(),
//				AssessSocialLifeFragment.class.getName(),
//				AssessServiceFragment.class.getName()
//		};
//		mTitleList = new String[] {
//				"评估信息",
//				getResources().getString(R.string.assess_title_person),
//				getResources().getString(R.string.assess_title_living),
//				getResources().getString(R.string.assess_title_selfcare),
//				getResources().getString(R.string.assess_title_cogn),
//				getResources().getString(R.string.assess_title_emot),
//				getResources().getString(R.string.assess_title_visual),
//				getResources().getString(R.string.assess_title_social),
//				getResources().getString(R.string.assess_title_service)
//		};
		
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
	
	private QCategoryData getQcategory(int id,String name){
		QCategoryData item = new QCategoryData();
		item.CateId = id+"";
		item.CateName = name;
		item.SubcateList.add(getQSubcate(
				1, name+" 评估子类-1"));
		item.SubcateList.add(getQSubcate(
				2, name+" 评估子类-2"));
		item.SubcateList.add(getQSubcate(
				3, name+" 评估子类-3"));
		item.SubcateList.add(getQSubcate(
				4, name+" 评估子类-4"));
		item.SubcateList.add(getQSubcate(
				5, name+" 评估子类-5"));
		item.SubcateList.add(getQSubcate(
				6, name+" 评估子类-6"));
		
		
		
		return item;
	}
	private QSubcategoryData setQSubcate(QSubcategoryData item){

//		if()
//		ArrayList<QItemData> itemList =
		item.ItemList =		
		QuesDBCtrl.getQItemListBySubcateId(this, item.SubcateId);
//		DLog.LOG(SysMng.TAG_DB, " item.ItemList count ["+item.ItemList.size()+"]");
//		String name = item.SubcateName;
//		item.ItemList.add(getQItem(
//				1,"正常","情绪稳定，对客观事物的主观态度体验与实际相符，能被常人理解的，程度等级评判为正常。"));
//		item.ItemList.add(getQItem(
//				2,"轻度",name+"-描述-2"));
//		item.ItemList.add(getQItem(
//				3,"中度",name+"-描述-3"));
//		item.ItemList.add(getQItem(
//				4,"重度",name+"-描述-4"));
		
		
		item.ItemTagList.add(getQItemLab(
				1,"快速标签1"));
		item.ItemTagList.add(getQItemLab(
				2,"快速标签2"));
		item.ItemTagList.add(getQItemLab(
				3,"快速标签3"));
		item.ItemTagList.add(getQItemLab(
				4,"快速标签4"));
		item.ItemTagList.add(getQItemLab(
				5,"快速标签5"));
		
		
		return item;
	}
	private QSubcategoryData getQSubcate(int id,String name){
		QSubcategoryData item = new QSubcategoryData();
		item.SubcateId = id+"";
		item.SubcateName = name;
		item.ItemList.add(getQItem(
				1,"正常","情绪稳定，对客观事物的主观态度体验与实际相符，能被常人理解的，程度等级评判为正常。"));
		item.ItemList.add(getQItem(
				2,"轻度",name+"-描述-2"));
		item.ItemList.add(getQItem(
				3,"中度",name+"-描述-3"));
		item.ItemList.add(getQItem(
				4,"重度",name+"-描述-4"));
		
		
		item.ItemTagList.add(getQItemLab(
				1,"快速标签1"));
		item.ItemTagList.add(getQItemLab(
				2,"快速标签2"));
		item.ItemTagList.add(getQItemLab(
				3,"快速标签3"));
		item.ItemTagList.add(getQItemLab(
				4,"快速标签4"));
		item.ItemTagList.add(getQItemLab(
				5,"快速标签5"));
		
		
		return item;
	}
	private QItemData getQItem(int id,String name,String desc){
		QItemData item = new QItemData();
		item.ItemId = id;
		item.ItemName = name;
		item.ItemDesc = desc;
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
		else
			mLayout.getBtnGoView().setText("提交");
		
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
		String tag = (i+1)+"";//mAssessFragmentList.get(i);
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
			addFragment();
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onBtnStratClick() {
		addFragment();
	}

}
