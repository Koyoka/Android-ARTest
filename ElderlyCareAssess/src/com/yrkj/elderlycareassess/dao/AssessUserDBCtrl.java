package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.bean.QCategoryData;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.db.DBMng;

public class AssessUserDBCtrl {

	public static AssessUserData getUserData(Context c,String userId){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessUserData.Col_UserId+" = '" + userId.trim() +"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessUserData.TblName, 
				AssessUserData.getColumnColl(), cdt);
		dbMng.close();
		
		
		AssessUserData item = null;
		if(cursor.moveToFirst()){
			item = AssessUserData.convertDataToModule(cursor);
		}
		cursor.close();
		return item;
	}
	
	public static boolean userLogin(Context c,String userId,String pwd){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = AssessUserData.Col_UserId+" = '" + userId.trim() +"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessUserData.TblName, 
				AssessUserData.getColumnColl(), cdt);
		dbMng.close();
		
		
		AssessUserData item = null;
		if(cursor.moveToFirst()){
			item = AssessUserData.convertDataToModule(cursor);
		}
		cursor.close();
		if(item != null && item.LocPassword.equals(pwd)){
			return true;
		}
		
		return false;
		
	}

	public static long insertUser(Context c,AssessUserData d){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(AssessUserData.TblName, 
				AssessUserData.getContentValues(d));
		dbMng.close();
		return result;
	}
	
	public static boolean updateUserPassword(Context c,String userId,String newPassword){
		ContentValues values = new ContentValues();
		values.put(AssessUserData.Col_LocPassword,newPassword);
		
		String condition = AssessUserData.Col_UserId + "='"+userId+"'";
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		boolean result = false;
		result = dbMng.update(AssessUserData.TblName, values, condition);
		dbMng.close();
		return result;
	}

}
