package com.yrkj.util.basedao;

import android.text.TextUtils;

public abstract class BaseBean {
	protected String report_type = "";
	protected String report_text = "";
	
	protected boolean checkJson(String json){
		if(TextUtils.isEmpty(json) || json.toUpperCase().equals("NULL") || json.toUpperCase().equals("[NULL]")){
			return false;
		}
		return true;
	}
	
	public abstract String getData(String path);
	
	protected boolean getBeanFiledPath(String path,String filedName){
		if(path.equals(filedName)){
			return true;
		}
		
		if(path.indexOf("/") != -1 &&
				path.indexOf(filedName,0) == 0){
			return true;
		}
		return false;
	}
	
	protected String getBeanChildPath(String path){
		if(path.indexOf("/",0)+1 > path.length()){
			return "";
		}
		return path.substring(path.indexOf("/",0)+1, path.length());
	}
}
