package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;

public class AssessSubmitFragment extends AssessBaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_submit, container,
				false);
		return v;
	}
}