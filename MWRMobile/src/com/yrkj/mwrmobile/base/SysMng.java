package com.yrkj.mwrmobile.base;

import android.app.Application;
import android.content.SharedPreferences;

//import com.yrkj.elderlycareassess.bean.AssessUserData;
//import com.yrkj.elderlycareassess.gusturelock.LockPatternView;
//import com.yrkj.elderlycareassess.gusturelock.MainActivity;


public class SysMng {

	public static final boolean IsDebug = true;
//	public static final String TAG_FRAGMENT = "ECA_Fragment";
//	public static final String TAG_UCTRL = "ECA_UCtrl";
//	public static final String TAG_DB = "ECA_DB";
//	public static final String TAG_ASSESS = "ECA_Assess";
//	public static final String TAG_SERVICE = "ECA_Service";
//	public static final String TAG_NET = "ECA_Net";
	
	
	private final static String PREF_NAME = "MWR_pref";
	private final static String PREF_KEY_USERID = "userName";
	public  final static String PREF_KEY_PASSWORD = "password";
	public static final String LOCK = "lock";
	public static final String LOCK_KEY = "lock_key";
	
	private static SharedPreferences mPrefs;
	private static SharedPreferences getPrefs(){
		if(mPrefs == null){
			mPrefs = BaseApplication.getInstance().getSharedPreferences(PREF_NAME, Application.MODE_PRIVATE);
		}
		return mPrefs;
	}
	
//	public static void saveUserInfo(String uid,String pwd){
//		SharedPreferences.Editor editor = getPrefs().edit();
//		editor.putString(PREF_KEY_USERID, uid.trim());
//		editor.putString(PREF_KEY_PASSWORD, pwd);
//		editor.commit();
//	}
//	public static AssessUserData getUserInfo(){
//		AssessUserData d = new AssessUserData();
//		d.UserId = getPrefs().getString(PREF_KEY_USERID, "").trim();
//		d.LocPassword = getPrefs().getString(PREF_KEY_PASSWORD, "");
//		if(d.UserId.isEmpty()){
//			return null;
//		}
//		
//		return d;
//	}
	
//	public static String getLockPattenString(){
//		SharedPreferences preferences = 
//				BaseApplication.getInstance().
//				getSharedPreferences(LOCK, Application.MODE_PRIVATE);
//		return preferences.getString(LOCK_KEY, null);
//	}
//	public static void clearLockPatten(){
//		BaseApplication.getInstance().
//			getSharedPreferences(LOCK, Application.MODE_PRIVATE).edit().clear().commit();
//	}
//	public static void setLockPattenString(String patternString){
//		 SharedPreferences preferences = BaseApplication.getInstance().
//				 getSharedPreferences(
//                 LOCK, Application.MODE_PRIVATE);
//         preferences
//                 .edit()
//                 .putString(LOCK_KEY,
//                		 patternString)
//                 .commit();
//	}
//	
}
