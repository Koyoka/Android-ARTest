package com.yrkj.mwrmobile.base;



import com.yrkj.util.log.DLog;

import android.app.Application;

public class BaseApplication extends Application {
	
	public static final String Service_URL = "http://192.168.1.104/Services/MWMobileWSHandler.ashx";
	
	private static BaseApplication mApplication = null;
	
	public static BaseApplication getInstance(){
		return mApplication;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DLog.LOGTAG = "MWR";
		mApplication = this;
		if(!MWRDBMng.init(this,null/*SysMng.IsDebug*/)){
			DLog.LOG("create db error [MWRDBMng]");
		}else{
//			sys_DBCreated = true;
		}
		
		if(!MWRBaseDBMng.init(this, null)){
			DLog.LOG("create db error [MWRBaseDBMng]");
		}else{
			
		}
			
		
		
		
//		String carCode, driver, driverName, inspector, inspectorName, wsCode;
//		//YG0001 YG0005 MWS001 ��A00001
//		carCode = "A00001";
//		driver = "YG0001";
//		driverName = "��3-˾��";
//		inspector = "YG0005";
//		inspectorName = "��3-��";
//		wsCode = "MWS001";
//		SysMng.saveWSInfo(wsCode,"HX###");
//		SysMng.saveTxnInfo(carCode,driver, driverName, inspector, inspectorName);
//		SysMng.Launch(this);
	}
	
	
}
