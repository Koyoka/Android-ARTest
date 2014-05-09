package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;

public class AssessBaseFragment extends Fragment {
	
	private MainAssessActivity mA;

	protected void setTitle(String title){
		if(mA == null){
			return;
		}
		mA.setAssessTitle(title);
	}
	protected void setTitle(int resId){
		if(mA == null){
			return;
		}
		mA.setAssessTitle(getResources().getString(resId));
		
	}
	
	public void setActy(MainAssessActivity a){
		mA = a;
	}
}
