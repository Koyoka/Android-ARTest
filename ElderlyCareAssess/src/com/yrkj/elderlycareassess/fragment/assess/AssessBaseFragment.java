package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.util.log.ToastUtil;

public abstract class AssessBaseFragment extends Fragment {
	
	private MainAssessActivity mA;
	protected QCategoryData mData = null;
	
	public AssessBaseFragment(){
		
	}
	protected boolean mNeedSync = false;
	public abstract ArrayList<AssessTaskDetailData> getSelectData();	
	protected CustomerData mCust;
	public AssessBaseFragment(MainAssessActivity a,CustomerData c){
		mA = a;
		mCust = c;
	}
	public AssessBaseFragment(MainAssessActivity a,CustomerData c,boolean needSync){
		mA = a;
		mCust = c;
		mNeedSync = needSync;
	}
//	
	public AssessBaseFragment(MainAssessActivity a,QCategoryData d,CustomerData c,boolean needSync){
		mA = a;
		mData = d;
		mCust = c;
		mNeedSync = needSync;
//		setTitle(d.CateName);
	}

	protected void setTitle(String title){
		
	}
	
	public void saveData(){
		
	}
	
	public boolean checkData(int page){
		return true;
	}
	
	
	protected OnCheckDataLisenter mOnCheckDataLisenter;
	public void setOnCheckDataLisenter(OnCheckDataLisenter l){
		mOnCheckDataLisenter = l;
	}
	public interface OnCheckDataLisenter{
		public void onCheck(int page,boolean check);
	}
	
	protected void setFrontBody(View v){
		if(!mNeedSync){
			
			if(mFrontClick == null){
				mFrontClick = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						ToastUtil.show(getActivity(), mNeedSync+"");
					}
				};
				v.findViewById(R.id.layoutFrontView).setOnClickListener(mFrontClick);
			}
			v.findViewById(R.id.layoutFrontView).setVisibility(View.VISIBLE);
		}else{
			v.findViewById(R.id.layoutFrontView).setVisibility(View.GONE);
			
		}
	}
	OnClickListener mFrontClick = null;
	
}
