package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupMenu;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.QuesDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessBaseFragment.OnCheckDataLisenter;
import com.yrkj.elderlycareassess.fragment.assess.AssessLivingFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessNewFragment.OnBtnStratClickListener;
import com.yrkj.elderlycareassess.fragment.assess.AssessPersonalInfoFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessQuestionnaireListFragment;
import com.yrkj.elderlycareassess.fragment.assess.AssessServiceFragment;
import com.yrkj.elderlycareassess.layout.ActivityMainAssess;
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
	
	private int mCurPage = -1;
	
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
		showFragment(0);
//		AssessNewFragment mNewFragment = new AssessNewFragment(this,mCust,mTask);
//		mNewFragment.setOnBtnStratClickListener(this);
//		
//		getSupportFragmentManager().beginTransaction()
//		.add(R.id.layoutBodyView,mNewFragment, AssessNewFragment.class.getName())
//		.commit();
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		List<Fragment> frags = getSupportFragmentManager().getFragments();
	    if (frags != null) {
	        for (Fragment f : frags) {
	            if (f != null){
	            	if(!f.isHidden()){
	            		f.onActivityResult(arg0, arg1, arg2);
	            	}
	            }
	        }
	    }
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

	private void initData(){
		
		if(mCustId != null){
			mCust = AssessDBCtrl.getCustomerByCustId(this, mCustId);
		}
		
		if(mAssessId != null){
			mTask = AssessDBCtrl.getAssessTaskById(this,mAssessId);
		}
		
		
		ArrayList<AssessBaseFragment> fList = 
				new ArrayList<AssessBaseFragment>();
		ArrayList<String> titleList = new ArrayList<String>();
		
		{
			AssessNewFragment mNewFragment = new AssessNewFragment(this,mCust,mTask);
			mNewFragment.setOnBtnStratClickListener(this);
			
//			getSupportFragmentManager().beginTransaction()
//			.add(R.id.layoutBodyView,mNewFragment, AssessNewFragment.class.getName())
//			.commit();
			fList.add(mNewFragment);
			titleList.add("评估信息");//default first page name
		}
		
		{
			fList.add(new AssessPersonalInfoFragment(this,mCust,mTask.NeedSync));
			titleList.add(getResources().getString(R.string.assess_title_person));
		}
		 
		{
			fList.add(new AssessLivingFragment(this,mCust,mTask.NeedSync));
			titleList.add(getResources().getString(R.string.assess_title_living));
		}
		
		{
			ArrayList<QCategoryData> qitemList = 
					getQCategoryList();
			
			for (QCategoryData qCategoryData : qitemList) {
				AssessQuestionnaireListFragment f
					=AssessQuestionnaireListFragment.getInstance(this,qCategoryData,mCust,
						mAssessId,mTask.NeedSync);
				fList.add(f);
				f.setOnCheckDataLisenter(mRecordCheckLisenter);
				titleList.add(qCategoryData.CateName);
			}
		}
		
		fList.add(new AssessServiceFragment(this,mCust,mAssessId,mTask.NeedSync));
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
		return item;
	}
	
	private void initActy() {
		mLayout.getBtnGoView().setOnClickListener(this);
		mLayout.getBtnBackView().setOnClickListener(this);
		mLayout.getBtnFinishView().setOnClickListener(this);
		mLayout.getBtnMenuView().setOnClickListener(this);
		
		
		
			
		
//		FragmentManager fMng = getSupportFragmentManager();
//		
//		fMng.addOnBackStackChangedListener(new OnBackStackChangedListener() {
//			
//			@Override
//			public void onBackStackChanged() {
////				DLog.LOG(SysMng.TAG_FRAGMENT,getSupportFragmentManager().getBackStackEntryCount()+" = addOnBackStackChangedListener");
//				setNavBtn(getSupportFragmentManager().getBackStackEntryCount());
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
			mLayout.getBtnGoView().setText("("+(index+2)+"/"+mTitleList.length+")"+mTitleList[index+1]);
		else{
			if(mTask.AssessState.equals(AssessTaskHeaderData.ASSESS_STATE_DONE)){
				mLayout.getBtnGoView().setText("返回");
			}else{
				mLayout.getBtnGoView().setText("提交");
			}
			
		}
		
		if(index-1 >= 0)
			mLayout.getBtnBackView().setText("("+(index)+"/"+mTitleList.length+")"+mTitleList[index-1]);
		else{
			mLayout.getBtnBackView().setText("");
		}
	}

