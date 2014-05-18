package com.yrkj.elderlycareassess.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentUserHeaderinfo;
import com.yrkj.util.log.DLog;

public class UserHeaderInfoFragment extends Fragment {
	
	FragmentUserHeaderinfo mLayout;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_headerinfo, container,
				false);
		mLayout = new FragmentUserHeaderinfo(v);
		initFragment();
		loadData();
		return v;
	}
	
	private void initFragment(){
		
	}
	
	private void loadData(){
		
		String s = SysDBCtrl.getLastLoginDate(getActivity());
		
		mLayout.getTxtLastLoginDateView().setText(s.isEmpty()?"Ê×´ÎµÇÂ½":s);
		
		AssessUserData u = SysMng.getUserInfo();
		u = AssessUserDBCtrl.getUserData(getActivity(), u.UserId);
		if(u != null){
			mLayout.getTxtUserNameView().setText(u.UserName);
			mLayout.getTxtAddressView().setText(u.OfficeAddress);
			mLayout.getTxtOfficeView().setText(u.Office);
		}
		
		
	}
}
