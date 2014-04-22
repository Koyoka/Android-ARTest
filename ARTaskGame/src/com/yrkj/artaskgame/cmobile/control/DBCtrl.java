package com.yrkj.artaskgame.cmobile.control;


import java.util.ArrayList;

import com.yrkj.artaskgame.base.TaskGameDBMng;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBCtrl {
	
	public static String getTaskCount(Context c){
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		
		DBCondition cdt = new DBCondition();
		dbMng.open();
		Cursor cursor = dbMng.query(
				TblTaskDetail.DBName,
				new String[]{"COUNT(*) AS [Totle]"}, 
				cdt);
		dbMng.close();
		
		String count = "";
		if(cursor.moveToFirst()){
			count = DBMng.GetDataString(cursor, "Totle");
		}
		return count;
	}
	
	public static String getTaskFinishCount(Context c){
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = TblTaskDetail.Col_Finish + " = '" + TblTaskDetail.FINISH_YES + "'";
		
		dbMng.open();
		Cursor cursor = dbMng.query(
				TblTaskDetail.DBName,
				new String[]{"COUNT(*) AS [Totle]"}, 
				cdt);
		dbMng.close();
		
		String count = "";
		if(cursor.moveToFirst()){
			count = DBMng.GetDataString(cursor, "Totle");
		}
		return count;
	}
	
	public static boolean finishTask(Context c,String taskId){
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		String cdt =  "id = '" + taskId+"'";
		ContentValues values = new ContentValues();
		values.put(TblTaskDetail.Col_Finish, "1");
		
		boolean result = false;
		dbMng.open();
		result = dbMng.update(TblTaskDetail.DBName, values, cdt);
		dbMng.close();
		
		return result;
	}
	
	public static TblTaskDetail getSelectTask(Context c,String taskId){
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = TblTaskDetail.Col_Id + " = '" + taskId + "'";
		
		Cursor cursor = dbMng.query(TblTaskDetail.DBName, 
				new String[]{
				TblTaskDetail.Col_Id,
				TblTaskDetail.Col_TaskTitle,
				TblTaskDetail.Col_TaskDesc,
				TblTaskDetail.Col_Finish,
				TblTaskDetail.Col_Score,
				TblTaskDetail.Col_TargetId
				}, cdt);
		dbMng.close();
		
		TblTaskDetail item = null;
		if(cursor.moveToFirst()){
			item = new TblTaskDetail();
			item.Id = DBMng.GetDataString(cursor, TblTaskDetail.Col_Id);
			item.TaskTitle = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskTitle);
			item.TaskDesc = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskDesc);
			item.Finish = DBMng.GetDataString(cursor, TblTaskDetail.Col_Finish);
			item.Score = DBMng.GetDataString(cursor, TblTaskDetail.Col_Score);
			item.TargetId = DBMng.GetDataString(cursor, TblTaskDetail.Col_TargetId);
			
		}
		return item;
	}
	
	public static TblTaskDetail getNextTask(Context c){
		
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
		cdt.Selection = "finish = '0' order by id Limit 1";
		
//		cdt.Selection = TblTaskDetail.Col_Finish + " = '0'";
//		cdt.OrderBy = TblTaskDetail.Col_Id;
//		cdt.Limit = "1";
		
		Cursor cursor = dbMng.query(TblTaskDetail.DBName, 
				new String[]{
				TblTaskDetail.Col_Id,
				TblTaskDetail.Col_TaskTitle,
				TblTaskDetail.Col_TaskDesc,
				TblTaskDetail.Col_Finish,
				TblTaskDetail.Col_Score,
				TblTaskDetail.Col_TargetId
				}, cdt);
		dbMng.close();
		
		TblTaskDetail item = null;
		if(cursor.moveToFirst()){
			item = new TblTaskDetail();
			item.Id = DBMng.GetDataString(cursor, TblTaskDetail.Col_Id);
			item.TaskTitle = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskTitle);
			item.TaskDesc = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskDesc);
			item.Finish = DBMng.GetDataString(cursor, TblTaskDetail.Col_Finish);
			item.Score = DBMng.GetDataString(cursor, TblTaskDetail.Col_Score);
			item.TargetId = DBMng.GetDataString(cursor, TblTaskDetail.Col_TargetId);
		}
		return item;
	}
	
	public static ArrayList<TblTaskDetail> getAllTaskList(Context c){
		TaskGameDBMng dbMng = new TaskGameDBMng(c);
		
//		String[] columns = { "moodid","moodcontent","phiz" };
		dbMng.open();
		Cursor cursor = dbMng.query(TblTaskDetail.DBName, 
				new String[]{
				TblTaskDetail.Col_Id,
				TblTaskDetail.Col_TaskTitle,
				TblTaskDetail.Col_TaskDesc,
				TblTaskDetail.Col_Finish,
				TblTaskDetail.Col_Score,
				TblTaskDetail.Col_TargetId
				}, null);
		dbMng.close();
		
		ArrayList<TblTaskDetail> itemList = new ArrayList<TblTaskDetail>();
		if(cursor.moveToFirst()){
			do {
				TblTaskDetail item = new TblTaskDetail();
				item.Id = DBMng.GetDataString(cursor, TblTaskDetail.Col_Id);
				item.TaskTitle = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskTitle);
				item.TaskDesc = DBMng.GetDataString(cursor, TblTaskDetail.Col_TaskDesc);
				item.Finish = DBMng.GetDataString(cursor, TblTaskDetail.Col_Finish);
				item.Score = DBMng.GetDataString(cursor, TblTaskDetail.Col_Score);
				item.TargetId = DBMng.GetDataString(cursor, TblTaskDetail.Col_TargetId);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		
		return itemList;
	}
}
