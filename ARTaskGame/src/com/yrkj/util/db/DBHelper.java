package com.yrkj.util.db;

import com.yrkj.util.log.DebugLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper{
	private Context mcontext = null;
	public DBHelper(Context context,String dbName,int dbVersion) {
		super(context,  dbName, null, dbVersion);
		mcontext = context;
		
		DebugLog.LOG(dbVersion + " = DB_VERSION");
		// TODO Auto-generated constructor stub
	}
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		MessageBox.getInstance(mcontext).Show("DBHelper onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(newVersion > oldVersion){
			NeedUpdate = true;
		}
	}
	
	public boolean NeedUpdate = false;

}