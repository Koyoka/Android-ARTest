package com.yrkj.mwrmobile.base;




import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.yrkj.util.log.DLog;

import android.app.Application;
import android.util.Log;

public class BaseApplication extends Application {
	
	public static final String Service_URL = "http://192.168.1.201/Services/MWMobileWSHandler.ashx";
	
	private static BaseApplication mApplication = null;
	public MyLocationListener mMyLocationListener;
	public LocationClient mLocationClient;
	
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
			
		mLocationClient.registerLocationListener(mMyLocationListener);
		
		
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
	
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				//运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
//			logMsg(sb.toString());
			Log.i("BaiduLocationApiDem", sb.toString());
		}


	}
	
	
	
	
}
