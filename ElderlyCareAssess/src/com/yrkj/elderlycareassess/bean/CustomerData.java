package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.elderlycareassess.R;
import com.yrkj.util.db.DBMng;

public class CustomerData {
	public static final String TblName = "t_customer";
	
	public static final String SEX_MALE = "1";
	public static final String SEX_FEMALE = "2";
	
	
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
	
	
	public static String getSexDesc(String s){
		String defineS = "";
		
		if(s.equals(SEX_MALE)){
			defineS = "男";
		}else if(s.equals(SEX_FEMALE)){
			defineS = "女";
		}
		return defineS;
		
	}
	
	
	public static String getEducationDesc(int id){
		String defineS = "";
		if(false){}
		else if(id == R.id.menuEducation1){ defineS = "文盲";}
		else if(id == R.id.menuEducation2){ defineS = "小小学";}
		else if(id == R.id.menuEducation3){ defineS = "初中";}
		else if(id == R.id.menuEducation4){ defineS = "高中";}
		else if(id == R.id.menuEducation5){ defineS = "大专";}
		else if(id == R.id.menuEducation6){ defineS = "本科及以上";}

		return defineS;
	}
	public static String getEducationId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("文盲")){defineS = "1";}
		else if(s.equals("小小学")){defineS = "2";}
		else if(s.equals("初中")){defineS = "3";}
		else if(s.equals("高中")){defineS = "4";}
		else if(s.equals("大专")){defineS = "5";}
		else if(s.equals("本科及以上")){defineS = "6";}

		return defineS;
	}
	public static String getEducationDesc(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("1")){defineS = "文盲";}
		else if(s.equals("2")){defineS = "小小学";}
		else if(s.equals("3")){defineS = "初中";}
		else if(s.equals("4")){defineS = "高中";}
		else if(s.equals("5")){defineS = "大专";}
		else if(s.equals("6")){defineS = "本科及以上";}
		else{defineS = "请选择";}

		return defineS;
	}
	
	
	public static String getEthnicDesc(int id){
		String defineS = "";
		if(false){}
		else if(id == R.id.menuEthnic1){ defineS = "汉族";}
		else if(id == R.id.menuEthnic2){ defineS = "壮族";}
		else if(id == R.id.menuEthnic3){ defineS = "回族";}
		else if(id == R.id.menuEthnic4){ defineS = "满族";}
		else if(id == R.id.menuEthnic5){ defineS = "维吾尔族";}
		else if(id == R.id.menuEthnic6){ defineS = "苗族";}
		else if(id == R.id.menuEthnic7){ defineS = "彝族";}
		else if(id == R.id.menuEthnic8){ defineS = "土家族";}
		else if(id == R.id.menuEthnic9){ defineS = "藏族";}
		else if(id == R.id.menuEthnic10){ defineS = "蒙古族";}
		else if(id == R.id.menuEthnic11){ defineS = "侗族";}
		else if(id == R.id.menuEthnic12){ defineS = "布依族";}
		else if(id == R.id.menuEthnic13){ defineS = "瑶族";}
		else if(id == R.id.menuEthnic14){ defineS = "白族";}
		else if(id == R.id.menuEthnic15){ defineS = "朝鲜族";}
		else if(id == R.id.menuEthnic16){ defineS = "哈尼族";}
		else if(id == R.id.menuEthnic17){ defineS = "黎族";}
		else if(id == R.id.menuEthnic18){ defineS = "哈萨克族";}
		else if(id == R.id.menuEthnic19){ defineS = "傣族";}
		else if(id == R.id.menuEthnic20){ defineS = "畲族";}
		else if(id == R.id.menuEthnic21){ defineS = "傈僳族";}
		else if(id == R.id.menuEthnic22){ defineS = "东乡族";}
		else if(id == R.id.menuEthnic23){ defineS = "仡佬族";}
		else if(id == R.id.menuEthnic24){ defineS = "拉祜族";}
		else if(id == R.id.menuEthnic25){ defineS = "佤族";}
		else if(id == R.id.menuEthnic26){ defineS = "水族";}
		else if(id == R.id.menuEthnic27){ defineS = "纳西族";}
		else if(id == R.id.menuEthnic28){ defineS = "羌族";}
		else if(id == R.id.menuEthnic29){ defineS = "土族";}
		else if(id == R.id.menuEthnic30){ defineS = "仫佬族";}
		else if(id == R.id.menuEthnic31){ defineS = "锡伯族";}
		else if(id == R.id.menuEthnic32){ defineS = "柯尔克孜族";}
		else if(id == R.id.menuEthnic33){ defineS = "景颇族";}
		else if(id == R.id.menuEthnic34){ defineS = "达斡尔族";}
		else if(id == R.id.menuEthnic35){ defineS = "撒拉族";}
		else if(id == R.id.menuEthnic36){ defineS = "布朗族";}
		else if(id == R.id.menuEthnic37){ defineS = "毛南族";}
		else if(id == R.id.menuEthnic38){ defineS = "塔吉克族";}
		else if(id == R.id.menuEthnic39){ defineS = "普米族";}
		else if(id == R.id.menuEthnic40){ defineS = "阿昌族";}
		else if(id == R.id.menuEthnic41){ defineS = "怒族";}
		else if(id == R.id.menuEthnic42){ defineS = "鄂温克族";}
		else if(id == R.id.menuEthnic43){ defineS = "京族";}
		else if(id == R.id.menuEthnic44){ defineS = "基诺族";}
		else if(id == R.id.menuEthnic45){ defineS = "德昂族";}
		else if(id == R.id.menuEthnic46){ defineS = "保安族";}
		else if(id == R.id.menuEthnic47){ defineS = "俄罗斯族";}
		else if(id == R.id.menuEthnic48){ defineS = "裕固族";}
		else if(id == R.id.menuEthnic49){ defineS = "乌孜别克族";}
		else if(id == R.id.menuEthnic50){ defineS = "门巴族";}
		else if(id == R.id.menuEthnic51){ defineS = "鄂伦春族";}
		else if(id == R.id.menuEthnic52){ defineS = "独龙族";}
		else if(id == R.id.menuEthnic53){ defineS = "赫哲族";}
		else if(id == R.id.menuEthnic54){ defineS = "高山族";}
		else if(id == R.id.menuEthnic55){ defineS = "珞巴族";}
		else if(id == R.id.menuEthnic56){ defineS = "塔塔尔族";}
		else if(id == R.id.menuEthnic57){ defineS = "其他";}

		
		return defineS;
	}
	public static String getEthnicId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("汉族")){defineS = "1";}
		else if(s.equals("壮族")){defineS = "2";}
		else if(s.equals("回族")){defineS = "3";}
		else if(s.equals("满族")){defineS = "4";}
		else if(s.equals("维吾尔族")){defineS = "5";}
		else if(s.equals("苗族")){defineS = "6";}
		else if(s.equals("彝族")){defineS = "7";}
		else if(s.equals("土家族")){defineS = "8";}
		else if(s.equals("藏族")){defineS = "9";}
		else if(s.equals("蒙古族")){defineS = "10";}
		else if(s.equals("侗族")){defineS = "11";}
		else if(s.equals("布依族")){defineS = "12";}
		else if(s.equals("瑶族")){defineS = "13";}
		else if(s.equals("白族")){defineS = "14";}
		else if(s.equals("朝鲜族")){defineS = "15";}
		else if(s.equals("哈尼族")){defineS = "16";}
		else if(s.equals("黎族")){defineS = "17";}
		else if(s.equals("哈萨克族")){defineS = "18";}
		else if(s.equals("傣族")){defineS = "19";}
		else if(s.equals("畲族")){defineS = "20";}
		else if(s.equals("傈僳族")){defineS = "21";}
		else if(s.equals("东乡族")){defineS = "22";}
		else if(s.equals("仡佬族")){defineS = "23";}
		else if(s.equals("拉祜族")){defineS = "24";}
		else if(s.equals("佤族")){defineS = "25";}
		else if(s.equals("水族")){defineS = "26";}
		else if(s.equals("纳西族")){defineS = "27";}
		else if(s.equals("羌族")){defineS = "28";}
		else if(s.equals("土族")){defineS = "29";}
		else if(s.equals("仫佬族")){defineS = "30";}
		else if(s.equals("锡伯族")){defineS = "31";}
		else if(s.equals("柯尔克孜族")){defineS = "32";}
		else if(s.equals("景颇族")){defineS = "33";}
		else if(s.equals("达斡尔族")){defineS = "34";}
		else if(s.equals("撒拉族")){defineS = "35";}
		else if(s.equals("布朗族")){defineS = "36";}
		else if(s.equals("毛南族")){defineS = "37";}
		else if(s.equals("塔吉克族")){defineS = "38";}
		else if(s.equals("普米族")){defineS = "39";}
		else if(s.equals("阿昌族")){defineS = "40";}
		else if(s.equals("怒族")){defineS = "41";}
		else if(s.equals("鄂温克族")){defineS = "42";}
		else if(s.equals("京族")){defineS = "43";}
		else if(s.equals("基诺族")){defineS = "44";}
		else if(s.equals("德昂族")){defineS = "45";}
		else if(s.equals("保安族")){defineS = "46";}
		else if(s.equals("俄罗斯族")){defineS = "47";}
		else if(s.equals("裕固族")){defineS = "48";}
		else if(s.equals("乌孜别克族")){defineS = "49";}
		else if(s.equals("门巴族")){defineS = "50";}
		else if(s.equals("鄂伦春族")){defineS = "51";}
		else if(s.equals("独龙族")){defineS = "52";}
		else if(s.equals("赫哲族")){defineS = "53";}
		else if(s.equals("高山族")){defineS = "54";}
		else if(s.equals("珞巴族")){defineS = "55";}
		else if(s.equals("塔塔尔族")){defineS = "56";}
		else if(s.equals("其他")){defineS = "57";}

		
		return defineS;
	}
	public static String getEthnicDesc(String s){
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "汉族";}
		else if(s.equals("2")){defineS = "壮族";}
		else if(s.equals("3")){defineS = "回族";}
		else if(s.equals("4")){defineS = "满族";}
		else if(s.equals("5")){defineS = "维吾尔族";}
		else if(s.equals("6")){defineS = "苗族";}
		else if(s.equals("7")){defineS = "彝族";}
		else if(s.equals("8")){defineS = "土家族";}
		else if(s.equals("9")){defineS = "藏族";}
		else if(s.equals("10")){defineS = "蒙古族";}
		else if(s.equals("11")){defineS = "侗族";}
		else if(s.equals("12")){defineS = "布依族";}
		else if(s.equals("13")){defineS = "瑶族";}
		else if(s.equals("14")){defineS = "白族";}
		else if(s.equals("15")){defineS = "朝鲜族";}
		else if(s.equals("16")){defineS = "哈尼族";}
		else if(s.equals("17")){defineS = "黎族";}
		else if(s.equals("18")){defineS = "哈萨克族";}
		else if(s.equals("19")){defineS = "傣族";}
		else if(s.equals("20")){defineS = "畲族";}
		else if(s.equals("21")){defineS = "傈僳族";}
		else if(s.equals("22")){defineS = "东乡族";}
		else if(s.equals("23")){defineS = "仡佬族";}
		else if(s.equals("24")){defineS = "拉祜族";}
		else if(s.equals("25")){defineS = "佤族";}
		else if(s.equals("26")){defineS = "水族";}
		else if(s.equals("27")){defineS = "纳西族";}
		else if(s.equals("28")){defineS = "羌族";}
		else if(s.equals("29")){defineS = "土族";}
		else if(s.equals("30")){defineS = "仫佬族";}
		else if(s.equals("31")){defineS = "锡伯族";}
		else if(s.equals("32")){defineS = "柯尔克孜族";}
		else if(s.equals("33")){defineS = "景颇族";}
		else if(s.equals("34")){defineS = "达斡尔族";}
		else if(s.equals("35")){defineS = "撒拉族";}
		else if(s.equals("36")){defineS = "布朗族";}
		else if(s.equals("37")){defineS = "毛南族";}
		else if(s.equals("38")){defineS = "塔吉克族";}
		else if(s.equals("39")){defineS = "普米族";}
		else if(s.equals("40")){defineS = "阿昌族";}
		else if(s.equals("41")){defineS = "怒族";}
		else if(s.equals("42")){defineS = "鄂温克族";}
		else if(s.equals("43")){defineS = "京族";}
		else if(s.equals("44")){defineS = "基诺族";}
		else if(s.equals("45")){defineS = "德昂族";}
		else if(s.equals("46")){defineS = "保安族";}
		else if(s.equals("47")){defineS = "俄罗斯族";}
		else if(s.equals("48")){defineS = "裕固族";}
		else if(s.equals("49")){defineS = "乌孜别克族";}
		else if(s.equals("50")){defineS = "门巴族";}
		else if(s.equals("51")){defineS = "鄂伦春族";}
		else if(s.equals("52")){defineS = "独龙族";}
		else if(s.equals("53")){defineS = "赫哲族";}
		else if(s.equals("54")){defineS = "高山族";}
		else if(s.equals("55")){defineS = "珞巴族";}
		else if(s.equals("56")){defineS = "塔塔尔族";}
		else if(s.equals("57")){defineS = "其他";}
		else{defineS = "请选择";}
		
		return defineS;
	}

	
	public static String getProvinceDesc(int id) {
		String defineS = "";
		
		if(false){}	
		else if(id == R.id.menuProvince1){ defineS = "北京市";}
		else if(id == R.id.menuProvince2){ defineS = "上海市";}
		else if(id == R.id.menuProvince3){ defineS = "天津市";}
		else if(id == R.id.menuProvince4){ defineS = "重庆市";}
		else if(id == R.id.menuProvince5){ defineS = "宁夏";}
		else if(id == R.id.menuProvince6){ defineS = "内蒙古";}
		else if(id == R.id.menuProvince7){ defineS = "安徽省";}
		else if(id == R.id.menuProvince8){ defineS = "福建省";}
		else if(id == R.id.menuProvince9){ defineS = "甘肃省";}
		else if(id == R.id.menuProvince10){ defineS = "广东省";}
		else if(id == R.id.menuProvince11){ defineS = "广西省";}
		else if(id == R.id.menuProvince12){ defineS = "贵州省";}
		else if(id == R.id.menuProvince13){ defineS = "海南省";}
		else if(id == R.id.menuProvince14){ defineS = "河北省";}
		else if(id == R.id.menuProvince15){ defineS = "河南省";}
		else if(id == R.id.menuProvince16){ defineS = "黑龙江";}
		else if(id == R.id.menuProvince17){ defineS = "湖北省";}
		else if(id == R.id.menuProvince18){ defineS = "湖南省";}
		else if(id == R.id.menuProvince19){ defineS = "吉林省";}
		else if(id == R.id.menuProvince20){ defineS = "江苏省";}
		else if(id == R.id.menuProvince21){ defineS = "江西省";}
		else if(id == R.id.menuProvince22){ defineS = "辽宁省";}
		else if(id == R.id.menuProvince23){ defineS = "青海省";}
		else if(id == R.id.menuProvince24){ defineS = "山东省";}
		else if(id == R.id.menuProvince25){ defineS = "山西省";}
		else if(id == R.id.menuProvince26){ defineS = "陕西省";}
		else if(id == R.id.menuProvince27){ defineS = "四川省";}
		else if(id == R.id.menuProvince28){ defineS = "云南省";}
		else if(id == R.id.menuProvince29){ defineS = "浙江省";}
		else if(id == R.id.menuProvince30){ defineS = "西藏";}
		else if(id == R.id.menuProvince31){ defineS = "新疆";}
		else if(id == R.id.menuProvince32){ defineS = "港澳台";}

		
		return defineS;
	}
	public static String getProvinceId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("北京市")){defineS = "1";}
		else if(s.equals("上海市")){defineS = "2";}
		else if(s.equals("天津市")){defineS = "3";}
		else if(s.equals("重庆市")){defineS = "4";}
		else if(s.equals("宁夏")){defineS = "5";}
		else if(s.equals("内蒙古")){defineS = "6";}
		else if(s.equals("安徽省")){defineS = "7";}
		else if(s.equals("福建省")){defineS = "8";}
		else if(s.equals("甘肃省")){defineS = "9";}
		else if(s.equals("广东省")){defineS = "10";}
		else if(s.equals("广西省")){defineS = "11";}
		else if(s.equals("贵州省")){defineS = "12";}
		else if(s.equals("海南省")){defineS = "13";}
		else if(s.equals("河北省")){defineS = "14";}
		else if(s.equals("河南省")){defineS = "15";}
		else if(s.equals("黑龙江")){defineS = "16";}
		else if(s.equals("湖北省")){defineS = "17";}
		else if(s.equals("湖南省")){defineS = "18";}
		else if(s.equals("吉林省")){defineS = "19";}
		else if(s.equals("江苏省")){defineS = "20";}
		else if(s.equals("江西省")){defineS = "21";}
		else if(s.equals("辽宁省")){defineS = "22";}
		else if(s.equals("青海省")){defineS = "23";}
		else if(s.equals("山东省")){defineS = "24";}
		else if(s.equals("山西省")){defineS = "25";}
		else if(s.equals("陕西省")){defineS = "26";}
		else if(s.equals("四川省")){defineS = "27";}
		else if(s.equals("云南省")){defineS = "28";}
		else if(s.equals("浙江省")){defineS = "29";}
		else if(s.equals("西藏")){defineS = "30";}
		else if(s.equals("新疆")){defineS = "31";}
		else if(s.equals("港澳台")){defineS = "32";}

		
		return defineS;
	}

	public static String getMarryDesc(int id) {
		String defineS = "";
		
		if(false){}
		else if(id == R.id.menuIsMarry1){ defineS = "未婚";}
		else if(id == R.id.menuIsMarry2){ defineS = "已婚";}
		else if(id == R.id.menuIsMarry3){ defineS = "丧偶";}
		else if(id == R.id.menuIsMarry4){ defineS = "离异";}

		return defineS;
	}
	public static String getMarryId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("未婚")){defineS = "1";}
		else if(s.equals("已婚")){defineS = "2";}
		else if(s.equals("丧偶")){defineS = "3";}
		else if(s.equals("离异")){defineS = "4";}
		return defineS;
	}
	public static String getMarryDesc(String s) {
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "未婚";}
		else if(s.equals("2")){defineS = "已婚";}
		else if(s.equals("3")){defineS = "丧偶";}
		else if(s.equals("4")){defineS = "离异";}
		else{defineS = "请选择";}


		return defineS;
	}


	public static String getRelationDesc(int id) {
		String defineS = "";
		
		if(false){}
		else if(id == R.id.menuRelation1){ defineS = "夫妻";}
		else if(id == R.id.menuRelation2){ defineS = "父母";}
		else if(id == R.id.menuRelation3){ defineS = "子女";}
		else if(id == R.id.menuRelation4){ defineS = "兄弟姐妹";}
		else if(id == R.id.menuRelation5){ defineS = "祖父母";}
		else if(id == R.id.menuRelation6){ defineS = "外祖父母";}
		else if(id == R.id.menuRelation7){ defineS = "孙子女";}
		else if(id == R.id.menuRelation8){ defineS = "外孙子女";}
		else if(id == R.id.menuRelation9){ defineS = "儿媳和公婆";}
		else if(id == R.id.menuProvince10){ defineS = "女婿和岳父母";}
		else if(id == R.id.menuProvince11){ defineS = "伯伯";}
		else if(id == R.id.menuProvince12){ defineS = "叔叔";}
		else if(id == R.id.menuProvince13){ defineS = "姑母";}
		else if(id == R.id.menuProvince14){ defineS = "舅";}
		else if(id == R.id.menuProvince15){ defineS = "阿姨";}
		else if(id == R.id.menuProvince16){ defineS = "侄子女";}
		else if(id == R.id.menuProvince17){ defineS = "甥子女";}
		else if(id == R.id.menuProvince18){ defineS = "堂兄弟姊妹";}
		else if(id == R.id.menuProvince19){ defineS = "表兄弟姊妹";}

		return defineS;
	}
	public static String getRelationId(String s){
		String defineS = "";
		
		if(false){}
		else if(s.equals("夫妻")){defineS = "1";}
		else if(s.equals("父母")){defineS = "2";}
		else if(s.equals("子女")){defineS = "3";}
		else if(s.equals("兄弟姐妹")){defineS = "4";}
		else if(s.equals("祖父母")){defineS = "5";}
		else if(s.equals("外祖父母")){defineS = "6";}
		else if(s.equals("孙子女")){defineS = "7";}
		else if(s.equals("外孙子女")){defineS = "8";}
		else if(s.equals("儿媳和公婆")){defineS = "9";}
		else if(s.equals("女婿和岳父母")){defineS = "10";}
		else if(s.equals("伯伯")){defineS = "11";}
		else if(s.equals("叔叔")){defineS = "12";}
		else if(s.equals("姑母")){defineS = "13";}
		else if(s.equals("舅")){defineS = "14";}
		else if(s.equals("阿姨")){defineS = "15";}
		else if(s.equals("侄子女")){defineS = "16";}
		else if(s.equals("甥子女")){defineS = "17";}
		else if(s.equals("堂兄弟姊妹")){defineS = "18";}
		else if(s.equals("表兄弟姊妹")){defineS = "19";}
		return defineS;
	}
	public static String getRelationDesc(String s) {
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "夫妻";}
		else if(s.equals("2")){defineS = "父母";}
		else if(s.equals("3")){defineS = "子女";}
		else if(s.equals("4")){defineS = "兄弟姐妹";}
		else if(s.equals("5")){defineS = "祖父母";}
		else if(s.equals("6")){defineS = "外祖父母";}
		else if(s.equals("7")){defineS = "孙子女";}
		else if(s.equals("8")){defineS = "外孙子女";}
		else if(s.equals("9")){defineS = "儿媳和公婆";}
		else if(s.equals("10")){defineS = "女婿和岳父母";}
		else if(s.equals("11")){defineS = "伯伯";}
		else if(s.equals("12")){defineS = "叔叔";}
		else if(s.equals("13")){defineS = "姑母";}
		else if(s.equals("14")){defineS = "舅";}
		else if(s.equals("15")){defineS = "阿姨";}
		else if(s.equals("16")){defineS = "侄子女";}
		else if(s.equals("17")){defineS = "甥子女";}
		else if(s.equals("18")){defineS = "堂兄弟姊妹";}
		else if(s.equals("19")){defineS = "表兄弟姊妹";}

		return defineS;
	}


	public static ContentValues getContentValues(CustomerData data) {
		ContentValues values = new ContentValues();
		
//		values.put(Col_id,data.id);
		values.put(Col_customername,data.customername);
		values.put(Col_idnumber,data.idnumber);
		values.put(Col_sex,data.sex);
		values.put(Col_birthday,data.birthday);
		values.put(Col_socialsecurity,data.socialsecurity);
		values.put(Col_tel,data.tel);
		values.put(Col_mobliephone,data.mobliephone);
		values.put(Col_area,data.area);
		values.put(Col_address,data.address);
		values.put(Col_mail,data.mail);
		values.put(Col_customerimg,data.customerimg);
		values.put(Col_ethnic,data.ethnic);
		values.put(Col_otherethnic,data.otherethnic);
		values.put(Col_education,data.education);
		values.put(Col_province,data.province);
		values.put(Col_work,data.work);
		values.put(Col_householdarea,data.householdarea);
		values.put(Col_householdaddr,data.householdaddr);
		values.put(Col_householdmail,data.householdmail);
		values.put(Col_ismarry,data.ismarry);
		values.put(Col_proxyname,data.proxyname);
		values.put(Col_proxyrelation,data.proxyrelation);
		values.put(Col_proxytel,data.proxytel);
		values.put(Col_proxyphone,data.proxyphone);
		values.put(Col_proxyarea,data.proxyarea);
		values.put(Col_proxyaddr,data.proxyaddr);
		values.put(Col_proxymail,data.proxymail);
		values.put(Col_economicstatus,data.economicstatus);
		values.put(Col_livestatus,data.livestatus);
		values.put(Col_housestatus,data.housestatus);
		values.put(Col_helpstatus,data.helpstatus);
		values.put(Col_helper,data.helper);
		values.put(Col_otherhelper,data.otherhelper);
		values.put(Col_medicalstatus,data.medicalstatus);
		values.put(Col_othermedical,data.othermedical);
		values.put(Col_adduser,data.adduser);
		values.put(Col_addtime,data.addtime);
		values.put(Col_isok,data.isok);
		values.put(Col_assessnum,data.assessnum);
		values.put(Col_firstassessdate,data.firstassessdate);
		values.put(Col_curassesstype,data.curassesstype);
		values.put(Col_curassessstatus,data.curassessstatus);
		values.put(Col_unitid,data.unitid);

		
		return values;
	}
}
	
