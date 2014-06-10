package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.AssessReportCountData;
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
		cursor.close();
		return item;
		
	}
	
	public static ArrayList<AssessTaskHeaderData> getAllAssessTaskList(Context c){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<AssessTaskHeaderData> itemList = new ArrayList<AssessTaskHeaderData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskHeaderData itemHeader = AssessTaskHeaderData.convertDataToModule(cursor);
				itemList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	public static ArrayList<AssessTaskHeaderData> getCanSyncAssessTaskList(Context c){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select h.* From MAIN.[AssessTaskHeader] as h ");
		sb.append("where h.[AssessState] = '"+AssessTaskHeaderData.ASSESS_STATE_DONE+"' and h.[NeedSync]=1  ");
		sb.append("and h.[Id] not in(Select s.[TaskHeaderId] From MAIN.[SysSync] as s) ");
		

		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		
		ArrayList<AssessTaskHeaderData> dataList
			= new ArrayList<AssessTaskHeaderData>();
		
		if(cursor.moveToFirst()){
			do {
				AssessTaskHeaderData itemHeader = AssessTaskHeaderData.convertDataToModule(cursor);
				dataList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return dataList;
	}
	public static int getCanSyncAssessTaskCount(Context c){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select count(*) as count From MAIN.[AssessTaskHeader] as h  ");
		sb.append("where h.[AssessState] = '"+AssessTaskHeaderData.ASSESS_STATE_DONE+"' and h.[NeedSync]=1  ");
		sb.append("and h.[Id] not in(Select s.[TaskHeaderId] From MAIN.[SysSync] as s) ");

		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		int count = 0;
		if(cursor.moveToFirst()){
			count = DBMng.getDataInt(cursor, "count");
		}
		cursor.close();
		return count;
//		
//		
//		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
//		
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("Select count(*) as count From MAIN.[AssessTaskHeader] where  ");
//		sb.append("[AssessState]='"+AssessTaskHeaderData.ASSESS_STATE_DONE+"' and [NeedSync]=1");
//
//		dbMng.open();
//		Cursor cursor = 
//				dbMng.rawQuery(sb.toString());
//		dbMng.close();
//		
//		int count = 0;
//		if(cursor.moveToFirst()){
//			count = DBMng.getDataInt(cursor, "count");
//			
//		}
//		cursor.close();
//		return count;
	}
	
	public static ArrayList<CustomerAssessTask> getDoingAssessTaskList(Context c){
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select a.*, ");
		sb.append("c.[customername],c.[sex],c.[mobliephone],c.[address] ");
		sb.append("From MAIN.[AssessTaskHeader] as a ");
		sb.append("left join MAIN.[t_customer] as c where c.[id] = a.[CustId] and a.[AssessState]='"+
				AssessTaskHeaderData.ASSESS_STATE_DOING+"' ");
		sb.append(" order by a.[CreateDate] desc");
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
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
	
	public static ArrayList<CustomerAssessTask> getDoneAssessTaskList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select a.*, ");
		sb.append("c.[customername],c.[sex],c.[mobliephone],c.[address], ");
		sb.append("(Select sum(score) as score From MAIN.[AssessTaskDetail] as d ");
		sb.append("where d.[TaskHeaderId] = a.[Id]) AS score ");
		sb.append(",(Select count(*) as scount From MAIN.[SysSync] as sy  where sy.[TaskHeaderId] = a.[Id] ) AS scount ");
		
		sb.append("From MAIN.[AssessTaskHeader] as a ");
		sb.append("left join MAIN.[t_customer] as c where c.[id] = a.[CustId] and a.[AssessState]='"+AssessTaskHeaderData.ASSESS_STATE_DONE+"' ");
		sb.append(" order by a.[CreateDate] desc");
		
		
		dbMng.open();
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
				itemHeader.score = DBMng.getDataInt(cursor, "score");
				itemHeader.scount = DBMng.getDataInt(cursor, "scount");
				itemList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}
	
	
	public static int getWaitingSyncAssessTaskCount(Context c){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select count(*) as scount From MAIN.[SysSync]");

		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		int count = 0;
		if(cursor.moveToFirst()){
			count = DBMng.getDataInt(cursor, "scount");
		}
		cursor.close();
		return count;
	}
	
	
	public static ArrayList<AssessTaskHeaderData> getUnSyncAssessTaskList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskHeaderData.Col_NeedSync+"=1";
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<AssessTaskHeaderData> itemList = new ArrayList<AssessTaskHeaderData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskHeaderData itemHeader = AssessTaskHeaderData.convertDataToModule(cursor);
				itemList.add(itemHeader);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static String getDoneAssessTaskDetailCateTotleScore(Context c,String taskHeaderId){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select d.catename, ");
		sb.append("(Select sum(d1.[score]) as score From MAIN.[AssessTaskDetail] as d1 ");
		sb.append("where   d.cateid = d1.cateid and d1.[TaskHeaderId] = d.[TaskHeaderId] and d1.[TaskType]='I' ");
		sb.append("group by d1.cateid ) as TotleScore    ");
		sb.append("From  ");
		sb.append("MAIN.[AssessTaskDetail] as d  ");
		sb.append("where d.taskheaderid = "+taskHeaderId);
		sb.append(" group by d.taskheaderid,d.cateid ");
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		
//		ArrayList<AssessTaskDetailData> dlist = new ArrayList<AssessTaskDetailData>();
		String s = "";
		if(cursor.moveToFirst()){
			do {
//				AssessTaskDetailData d = new AssessTaskDetailData();
				String CateName = DBMng.GetDataString(cursor, "CateName");
				int Score = DBMng.getDataInt(cursor, "TotleScore");
				s += CateName + " " + Score + " ";
//				dlist.add(d);
			}while (cursor.moveToNext());
		}
		cursor.close();
		
		return s;
	}
	
	/*
	 Select d.catename,
(
Select sum(d1.[score]) as score From MAIN.[AssessTaskDetail] as d1 
where   d.cateid = d1.cateid and d1.[TaskHeaderId] = d.[TaskHeaderId] and d1.[TaskType]='I'
group by d1.cateid  
) as TotleScore   
From 
MAIN.[AssessTaskDetail] as d 
--where d.taskheaderid = 1  
group by d.taskheaderid,d.cateid


Limit 1000 ;


--Limit 1000
	 * */
	
	public static ArrayList<AssessTaskDetailData> getAssessTaskDetailByTaskId(Context c,String id){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskDetailData.Col_TaskHeaderId + "=" + id;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskDetailData.TblName, 
				AssessTaskDetailData.getColumnColl(), cdt);
		dbMng.close();

		ArrayList<AssessTaskDetailData> itemList = new ArrayList<AssessTaskDetailData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskDetailData item = 
				AssessTaskDetailData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
		
	}
	public static ArrayList<AssessTaskDetailData> getAssessTaskDetailByTaskIdCateId(Context c,String id,String cateId){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessTaskDetailData.Col_TaskHeaderId + "=" + id 
				+ " and " + AssessTaskDetailData.Col_CateId + "=" + cateId;
				;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskDetailData.TblName, 
				AssessTaskDetailData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<AssessTaskDetailData> itemList = new ArrayList<AssessTaskDetailData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskDetailData item = 
						AssessTaskDetailData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
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
		cursor.close();
		return item;
		
	}
	
	public static long insertAssessTaskHeader(Context c,AssessTaskHeaderData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getContentValues(data));
		dbMng.close();
		return result;
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
		String condition = 
				AssessTaskDetailData.Col_TaskHeaderId + "=" + taskHeaderId + " and "+
						AssessTaskDetailData.Col_CateId + "=" + cateId + " and "+
						"1=1"
						;
		dbMng.open();
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
		dbMng.close();

		ArrayList<AssessTaskServiceData> itemList = new ArrayList<AssessTaskServiceData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskServiceData item = 
						AssessTaskServiceData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}
	
	public static long insertAssessTaskService(Context c,AssessTaskServiceData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		long result = 0;
		dbMng.open();
		result = dbMng.insert(AssessTaskServiceData.TblName, 
				AssessTaskServiceData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static boolean deleteAssessTaskService(Context c,
			String taskHeaderId
			){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = 
				AssessTaskServiceData.Col_TaskHeaderId + "=" + taskHeaderId + " and "+
						"1=1"
						;
		dbMng.open();
		boolean r = dbMng.delete(AssessTaskServiceData.TblName, condition);
		dbMng.close();
		return r;
	}
	
	public static boolean updateAssessTaskHeaderById(Context c,AssessTaskHeaderData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = AssessTaskHeaderData.Col_Id+"="+data.Id;
		boolean result = false;
		dbMng.open();
		result = dbMng.update(AssessTaskHeaderData.TblName, 
				AssessTaskHeaderData.getContentValues(data),condition);
		dbMng.close();
		return result;
	}
	
	public static long insertCustomer(Context c,CustomerData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		long result = 0;
		ContentValues values = CustomerData.getContentValues(data);
		values.put(CustomerData.Col_id,data.id);
		dbMng.open();
		result = dbMng.insert(CustomerData.TblName, 
				values);
		dbMng.close();
		return result;
	}
	public static boolean updateCustomerById(Context c,CustomerData data){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = CustomerData.Col_id+"='"+data.id+"'";
		boolean result = false;
		dbMng.open();
		result = dbMng.update(CustomerData.TblName, 
				CustomerData.getContentValues(data),condition);
		dbMng.close();
		return result;
	}
	
	public static AssessReportCountData getAssessReportCount(Context c){
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("(Select count(*) From MAIN.[AssessTaskHeader] where assessstate = 'ON') as doningCount, ");
		sb.append("(Select count(*) From MAIN.[AssessTaskHeader] where assessstate = 'OFF') as doneCount ");

		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		Cursor cursor = 
				dbMng.rawQuery(sb.toString());
		dbMng.close();
		
		AssessReportCountData data = null;
		if(cursor.moveToFirst()){
			data = new AssessReportCountData();
			data.doingTaskCount = DBMng.GetDataString(cursor,"doningCount");
			data.doneTaskCount = DBMng.GetDataString(cursor,"doneCount");
		}
		cursor.close();
		return data;
	
	}
	
	public static boolean deletAssessTaskHeaderById(Context c,String taskHeaderId){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = AssessTaskHeaderData.Col_Id+"="+taskHeaderId;
		boolean result = false;
		
		dbMng.open();
		result = dbMng.delete(AssessTaskHeaderData.TblName, condition);
		dbMng.close();
		
		return result;
	}
	
	public static boolean updateAssessTaksHeaderStateByTaskId(Context c,String taskHeaderId,String state){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		String condition = AssessTaskHeaderData.Col_Id+"="+taskHeaderId;
		boolean result = false;
		
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
//		values.put(Col_AssessNum,data.AssessNum);
//		values.put(Col_CustId,data.CustId);
//		values.put(Col_CreateDate,data.CreateDate);
//		values.put(Col_EndAssessDate,data.EndAssessDate);
//		values.put(Col_LastAssessDate,data.LastAssessDate);
//		values.put(Col_AssessType,data.AssessType);
		values.put(AssessTaskHeaderData.Col_AssessState,state);
		dbMng.open();
		result = dbMng.update(AssessTaskHeaderData.TblName, 
				values,
				condition);
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
