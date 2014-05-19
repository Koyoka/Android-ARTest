package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskAttachmentSoundData {
	public static final String TblName = "AssessTaskAttachmentSound";
	
	public static final String Col_Id = "Id";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_CateId = "CateId";
	public static final String Col_SoundPath = "SoundPath";



	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_TaskHeaderId,
				Col_CateId,
				Col_SoundPath
				};
	}
	
	public int Id = 0;
	public int TaskHeaderId = 0;
	public int CateId = 0;
	public String SoundPath = "";



	public static AssessTaskAttachmentSoundData convertDataToModule(Cursor c){
		AssessTaskAttachmentSoundData item = new AssessTaskAttachmentSoundData();
		item.Id = DBMng.getDataInt(c, Col_Id);
		item.TaskHeaderId = DBMng.getDataInt(c, Col_TaskHeaderId);
		item.CateId = DBMng.getDataInt(c, Col_CateId);
		item.SoundPath = DBMng.GetDataString(c, Col_SoundPath);

		return item;
	}
	
	public static ContentValues getContentValues(AssessTaskAttachmentSoundData data){
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_CateId,data.CateId);
		values.put(Col_SoundPath,data.SoundPath);

		return values;
	}
	
	
}
