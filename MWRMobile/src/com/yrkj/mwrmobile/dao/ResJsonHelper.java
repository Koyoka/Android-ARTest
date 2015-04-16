package com.yrkj.mwrmobile.dao;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
import com.yrkj.mwrmobile.bean.response.ResponseConfigBody;
import com.yrkj.mwrmobile.bean.response.ResponseInitMWSSubmitBody;

public class ResJsonHelper {
	public static ResponseConfigBody getConfigBodyFromJson(String s){
		
		if(s.equals("")){
			return null;
		}
		
		try{
			Gson gs =  new Gson();
			ResponseConfigBody body = gs.fromJson(s,ResponseConfigBody.class);
			return body;
		}catch(Exception ex){
			return null;
		}
	}
	
	public static ResponseBody getBodyFromJson(String s){
		if(s.equals("")){
			return null;
		}
		
		try{
			Gson gs =  new Gson();
			ResponseBody body = gs.fromJson(s,ResponseBody.class);
		return body;
		}catch(Exception ex){
			return null;
		}
	}
	
	public static ResponseInitMWSSubmitBody getInitBodyFromJson(String s){
		if(s.equals("")){
			return null;
		}
		
		try{
			Gson gs =  new Gson();
			ResponseInitMWSSubmitBody body = gs.fromJson(s,ResponseInitMWSSubmitBody.class);
		return body;
		}catch(Exception ex){
			return null;
		}
	}
	
	public static <T> T ConventJsonToBody(String s,Class<T> t){
		if(s.equals("")){
			return null;
		}
		
		try{
			Gson gs =  new Gson();
			T body = gs.fromJson(s,t);
		return body;
		}catch(Exception ex){
			return null;
		}
	}
	
}
