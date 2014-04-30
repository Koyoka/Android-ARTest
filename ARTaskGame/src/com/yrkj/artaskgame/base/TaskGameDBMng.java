package com.yrkj.artaskgame.base;

import com.yrkj.util.db.DBMng;

import android.content.Context;


public class TaskGameDBMng extends DBMng {

	
	
	
	public static boolean init(Context c,boolean reCreate){
		return new TaskGameDBMng(c).initDB(reCreate);
	}
	
	public TaskGameDBMng(Context context,String packageName) {
		super(context,packageName);
		initDB(false);
	}
	
	public TaskGameDBMng(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String getDBName() {
		// TODO Auto-generated method stub
		return "dbtaskgame";
	}

	@Override
	protected int getDBVersion() {
		// TODO Auto-generated method stub
		return 4;
//		return 3;
//		return 2;
//		return 1;
	}

}
