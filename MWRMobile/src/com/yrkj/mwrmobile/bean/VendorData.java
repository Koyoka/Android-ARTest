package com.yrkj.mwrmobile.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.util.db.DBMng;

public class VendorData {
	public static final String TblName = "MWVendor";
	
	public static final String Col_VendorCode = "VendorCode";
	public static final String Col_Vendor = "Vendor";
	public static final String Col_Address = "Address";

	
	public static String[] getColumnColl(){
		return new String[]{
				Col_VendorCode,
				Col_Vendor,
				Col_Address
				};
	}
	
	public String VendorCode = "";
	public String Vendor = "";
	public String Address = "";

	public static VendorData convertDataToModule(Cursor c){
		VendorData item = new VendorData();
		item.VendorCode = DBMng.GetDataString(c, Col_VendorCode);
		item.Vendor = DBMng.GetDataString(c, Col_Vendor);
		item.Address = DBMng.GetDataString(c, Col_Address);

		return item;
	}
	
	public static ContentValues getContentValues(VendorData data){
		ContentValues values = new ContentValues();
		values.put(Col_VendorCode,data.VendorCode);
		values.put(Col_Vendor,data.Vendor);
		values.put(Col_Address,data.Address);

		return values;
	}
	
}
