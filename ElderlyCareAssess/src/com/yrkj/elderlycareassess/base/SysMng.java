package com.yrkj.elderlycareassess.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.yrkj.elderlycareassess.bean.AssessUserData;


public class SysMng {

	public static final boolean IsDebug = true;
	public static final String TAG_FRAGMENT = "ECA_Fragment";
	public static final String TAG_UCTRL = "ECA_UCtrl";
	public static final String TAG_DB = "ECA_DB";
	public static final String TAG_ASSESS = "ECA_Assess";
	
	private final static String PREF_NAME = "ECA_pref";
	private final static String PREF_KEY_USERID = "userName";
	private final static String PREF_KEY_PASSWORD = "password";
	
	private static SharedPreferences mPrefs;
	private static SharedPreferences getPrefs(){
		if(mPrefs == null){
			mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		}
		return mPrefs;
	}
	
	public static void saveUserInfo(String uid,String pwd){
		SharedPreferences.Editor editor = getPrefs().edit();
		editor.putString(PREF_KEY_USERID, uid.trim());
		editor.putString(PREF_KEY_PASSWORD, pwd);
		editor.commit();
	}
	public static AssessUserData getUserInfo(){
		AssessUserData d = new AssessUserData();
		d.UserId = getPrefs().getString(PREF_KEY_USERID, "").trim();
		d.LocPassword = getPrefs().getString(PREF_KEY_PASSWORD, "");
		if(d.UserId.isEmpty()){
			return null;
		}
		
		return d;
	}
	
}
