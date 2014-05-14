package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;

import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class QItemData {
	public static final String TblName = "QItem";
	

	public static final String Col_ItemId = "ItemId";
	public static final String Col_ItemName = "ItemName";
	public static final String Col_ItemDesc = "ItemDesc";
	public static final String Col_SortIndex = "SortIndex";
	public static final String Col_IsActive = "IsActive";
	
	public static String[] getColumnColl(){
		return new String[]{
				Col_ItemId,
				Col_ItemName,
				Col_ItemDesc,
				Col_SortIndex,
				Col_IsActive
				};
	}
	
	public int ItemId = 0;
	public String ItemName = "";
	public String ItemDesc = "";
	public int SortIndex = 0;
	public boolean IsActive = false;
	
	
	
	public static QItemData convertDataToModule(Cursor c){
		QItemData item = new QItemData();
		item.ItemId = DBMng.getDataInt(c, Col_ItemId);
		item.ItemName = DBMng.GetDataString(c, Col_ItemName);
		item.ItemDesc = DBMng.GetDataString(c, Col_ItemDesc);
		item.SortIndex = DBMng.getDataInt(c, Col_SortIndex);
		item.IsActive = DBMng.getDataBoolean(c, Col_IsActive);
		return item;
	}
}
