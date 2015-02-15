package com.yrkj.mwrmobile.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.base.MWRBaseDBMng;
import com.yrkj.mwrmobile.bean.InitWSBody;
import com.yrkj.mwrmobile.bean.RequestBody;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.http.HttpMng;


public class BaseDataDao {

	public static ArrayList<VendorData> getVendorList(Context c){
		MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
		
		DBCondition cdt = new DBCondition();
//		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(VendorData.TblName, 
				VendorData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<VendorData> itemList = new ArrayList<VendorData>();
		if(cursor.moveToFirst()){
			do {
				VendorData item = VendorData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static ArrayList<WasteCategoryData> getWasterList(Context c){
		MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
		
		DBCondition cdt = new DBCondition();
//		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(WasteCategoryData.TblName, 
				WasteCategoryData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<WasteCategoryData> itemList = new ArrayList<WasteCategoryData>();
		if(cursor.moveToFirst()){
			do {
				WasteCategoryData item = WasteCategoryData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static String RegistWS(Context c,String url,String wsCode,String accessKey,String secretKey,Handler handler){
		
		Message msg = new Message();
		
		InitWSBody mBody = new InitWSBody();
		mBody.wsCode = wsCode;
		mBody.accessKey = accessKey;
		mBody.secretKey = secretKey;
		
		RequestBody rBody = new RequestBody();
		rBody.action = "InitMWSSubmit";
		rBody.value = mBody;
		
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		
		return resultStr;
	}
	
	
}