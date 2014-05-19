package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.yrkj.elderlycareassess.base.ECAQuesDBMng;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.util.db.DBCondition;

public class AttachmentDBCtrl {

	
	public static long addAttachmentImg(Context c,AssessTaskAttachmentImageData d){
		
		
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		dbMng.open();
		long result = 0;
		result = dbMng.insert(AssessTaskAttachmentImageData.TblName, 
				AssessTaskAttachmentImageData.getContentValues(d));
		dbMng.close();
		return result;
		
	}
	
	public static ArrayList<AssessTaskAttachmentImageData> getAttachmentImgList(Context c){
		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
		
		dbMng.open();
		DBCondition cdt = new DBCondition();
//		cdt.Selection = AssessTaskDetailData.Col_TaskHeaderId + "=" + id;
		dbMng.open();
		Cursor cursor = dbMng.query(AssessTaskAttachmentImageData.TblName, 
				AssessTaskAttachmentImageData.getColumnColl(), cdt);
		dbMng.close();

		ArrayList<AssessTaskAttachmentImageData> itemList = new ArrayList<AssessTaskAttachmentImageData>();
		if(cursor.moveToFirst()){
			do {
				AssessTaskAttachmentImageData item = 
						AssessTaskAttachmentImageData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return itemList;
	}
	
//	public static long addSysLoginLog(Context c,String userId){
//		
//		SysLogData data = new SysLogData();
//		data.LogDesc = userId;//+" ["+userName+"]";
//		data.LogType = SysLogData.LOGTYPE_SYS_LOGIN;
//		data.LogDate = DateHelper.getTodayAndTime();
//		DLog.LOG(SysMng.TAG_DB,data.LogDate);
//		
//		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
//		dbMng.open();
//		long result = 0;
//		result = dbMng.insert(SysLogData.TblName, 
//				SysLogData.getContentValues(data));
//		dbMng.close();
//		return result;
//	}
//	
//	public static String getLastLoginDate(Context c){
//		
//		ECAQuesDBMng dbMng = new ECAQuesDBMng(c);
//		
//		DBCondition cdt = new DBCondition();
//		cdt.Selection = SysLogData.Col_LogType+" = '" + SysLogData.LOGTYPE_SYS_LOGIN+"'";//QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = SysLogData.Col_Id+" desc";
//		cdt.Limit = "1,1";
//		
//		dbMng.open();
//		Cursor cursor = dbMng.query(SysLogData.TblName, 
//				SysLogData.getColumnColl(), cdt);
//		dbMng.close();
//		
//		
//		SysLogData item = null;
//		
//		if(cursor.moveToFirst()){
//			item = SysLogData.convertDataToModule(cursor);
//			
//		}
//		cursor.close();
//		if(item != null)
//			return item.LogDate;
//		else
//			return "";
//	}
}
