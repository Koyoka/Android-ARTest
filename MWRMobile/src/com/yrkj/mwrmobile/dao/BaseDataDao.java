package com.yrkj.mwrmobile.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.mwrmobile.base.MWRBaseDBMng;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
import com.yrkj.util.db.DBCondition;


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
	
	
}
