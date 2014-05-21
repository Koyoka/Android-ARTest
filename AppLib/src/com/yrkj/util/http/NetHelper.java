package com.yrkj.util.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetHelper {

	public static final int CMNET = 3;
	public static final int CMWAP = 2;
	public static final int WIFI = 1;

	/**
	 * @author sky Email 获取当前的网络状态 -1：没有网络
	 *         1：WIFI网络2：wap网络3：net网络
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			// Log.e("networkInfo.getExtraInfo()",
			// "networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo());
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {

				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}
		return netType;
	}

}

/*
 @Override 
public void onReceive(Context context, Intent intent) { 
Log.e(TAG, "网络状态改变"); 
boolean success = false; 
//获得网络连接服务 
ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE); 
// State state = connManager.getActiveNetworkInfo().getState(); 
State state = connManager.getNetworkInfo( 
ConnectivityManager.TYPE_WIFI).getState(); // 获取网络连接状态 
if (State.CONNECTED == state) { // 判断是否正在使用WIFI网络 
success = true; 
} 
state = connManager.getNetworkInfo( 
ConnectivityManager.TYPE_MOBILE).getState(); // 获取网络连接状态 
if (State.CONNECTED != state) { // 判断是否正在使用GPRS网络 
success = true; 
} 
if (!success) { 
Toast.makeText(LocationMapActivity.this, "您的网络连接已中断", Toast.LENGTH_LONG).show(); 
} 
}
 * 
 * */
