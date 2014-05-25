package com.yrkj.elderlycareassess.base;

import com.yrkj.util.db.DBMng;
import com.yrkj.util.db.SqlDBMng;

import android.content.Context;


public class TestDBMng extends SqlDBMng {

	
	
	
	public static boolean init(Context c,boolean reCreate){
		return new TestDBMng(c).initDB(reCreate);
	}
	
	public TestDBMng(Context context,String packageName) {
		super(context,packageName);
		initDB(false);
	}
	
	public TestDBMng(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String getDBName() {
		// TODO Auto-generated method stub
		return "test";
	}

	@Override
	protected int getDBVersion() {
		// TODO Auto-generated method stub
//		return 4;
//		return 3;
//		return 2;
		return 1;
	}

}
