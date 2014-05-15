package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;

import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class CustomerData {
	public static final String TblName = "t_customer";
	
	public static final String SEX_MALE = "1";
	public static final String SEX_FEMALE = "2";
	public static String getSexDesc(String s){
		String defineS = "";
		
		if(s.equals(SEX_MALE)){
			defineS = "ÄÐ";
		}else if(s.equals(SEX_FEMALE)){
			defineS = "Å®";
		}
		return defineS;
		
	}
	
	public static final String Col_id = "id";
	public static final String Col_customername = "customername";
	public static final String Col_idnumber = "idnumber";
	public static final String Col_sex = "sex";
	public static final String Col_birthday = "birthday";
	public static final String Col_socialsecurity = "socialsecurity";
	public static final String Col_tel = "tel";
	public static final String Col_mobliephone = "mobliephone";
	public static final String Col_area = "area";
	public static final String Col_address = "address";
	public static final String Col_mail = "mail";
	public static final String Col_customerimg = "customerimg";
	public static final String Col_ethnic = "ethnic";
	public static final String Col_otherethnic = "otherethnic";
	public static final String Col_education = "education";
	public static final String Col_province = "province";
	public static final String Col_work = "work";
	public static final String Col_householdarea = "householdarea";
	public static final String Col_householdaddr = "householdaddr";
	public static final String Col_householdmail = "householdmail";
	public static final String Col_ismarry = "ismarry";
	public static final String Col_proxyname = "proxyname";
	public static final String Col_proxyrelation = "proxyrelation";
	public static final String Col_proxytel = "proxytel";
	public static final String Col_proxyphone = "proxyphone";
	public static final String Col_proxyarea = "proxyarea";
	public static final String Col_proxyaddr = "proxyaddr";
	public static final String Col_proxymail = "proxymail";
	public static final String Col_economicstatus = "economicstatus";
	public static final String Col_livestatus = "livestatus";
	public static final String Col_housestatus = "housestatus";
	public static final String Col_helpstatus = "helpstatus";
	public static final String Col_helper = "helper";
	public static final String Col_otherhelper = "otherhelper";
	public static final String Col_medicalstatus = "medicalstatus";
	public static final String Col_othermedical = "othermedical";
	public static final String Col_adduser = "adduser";
	public static final String Col_addtime = "addtime";
	public static final String Col_isok = "isok";
	public static final String Col_assessnum = "assessnum";
	public static final String Col_firstassessdate = "firstassessdate";
	public static final String Col_curassesstype = "curassesstype";
	public static final String Col_curassessstatus = "curassessstatus";
	public static final String Col_unitid = "unitid";

	
	public static String[] getColumnColl(){
		return new String[]{
				Col_id,
				Col_customername,
				Col_idnumber,
				Col_sex,
				Col_birthday,
				Col_socialsecurity,
				Col_tel,
				Col_mobliephone,
				Col_area,
				Col_address,
				Col_mail,
				Col_customerimg,
				Col_ethnic,
				Col_otherethnic,
				Col_education,
				Col_province,
				Col_work,
				Col_householdarea,
				Col_householdaddr,
				Col_householdmail,
				Col_ismarry,
				Col_proxyname,
				Col_proxyrelation,
				Col_proxytel,
				Col_proxyphone,
				Col_proxyarea,
				Col_proxyaddr,
				Col_proxymail,
				Col_economicstatus,
				Col_livestatus,
				Col_housestatus,
				Col_helpstatus,
				Col_helper,
				Col_otherhelper,
				Col_medicalstatus,
				Col_othermedical,
				Col_adduser,
				Col_addtime,
				Col_isok,
				Col_assessnum,
				Col_firstassessdate,
				Col_curassesstype,
				Col_curassessstatus,
				Col_unitid
				};
	}
	
	public String id = "";
	public String customername = "";
	public String idnumber = "";
	public String sex = "";
	public String birthday = "";
	public String socialsecurity = "";
	public String tel = "";
	public String mobliephone = "";
	public String area = "";
	public String address = "";
	public String mail = "";
	public String customerimg = "";
	public String ethnic = "";
	public String otherethnic = "";
	public String education = "";
	public String province = "";
	public String work = "";
	public String householdarea = "";
	public String householdaddr = "";
	public String householdmail = "";
	public String ismarry = "";
	public String proxyname = "";
	public String proxyrelation = "";
	public String proxytel = "";
	public String proxyphone = "";
	public String proxyarea = "";
	public String proxyaddr = "";
	public String proxymail = "";
	public String economicstatus = "";
	public String livestatus = "";
	public String housestatus = "";
	public String helpstatus = "";
	public String helper = "";
	public String otherhelper = "";
	public String medicalstatus = "";
	public String othermedical = "";
	public String adduser = "";
	public String addtime = "";
	public String isok = "";
	public String assessnum = "";
	public String firstassessdate = "";
	public String curassesstype = "";
	public String curassessstatus = "";
	public String unitid = "";

	
	
	
	public static CustomerData convertDataToModule(Cursor c){
		CustomerData item = new CustomerData();
		item.id = DBMng.GetDataString(c, Col_id);
		item.customername = DBMng.GetDataString(c, Col_customername);
		item.idnumber = DBMng.GetDataString(c, Col_idnumber);
		item.sex = DBMng.GetDataString(c, Col_sex);
		item.birthday = DBMng.GetDataString(c, Col_birthday);
		item.socialsecurity = DBMng.GetDataString(c, Col_socialsecurity);
		item.tel = DBMng.GetDataString(c, Col_tel);
		item.mobliephone = DBMng.GetDataString(c, Col_mobliephone);
		item.area = DBMng.GetDataString(c, Col_area);
		item.address = DBMng.GetDataString(c, Col_address);
		item.mail = DBMng.GetDataString(c, Col_mail);
		item.customerimg = DBMng.GetDataString(c, Col_customerimg);
		item.ethnic = DBMng.GetDataString(c, Col_ethnic);
		item.otherethnic = DBMng.GetDataString(c, Col_otherethnic);
		item.education = DBMng.GetDataString(c, Col_education);
		item.province = DBMng.GetDataString(c, Col_province);
		item.work = DBMng.GetDataString(c, Col_work);
		item.householdarea = DBMng.GetDataString(c, Col_householdarea);
		item.householdaddr = DBMng.GetDataString(c, Col_householdaddr);
		item.householdmail = DBMng.GetDataString(c, Col_householdmail);
		item.ismarry = DBMng.GetDataString(c, Col_ismarry);
		item.proxyname = DBMng.GetDataString(c, Col_proxyname);
		item.proxyrelation = DBMng.GetDataString(c, Col_proxyrelation);
		item.proxytel = DBMng.GetDataString(c, Col_proxytel);
		item.proxyphone = DBMng.GetDataString(c, Col_proxyphone);
		item.proxyarea = DBMng.GetDataString(c, Col_proxyarea);
		item.proxyaddr = DBMng.GetDataString(c, Col_proxyaddr);
		item.proxymail = DBMng.GetDataString(c, Col_proxymail);
		item.economicstatus = DBMng.GetDataString(c, Col_economicstatus);
		item.livestatus = DBMng.GetDataString(c, Col_livestatus);
		item.housestatus = DBMng.GetDataString(c, Col_housestatus);
		item.helpstatus = DBMng.GetDataString(c, Col_helpstatus);
		item.helper = DBMng.GetDataString(c, Col_helper);
		item.otherhelper = DBMng.GetDataString(c, Col_otherhelper);
		item.medicalstatus = DBMng.GetDataString(c, Col_medicalstatus);
		item.othermedical = DBMng.GetDataString(c, Col_othermedical);
		item.adduser = DBMng.GetDataString(c, Col_adduser);
		item.addtime = DBMng.GetDataString(c, Col_addtime);
		item.isok = DBMng.GetDataString(c, Col_isok);
		item.assessnum = DBMng.GetDataString(c, Col_assessnum);
		item.firstassessdate = DBMng.GetDataString(c, Col_firstassessdate);
		item.curassesstype = DBMng.GetDataString(c, Col_curassesstype);
		item.curassessstatus = DBMng.GetDataString(c, Col_curassessstatus);
		item.unitid = DBMng.GetDataString(c, Col_unitid);
		return item;
	}
}
