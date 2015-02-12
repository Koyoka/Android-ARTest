package com.yrkj.mwrmobile.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class TxnHeaderData {
	
	public static final String TblName = "TxnHeader";
	
	public static final String Col_RecoHeaderId = "RecoHeaderId";
	public static final String Col_TxnNum = "TxnNum";
	public static final String Col_CarCode = "CarCode";
	public static final String Col_Driver = "Driver";
	public static final String Col_DriverCode = "DriverCode";
	public static final String Col_Inspector = "Inspector";
	public static final String Col_InspectorCode = "InspectorCode";
	public static final String Col_RecoMWSCode = "RecoMWSCode";
	public static final String Col_RecoWSCode = "RecoWSCode";
	public static final String Col_RecoEmpyName = "RecoEmpyName";
	public static final String Col_RecoEmpyCode = "RecoEmpyCode";
	public static final String Col_StartDate = "StartDate";
	public static final String Col_EndDate = "EndDate";
	public static final String Col_OperateType = "OperateType";
	public static final String Col_TotalCrateQty = "TotalCrateQty";
	public static final String Col_TotalSubWeight = "TotalSubWeight";
	public static final String Col_TotalTxnWeight = "TotalTxnWeight";
	public static final String Col_CarDisId = "CarDisId";
	public static final String Col_Status = "Status";

	
	public static String[] getColumnColl(){
		return new String[]{
				Col_RecoHeaderId,
				Col_TxnNum,
				Col_CarCode,
				Col_Driver,
				Col_DriverCode,
				Col_Inspector,
				Col_InspectorCode,
				Col_RecoMWSCode,
				Col_RecoWSCode,
				Col_RecoEmpyName,
				Col_RecoEmpyCode,
				Col_StartDate,
				Col_EndDate,
				Col_OperateType,
				Col_TotalCrateQty,
				Col_TotalSubWeight,
				Col_TotalTxnWeight,
				Col_CarDisId,
				Col_Status
				};
	}
	
	public String RecoHeaderId = "";
	public String TxnNum = "";
	public String CarCode = "";
	public String Driver = "";
	public String DriverCode = "";
	public String Inspector = "";
	public String InspectorCode = "";
	public String RecoMWSCode = "";
	public String RecoWSCode = "";
	public String RecoEmpyName = "";
	public String RecoEmpyCode = "";
	public String StartDate = "";
	public String EndDate = "";
	public String OperateType = "";
	public String TotalCrateQty = "";
	public String TotalSubWeight = "";
	public String TotalTxnWeight = "";
	public String CarDisId = "";
	public String Status = "";

	
	public static TxnHeaderData convertDataToModule(Cursor c){
		TxnHeaderData item = new TxnHeaderData();
		item.RecoHeaderId = DBMng.GetDataString(c, Col_RecoHeaderId);
		item.TxnNum = DBMng.GetDataString(c, Col_TxnNum);
		item.CarCode = DBMng.GetDataString(c, Col_CarCode);
		item.Driver = DBMng.GetDataString(c, Col_Driver);
		item.DriverCode = DBMng.GetDataString(c, Col_DriverCode);
		item.Inspector = DBMng.GetDataString(c, Col_Inspector);
		item.InspectorCode = DBMng.GetDataString(c, Col_InspectorCode);
		item.RecoMWSCode = DBMng.GetDataString(c, Col_RecoMWSCode);
		item.RecoWSCode = DBMng.GetDataString(c, Col_RecoWSCode);
		item.RecoEmpyName = DBMng.GetDataString(c, Col_RecoEmpyName);
		item.RecoEmpyCode = DBMng.GetDataString(c, Col_RecoEmpyCode);
		item.StartDate = DBMng.GetDataString(c, Col_StartDate);
		item.EndDate = DBMng.GetDataString(c, Col_EndDate);
		item.OperateType = DBMng.GetDataString(c, Col_OperateType);
		item.TotalCrateQty = DBMng.GetDataString(c, Col_TotalCrateQty);
		item.TotalSubWeight = DBMng.GetDataString(c, Col_TotalSubWeight);
		item.TotalTxnWeight = DBMng.GetDataString(c, Col_TotalTxnWeight);
		item.CarDisId = DBMng.GetDataString(c, Col_CarDisId);
		item.Status = DBMng.GetDataString(c, Col_Status);

		return item;
	}
	
	public static ContentValues getContentValues(TxnHeaderData data){
		ContentValues values = new ContentValues();
		values.put(Col_RecoHeaderId,data.RecoHeaderId);
		values.put(Col_TxnNum,data.TxnNum);
		values.put(Col_CarCode,data.CarCode);
		values.put(Col_Driver,data.Driver);
		values.put(Col_DriverCode,data.DriverCode);
		values.put(Col_Inspector,data.Inspector);
		values.put(Col_InspectorCode,data.InspectorCode);
		values.put(Col_RecoMWSCode,data.RecoMWSCode);
		values.put(Col_RecoWSCode,data.RecoWSCode);
		values.put(Col_RecoEmpyName,data.RecoEmpyName);
		values.put(Col_RecoEmpyCode,data.RecoEmpyCode);
		values.put(Col_StartDate,data.StartDate);
		values.put(Col_EndDate,data.EndDate);
		values.put(Col_OperateType,data.OperateType);
		values.put(Col_TotalCrateQty,data.TotalCrateQty);
		values.put(Col_TotalSubWeight,data.TotalSubWeight);
		values.put(Col_TotalTxnWeight,data.TotalTxnWeight);
		values.put(Col_CarDisId,data.CarDisId);
		values.put(Col_Status,data.Status);

		return values;
	}

	
	
	
}
