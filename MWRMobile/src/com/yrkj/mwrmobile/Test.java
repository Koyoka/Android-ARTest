package com.yrkj.mwrmobile;

import org.json.JSONException;
import org.json.JSONObject;

import com.yrkj.util.http.HttpMng;

public class Test {

	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("11111");    
		String host = "localhost";
		int port = 15809;
		String url = "Services/MWMobileWSHandler.ashx?a=1";
		url = "http://localhost:15809/Services/MWMobileWSHandler.ashx";
		String accessKey = "9e15f4f7d6fdc178eeab8caf79d863054bdfea78";
		String secretKey = "ae46214f1ee0269f7eb5126895ff166f02ede4f1";
		String body = "";
		
		
		JSONObject jo = new JSONObject();
		try {
			JSONObject subJo = new JSONObject();
			subJo.put("name", "eleven");
			jo.put("action", "test");
			jo.put("value", subJo);
			body = jo.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String result = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		System.out.println(jo.toString() + " " +result + "  cc"); 
		
	}
}
