package com.yrkj.elderlycareassess.base;

import com.yrkj.util.db.DBMng;

import android.content.Context;


public class ECAQuesDBMng extends DBMng {

	
	
	
	public static boolean init(Context c,boolean reCreate){
		return new ECAQuesDBMng(c).initDB(reCreate);
	}
	
	public ECAQuesDBMng(Context context,String packageName) {
		super(context,packageName);
		initDB(false);
	}
	
	public ECAQuesDBMng(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String getDBName() {
		// TODO Auto-generated method stub
		return "ecaassessquestion";
	}

	@Override
	protected int getDBVersion() {
		// TODO Auto-generated method stub
//		return 4;
		return 3;
//		return 2;
//		return 1;
	}

}
