package com.yrkj.elderlycareassess.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskAttachmentDiseaseData {
	public static final String TblName = "AssessTaskAttachmentDisease";
	
	public static final String Col_Id = "Id";
	public static final String Col_TaskHeaderId = "TaskHeaderId";
	public static final String Col_CateId = "CateId";
	public static final String Col_DiseaseName = "DiseaseName";
	public static final String Col_SickDate = "SickDate";
	public static final String Col_IsMedication = "IsMedication";
	public static final String Col_DiseaseDesc = "DiseaseDesc";




	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_TaskHeaderId,
				Col_CateId,
				Col_DiseaseName,
				Col_SickDate,
				Col_IsMedication,
				Col_DiseaseDesc
				};
	}
	
	public String Id = "";
	public int TaskHeaderId = 0;
	public int CateId = 0;
	public String DiseaseName = "";
	public String SickDate = "";
	public boolean IsMedication = false;
	public String DiseaseDesc = "";

	public static AssessTaskAttachmentDiseaseData convertDataToModule(Cursor c){
		AssessTaskAttachmentDiseaseData item = new AssessTaskAttachmentDiseaseData();
		item.Id = DBMng.GetDataString(c, Col_Id);
		item.TaskHeaderId = DBMng.getDataInt(c, Col_TaskHeaderId);
		item.CateId = DBMng.getDataInt(c, Col_CateId);
		item.DiseaseName = DBMng.GetDataString(c, Col_DiseaseName);
		item.SickDate = DBMng.GetDataString(c, Col_SickDate);
		item.IsMedication = DBMng.getDataBoolean(c, Col_IsMedication);
		item.DiseaseDesc = DBMng.GetDataString(c, Col_DiseaseDesc);

		return item;
	}
	
	public static ContentValues getContentValues(AssessTaskAttachmentDiseaseData data){
		ContentValues values = new ContentValues();
		
//		values.put(Col_Id,data.Id);
		values.put(Col_TaskHeaderId,data.TaskHeaderId);
		values.put(Col_CateId,data.CateId);
		values.put(Col_DiseaseName,data.DiseaseName);
		values.put(Col_SickDate,data.SickDate);
		values.put(Col_IsMedication,data.IsMedication);
		values.put(Col_DiseaseDesc,data.DiseaseDesc);


		return values;
	}
	
	
}
