package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment.onDateSelected;
import com.yrkj.elderlycareassess.layout.FragmentAssessPersonalinfo;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class AssessPersonalInfoFragment extends AssessBaseFragment implements OnClickListener {
	
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
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_person));
		
		mLayout.getTxtBirthdayView().setOnClickListener(this);
//		mLayout.getTxtSexView().setOnClickListener(this);
		mLayout.getTxtEthnicView().setOnClickListener(this);
		mLayout.getTxtEducationView().setOnClickListener(this);
		mLayout.getTxtProvinceView().setOnClickListener(this);
		mLayout.getTxtIsMarryView().setOnClickListener(this);
		mLayout.getTxtProxyRelationView().setOnClickListener(this);
		
		if(mCust == null){
			return;
		}
		mLayout.getTxtCustomerNameView().setText(mCust.customername);
		mLayout.getTxtIdNumberView().setText(mCust.idnumber);
//		mLayout.getRdoSexView()//.setText(CustomerData.getSexDesc(mCust.sex));
		
		mLayout.getRdoSexFView().setTag(mCust.sex);
		if(!mCust.sex.equals(CustomerData.SEX_MALE)){
			mLayout.getRdoSexFView().setChecked(true);
		}else{
			mLayout.getRdoSexMView().setChecked(true);
		}
		
		mLayout.getTxtSocialSecurityView().setText(mCust.socialsecurity);
		
		mLayout.getTxtEthnicView().setText(CustomerData.getEthnicDesc(mCust.ethnic));
		mLayout.getTxtEthnicView().setTag(mCust.ethnic);
		
		mLayout.getTxtEducationView().setText(CustomerData.getEducationDesc(mCust.education));
		mLayout.getTxtEducationView().setTag(mCust.education);
		
		if(mCust.birthday!= null && !mCust.birthday.isEmpty()){
			String defineDateStr = "";
			Date d =DateHelper.parseDate(mCust.birthday);
			if(d != null){
				defineDateStr = DateHelper.getDateStr(d);
				mLayout.getTxtBirthdayView().setText(defineDateStr);
			}
		}
		
		mLayout.getTxtWorkView().setText(mCust.work);
		mLayout.getTxtProvinceView().setText(mCust.province);
		
		mLayout.getTxtIsMarryView().setText(CustomerData.getMarryDesc(mCust.ismarry));
		mLayout.getTxtIsMarryView().setTag(mCust.ismarry);
		
		mLayout.getTxtHouseHoldAddrView().setText(mCust.householdaddr);
		mLayout.getTxtAddressView().setText(mCust.address);
		mLayout.getTxtHouseHoldMailView().setText(mCust.householdmail);
		mLayout.getTxtMailView().setText(mCust.mail);
		mLayout.getTxtTelView().setText(mCust.tel);
		mLayout.getTxtMobliePhoneView().setText(mCust.mobliephone);
		
		mLayout.getTxtProxyNameView().setText(mCust.proxyname);
		
		mLayout.getTxtProxyRelationView().setText(
				CustomerData.getRelationDesc(
				mCust.proxyrelation));
		mLayout.getTxtProxyRelationView().setTag(mCust.proxyrelation);
		
		mLayout.getTxtProxyAddrView().setText(mCust.proxyaddr);
		mLayout.getTxtProxyMailView().setText(mCust.proxymail);
		mLayout.getTxtProxyTelView().setText(mCust.proxytel);
		mLayout.getTxtProxyPhoneView().setText(mCust.proxyphone);
		
		
	}
	
	private void setUpdateCustData(){
		mCust.customername = mLayout.getTxtCustomerNameView().getText().toString();
		mCust.idnumber = mLayout.getTxtIdNumberView().getText().toString();
		
		switch (mLayout.getRdoSexView().getCheckedRadioButtonId()) {
		case FragmentAssessPersonalinfo.RdoSexMViewId://.getRdoSexFView():
			mCust.sex = CustomerData.SEX_MALE;
			break;
		case FragmentAssessPersonalinfo.RdoSexFViewId:
			mCust.sex = CustomerData.SEX_FEMALE;
			break;
		default:
			mCust.sex = CustomerData.SEX_MALE;
			break;
		}
//		mCust.sex = mLayout.getRdoSexView().getCheckedRadioButtonId();
		
		mCust.socialsecurity =mLayout.getTxtSocialSecurityView().getText().toString();
		
		mCust.ethnic = mLayout.getTxtEthnicView().getTag()+"";//.toString();
		
		mCust.education = mLayout.getTxtEducationView().getTag()+"";//.toString();
		
		
		mCust.birthday =mLayout.getTxtBirthdayView().getText().toString();
		mCust.work =mLayout.getTxtWorkView().getText().toString();
		
		mCust.province =mLayout.getTxtProvinceView().getText().toString();
		
		mCust.ismarry =mLayout.getTxtIsMarryView().getTag()+"";//.getText().toString();
		
		
		mCust.householdaddr =mLayout.getTxtHouseHoldAddrView().getText().toString();
		mCust.address =mLayout.getTxtAddressView().getText().toString();
		mCust.householdmail =mLayout.getTxtHouseHoldMailView().getText().toString();
		mCust.mail =mLayout.getTxtMailView().getText().toString();
		mCust.tel =mLayout.getTxtTelView().getText().toString();
		mCust.mobliephone =mLayout.getTxtMobliePhoneView().getText().toString();
		
		mCust.proxyname =mLayout.getTxtProxyNameView().getText().toString();
		
		mCust.proxyrelation =mLayout.getTxtProxyRelationView().getTag()+"";//.toString();
		
		
		mCust.proxyaddr =mLayout.getTxtProxyAddrView().getText().toString();
		mCust.proxymail =mLayout.getTxtProxyMailView().getText().toString();
		mCust.proxytel =mLayout.getTxtProxyTelView().getText().toString();
		mCust.proxyphone =mLayout.getTxtProxyPhoneView().getText().toString();
	}

	@Override
	public ArrayList<AssessTaskDetailData> getSelectData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case FragmentAssessPersonalinfo.TxtBirthdayViewId:
			MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.DATE_PICKER_DIALOG);
			newDialog.setOnDateSelected(new onDateSelected() {
				
				@Override
				public void onSelected(int y, int m, int d) {
					// TODO Auto-generated method stub
					mLayout.getTxtBirthdayView().setText(y+"-"+(m+1)+"-"+d);
				}
			});
			if(mLayout.getTxtBirthdayView().getText()!= null 
					&& !mLayout.getTxtBirthdayView().getText().toString().isEmpty()){
				Date d =DateHelper.parseDate(mLayout.getTxtBirthdayView().getText()+"");
				if(d != null)
					newDialog.setDateDialogValue(d.getYear()+1900,d.getMonth(),d.getDate());
//				DLog.LOG(SysMng.TAG_DB, d.getYear()+1900+" "+d.getMonth()+" "+d.getDate());
			}
			FragmentManager fm = getChildFragmentManager();
			newDialog.show(fm, "");
			break;

