package com.yrkj.artaskgame.base;


import java.util.Date;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;

import com.yrkj.artaskgame.acty.qcar.QCARInitActivityActy;
import com.yrkj.artaskgame.cmobile.acty.LaunchLiuActivity;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.log.DLog;
import com.yrkj.util.sys.SysHelper;


public class SysMng {

	private final static String PREF_NAME = "SmokingPref";
	private final static String PREF_KEY_FISTERBOOT = "firstBoot";
	private final static String PREF_KEY_DRIVERID = "driverId";
	private final static String PREF_KEY_USERNAME = "userName";
	private final static String PREF_KEY_FIRSTTASK = "firstTask";
	private final static String PREF_KEY_CURRENTID = "currentTaskId";
	
	private static SharedPreferences mPrefs;
	public static Boolean sys_firstBoot = true;
	public static Boolean sys_DBCreated = false;
	public static String sys_DriverId = "";
	public static String sys_appversion = "1";
	public static String sys_moodversion = "1";
	public static double sys_Latitude = 0;
	public static double sys_Longitude = 0;
	
	public static String sys_UserName = "userName";
//	public static boolean sys_hasBeenTracking = false;
//	private static BaiduLocationHelper mDBLoaction;
	
//	public static String biz_currentTaskId1 = "";
	
	public static void OnDeInit(){
		
//		mDBLoaction.stop();
	}
	
	public static void OnInit(){
		
//		mDBLoaction.start();
	}
	
	public static void closeApp(Context c){
		Intent intent = new Intent(c,LaunchLiuActivity.class);
//		Intent intent = new Intent(c,QCARInitActivityActy.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(LaunchLiuActivity.INTENT_KEY_CLOSEAPP, true);
		c.startActivity(intent);
	}
	
	public static void reInit(){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_USERNAME, "");
		editor.putBoolean(PREF_KEY_FIRSTTASK, false);
		editor.putString(PREF_KEY_CURRENTID, "");
		editor.commit();
//		clearCurrentId();
	}
	
	public static void setCurrentId(String id){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_CURRENTID, id);
		editor.commit();
	}
	public static String getCurrentId(){
		return getPrefs().getString(PREF_KEY_CURRENTID, "");
	}
	public static void clearCurrentId(){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_CURRENTID, "");
		editor.commit();
	}
	
	public static void setUserName(String userName){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_USERNAME, userName);
		editor.commit();
	}
	public static boolean hasSaveUserName(){
		String defineUserName = getPrefs().getString(PREF_KEY_USERNAME, "");
		if(defineUserName.equals("")){
			return false;
		}
		return true;
	}
	public static String getUserName(){
		String defineUserName = getPrefs().getString(PREF_KEY_USERNAME, "");
		return defineUserName;
	}
	
	
	public static void finishFirstTask(){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putBoolean(PREF_KEY_FIRSTTASK, true);
		editor.commit();
		
	}
	public static boolean hasBeenFinishFirstTask(){
		boolean bl = false;
		bl = getPrefs().getBoolean(PREF_KEY_FIRSTTASK, false);
		return bl;
	}
	
	private static SharedPreferences getPrefs(){
		if(mPrefs == null){
			mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		}
		return mPrefs;
	}
	
	public static String getDriverId(){
		return getPrefs().getString(PREF_KEY_DRIVERID, "");
	}
	
	
	public static void Launch(Context c){
		mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		
		sys_firstBoot = getPrefs().getBoolean(PREF_KEY_FISTERBOOT, true);
		
		sys_DriverId = getPrefs().getString(PREF_KEY_DRIVERID, "");
		DLog.LOG("sys_DriverId = " + sys_DriverId + " sys_firstBoot = " + sys_firstBoot);
		if(sys_DriverId.isEmpty()){
			setDriverId(c);
		}
		
		if(!TaskGameDBMng.init(c,false)){
			DLog.LOG("create db error");
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
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putBoolean(PREF_KEY_FISTERBOOT, false);
		editor.commit();
	}
	
	public static void setDriverId(Context c){
		sys_DriverId = SysHelper.getDeviceUuid(c).toString();
		SharedPreferences.Editor editor = getPrefs().edit();
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
