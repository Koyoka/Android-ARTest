package com.yrkj.yrkjdemo.dao;

import android.content.Context;

import com.yrkj.util.db.DBMng;
import com.yrkj.util.db.DBUpdate.OnDBUpdateListen;

public class DemoDBMng extends DBMng{

	public DemoDBMng(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static boolean init(Context c,OnDBUpdateListen l){
		return new DemoDBMng(c).initDB2(l);
	}

	@Override
	protected String getDBName() {
		// TODO Auto-generated method stub
		return "demodb";
	}

	@Override
	protected int getDBVersion() {
		// TODO Auto-generated method stub
		return 8;
	}

}