//		case FragmentAssessPersonalinfo.TxtSexViewId:
//			popSex();
//			break;
		case FragmentAssessPersonalinfo.TxtEthnicViewId:
			popEthnic();
			break;
		case FragmentAssessPersonalinfo.TxtEducationViewId:
			popEducation();
			break;
		case FragmentAssessPersonalinfo.TxtProvinceViewId:
			popProvince();
			break;
		case FragmentAssessPersonalinfo.TxtIsMarryViewId:
			popMarry();	
			break;
		case FragmentAssessPersonalinfo.TxtProxyRelationViewId:
			popRelation();
			break;
		default:
			break;
		}
		
	}
	
	
	private void popEthnic(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtEthnicView());
        popup.getMenuInflater().inflate(R.menu.ethnic, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	String defineS = CustomerData.getEthnicDesc(item.getItemId());
            	mLayout.getTxtEthnicView().setText(defineS);
            	mLayout.getTxtEthnicView().setTag(
//            			item.getItemId()
            			CustomerData.getEthnicId(defineS)
            			);
            	ToastUtil.show(getActivity(), mLayout.getTxtEthnicView().getTag()+"");
                return true;
            }
        });

        popup.show();
	}
	
	private void popEducation(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtEducationView());
        popup.getMenuInflater().inflate(R.menu.education, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	String defineS = CustomerData.getEducationDesc(item.getItemId());
            	mLayout.getTxtEducationView().setText(defineS);
            	mLayout.getTxtEducationView().setTag(
//            			item.getItemId()
            			CustomerData.getEducationId(defineS)
            			);
                return true;
            }
        });

        popup.show();
	}
	
	private void popProvince(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtProvinceView());
        popup.getMenuInflater().inflate(R.menu.province, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	String defineS = CustomerData.getProvinceDesc(item.getItemId());
            	mLayout.getTxtProvinceView().setText(defineS);
                return true;
            }
        });

        popup.show();
	}
	
	private void popMarry(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtIsMarryView());
		popup.getMenuInflater().inflate(R.menu.marry, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				String defineS = CustomerData.getMarryDesc(item.getItemId());
				mLayout.getTxtIsMarryView().setText(defineS);
				mLayout.getTxtIsMarryView().setTag(
//						item.getItemId()
						CustomerData.getMarryId(defineS)
						);
				return true;
			}
		});
		
		popup.show();
	}
	
	private void popRelation(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtProxyRelationView());
		popup.getMenuInflater().inflate(R.menu.relation, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				String defineS = CustomerData.getRelationDesc(item.getItemId());
				mLayout.getTxtProxyRelationView().setText(defineS);
				mLayout.getTxtProxyRelationView().setTag(
//						item.getItemId()
						CustomerData.getRelationId(defineS)
						);
				return true;
			}
		});
		
		popup.show();
	}

	public void saveData() {
		// TODO Auto-generated method stub
		setUpdateCustData();
		AssessDBCtrl.updateCustomerById(getActivity(), mCust);
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if(hidden){
			saveData();
		}
	}
	
	
}
