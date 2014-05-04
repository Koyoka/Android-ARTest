package com.yrkj.util.basedao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class BeanSon<T> {
	public abstract T convertJsonToBean(JSONObject jsonObj)throws Exception ;
	public ArrayList<T> convertJsonToArrayBean(JSONArray jsonAry) throws Exception{
		if (jsonAry == null) {
			return new ArrayList<T>();
		}

		ArrayList<T> tempDataList = new ArrayList<T>();

		for (int i = 0; i < jsonAry.length(); i++) {
			JSONObject jsonObj = jsonAry.getJSONObject(i);
			tempDataList.add(convertJsonToBean(jsonObj));
		}

		return tempDataList;
	}
}
