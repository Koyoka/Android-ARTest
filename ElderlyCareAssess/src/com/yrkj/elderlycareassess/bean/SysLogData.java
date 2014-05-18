package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class SysLogData {
	public static final String TblName = "SysLog";
	
	public static final String Col_Id = "Id";
	public static final String Col_LogType = "LogType";
	public static final String Col_LogDesc = "LogDesc";
	public static final String Col_LogDate = "LogDate";



	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_LogType,
				Col_LogDesc,
				Col_LogDate

				};
	}
	
	public String Id = "";
	public String LogType = "";
	public String LogDesc = "";
	public String LogDate = "";

	public static final String LOGTYPE_ASSESS = "AS";
	public static final String LOGTYPE_SYS_LOGIN = "SL";

	

	public static SysLogData convertDataToModule(Cursor c){
		SysLogData item = new SysLogData();
		item.Id = DBMng.GetDataString(c, Col_Id);
		item.LogType = DBMng.GetDataString(c, Col_LogType);
		item.LogDesc = DBMng.GetDataString(c, Col_LogDesc);
		item.LogDate = DBMng.GetDataString(c, Col_LogDate);

		return item;
	}
	
	public static ContentValues getContentValues(SysLogData data){
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
		values.put(Col_LogType,data.LogType);
		values.put(Col_LogDesc,data.LogDesc);
		values.put(Col_LogDate,data.LogDate);
		
		return values;
	}
	
	
}
