package com.yrkj.elderlycareassess.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessReportCountData;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentHome;
import com.yrkj.elderlycareassess.widget.UIReportCount;
import com.yrkj.util.log.DLog;

public class HomeFragment extends Fragment 
implements OnClickListener
{
	
	View mV;
	
	UIReportCount mRc1;
	UIReportCount mRc2;
	UIReportCount mRc3;
	UIReportCount mRc4;
	UIReportCount mRc5;
	UIReportCount mRc6;
	UIReportCount mRc7;
	
	FragmentHome mLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container,
				false);
		mV = v;
		mLayout = new FragmentHome(v);
		
		initFragment();
		loadData();
		return v;
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
//		ToastUtil.show(getActivity(), "hidden = "+hidden);
//		if(!hidden){
//			mRc1.setValue(20);
//			mRc2.setValue(60);
//			mRc3.setValue(50);
//			mRc4.setValue(30);
//			mRc5.setValue(20);
//			mRc6.setValue(90);
//			mRc7.setValue(80);
//		}else{
//			mRc1.setValue(0);
//			mRc2.setValue(0);
//			mRc3.setValue(0);
//			mRc4.setValue(0);
//			mRc5.setValue(0);
//			mRc6.setValue(0);
//			mRc7.setValue(0);
//		}
	}
	
	private void initFragment(){
		
		FragmentHome mLayout = new FragmentHome(mV);
		mLayout.getTxtReportTotleCountView().setOnClickListener(this);
		
		if(mV != null){
			mRc1 = (UIReportCount) mV.findViewById(R.id.uctrlRC1View);
			mRc2 = (UIReportCount) mV.findViewById(R.id.uctrlRC2View);
			mRc3 = (UIReportCount) mV.findViewById(R.id.uctrlRC3View);
			mRc4 = (UIReportCount) mV.findViewById(R.id.uctrlRC4View);
			mRc5 = (UIReportCount) mV.findViewById(R.id.uctrlRC5View);
			mRc6 = (UIReportCount) mV.findViewById(R.id.uctrlRC6View);
			mRc7 = (UIReportCount) mV.findViewById(R.id.uctrlRC7View);
//			UIReportCount mRc1 = (UIReportCount) mV.findViewById(R.id.uctrlRC1View);
		}
	}

	private void loadData(){
		
//		String s = SysDBCtrl.getLastLoginDate(getActivity());
//		
//		mLayout.getTxtLastLoginDateView().setText(s.isEmpty()?"首次登陆":s);
//		
//		AssessUserData u = SysMng.getUserInfo();
//		DLog.LOG(SysMng.TAG_DB, u+" 1 ===["+u.UserId);
//		u = AssessUserDBCtrl.getUserData(getActivity(), u.UserId);
//		DLog.LOG(SysMng.TAG_DB, u+" 2 ===[");
//		if(u != null){
//			mLayout.getTxtUserNameView().setText(u.UserName);
//			mLayout.getTxtAddressView().setText(u.OfficeAddress);
//			mLayout.getTxtOfficeView().setText(u.Office);
//		}
		
		AssessReportCountData data = AssessDBCtrl.getAssessReportCount(getActivity());
		mLayout.getTxtDoingTaskDescView().setText("还有"+data.doingTaskCount+"份评估未完成");
		mLayout.getTxtDoneTaskDescView().setText("已完成评估"+data.doneTaskCount+"份");
		
		
		
		mRc1.setValue(20);
		mRc2.setValue(60);
		mRc3.setValue(50);
		mRc4.setValue(30);
		mRc5.setValue(20);
		mRc6.setValue(90);
		mRc7.setValue(80);
		
	}
	
	int c = 0;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mRc1.setValue(c == 0?90:0);
		mRc2.setValue(c);
		if(c==0)
			c=90;
		else
			c = 0;
	}
}











