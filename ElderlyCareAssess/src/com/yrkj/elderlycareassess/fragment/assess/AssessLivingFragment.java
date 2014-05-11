package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.dao.QCategoryData;

public class AssessLivingFragment extends AssessBaseFragment {

	public AssessLivingFragment(MainAssessActivity a) {
		super(a);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_living, container,
				false);
		initFragment();
		return v;
	}

	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_living));
	}
	
}
