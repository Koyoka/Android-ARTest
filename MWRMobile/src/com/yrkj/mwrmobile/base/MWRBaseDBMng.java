package com.yrkj.mwrmobile.base;

import android.content.Context;

import com.yrkj.util.db.DBMng;
import com.yrkj.util.db.DBUpdate.OnDBUpdateListen;

public class MWRBaseDBMng extends DBMng{

	public MWRBaseDBMng(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public static boolean init(Context c,OnDBUpdateListen l){
		return new MWRBaseDBMng(c).initDB2(l);
	}

	@Override
	protected String getDBName() {
		// TODO Auto-generated method stub
		return "mwrbasedb";
	}

	@Override
	protected int getDBVersion() {
		// TODO Auto-generated method stub
		return 1;
	}

}
