package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment.onDateSelected;
import com.yrkj.elderlycareassess.layout.FragmentAssessPersonalinfo;
import com.yrkj.util.date.DateHelper;

public class AssessPersonalInfoFragment extends AssessBaseFragment implements OnClickListener {
	
	public AssessPersonalInfoFragment(MainAssessActivity a,CustomerData c,boolean d) {
		super(a,c,d);
		// TODO Auto-generated constructor stub
	}
	
	private FragmentAssessPersonalinfo mLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_personalinfo, container,
				false);
		
		mLayout = new FragmentAssessPersonalinfo(v);
		setFrontBody(v);
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
		
		mLayout.getTxtWorkView().setText(mCust.workarea);
		mLayout.getTxtProvinceView().setText(
				getPmap().get(
				mCust.province)+"");
		mLayout.getTxtProvinceView().setTag(mCust.province+"");
		
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
		mCust.workarea =mLayout.getTxtWorkView().getText().toString();
		
		mCust.province =mLayout.getTxtProvinceView().getTag().toString();
		
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
//            	ToastUtil.show(getActivity(), mLayout.getTxtEthnicView().getTag()+"");
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
	private Map provinceList;
	private Map getPmap(){
		if(provinceList!=null){
			return provinceList;
		}
		provinceList = new HashMap<String,String>();
		provinceList.put("110000", "北京市");
		provinceList.put("120000", "天津市");
		provinceList.put("130000", "河北省");
		provinceList.put("140000", "山西省");
		provinceList.put("150000", "内蒙古自治区");
		provinceList.put("210000", "辽宁省");
		provinceList.put("220000", "吉林省");
		provinceList.put("230000", "黑龙江省");
		provinceList.put("310000", "上海市");
		provinceList.put("320000", "江苏省");
		provinceList.put("330000", "浙江省");
		provinceList.put("340000", "安徽省");
		provinceList.put("350000", "福建省");
		provinceList.put("360000", "江西省");
		provinceList.put("370000", "山东省");
		provinceList.put("410000", "河南省");
		provinceList.put("420000", "湖北省");
		provinceList.put("430000", "湖南省");
		provinceList.put("440000", "广东省");
		provinceList.put("450000", "广西壮族自治区");
		provinceList.put("460000", "海南省");
		provinceList.put("500000", "重庆市");
		provinceList.put("510000", "四川省");
		provinceList.put("520000", "贵州省");
		provinceList.put("530000", "云南省");
		provinceList.put("540000", "西藏自治区");
		provinceList.put("610000", "陕西省");
		provinceList.put("620000", "甘肃省");
		provinceList.put("630000", "青海省");
		provinceList.put("640000", "宁夏回族自治区");
		provinceList.put("650000", "新疆维吾尔自治区");
		provinceList.put("710000", "台湾省");
		provinceList.put("810000", "香港");
		provinceList.put("820000", "澳门");
		return provinceList;
	}
	
	
	
	private void popProvince(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getTxtProvinceView());
		popup.getMenu().add(Menu.NONE, 110000, Menu.NONE, "北京市");
		popup.getMenu().add(Menu.NONE, 120000, Menu.NONE, "天津市");
		popup.getMenu().add(Menu.NONE, 130000, Menu.NONE, "河北省");
		popup.getMenu().add(Menu.NONE, 140000, Menu.NONE, "山西省");
		popup.getMenu().add(Menu.NONE, 150000, Menu.NONE, "内蒙古自治区");
		popup.getMenu().add(Menu.NONE, 210000, Menu.NONE, "辽宁省");
		popup.getMenu().add(Menu.NONE, 220000, Menu.NONE, "吉林省");
		popup.getMenu().add(Menu.NONE, 230000, Menu.NONE, "黑龙江省");
		popup.getMenu().add(Menu.NONE, 310000, Menu.NONE, "上海市");
		popup.getMenu().add(Menu.NONE, 320000, Menu.NONE, "江苏省");
		popup.getMenu().add(Menu.NONE, 330000, Menu.NONE, "浙江省");
		popup.getMenu().add(Menu.NONE, 340000, Menu.NONE, "安徽省");
		popup.getMenu().add(Menu.NONE, 350000, Menu.NONE, "福建省");
		popup.getMenu().add(Menu.NONE, 360000, Menu.NONE, "江西省");
		popup.getMenu().add(Menu.NONE, 370000, Menu.NONE, "山东省");
		popup.getMenu().add(Menu.NONE, 410000, Menu.NONE, "河南省");
		popup.getMenu().add(Menu.NONE, 420000, Menu.NONE, "湖北省");
		popup.getMenu().add(Menu.NONE, 430000, Menu.NONE, "湖南省");
		popup.getMenu().add(Menu.NONE, 440000, Menu.NONE, "广东省");
		popup.getMenu().add(Menu.NONE, 450000, Menu.NONE, "广西壮族自治区");
		popup.getMenu().add(Menu.NONE, 460000, Menu.NONE, "海南省");
		popup.getMenu().add(Menu.NONE, 500000, Menu.NONE, "重庆市");
		popup.getMenu().add(Menu.NONE, 510000, Menu.NONE, "四川省");
		popup.getMenu().add(Menu.NONE, 520000, Menu.NONE, "贵州省");
		popup.getMenu().add(Menu.NONE, 530000, Menu.NONE, "云南省");
		popup.getMenu().add(Menu.NONE, 540000, Menu.NONE, "西藏自治区");
		popup.getMenu().add(Menu.NONE, 610000, Menu.NONE, "陕西省");
		popup.getMenu().add(Menu.NONE, 620000, Menu.NONE, "甘肃省");
		popup.getMenu().add(Menu.NONE, 630000, Menu.NONE, "青海省");
		popup.getMenu().add(Menu.NONE, 640000, Menu.NONE, "宁夏回族自治区");
		popup.getMenu().add(Menu.NONE, 650000, Menu.NONE, "新疆维吾尔自治区");
		popup.getMenu().add(Menu.NONE, 710000, Menu.NONE, "台湾省");
		popup.getMenu().add(Menu.NONE, 810000, Menu.NONE, "香港");
		popup.getMenu().add(Menu.NONE, 820000, Menu.NONE, "澳门");
//        popup.getMenuInflater().inflate(R.menu.province, popup.getMenu());
//		getPmap().
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	String defineS = getPmap().get(item.getItemId()+"")+"";//CustomerData.getProvinceDesc(item.getItemId());
            	mLayout.getTxtProvinceView().setText(defineS);
            	mLayout.getTxtProvinceView().setTag(item.getItemId()+"");
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
