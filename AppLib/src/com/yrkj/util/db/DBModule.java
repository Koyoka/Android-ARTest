package com.yrkj.util.db;

import android.content.ContentValues;


public abstract class DBModule {
	public static String[] Columns = null;
	
	protected abstract String[] GetColumns();
	public abstract ContentValues GetContentValues();
	
	public void TblBaseModule(){
		Columns = GetColumns();
	}
	
	
}