//	public void goBack(){
//		FragmentManager fMng = getSupportFragmentManager();
//		fMng.popBackStack();
//	}
	
	
	
	public synchronized void goFragment(boolean isGo){
		
		int nextPage;
		if(isGo){
			nextPage = mCurPage+1;
			if(nextPage>=mAssessFragmentList.size()){
				return;
			}
		}else{
			nextPage = mCurPage-1;
			if(nextPage<0){
				return;
			}
		}
		showFragment(nextPage);
	}
	
	private OnCheckDataLisenter mRecordCheckLisenter = new OnCheckDataLisenter() {
		
		@Override
		public void onCheck(int page,boolean check) {
			if(check){
				showFragment(page);
			}
		}
	};
	private synchronized void showFragment(int index){
		if(index==mCurPage){
			return;
		}
		
		
		FragmentManager fMng = getSupportFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		
		String tag = index+"";
		
		Fragment nextFragment = fMng.findFragmentByTag(tag);
		AssessBaseFragment curFragment = mCurPage!=-1&&mCurPage<mAssessFragmentList.size()?mAssessFragmentList.get(mCurPage):null;
		
		if(curFragment!=null){
			if(!curFragment.checkData(index)){
				return;
			}
			
			ft.hide(curFragment);
		}
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		if(nextFragment==null){
			AssessBaseFragment mFragment = mAssessFragmentList.get(index);
			if(!mFragment.isAdded() ){
				ft.add(R.id.layoutBodyView, mFragment, tag);
				ft.commit();
				fMng.executePendingTransactions();
			}
		}else{
			
			ft.show(nextFragment);
//			ft.commitAllowingStateLoss();
			ft.commit();
		}
		mCurPage = index;
		setNavBtn(mCurPage);
	}
	
	
//	public synchronized void addFragment() {
//		
//		FragmentManager fMng = getSupportFragmentManager();
//		int i = fMng.getBackStackEntryCount();
//				
//		if(i >= mAssessFragmentList.size()){
//			return;
//		}
//		String tag = (i+1)+"";
////		DLog.LOG(SysMng.TAG_FRAGMENT, "begin "+tag
////				+" size["+mAssessFragmentList.size()+"] haveSize["+fMng.getFragments().size()+"] index["+i+"]=================================");
//		
//		Fragment lastFragment = fMng.getFragments().get(i);//fMng.findFragmentById(R.id.layoutBodyView);
//		AssessBaseFragment mFragment = mAssessFragmentList.get(i);
//		{
//			
//			if(!mFragment.isAdded() /*&& fMng.findFragmentByTag(tag) == null*/){
//				FragmentTransaction ft = fMng.beginTransaction();
//				ft.add(R.id.layoutBodyView, mFragment, tag);
////				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//				ft.commit();
//				fMng.executePendingTransactions();
//			}
//		}
//		
//		if(fMng.getFragments().contains(mFragment))
//		{
//			FragmentTransaction ft = fMng.beginTransaction();
////			ft.setCustomAnimations(R.anim.fragment_slide_left_enter,
////		                R.anim.fragment_slide_left_exit,
////		                R.anim.fragment_slide_right_enter,
////		                R.anim.fragment_slide_right_exit);
//			ft.hide(lastFragment);
////			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//			ft.show(mFragment);
//			ft.addToBackStack(null);
////			ft.commitAllowingStateLoss();
//			ft.commit();
//			fMng.executePendingTransactions();
//			
//		}
////		DLog.LOG(SysMng.TAG_FRAGMENT, "end "+tag+" "+ mFragment.isAdded()+ " =================================");
//		
//		
//	}
	
	
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
			goFragment(false);
//			goBack();
			break;
		case ActivityMainAssess.BtnMenuViewId:
			pageMenuPop();
			break;
		case ActivityMainAssess.BtnGoViewId:
			if(mLayout.getBtnGoView().getText().equals("提交")
					|| mLayout.getBtnGoView().getText().equals("返回")){
				
				mAssessFragmentList.get(mAssessFragmentList.size()-1).saveData();

				if(mTask != null){
					mTask.AssessState = AssessTaskHeaderData.ASSESS_STATE_DONE;
//					mTask.NeedSync = true;
					AssessDBCtrl.updateAssessTaskHeaderById(this, mTask);
					
					SysDBCtrl.addSubmitAssessLog(this, mTask.AssessNum);
					if(mTask.NeedSync){
						ToastUtil.show(mActy, "提交成功。");
					}
					setResult(RESULT_SUBMIT);
					this.finish();
				}
			}
			goFragment(true);
//			addFragment();
			break;

		default:
			break;
		}
	}
	class TaskData{
		CustomerData cust = null;
		AssessTaskHeaderData header = null;
		ArrayList<AssessTaskDetailData> detail = null;
		ArrayList<AssessTaskServiceData> service = null;
	}
	
	
	
	public static final int RESULT_SUBMIT = 101; 

	@Override
	public void onBtnStratClick() {
//		addFragment();
		goFragment(true);
	}
	
	private void pageMenuPop(){
		PopupMenu popup = new PopupMenu(this, mLayout.getBtnMenuView());
//		popup.getMenuInflater().inflate(R.menu.relation, popup.getMenu());
//		mTitleList
		for (int i = 0; i<mTitleList.length;i++) {
			MenuItem menu = popup.getMenu().add(Menu.NONE, i, Menu.NONE, mTitleList[i]);
			
		}
		
//	
//		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
//				ToastUtil.show(mActy, item.getItemId()+" "+item.getTitle());
				showFragment(item.getItemId());
//				String defineS = CustomerData.getRelationDesc(item.getItemId());
//				mLayout.getTxtProxyRelationView().setText(defineS);
//				mLayout.getTxtProxyRelationView().setTag(
////							item.getItemId()
//						CustomerData.getRelationId(defineS)
//						);
				return true;
			}
		});
		
		popup.show();
	}
	
	

}
