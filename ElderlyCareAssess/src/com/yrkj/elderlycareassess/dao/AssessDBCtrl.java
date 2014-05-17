package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;

public class AssessDBCtrl {

	public static AssessTaskHeaderData getAssessTaskById(Context c,String id){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskHeaderData.Col_Id+" = " + id;//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getColumnColl(), cdt);
		dbMng.close();
		
		
		AssessTaskHeaderData item = null;
		if(cursor.moveToFirst()){
			item = AssessTaskHeaderData.convertDataToModule(cursor);
		}
		
		return item;
		
	}
	
	public static ArrayList<CustomerAssessTask> getDoingAssessTaskList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		StringBuilder sb = new StringBuilder();
		sb.append("Select a.*, ");
		sb.append("c.[customername],c.[sex],c.[mobliephone],c.[address] ");
		sb.append("From MAIN.[AssessTaskHeader] as a ");
		sb.append("left join MAIN.[t_customer] as c where c.[id] = a.[CustId] ");
		
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		
		ArrayList<CustomerAssessTask> itemList = new ArrayList<CustomerAssessTask>();
		if(cursor.moveToFirst()){
			do {

				CustomerAssessTask itemHeader = new CustomerAssessTask();
				
				itemHeader.mTask = AssessTaskHeaderData.convertDataToModule(cursor);
				CustomerData item = new CustomerData();
				item.customername = DBMng.GetDataString(cursor, CustomerData.Col_customername);
				item.sex = DBMng.GetDataString(cursor, CustomerData.Col_sex);
				item.mobliephone = DBMng.GetDataString(cursor, CustomerData.Col_mobliephone);
				item.address = DBMng.GetDataString(cursor, CustomerData.Col_address);
				itemHeader.mCust = item;
				itemList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}
	
	public void getDoneAssessTaskList(){
		
	}
	
	public static ArrayList<AssessTaskDetailData> getAssessTaskDetailByTaskId(Context c,String id){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskDetailData.Col_TaskHeaderId + "=" + id;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskDetailData.TblName, 
				AssessTaskDetailData.getColumnColl(), cdt);

		ArrayList<AssessTaskDetailData> itemList = new ArrayList<AssessTaskDetailData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskDetailData item = 
				AssessTaskDetailData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		dbMng.close();
		
		return itemList;
		
	}
	
	public static CustomerData getCustomerByCustId(Context c,String custId){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = CustomerData.Col_id+" = '" + custId+"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = "";//QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(CustomerData.TblName, 
				CustomerData.getColumnColl(), cdt);
		dbMng.close();
		
		
		CustomerData item = null;
		if(cursor.moveToFirst()){
			item = CustomerData.convertDataToModule(cursor);
		}
		
		return item;
		
	}
	
	public static long insertAssessTaskDetail(Context c,AssessTaskDetailData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(AssessTaskDetailData.TblName, 
				AssessTaskDetailData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static boolean deleteAssessTaskDetail(Context c,
			String taskHeaderId,
			String cateId
			){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		String condition = 
				AssessTaskDetailData.Col_TaskHeaderId + "=" + taskHeaderId + " and "+
				AssessTaskDetailData.Col_CateId + "=" + cateId + " and "+
				"1=1"
				;
		boolean r = dbMng.delete(AssessTaskDetailData.TblName, condition);
		dbMng.close();
		return r;
	}
	
	public static ArrayList<AssessTaskServiceData> getAssessTaskServiceByTaskId(Context c,String id){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskServiceData.Col_TaskHeaderId + "=" + id;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskServiceData.TblName, 
				AssessTaskServiceData.getColumnColl(), cdt);

		ArrayList<AssessTaskServiceData> itemList = new ArrayList<AssessTaskServiceData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskServiceData item = 
						AssessTaskServiceData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		dbMng.close();
		
		return itemList;
	}
	
	public static long insertAssessTaskService(Context c,AssessTaskServiceData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(AssessTaskServiceData.TblName, 
				AssessTaskServiceData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static boolean deleteAssessTaskService(Context c,
			String taskHeaderId
			){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		String condition = 
				AssessTaskServiceData.Col_TaskHeaderId + "=" + taskHeaderId + " and "+
						"1=1"
						;
		boolean r = dbMng.delete(AssessTaskServiceData.TblName, condition);
		dbMng.close();
		return r;
	}
	
	
	
	
	
	public static boolean updateAssessTaskHeaderById(Context c,AssessTaskHeaderData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		String condition = AssessTaskHeaderData.Col_Id+"="+data.Id;
		boolean result = false;
		result = dbMng.update(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getContentValues(data),condition);
		dbMng.close();
		return result;
	}
	
	public static boolean updateCustomerById(Context c,CustomerData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		String condition = CustomerData.Col_id+"='"+data.id+"'";
		boolean result = false;
		result = dbMng.update(CustomerData.TblName, 
				CustomerData.getContentValues(data),condition);
		dbMng.close();
		return result;
	}
	
	
//	public class CustomerAssessTask{
//		public CustomerAssessTask getInstance(){
//			return new CustomerAssessTask();
//		}
//		
//	}
}
