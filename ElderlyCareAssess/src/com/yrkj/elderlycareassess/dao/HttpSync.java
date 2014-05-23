package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.util.http.CustomMultipartEntity.HttpProgressListener;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.log.DLog;

public class HttpSync {
	static boolean  debug = false;
	private static String host = debug?"192.168.1.101":"121.199.17.68";
	private static int port = debug?8080:80;
	private static String mUrl = "tymk/interface_getClientMessage.do";

	public static String doHttp(String url, HttpRequestValue mReqGetValues,
			HttpRequestValue mReqPostValues, HttpRequestValue mReqFileValues,HttpProgressListener l)
			throws Exception {

		return HttpMng.GetHttpClientHelper(host, port).FilePostRequest(url,
				mReqGetValues, mReqPostValues, mReqFileValues,l);

	}
	
	
	public static boolean uploadAssessTask(Context c,String assessJsonString,HttpProgressListener l){
		DLog.LOG(SysMng.TAG_NET,"uploadAssessTask 1--------");
		DLog.LOG(SysMng.TAG_NET,assessJsonString);
		try {
			HttpRequestValue v = new HttpRequestValue();
			v.Add("method", "cmttask");
			v.Add("json", assessJsonString);
			String jsonStr = doHttp(mUrl,null,v,null,l);
			DLog.LOG(SysMng.TAG_NET,"uploadAssessTask 2--------"+jsonStr);
			JSONBean b = JSONBean.getInstance(jsonStr);
			if(!b.IsSuccess()){
				return false;
			}
			JSONObject jo = new JSONObject(b.body);
			if(jo.getString("status").equals("0")){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			DLog.LOG(SysMng.TAG_NET,"uploadAssessTask err --------"+e.getMessage());
			return false;
		}
		
//		return true;
	}
	
	public static boolean backAssessTask(Context c,String userId,String netTaskHeaderId){
		
		// ²ÎÊý assessid, username
//		{"success":"0","errormessage":"","body":{"status":"-1","statusmessage":"assess is null"}}
		
		try {
			HttpRequestValue v = new HttpRequestValue();
			v.Add("method", "backtask");
			v.Add("username", userId);
			v.Add("assessid", netTaskHeaderId);
			String jsonStr = doHttp(mUrl,null,v,null,null);
			DLog.LOG(SysMng.TAG_NET,"backAssessTask 3--------"+jsonStr+" "+netTaskHeaderId+" "+userId);
			JSONBean b = JSONBean.getInstance(jsonStr);
			if(!b.IsSuccess()){
				return false;
			}
			
			JSONObject jo = new JSONObject(b.body);
			if(jo.getString("status").equals("0")){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean downLoadAssessTask(Context c,String userId) {
		
		try {
			
			ArrayList<AssessTaskHeaderData> itemList =
					AssessDBCtrl.getAllAssessTaskList(c);
			String assessid = "-1";
			for (AssessTaskHeaderData item : itemList) {
				assessid += ","+item.NetTaskHeaderId;
			}
			
			HttpRequestValue v = new HttpRequestValue();
			v.Add("method", "gettaskinfo");
			v.Add("username", userId);
			v.Add("assessid", assessid);
			
			String jsonStr = doHttp(mUrl,null,v,null,null);
			DLog.LOG(SysMng.TAG_NET,"downLoadAssessTask 3--------"+jsonStr);
			JSONBean b = JSONBean.getInstance(jsonStr);
			if(!b.IsSuccess()){
				return false;
			}

			JSONArray jay = new JSONArray(b.body);
			for(int i = 0; i<jay.length();i++){
				JSONObject b1 = jay.getJSONObject(i);
				AssessTaskHeaderData dataAssess =
						JsonAssess.convertToModule(b1.getString("assess"));
				dataAssess.AssessType = AssessTaskHeaderData.ASSESS_TYPE_FIRST;
				dataAssess.AssessState = AssessTaskHeaderData.ASSESS_STATE_DOING;
				dataAssess.NeedSync = false;
				
				CustomerData cust = 
						JsonCustomer.convertToModule(b1.getString("customer"));
				
				AssessDBCtrl.insertCustomer(c, cust);
				AssessDBCtrl.insertAssessTaskHeader(c, dataAssess);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return false;
	}
	
	public static boolean userNetLogin(Context c,String userId,String password){
//		DLog.LOG(SysMng.TAG_NET,"1--------");
		try {
			ArrayList<AssessTaskHeaderData> itemList =
					AssessDBCtrl.getAllAssessTaskList(c);
			String assessid = "-1";
			for (AssessTaskHeaderData item : itemList) {
				assessid += ","+item.NetTaskHeaderId;
			}
//			DLog.LOG(SysMng.TAG_NET,"2--------");
			HttpRequestValue v = new HttpRequestValue();
			v.Add("method", "getuserinfo");
			v.Add("username", userId);
			v.Add("password", password);
			v.Add("assessid", assessid);
			
			String jsonStr = doHttp(mUrl,null,v,null,null);
//			DLog.LOG(SysMng.TAG_NET,"3--------"+jsonStr);
			JSONBean b = JSONBean.getInstance(jsonStr);
//			DLog.LOG(SysMng.TAG_NET,"4--------"+b.IsSuccess());
			if(!b.IsSuccess()){
				return false;
			}
			
			
			JsonUserBean user = JsonUserBean.getInstance(b.body);
			
			AssessUserData d = new AssessUserData();
//			DLog.LOG(SysMng.TAG_NET,"4.1--------"+user);
			if(user != null && user.islogin.equals("1")){
				d.UserId = user.loginname;
				d.LocPassword = password;
				d.OfficeAddress = user.unitname;
				d.Office = user.rolename;
				d.UserName = user.realname;
				d.Taskcount = user.taskcount;
				d.Cmtcount = user.cmtcount;
				AssessUserDBCtrl.insertUser(c, d);
//				DLog.LOG(SysMng.TAG_NET,"5--------");
				return true;
			}else{
//				DLog.LOG(SysMng.TAG_NET,"6--------");
				return false;
			}
			
		} catch (Exception e) {
			DLog.LOG(SysMng.TAG_NET,"err "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static  class  JSONBean{
		public String success = "";
		public String errormessage = "";
		public String body = "";
		
		public boolean IsSuccess(){
			if(success.equals("0")){
				return true;
			}
			return false;
		}
		
		public static JSONBean getInstance(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					JSONBean b = new JSONBean();
					b.success = jo.getString("success");
					b.errormessage = jo.getString("errormessage");
					b.body = jo.getString("body");
					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	public static class JsonUserBean{
		
		public String realname = "";
		public String loginname = "";
		public String unitname = "";
		public String rolename = "";
		public String taskcount = "";
		public String cmtcount = "";
		public String islogin = "";	
		public String userimg = "";
		
		public static JsonUserBean getInstance(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					JsonUserBean b = new JsonUserBean();
					b.realname = jo.getString("realname");
					b.loginname = jo.getString("loginname");
					b.unitname = jo.getString("unitname");
					b.rolename = jo.getString("rolename");
					b.taskcount = jo.getString("taskcount");
					b.cmtcount = jo.getString("cmtcount");
					b.islogin = jo.getString("islogin");
//					b.userimg = jo.getString("userimg");
					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}
	}
	
	public static class JsonAssess{
		public String assessnum = "";
		public String assessid = "";
		public String custid = "";
		public String createtime = "";
		public String endassesstime = "";
		public String lastassesstime = "";
		public String assesstype = "";


		public static JsonAssess getInstance(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					JsonAssess b = new JsonAssess();
					b.assessnum = jo.getString("assessnum");
					b.assessid = jo.getString("assessid");
					b.custid = jo.getString("custid");
					b.createtime = jo.getString("createtime");
					b.endassesstime = jo.getString("endassesstime");
					b.lastassesstime = jo.getString("lastassesstime");
					b.assesstype = jo.getString("assesstype");
					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public static AssessTaskHeaderData convertToModule(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					AssessTaskHeaderData b = new AssessTaskHeaderData();
					b.AssessNum = jo.getString("assessnum");
					b.NetTaskHeaderId = jo.getString("assessid");
					b.CustId = jo.getString("custid");
					b.CreateDate = jo.getString("createtime");
					b.EndAssessDate = jo.getString("endassesstime");
					b.LastAssessDate = jo.getString("lastassesstime");
					b.AssessType = jo.getString("assesstype");

					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;

		}	
	}
	
	public static class JsonCustomer{
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
		
		public static JsonCustomer getInstance(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					JsonCustomer b = new JsonCustomer();
					b.id = jo.getString("id");
					b.customername = jo.getString("customername");
					b.idnumber = jo.getString("idnumber");
					b.sex = jo.getString("sex");
					b.birthday = jo.getString("birthday");
					b.socialsecurity = jo.getString("socialsecurity");
					b.tel = jo.getString("tel");
					b.mobliephone = jo.getString("mobliephone");
					b.area = jo.getString("area");
					b.address = jo.getString("address");
					b.mail = jo.getString("mail");
					b.customerimg = jo.getString("customerimg");
					b.ethnic = jo.getString("ethnic");
					b.otherethnic = jo.getString("otherethnic");
					b.education = jo.getString("education");
					b.province = jo.getString("province");
					b.work = jo.getString("work");
					b.householdarea = jo.getString("householdarea");
					b.householdaddr = jo.getString("householdaddr");
					b.householdmail = jo.getString("householdmail");
					b.ismarry = jo.getString("ismarry");
					b.proxyname = jo.getString("proxyname");
					b.proxyrelation = jo.getString("proxyrelation");
					b.proxytel = jo.getString("proxytel");
					b.proxyphone = jo.getString("proxyphone");
					b.proxyarea = jo.getString("proxyarea");
					b.proxyaddr = jo.getString("proxyaddr");
					b.proxymail = jo.getString("proxymail");
					b.economicstatus = jo.getString("economicstatus");
					b.livestatus = jo.getString("livestatus");
					b.housestatus = jo.getString("housestatus");
					b.helpstatus = jo.getString("helpstatus");
					b.helper = jo.getString("helper");
					b.otherhelper = jo.getString("otherhelper");
					b.medicalstatus = jo.getString("medicalstatus");
					b.othermedical = jo.getString("othermedical");
					b.adduser = jo.getString("adduser");
					b.addtime = jo.getString("addtime");
					b.isok = jo.getString("isok");
					b.assessnum = jo.getString("assessnum");
					b.firstassessdate = jo.getString("firstassessdate");
					b.curassesstype = jo.getString("curassesstype");
					b.curassessstatus = jo.getString("curassessstatus");
					b.unitid = jo.getString("unitid");


					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		public static CustomerData convertToModule(String s){
			if(s!=null && !s.isEmpty()){
				JSONObject jo;
				try {
					jo = new JSONObject(s);
					CustomerData b = new CustomerData();
					b.id = jo.getString("id");
					b.customername = jo.getString("customername");
					b.idnumber = jo.getString("idnumber");
					b.sex = jo.getString("sex");
					b.birthday = jo.getString("birthday");
					b.socialsecurity = jo.getString("socialsecurity");
					b.tel = jo.getString("tel");
					b.mobliephone = jo.getString("mobliephone");
					b.area = jo.getString("area");
					b.address = jo.getString("address");
					b.mail = jo.getString("mail");
					b.customerimg = jo.getString("customerimg");
					b.ethnic = jo.getString("ethnic");
					b.otherethnic = jo.getString("otherethnic");
					b.education = jo.getString("education");
					b.province = jo.getString("province");
					b.work = jo.getString("work");
					b.householdarea = jo.getString("householdarea");
					b.householdaddr = jo.getString("householdaddr");
					b.householdmail = jo.getString("householdmail");
					b.ismarry = jo.getString("ismarry");
					b.proxyname = jo.getString("proxyname");
					b.proxyrelation = jo.getString("proxyrelation");
					b.proxytel = jo.getString("proxytel");
					b.proxyphone = jo.getString("proxyphone");
					b.proxyarea = jo.getString("proxyarea");
					b.proxyaddr = jo.getString("proxyaddr");
					b.proxymail = jo.getString("proxymail");
					b.economicstatus = jo.getString("economicstatus");
					b.livestatus = jo.getString("livestatus");
					b.housestatus = jo.getString("housestatus");
					b.helpstatus = jo.getString("helpstatus");
					b.helper = jo.getString("helper");
					b.otherhelper = jo.getString("otherhelper");
					b.medicalstatus = jo.getString("medicalstatus");
					b.othermedical = jo.getString("othermedical");
					b.adduser = jo.getString("adduser");
					b.addtime = jo.getString("addtime");
					b.isok = jo.getString("isok");
					b.assessnum = jo.getString("assessnum");
					b.firstassessdate = jo.getString("firstassessdate");
					b.curassesstype = jo.getString("curassesstype");
					b.curassessstatus = jo.getString("curassessstatus");
					b.unitid = jo.getString("unitid");
					
					
					return b;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		
	}
	
	
}














