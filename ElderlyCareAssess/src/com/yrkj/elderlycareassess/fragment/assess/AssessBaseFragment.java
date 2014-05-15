package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;

public class AssessBaseFragment extends Fragment {
	
	private MainAssessActivity mA;
	protected QCategoryData mData = null;
	
	public AssessBaseFragment(){
		
	}
	protected CustomerData mCust;
	public AssessBaseFragment(MainAssessActivity a,CustomerData c){
		mA = a;
		mCust = c;
	}
//	
	public AssessBaseFragment(MainAssessActivity a,QCategoryData d,CustomerData c){
		mA = a;
		mData = d;
		mCust = c;
//		setTitle(d.CateName);
	}

	protected void setTitle(String title){
		
	}
//		if(mA == null){
//			return;
//		}
////		mA.setAssessTitle(title);
//	}
	
//	public void setActy(MainAssessActivity a){
//		mA = a;
//	}
}
