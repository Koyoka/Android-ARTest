package com.yrkj.mwrmobile.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class WasteCategoryData {

	public static final String TblName = "MWWasteCategory";
	
	public static final String Col_WasteCode = "WasteCode";
	public static final String Col_Waste = "Waste";
	
	public static String[] getColumnColl(){
		return new String[]{
				Col_WasteCode,
				Col_Waste
				};
	}
	
	public String WasteCode = "";
	public String Waste = "";

	public static WasteCategoryData convertDataToModule(Cursor c){
		WasteCategoryData item = new WasteCategoryData();
		item.WasteCode = DBMng.GetDataString(c, Col_WasteCode);
		item.Waste = DBMng.GetDataString(c, Col_Waste);

		return item;
	}
	
	public static ContentValues getContentValues(WasteCategoryData data){
		ContentValues values = new ContentValues();
		values.put(Col_WasteCode,data.WasteCode);
		values.put(Col_Waste,data.Waste);

		return values;
	}
	
	
	
	
	
}
