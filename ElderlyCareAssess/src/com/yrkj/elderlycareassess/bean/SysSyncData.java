package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class SysSyncData {
	public static final String TblName = "SysSync";
	
	public static final String Col_Id = "Id";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_State = "State";
	public static final String Col_StartTime = "StartTime";
	public static final String Col_EndTime = "EndTime";

	public static final String SYNC_STATE_WAIT = "W";
	public static final String SYNC_STATE_DOING = "D";
	public static final String SYNC_STATE_FINISH = "F";


	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_TaskHeaderId,
				Col_State,
				Col_StartTime,
				Col_EndTime
				};
	}
	
	public int Id = 0;
	public int TaskHeaderId = 0;
	public String State = "";
	public String startTime = "";
	public String EndTime = "";


	public static SysSyncData convertDataToModule(Cursor c){
		SysSyncData item = new SysSyncData();
		item.Id = DBMng.getDataInt(c, Col_Id);
		item.TaskHeaderId = DBMng.getDataInt(c, Col_TaskHeaderId);
		item.State = DBMng.GetDataString(c, Col_State);
		item.startTime = DBMng.GetDataString(c, Col_StartTime);
		item.EndTime = DBMng.GetDataString(c, Col_EndTime);

		return item;
	}
	
	public static ContentValues getContentValues(SysSyncData data){
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_State,data.State);
		values.put(Col_StartTime,data.startTime);
		values.put(Col_EndTime,data.EndTime);

		
		return values;
	}
	
	
}
