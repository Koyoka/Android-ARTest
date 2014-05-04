package com.yrkj.util.ui.layout;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

import com.yrkj.util.http.HttpAsyncLoadImg;

public class LayoutDataAdapter {
	public HttpAsyncLoadImg mHttpAsyncLoadImg = null;
	public Activity mActy = null; 
	public LayoutDataAdapter(Activity acty){
		mHttpAsyncLoadImg = new HttpAsyncLoadImg();
		mActy = acty;
	}
	
	public Map<Integer, String> BindFiledList = null;//new HashMap<Integer, String>();
	public Map<Integer,JoinData> BindJoinFiledList = null;
	public class JoinData{
		public String formatString = "";
		public String type = "";
		public String[] data = null;
	}
	
	public void add(int id,String data){
		if(BindFiledList == null){
			BindFiledList = new HashMap<Integer, String>();
		}
		BindFiledList.put(id, data);
	}
	
	public void addJoin(int id,String format,String... data){
		if(BindJoinFiledList == null){
			BindJoinFiledList = new HashMap<Integer, JoinData>();
		}
		JoinData joinData = new JoinData();
		joinData.formatString = format;
		joinData.data = data;
		BindJoinFiledList.put(id, joinData);
	}
}
