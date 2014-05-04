package com.yrkj.elderlycareassess.base;



import android.app.Application;

public class BaseApplication extends Application {
	
	private static BaseApplication mApplication = null;
	
	public static BaseApplication getInstance(){
		return mApplication;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		mApplication = this;
//		SysMng.Launch(this);
	}
	
	
}
