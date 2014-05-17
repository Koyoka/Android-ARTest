package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskServiceData {
	public static final String TblName = "AssessTaskService";
	
	
	public static final String Col_Id = "Id";
	public static final String Col_ServiceName = "ServiceName";
	public static final String Col_ServiceContentId = "ServiceContentId";
	public static final String Col_ServiceContent = "ServiceContent";
	public static final String Col_prefix = "prefix";
	public static final String Col_unit = "unit";
	public static final String Col_count = "count";
	public static final String Col_ServiceId = "ServiceId";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_Value = "Value";


	public static final String UNIT_HOUR = "H";
	public static final String UNIT_DAY = "D";
	public static final String UNIT_WEEK = "W";
	public static final String UNIT_MONTH = "M";
	
	public static String getUnitDesc(String s){
		if(false){}
		else if(s.equals(UNIT_HOUR)){
			return "小时";
		}
		else if(s.equals(UNIT_DAY)){
			return "天";
		}
		else if(s.equals(UNIT_WEEK)){
			return "周";
		}
		else if(s.equals(UNIT_MONTH)){
			return "月";
		}
		
		return "";
	}
	
	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_ServiceName,
				Col_ServiceContentId,
				Col_ServiceContent,
				Col_prefix,
				Col_unit,
				Col_count,
				Col_ServiceId,
				Col_TaskHeaderId,
				Col_Value,
				};
	}
	
	public String Id = "";
	public String ServiceName = "";
	public String ServiceContentId = "";
	public String ServiceContent = "";
	public String prefix = "";
	public String unit = "";
	public String count = "";
	public String ServiceId = "";
	public String TaskHeaderId = "";
	public String Value = "";

	
	public static AssessTaskServiceData convertDataToModule(Cursor c){
		AssessTaskServiceData item = new AssessTaskServiceData();
		item.Id = DBMng.GetDataString(c, Col_Id);
		item.ServiceName = DBMng.GetDataString(c, Col_ServiceName);
		item.ServiceContentId = DBMng.GetDataString(c, Col_ServiceContentId);
		item.ServiceContent = DBMng.GetDataString(c, Col_ServiceContent);
		item.prefix = DBMng.GetDataString(c, Col_prefix);
		item.unit = DBMng.GetDataString(c, Col_unit);
		item.count = DBMng.GetDataString(c, Col_count);
		item.ServiceId = DBMng.GetDataString(c, Col_ServiceId);
		item.TaskHeaderId = DBMng.GetDataString(c, Col_TaskHeaderId);
		item.Value = DBMng.GetDataString(c, Col_Value);


		return item;
	}
	
	
	public static ContentValues getContentValues(AssessTaskServiceData data){
		ContentValues values = new ContentValues();
//		values.put(Col_Id,data.Id);
		values.put(Col_ServiceName,data.ServiceName);
		values.put(Col_ServiceContentId,data.ServiceContentId);
		values.put(Col_ServiceContent,data.ServiceContent);
		values.put(Col_prefix,data.prefix);
		values.put(Col_unit,data.unit);
		values.put(Col_count,data.count);
		values.put(Col_ServiceId,data.ServiceId);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_Value,data.Value);

		
		return values;
	}
	
	
	
	
	
	
	
}
