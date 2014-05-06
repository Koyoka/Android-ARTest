package com.yrkj.util.http;





//import com.lc.sm.base.Config;

public class HttpMng {
	private static HttpClientHelper mHttpCHelper = null;
//	private static HttpAsyncLoadImg mHttpAsyLoadImg = null;
	
	protected HttpRequestValue mReqValues = null;
	protected HttpRequestValue mReqPostValues = null;
	protected HttpRequestValue mReqFileValues = null;
	
	public static String doHttp(String host,int port,String url,HttpRequestValue value){
		
		try {
			return GetHttpClientHelper(host,port).GetRequest(url, value.GetValus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static HttpClientHelper GetHttpClientHelper(String host,int port){
		if(mHttpCHelper == null){
			mHttpCHelper = new HttpClientHelper(host,port);
//			mHttpCHelper = new HttpClientHelper(SysConfig.SERVICE_HOST,SysConfig.SERVICE_HTTP_PORT);
			
			
		}
		return mHttpCHelper;
		
	}
	
//	public static HttpAsyncLoadImg GetHttpAsyLoadImg(){
//		if(mHttpAsyLoadImg == null){
//			mHttpAsyLoadImg = new HttpAsyncLoadImg();
//		}
//		return mHttpAsyLoadImg;
//	}
}
