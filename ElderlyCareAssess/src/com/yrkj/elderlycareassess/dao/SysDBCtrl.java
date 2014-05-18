package com.yrkj.elderlycareassess.dao;

import java.text.ParseException;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.SysLogData;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.log.DLog;

public class SysDBCtrl {

	public static long addSysLoginLog(Context c,String userId){
		
		SysLogData data = new SysLogData();
		data.LogDesc = userId;//+" ["+userName+"]";
		data.LogType = SysLogData.LOGTYPE_SYS_LOGIN;
		data.LogDate = DateHelper.getTodayAndTime();
		DLog.LOG(SysMng.TAG_DB,data.LogDate);
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(SysLogData.TblName, 
				SysLogData.getContentValues(data));
		dbMng.close();
		return result;
	}
	
	public static String getLastLoginDate(Context c){
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = SysLogData.Col_LogType+" = '" + SysLogData.LOGTYPE_SYS_LOGIN+"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
		cdt.OrderBy = SysLogData.Col_Id+" desc";
		cdt.Limit = "1,1";
		
		dbMng.open();
		Cursor cursor = dbMng.query(SysLogData.TblName, 
				SysLogData.getColumnColl(), cdt);
		dbMng.close();
		
		
		SysLogData item = null;
		
		if(cursor.moveToFirst()){
			item = SysLogData.convertDataToModule(cursor);
			
		}
		cursor.close();
		if(item != null)
			return item.LogDate;
		else
			return "";
	}
}
