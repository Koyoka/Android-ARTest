package com.yrkj.elderlycareassess.bean;

import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class QItemTagData {
	public static final String TblName = "QItemTag";
	

	public static final String Col_ItemTagId = "ItemTagId";
	public static final String Col_ItemTagName = "ItemTagName";
	public static final String Col_ItemTagDesc = "ItemTagDesc";
	public static final String Col_SortIndex = "SortIndex";
	public static final String Col_IsActive = "IsActive";
	
	public int ItemTagId = 0;
	public String ItemTagName = "";
	public String ItemTagDesc = "";
	public String SortIndex = "";
	public String IsActive = "";
	
	public static String[] getColumnColl(){
		return new String[]{
				Col_ItemTagId,
				Col_ItemTagName,
				Col_ItemTagDesc,
				Col_SortIndex,
				Col_IsActive
				};
	}
	
	public static QItemTagData convertDataToModule(Cursor c){
		QItemTagData item = new QItemTagData();
		item.ItemTagId = DBMng.getDataInt(c, Col_ItemTagId);
		item.ItemTagName = DBMng.GetDataString(c, Col_ItemTagName);
		item.ItemTagDesc = DBMng.GetDataString(c, Col_ItemTagDesc);
		item.SortIndex = DBMng.GetDataString(c, Col_SortIndex);
		item.IsActive = DBMng.GetDataString(c, Col_IsActive);
		return item;
	}
}
