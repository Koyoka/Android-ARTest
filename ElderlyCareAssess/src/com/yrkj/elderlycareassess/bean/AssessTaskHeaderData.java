package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;

import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class AssessTaskHeaderData {

	public static final String ASSESS_STATE_DOING = "OFF";
	public static final String ASSESS_STATE_DONE = "ON";
	public static final String ASSESS_TYPE_FIRST = "F";
	public static final String ASSESS_TYPE_REVIEW = "R";
	public static final String ASSESS_TYPE_CONTINUE = "C";
	
	public static final String getAssessTypeDesc(String s){
		String defineS = "";
		if(s.equals(ASSESS_TYPE_FIRST)){
			defineS = "首次评估"; 
		}else if(s.equals(ASSESS_TYPE_REVIEW)){
			defineS = "符合评估"; 
		}else if(s.equals(ASSESS_TYPE_CONTINUE)){
			defineS = "持续评估"; 
		}
		return defineS;
	}
	
	public static final String TblName = "AssessTaskHeader";
	
	public static final String Col_Id = "Id";
	public static final String Col_AssessNum = "AssessNum";
	public static final String Col_CustId = "CustId";
	public static final String Col_CreateDate = "CreateDate";
	public static final String Col_EndAssessDate = "EndAssessDate";
	public static final String Col_LastAssessDate = "LastAssessDate";
	public static final String Col_AssessType = "AssessType";
	public static final String Col_AssessState = "AssessState";

	
	public static String[] getColumnColl(){
		return new String[]{
				Col_Id,
				Col_AssessNum,
				Col_CustId,
				Col_CreateDate,
				Col_EndAssessDate,
				Col_LastAssessDate,
				Col_AssessType,
				Col_AssessState
				};
	}
	
	public String Id = "";
	public String AssessNum = "";
	public String CustId = "";
	public String CreateDate = "";
	public String EndAssessDate = "";
	public String LastAssessDate = "";
	public String AssessType = "";
	public String AssessState = "";

	
	public static AssessTaskHeaderData convertDataToModule(Cursor c){
		AssessTaskHeaderData item = new AssessTaskHeaderData();
		item.Id = DBMng.GetDataString(c, Col_Id);
		item.AssessNum = DBMng.GetDataString(c, Col_AssessNum);
		item.CustId = DBMng.GetDataString(c, Col_CustId);
		item.CreateDate = DBMng.GetDataString(c, Col_CreateDate);
		item.EndAssessDate = DBMng.GetDataString(c, Col_EndAssessDate);
		item.LastAssessDate = DBMng.GetDataString(c, Col_LastAssessDate);
		item.AssessType = DBMng.GetDataString(c, Col_AssessType);
		item.AssessState = DBMng.GetDataString(c, Col_AssessState);

		return item;
	}
}
