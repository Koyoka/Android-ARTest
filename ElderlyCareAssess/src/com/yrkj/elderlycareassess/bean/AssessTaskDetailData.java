package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskDetailData {
	public static final String TblName = "AssessTaskDetail";
	
	public static final String Col_Id = "Id";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_CateId = "CateId";
	public static final String Col_SubcateId = "SubcateId";
	public static final String Col_ItemId = "ItemId";
	public static final String Col_ItemTagId = "ItemTagId";
	public static final String Col_CateName = "CateName";
	public static final String Col_SubcateName = "SubcateName";
	public static final String Col_ItemName = "ItemName";
	public static final String Col_ItemDesc = "ItemDesc";
	public static final String Col_ItemTagName = "ItemTagName";
	public static final String Col_TaskState = "TaskState";

	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_TaskHeaderId,
				Col_CateId,
				Col_SubcateId,
				Col_ItemId,
				Col_ItemTagId,
				Col_CateName,
				Col_SubcateName,
				Col_ItemName,
				Col_ItemDesc,
				Col_ItemTagName,
				Col_TaskState
				};
	}
	
	public String Id = "";
	public String TaskHeaderId = "";
	public String CateId = "";
	public String SubcateId = "";
	public String ItemId = "";
	public String ItemTagId = "";
	public String CateName = "";
	public String SubcateName = "";
	public String ItemName = "";
	public String ItemDesc = "";
	public String ItemTagName = "";
	public String TaskState = "";

	public static AssessTaskDetailData convertDataToModule(Cursor c){
		AssessTaskDetailData item = new AssessTaskDetailData();
		item.Id = DBMng.GetDataString(c, Col_Id);
		item.TaskHeaderId = DBMng.GetDataString(c, Col_TaskHeaderId);
		item.CateId = DBMng.GetDataString(c, Col_CateId);
		item.SubcateId = DBMng.GetDataString(c, Col_SubcateId);
		item.ItemId = DBMng.GetDataString(c, Col_ItemId);
		item.ItemTagId = DBMng.GetDataString(c, Col_ItemTagId);
		item.CateName = DBMng.GetDataString(c, Col_CateName);
		item.SubcateName = DBMng.GetDataString(c, Col_SubcateName);
		item.ItemName = DBMng.GetDataString(c, Col_ItemName);
		item.ItemDesc = DBMng.GetDataString(c, Col_ItemDesc);
		item.ItemTagName = DBMng.GetDataString(c, Col_ItemTagName);
		item.TaskState = DBMng.GetDataString(c, Col_TaskState);

		return item;
	}
	
	public static ContentValues getContentValues(AssessTaskDetailData data){
		ContentValues values = new ContentValues();
		
		values.put(Col_Id,data.Id);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_CateId,data.CateId);
		values.put(Col_SubcateId,data.SubcateId);
		values.put(Col_ItemId,data.ItemId);
		values.put(Col_ItemTagId,data.ItemTagId);
		values.put(Col_CateName,data.CateName);
		values.put(Col_SubcateName,data.SubcateName);
		values.put(Col_ItemName,data.ItemName);
		values.put(Col_ItemDesc,data.ItemDesc);
		values.put(Col_ItemTagName,data.ItemTagName);
		values.put(Col_TaskState,data.TaskState);
		
		return values;
	}
	
}
