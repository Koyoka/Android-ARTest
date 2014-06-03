package com.yrkj.elderlycareassess.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.SettingContentActivity;
import com.yrkj.elderlycareassess.bean.AssessReportCountData;
import com.yrkj.elderlycareassess.bean.SysLogData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentHome;
import com.yrkj.elderlycareassess.widget.UIReportCount;

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
//		layoutHomeContentLogView
		mLayout.getLayoutHomeContentLogView().setOnClickListener(this);
		mLayout.getLayoutHomeContentDoingTaskView().setOnClickListener(this);
		mLayout.getLayoutHomeContentDoneTaskView().setOnClickListener(this);
		
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
		
		
		AssessReportCountData data = AssessDBCtrl.getAssessReportCount(getActivity());
		mLayout.getTxtDoingTaskDescView().setText("还有"+data.doingTaskCount+"份评估未完成");
		mLayout.getTxtDoneTaskDescView().setText("已完成评估"+data.doneTaskCount+"份");
		
		int totle = 0;
		try {
			
			int c1 = data.doingTaskCount!=null && !data.doingTaskCount.isEmpty()?
					Integer.parseInt(data.doingTaskCount, 10):0;
			int c2 = data.doingTaskCount!=null && !data.doingTaskCount.isEmpty()?
					Integer.parseInt(data.doneTaskCount, 10):0;
			totle = c1+c2;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mLayout.getTxtReportTotleCountView().setText(totle+"");
		
		ArrayList<SysLogData>  items = SysDBCtrl.getAllLog(getActivity(),1);
		String s = "";
		if(items.size() != 0){
			SysLogData item = items.get(0);
			if(items.get(0).LogType.equals(SysLogData.LOGTYPE_ASSESS)){
				s = "完成评估  - 评估编号 - ";
			}else if(items.get(0).LogType.equals(SysLogData.LOGTYPE_SYNC)){
				s = "同步数据  - 评估编号 - ";
			}else if(items.get(0).LogType.equals(SysLogData.LOGTYPE_SYS_LOGIN)){
				s = "用户登录 - ";
			}
			s = s + item.LogDesc+" 日期:" + item.LogDate;
		}
		
		mLayout.getTxtLogDescView().setText(s);
		
		
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
		
		switch (v.getId()) {
		case FragmentHome.LayoutHomeContentLogViewId:
			Intent intent = new Intent(getActivity(), SettingContentActivity.class);
			startActivity(intent);
			break;

		case FragmentHome.LayoutHomeContentDoingTaskViewId:
			if(mOnAssessTaskClick != null)
				mOnAssessTaskClick.onClick(AssessTaskListFragment.class.getName());
			break;
		case FragmentHome.LayoutHomeContentDoneTaskViewId:
			if(mOnAssessTaskClick != null)
				mOnAssessTaskClick.onClick(AssessDoneListFragment.class.getName());
			break;
		default:
			mRc1.setValue(c == 0?90:0);
			mRc2.setValue(c);
			if(c==0)
				c=90;
			else
				c = 0;
			break;
		}
		
	}
	
	private OnAssessTaskClick mOnAssessTaskClick;
	public void setOnAssessTaskClick(OnAssessTaskClick l){
		mOnAssessTaskClick = l;
	}
	public interface OnAssessTaskClick{
		void onClick(String name);
	}
}











