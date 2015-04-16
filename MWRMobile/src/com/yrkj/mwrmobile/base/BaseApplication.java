package com.yrkj.mwrmobile.base;




import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.yrkj.util.log.DLog;

public class BaseApplication extends Application {
	
	public static final String Servive_Host = "192.168.1.105";
//	public static final String Service_UR1L = "http://192.168.1.201/Services/MWMobileWSHandler.ashx";
	public static void setSerciverUrlHandler(String s){
		
		mUrl = s;
	}
	private static String mUrl = null;//"/Services/MWMobileWSHandler.ashx";
	public static String getServiceUrl(){
		
		return "http://" + Servive_Host +"/"+ (mUrl==null?"":mUrl);
	}
	public static String getServiceConfig(){
		return "http://" + Servive_Host + "/Services/config.html";
		
	}
	
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
			
		
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		InitLocation();
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
			mLocation = location;
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
			logMsg(sb.toString());
		}
	}
	
	public void logMsg(String str) {
		try {
			if (locationTextView != null)
				locationTextView.setText(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TextView locationTextView = null;
	public BDLocation mLocation = null;
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor="gcj02";
	private void InitLocation(){
		
		tempMode = LocationMode.Hight_Accuracy;
//		tempMode = LocationMode.Battery_Saving;
//		tempMode = LocationMode.Device_Sensors;
		
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(false);
		mLocationClient.setLocOption(option);
	}
	
	
}
