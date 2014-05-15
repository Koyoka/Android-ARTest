package com.yrkj.elderlycareassess.fragment.assess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.layout.FragmentAssessPersonalinfo;

public class AssessPersonalInfoFragment extends AssessBaseFragment {
	public AssessPersonalInfoFragment(MainAssessActivity a,CustomerData c) {
		super(a,c);
		// TODO Auto-generated constructor stub
	}

	private FragmentAssessPersonalinfo mLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_personalinfo, container,
				false);
		
		mLayout = new FragmentAssessPersonalinfo(v);
		
		initFragment();
		return v;
	}
	
//	CustomerData mCust = null;
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_person));
		
		
		if(mCust == null){
			return;
		}
		mLayout.getTxtCustomerNameView().setText(mCust.customername);
		mLayout.getTxtIdNumberView().setText(mCust.idnumber);
		mLayout.getTxtSexView().setText(mCust.sex);
		mLayout.getTxtSocialSecurityView().setText(mCust.socialsecurity);
		mLayout.getTxtEthnicView().setText(mCust.ethnic);
		mLayout.getTxtEducationView().setText(mCust.education);
		mLayout.getTxtBirthdayView().setText(mCust.birthday);
		mLayout.getTxtWorkView().setText(mCust.work);
		mLayout.getTxtProvinceView().setText(mCust.province);
		mLayout.getTxtIsMarryView().setText(mCust.ismarry);
		mLayout.getTxtHouseHoldAddrView().setText(mCust.householdaddr);
		mLayout.getTxtAddressView().setText(mCust.address);
		mLayout.getTxtHouseHoldMailView().setText(mCust.householdmail);
		mLayout.getTxtMailView().setText(mCust.mail);
		mLayout.getTxtTelView().setText(mCust.tel);
		mLayout.getTxtMobliePhoneView().setText(mCust.mobliephone);
		
		mLayout.getTxtProxyNameView().setText(mCust.proxyname);
		mLayout.getTxtProxyRelationView().setText(mCust.proxyrelation);
		mLayout.getTxtProxyAddrView().setText(mCust.proxyaddr);
		mLayout.getTxtProxyMailView().setText(mCust.proxymail);
		mLayout.getTxtProxyTelView().setText(mCust.proxytel);
		mLayout.getTxtProxyPhoneView().setText(mCust.proxyphone);
		
		
		
		
		
		
		
	}
	
}
