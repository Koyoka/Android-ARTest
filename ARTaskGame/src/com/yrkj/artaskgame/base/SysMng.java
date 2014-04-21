package com.yrkj.artaskgame.base;


import java.util.Date;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationListener;

import com.yrkj.util.date.DateHelper;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.log.DebugLog;
import com.yrkj.util.sys.SysHelper;


public class SysMng {

	private final static String PREF_NAME = "SmokingPref";
	private final static String PREF_KEY_FISTERBOOT = "firstBoot";
	private final static String PREF_KEY_DRIVERID = "driverId";
	private final static String PREF_KEY_USERNAME = "userName";
	private final static String PREF_KEY_FIRSTTASK = "firstTask";
	
	private static SharedPreferences mPrefs;
	public static Boolean sys_firstBoot = true;
	public static Boolean sys_DBCreated = false;
	public static String sys_DriverId = "";
	public static String sys_appversion = "1";
	public static String sys_moodversion = "1";
	public static double sys_Latitude = 0;
	public static double sys_Longitude = 0;
	
	public static String sys_UserName = "userName";
	public static boolean sys_firstTask = false;
	public static boolean sys_closeApp = false;
//	public static boolean sys_hasBeenTracking = false;
//	private static BaiduLocationHelper mDBLoaction;
	
	public static String biz_currentTaskId = "";
	
	public static void OnDeInit(){
		
//		mDBLoaction.stop();
	}
	
	public static void OnInit(){
		
//		mDBLoaction.start();
	}
	
	public static void setUserName(String userName){
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(PREF_KEY_USERNAME, userName);
		editor.commit();
	}
	public static boolean hasSaveUserName(){
		String defineUserName = mPrefs.getString(PREF_KEY_USERNAME, "");
		if(defineUserName.equals("")){
			return false;
		}
		return true;
	}
	
	public static void finishFirstTask(){
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(PREF_KEY_FIRSTTASK, true);
		editor.commit();
		
	}
	public static boolean hasBeenFinishFirstTask(){
		boolean bl = false;
		bl = mPrefs.getBoolean(PREF_KEY_FIRSTTASK, false);
		return bl;
	}
	
	
	public static void Launch(Context c){
		mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		
		sys_firstBoot = mPrefs.getBoolean(PREF_KEY_FISTERBOOT, true);
		
		sys_DriverId = mPrefs.getString(PREF_KEY_DRIVERID, "");
		DebugLog.LOG("sys_DriverId = " + sys_DriverId + " sys_firstBoot = " + sys_firstBoot);
		if(sys_DriverId.isEmpty()){
			setDriverId(c);
		}
		
		if(!TaskGameDBMng.init(c,false)){
			DebugLog.LOG("create db error");
		}else{
			sys_DBCreated = true;
		}
		
//		if(mDBLoaction == null){
//			
//			mDBLoaction = new BaiduLocationHelper();
//			mDBLoaction.InitBDLocation(c.getApplicationContext(), new LocationListener() {
//
//				@Override
//				public void onLocationChanged(double lat, double lng) {
//					// TODO Auto-generated method stub
//					sys_Latitude = lat;
//					sys_Longitude = lng;
//				}
//			});
//		}
		
	}
	
	public static void HasBeenFirstBoot(){
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(PREF_KEY_FISTERBOOT, false);
		editor.commit();
	}
	
	public static void setDriverId(Context c){
		sys_DriverId = SysHelper.getDeviceUuid(c).toString();
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(PREF_KEY_DRIVERID, sys_DriverId);
		editor.commit();
	}
	
	public static void UpdateSystemBaseData(){
		HttpRequestValue value = new HttpRequestValue();
		value.Add("method", "");
		value.Add("userid", SysMng.sys_DriverId);
		Date now = new Date() ;
		value.Add("date", DateHelper.getDateStr(now));
		value.Add("appversion", sys_appversion);
		value.Add("moodversion", sys_moodversion);
		
		String result = HttpMng.doHttp("", value);
		
//		HttpMng.GetHttpClientHelper().
	}
	
}