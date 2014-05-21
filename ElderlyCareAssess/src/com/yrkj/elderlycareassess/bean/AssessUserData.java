package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessUserData {
	public static final String TblName = "AssessUser";
	
	public static final String Col_UserId = "UserId";
	public static final String Col_UserName = "UserName";
	public static final String Col_LocPassword = "LocPassword";
	public static final String Col_OfficeAddress = "OfficeAddress";
	public static final String Col_Office = "Office";


	public static String[] getColumnColl(){
		return new String[]{
				Col_UserId,
				Col_UserName,
				Col_LocPassword,
				Col_OfficeAddress,
				Col_Office

				};
	}
	
	public String UserId = "";
	public String UserName = "";
	public String LocPassword = "";
	public String OfficeAddress = "";
	public String Office = "";
	public String Taskcount = "";
	public String Cmtcount = "";


	public static AssessUserData convertDataToModule(Cursor c){
		AssessUserData item = new AssessUserData();
		item.UserId = DBMng.GetDataString(c, Col_UserId);
		item.UserName = DBMng.GetDataString(c, Col_UserName);
		item.LocPassword = DBMng.GetDataString(c, Col_LocPassword);
		item.OfficeAddress = DBMng.GetDataString(c, Col_OfficeAddress);
		item.Office = DBMng.GetDataString(c, Col_Office);

		return item;
	}
	
	public static ContentValues getContentValues(AssessUserData data){
		ContentValues values = new ContentValues();
		
		values.put(Col_UserId,data.UserId);
		values.put(Col_UserName,data.UserName);
		values.put(Col_LocPassword,data.LocPassword);
		values.put(Col_OfficeAddress,data.OfficeAddress);
		values.put(Col_Office,data.Office);

		
		return values;
	}
	
	
}
