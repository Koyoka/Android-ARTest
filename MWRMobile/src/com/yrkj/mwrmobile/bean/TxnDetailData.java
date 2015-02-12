package com.yrkj.mwrmobile.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class TxnDetailData {
		public static final String TblName = "TxnDetail";
		
		public static final String Col_TxnDetailId = "TxnDetailId";
		public static final String Col_TxnType = "TxnType";
		public static final String Col_TxnNum = "TxnNum";
		public static final String Col_WSCode = "WSCode";
		public static final String Col_EmpyName = "EmpyName";
		public static final String Col_EmpyCode = "EmpyCode";
		public static final String Col_CrateCode = "CrateCode";
		public static final String Col_Vendor = "Vendor";
		public static final String Col_VendorCode = "VendorCode";
		public static final String Col_Waste = "Waste";
		public static final String Col_WasteCode = "WasteCode";
		public static final String Col_SubWeight = "SubWeight";
		public static final String Col_TxnWeight = "TxnWeight";
		public static final String Col_EntryDate = "EntryDate";
		public static final String Col_InvRecordId = "InvRecordId";
		public static final String Col_InvAuthId = "InvAuthId";
		public static final String Col_Status = "Status";

		
		public static String[] getColumnColl(){
			return new String[]{
					Col_TxnDetailId,
					Col_TxnType,
					Col_TxnNum,
					Col_WSCode,
					Col_EmpyName,
					Col_EmpyCode,
					Col_CrateCode,
					Col_Vendor,
					Col_VendorCode,
					Col_Waste,
					Col_WasteCode,
					Col_SubWeight,
					Col_TxnWeight,
					Col_EntryDate,
					Col_InvRecordId,
					Col_InvAuthId,
					Col_Status
					};
		}
		
		public String TxnDetailId = "";
		public String TxnType = "";
		public String TxnNum = "";
		public String WSCode = "";
		public String EmpyName = "";
		public String EmpyCode = "";
		public String CrateCode = "";
		public String Vendor = "";
		public String VendorCode = "";
		public String Waste = "";
		public String WasteCode = "";
		public String SubWeight = "";
		public String TxnWeight = "";
		public String EntryDate = "";
		public String InvRecordId = "";
		public String InvAuthId = "";
		public String Status = "";

		
		public static TxnDetailData convertDataToModule(Cursor c){
			TxnDetailData item = new TxnDetailData();
			item.TxnDetailId = DBMng.GetDataString(c, Col_TxnDetailId);
			item.TxnType = DBMng.GetDataString(c, Col_TxnType);
			item.TxnNum = DBMng.GetDataString(c, Col_TxnNum);
			item.WSCode = DBMng.GetDataString(c, Col_WSCode);
			item.EmpyName = DBMng.GetDataString(c, Col_EmpyName);
			item.EmpyCode = DBMng.GetDataString(c, Col_EmpyCode);
			item.CrateCode = DBMng.GetDataString(c, Col_CrateCode);
			item.Vendor = DBMng.GetDataString(c, Col_Vendor);
			item.VendorCode = DBMng.GetDataString(c, Col_VendorCode);
			item.Waste = DBMng.GetDataString(c, Col_Waste);
			item.WasteCode = DBMng.GetDataString(c, Col_WasteCode);
			item.SubWeight = DBMng.GetDataString(c, Col_SubWeight);
			item.TxnWeight = DBMng.GetDataString(c, Col_TxnWeight);
			item.EntryDate = DBMng.GetDataString(c, Col_EntryDate);
			item.InvRecordId = DBMng.GetDataString(c, Col_InvRecordId);
			item.InvAuthId = DBMng.GetDataString(c, Col_InvAuthId);
			item.Status = DBMng.GetDataString(c, Col_Status);
	
			return item;
		}
		
		public static ContentValues getContentValues(TxnDetailData data){
			ContentValues values = new ContentValues();
			values.put(Col_TxnDetailId,data.TxnDetailId);
			values.put(Col_TxnType,data.TxnType);
			values.put(Col_TxnNum,data.TxnNum);
			values.put(Col_WSCode,data.WSCode);
			values.put(Col_EmpyName,data.EmpyName);
			values.put(Col_EmpyCode,data.EmpyCode);
			values.put(Col_CrateCode,data.CrateCode);
			values.put(Col_Vendor,data.Vendor);
			values.put(Col_VendorCode,data.VendorCode);
			values.put(Col_Waste,data.Waste);
			values.put(Col_WasteCode,data.WasteCode);
			values.put(Col_SubWeight,data.SubWeight);
			values.put(Col_TxnWeight,data.TxnWeight);
			values.put(Col_EntryDate,data.EntryDate);
			values.put(Col_InvRecordId,data.InvRecordId);
			values.put(Col_InvAuthId,data.InvAuthId);
			values.put(Col_Status,data.Status);

			return values;
		}

}
