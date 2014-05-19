package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskAttachmentImageData {
	public static final String TblName = "AssessTaskAttachmentImage";
	
	public static final String Col_Id = "Id";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_CateId = "CateId";
	public static final String Col_ImgPath = "ImgPath";



	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_TaskHeaderId,
				Col_CateId,
				Col_ImgPath
				};
	}
	
	public int Id = 0;
	public int TaskHeaderId = 0;
	public int CateId = 0;
	public String ImgPath = "";



	public static AssessTaskAttachmentImageData convertDataToModule(Cursor c){
		AssessTaskAttachmentImageData item = new AssessTaskAttachmentImageData();
		item.Id = DBMng.getDataInt(c, Col_Id);
		item.TaskHeaderId = DBMng.getDataInt(c, Col_TaskHeaderId);
		item.CateId = DBMng.getDataInt(c, Col_CateId);
		item.ImgPath = DBMng.GetDataString(c, Col_ImgPath);

		return item;
	}
	
	public static ContentValues getContentValues(AssessTaskAttachmentImageData data){
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_CateId,data.CateId);
		values.put(Col_ImgPath,data.ImgPath);

		return values;
	}
	
	
}
