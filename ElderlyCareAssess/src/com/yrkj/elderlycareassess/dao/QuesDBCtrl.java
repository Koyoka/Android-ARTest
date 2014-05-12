package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.elderlycareassess.bean.QSubcategoryData;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;

public class QuesDBCtrl {

	public static ArrayList<QCategoryData> getQCategoryList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(QCategoryData.TblName, 
				new String[]{
				QCategoryData.Col_CateId,
				QCategoryData.Col_CateName,
				QCategoryData.Col_IsActive,
				QCategoryData.Col_SortIndex
				}, cdt);
		dbMng.close();
		
		ArrayList<QCategoryData> itemList = new ArrayList<QCategoryData>();
		if(cursor.moveToFirst()){
			do {
				QCategoryData item = new QCategoryData();
				item.CateId = DBMng.GetDataString(cursor, QCategoryData.Col_CateId);
				item.CateName = DBMng.GetDataString(cursor, QCategoryData.Col_CateName);
				item.IsActive = DBMng.GetDataString(cursor, QCategoryData.Col_IsActive);
				item.SortIndex = DBMng.GetDataString(cursor, QCategoryData.Col_SortIndex);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static ArrayList<QSubcategoryData> getQSubcategoryList(Context c,String cateId){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		
//		select * from MAIN.[qsubcategory] 
//				where subcateid in
//				(Select subcateid From 
//				MAIN.[qcategorydetail] 
//				where cateid = 1);
		
		String subSql = "Select Subcateid From QCategoryDetail where CateId = " + cateId;
		cdt.Selection = QSubcategoryData.Col_SubcateId + " IN("+ subSql +")";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = QSubcategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(QSubcategoryData.TblName, 
				new String[]{
				QSubcategoryData.Col_SubcateId,
				QSubcategoryData.Col_SubcateName,
				QSubcategoryData.Col_UseType,
				QSubcategoryData.Col_SortIndex
				}, cdt);
		dbMng.close();
		
		
		ArrayList<QSubcategoryData> itemList = new ArrayList<QSubcategoryData>();
		if(cursor.moveToFirst()){
			do {

				QSubcategoryData item = new QSubcategoryData();
				item.SubcateId = DBMng.GetDataString(cursor, QSubcategoryData.Col_SubcateId);
				item.SubcateName = DBMng.GetDataString(cursor, QSubcategoryData.Col_SubcateName);
				item.UseType = DBMng.GetDataString(cursor, QSubcategoryData.Col_UseType);
				item.SortIndex = DBMng.GetDataString(cursor, QSubcategoryData.Col_SortIndex);
				
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
}
















