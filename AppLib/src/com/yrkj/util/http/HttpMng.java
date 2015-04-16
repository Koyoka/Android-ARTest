package com.yrkj.util.http;





//import com.lc.sm.base.Config;

public class HttpMng {
	private static HttpClientHelper mHttpCHelper = null;
	private static HttpClientHelper mHttpCHelperURL = null;
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
	
	public static String doHttpSignature(String host,int port,String url,
			String accessKey,String secretKey,String body){
			
		try {
			return GetHttpClientHelper(host,port).httpSignatureRequest(url, accessKey, secretKey, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String doHttpSignatureURL(String url,
			String accessKey,String secretKey,String body){
			
		try {
			return GetHttpClientHelper().httpSignatureRequestURL(url, accessKey, secretKey, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String doHttpURL(String url,String body){
		
		try {
			return GetHttpClientHelper().httpRequestURL(url,  body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String doHttpReadFileURL(String url){
		try {
			return GetHttpClientHelper().httpReadFileURL(url);
		} catch (Exception e) {
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
	public static HttpClientHelper GetHttpClientHelper(){
		if(mHttpCHelperURL == null){
			mHttpCHelperURL = new HttpClientHelper();
//			mHttpCHelper = new HttpClientHelper(SysConfig.SERVICE_HOST,SysConfig.SERVICE_HTTP_PORT);
		}
		return mHttpCHelperURL;
		
	}
	
//	public static HttpAsyncLoadImg GetHttpAsyLoadImg(){
//		if(mHttpAsyLoadImg == null){
//			mHttpAsyLoadImg = new HttpAsyncLoadImg();
//		}
//		return mHttpAsyLoadImg;
//	}
}
