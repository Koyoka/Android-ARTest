package com.yrkj.mwrmobile.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.mwrmobile.base.MWRDBMng;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.util.db.DBCondition;

public class TxnDao {

	public static ArrayList<TxnDetailData> getTxnDetail(Context c){
		
		MWRDBMng dbMng = new MWRDBMng(c);
		
		DBCondition cdt = new DBCondition();
//		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(TxnDetailData.TblName, 
				TxnDetailData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<TxnDetailData> itemList = new ArrayList<TxnDetailData>();
		if(cursor.moveToFirst()){
			do {
				TxnDetailData item = TxnDetailData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
}
