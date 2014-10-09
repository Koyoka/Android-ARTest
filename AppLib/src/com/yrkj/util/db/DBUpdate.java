package com.yrkj.util.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yrkj.util.file.FileHelper;
import com.yrkj.util.log.DLog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

public class DBUpdate {

	public interface OnDBUpdateListen{
		public void begin();
		public void end();
		public void l(String s);
	}
	private OnDBUpdateListen mOnDBUpdateListen = null;
	
	private final String TEMP_DBEXNAME = "_temp_dbupdate_ver_";
	private final String TEMP_FIX = "_temp"; 
	private final String regEx = "\\[(.*?)\\]";
	
//	public DBHelper mDBHelper = null;
	public Context mContext = null;
	public String mDBName = "";
	public String mTempDBName = "";
	public int mDBVer = 0;
	
	public DBUpdate(Context c,String dbName,int ver,OnDBUpdateListen l){
//		mDBHelper = dh;
		mContext = c;
		mDBName = dbName;
		mDBVer = ver;
		mOnDBUpdateListen = l;
	}
	
	Handler mH = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 0){
				String s = msg.obj.toString();
				if(mOnDBUpdateListen!= null)
					mOnDBUpdateListen.l(s);
			}else if(msg.what == 1){
				if(mOnDBUpdateListen!= null)
					mOnDBUpdateListen.end();
			}else if(msg.what == 2){
				if(mOnDBUpdateListen!= null)
					mOnDBUpdateListen.begin();
			}
			
		}
	};
	
	public boolean update(){
		
		
		
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				beginListen();
				mTempDBName = mDBName + TEMP_DBEXNAME + mDBVer;
				printListen("初始化数据");
				if (!createNewDBTempFile()) {
					printListen("初始化数据错误");
					// return false;
				}
				printListen("初始化数据完成");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printListen("开始数据更新");
				if (!updateDBData()) {
					printListen("数据更新失败");
					// return false;
				}
				printListen("数据更新完成");
				finishListen();
//				mOnDBUpdateListen.l("", true);
				// if(!delTempDB())
				// return false;
			}
		}.start();
		return true;
	}
	
	private void printListen(String s){
		
		Message msg = new Message();      
		msg.what = 0;
		msg.obj = s;
		mH.sendMessage(msg);
		
//		if(mOnDBUpdateListen!= null)
//			mOnDBUpdateListen.l(s,false);
	}
	private void beginListen(){
		Message msg = new Message();      
		msg.what = 2;
		mH.sendMessage(msg);
	}
	private void finishListen(){
		Message msg = new Message();      
		msg.what = 1;
		mH.sendMessage(msg);
	}
	
	private String mTempDBFile = "";
	private String mDBFile = "";
	
	private boolean createNewDBTempFile(){
		
		String DATABASEDIR = "/data/data/" + mContext.getPackageName() + "/databases";
		mDBFile = 
				DATABASEDIR+"/"
				+mDBName;//+DB_EXPNAme;
		mTempDBFile = 
				DATABASEDIR+"/"
				+mTempDBName;
		
		if(new File(mTempDBFile).exists()){
			return true;
		}
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = mContext.getAssets().open(mDBName);
			outputStream = new FileOutputStream(mTempDBFile);
			if(!FileHelper.CopyFile(inputStream, outputStream)){
//			if(!FileHelper.CopyFile(inputStream, new FileOutputStream(mTempDBFile))){
				DLog.LOG("CopyFile err");
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			if(inputStream != null){
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			}
		}
		
		return true;
	}
	private boolean updateDBData(){
		
		DBHelper oldDH = new DBHelper(mContext, mDBName, mDBVer);
		
		String tableSql = "Select * From MAIN.[sqlite_master] where type = 'table' and tbl_name not like 'sqlite_%' --and tbl_name='test' limit 0,1";
		
		Map oldTables = new HashMap<String, String>();{
			SQLiteDatabase oldDB = oldDH.getWritableDatabase();
			
			Cursor cursor = null;
			cursor = oldDB.rawQuery(tableSql, null);
			
			printListen("开始计算旧数据量");
			if(cursor.moveToFirst()){
				do{
					String tabName = cursor.getString( cursor.getColumnIndex("name"));//("name");
					String createSql = cursor.getString(cursor.getColumnIndex("sql"));
					oldTables.put(tabName, createSql);
				}while (cursor.moveToNext()) ;
			}
			printListen("旧数据量计算完成");
			cursor.close();
			oldDB.close();
			oldDH.close();
		}
		
		DBHelper tempDH = new DBHelper(mContext, mTempDBName, mDBVer);
		{
			SQLiteDatabase tempDB = tempDH.getWritableDatabase();
			
			Cursor cursor = null;
			cursor = tempDB.rawQuery(tableSql, null);
			
			printListen("开始数据更新");
			int total = cursor.getCount();
			int i=1;
			printListen("开始数据更新-0%");
			if(cursor.moveToFirst()){
				do{
					
//					(int)( (i/total)*100);
					
					String tabName = cursor.getString( cursor.getColumnIndex("name"));//("name");
					String newCreateSql = cursor.getString(cursor.getColumnIndex("sql"));

					if(oldTables.get(tabName) == null){
						 ArrayList<String> sqls = new ArrayList<String>();
						 sqls.add(newCreateSql);
						 updateTable(sqls);
					 }else{
						 String oldCreateSql = oldTables.get(tabName).toString();
						 updateTable( getUpdateTableSql(tabName,newCreateSql,oldCreateSql));
					 }
					printListen("开始数据更新-"+(int)( (i/total)*100)+"%");
					i++;
					
				}while (cursor.moveToNext()) ;
			}
			printListen("数据更新完成");
			cursor.close();
			tempDB.close();
			tempDH.close();
			
		}
		
		return true;
	}
	
	private boolean delTempDB(){
		
		return true;
	}

	private void updateTable(ArrayList<String> sqls){
		DBHelper oldDH = new DBHelper(mContext, mDBName, mDBVer);
		{
			
			
			SQLiteDatabase oldDB = oldDH.getWritableDatabase();
			
			oldDB.beginTransaction();
			try {

				for (String s : sqls) {
					oldDB.execSQL(s);
				}
				
				oldDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			} finally {
				oldDB.endTransaction();
				oldDB.close();
				oldDH.close();
			}
		}
		
//		Connection conn = null;
//		Statement stat = null;
//		try {
//			conn = getConnection(mConn);
//			stat = conn.createStatement();
//			conn.setAutoCommit(false);  
//			for (String s : sqls) {
//				
//				stat.addBatch(s);
//				System.out.print(s+"\r\n");
//			}
//			stat.executeBatch();
//			conn.commit();// 提交  
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {  
//				conn.rollback();// 回滚  
//            } catch (SQLException e1) {  
//                e1.printStackTrace();  
//            }  
//		}finally {  
//            if (stat != null) {  
//                try {  
//                	stat.close();  
//                } catch (SQLException e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//            if(conn != null){
//            	
//            	try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//            }
//        }  
	}
	private  ArrayList<String> getUpdateTableSql(String tabName,String newCreateSql,String oldCreateSql){
		ArrayList<String> sqls = new ArrayList<String>();
//		StringBuilder sb = new StringBuilder();
//		appendLine(sb,"Begin Transaction;");
//		appendLine(sb,"");
		
//		1.=====  创建更新零时表
		String sql1 = getNewTableTempSql(tabName,newCreateSql);
		sqls.add(sql1);
////		2.=====  向零时表中导入旧数据
		String sql2 = getSyncDataSql(tabName,oldCreateSql);
		sqls.add(sql2);
////		3.=====	  删除旧表
		String sql3 = "Drop Table ["+tabName+"];";
		sqls.add(sql3);
//		appendLine(sb,);
////		4.=====  更新零时表名称
		String sql4 = "Alter Table ["+ tabName + TEMP_FIX +"] Rename To ["+tabName+"];";
		sqls.add(sql4);
//		appendLine(sb,);
//		
//		
//		appendLine(sb,"Commit Transaction;");
		
		return sqls;
	}
	
	private String getNewTableTempSql(String tabName,String createSql){
		String sql = createSql.replaceFirst(tabName, tabName + TEMP_FIX)+";";
//		appendLine(sb,sql);
//		appendLine(sb,"");
		return sql;
	}
	
	private String getSyncDataSql(String tabName,String createSql){
		StringBuilder sb = new StringBuilder();
		ArrayList<String> fields = getFields(tabName,createSql);
		String fieldsSql = "";
		for(int i = 0; i<fields.size();i++){
			String s = fields.get(i);
			fieldsSql += (s + ((i==(fields.size()-1))?"":", "));
		}
		
		appendLine(sb,"Insert Into [" + tabName + TEMP_FIX + "] ");
		sb.append("( ");
		sb.append(fieldsSql);
		sb.append(") ");
		appendLine(sb,"");
		sb.append("Select ");
		sb.append(fieldsSql);		
		appendLine(sb,"");
		appendLine(sb,"From ["+tabName+"]; ");
		return sb.toString();
		
	}
	private void appendLine(StringBuilder sb ,String s){
		sb.append(s);
		sb.append("");
	}
	private ArrayList<String> getFields(String tabName,String createSql){
		Pattern pat = Pattern.compile(regEx);  
		String s = createSql.replaceFirst("\\["+tabName+"\\]", "");
//		System.out.println(s);
		Matcher mat = pat.matcher(s);  
		
		ArrayList<String> items = new ArrayList<String>();
		while (mat.find()) {
//			System.out.println(mat.group(0));     
			items.add(mat.group(0));
        } 
		
		return items;
	}
}
