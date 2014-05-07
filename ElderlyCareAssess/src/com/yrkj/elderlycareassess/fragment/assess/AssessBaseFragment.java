package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;

public class AssessBaseFragment extends Fragment {
	
	protected void setTitle(String title){
		getActivity().setTitle(title);
	}
	protected void setTitle(int resId){
		getActivity().setTitle(getResources().getString(resId));
		
		
	}
}
