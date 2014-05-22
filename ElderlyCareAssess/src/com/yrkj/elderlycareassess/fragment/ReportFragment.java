package com.yrkj.elderlycareassess.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.AssessReportCountData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentReport;
import com.yrkj.elderlycareassess.widget.UIReportCount;

public class ReportFragment extends Fragment {
	UIReportCount mRc1;
	UIReportCount mRc2;
	UIReportCount mRc3;
	UIReportCount mRc4;
	UIReportCount mRc5;
	UIReportCount mRc6;
	UIReportCount mRc7;
	UIReportCount mRc8;
	UIReportCount mRc9;
	UIReportCount mRc10;
	UIReportCount mRc11;
	UIReportCount mRc12;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mV = inflater.inflate(R.layout.fragment_report, container,
				false);
		
		AssessReportCountData data = AssessDBCtrl.getAssessReportCount(getActivity());
		
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
		
		new FragmentReport(mV).getTxtReportTotleCountView().setText(totle+"");
		
		mRc1 = (UIReportCount) mV.findViewById(R.id.uctrlRC1View);
		mRc2 = (UIReportCount) mV.findViewById(R.id.uctrlRC2View);
		mRc3 = (UIReportCount) mV.findViewById(R.id.uctrlRC3View);
		mRc4 = (UIReportCount) mV.findViewById(R.id.uctrlRC4View);
		mRc5 = (UIReportCount) mV.findViewById(R.id.uctrlRC5View);
		mRc6 = (UIReportCount) mV.findViewById(R.id.uctrlRC6View);
		mRc7 = (UIReportCount) mV.findViewById(R.id.uctrlRC7View);
		mRc8 = (UIReportCount) mV.findViewById(R.id.uctrlRC8View);
		mRc9 = (UIReportCount) mV.findViewById(R.id.uctrlRC9View);
		mRc10 = (UIReportCount) mV.findViewById(R.id.uctrlRC10View);
		mRc11 = (UIReportCount) mV.findViewById(R.id.uctrlRC11View);
		mRc12 = (UIReportCount) mV.findViewById(R.id.uctrlRC12View);
//			UIReportCount mRc1 = (UIReportCount) mV.findViewById(R.id.uctrlRC1View);
		mRc1.setValue(20);
		mRc2.setValue(60);
		mRc3.setValue(50);
		mRc4.setValue(30);
		mRc5.setValue(20);
		mRc6.setValue(90);
		mRc7.setValue(80);
		mRc8.setValue(30);
		mRc9.setValue(10);
		mRc10.setValue(20);
		mRc11.setValue(10);
		mRc12.setValue(60);
		
		return mV;
	}
}
