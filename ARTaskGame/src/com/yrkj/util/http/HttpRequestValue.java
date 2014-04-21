package com.yrkj.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequestValue {
	
	private List<NameValuePair> mParams =null;
	public HttpRequestValue(){
		mParams = new ArrayList<NameValuePair>();
//		mFileParams = new HashMap<String, InputFileObj>();
	}
	
	public List<NameValuePair> GetValus(){
		return mParams;
	}
	public Map<String,InputFileObj> GetFileValues(){
		return mFileParams;
	}
	
	public Map<String,InputFileObj> mFileParams = null;
	
	public void Add(String key,String value)/* throws UnsupportedEncodingException*/{
//		String encodeVal = "";
//		try {
//			encodeVal = URLEncoder.encode(value, HttpClientHelper.CHARSET);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(mParams==null){
//			mParams = new ArrayList<NameValuePair>();
//		}
//		mParams.add(new BasicNameValuePair(key,encodeVal));
		mParams.add(new BasicNameValuePair(key,value));
	}
	
//	public void AddURLEncoder(String key,String value){
//		String encodeVal = "";
//		try {
//			encodeVal = URLEncoder.encode(value, HttpClientHelper.CHARSET);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mParams.add(new BasicNameValuePair(key,value));
//		
//	}
	
//	public class InputFileObj{
//		public InputFileObj(String fileName,InputStream is){
//			FileName = fileName;
//			FileIS = is;
//		}
//		public String FileName = "";
//		public InputStream FileIS = null;
//	}
	
//	public static InputFileObj createInFileObj(String fileName,InputStream is){
//		return new InputFileObj(fileName,is);
//	}
	
	public void AddFile(String key,InputFileObj file){
		if(mFileParams == null){
			mFileParams = new HashMap<String, InputFileObj>();
		}
		
		mFileParams.put(key, file);
//		mFileParams.
//		Iterator it = mFileParams.entrySet().iterator();  
//		
//		for (Map.Entry<String, File> entry : mFileParams.entrySet()) {
//
//			String k = entry.getKey().toString();
//
//			String v = entry.getValue().toString();
//
//			System.out.println("key=" + k + " value=" + v);
//
//		}
	}
	
	
}
