package com.yrkj.elderlycareassess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class t {

	static final String mConn = "E:/document/db/a.db";
	static final String mNewConn = "E:/document/db/b.db";
	static final String TEMP_FIX = "_temp"; 
	static final String regEx = "\\[(.*?)\\]";
	
	
	
	public static void main(String[] args) {
//		String s = " [aa] [bbb] [cc]";
////		String regEx = "\\[(.*?)\\]"; //表示a或F  
//		Pattern pat = Pattern.compile(regEx);  
//		Matcher mat = pat.matcher(s);  
////		System.out.println(mat.find());
////		System.out.println(mat.group()); 
//		while (mat.find()) {
//			
//			System.out.println(mat.group(0));            
//        } 
		t();
//		String sql = "Select * From [QItem] where itemid in(Select itemid From [QSubcategoryDetail] where subcateid = {0} and itemtype = ''{1}'')";
//        
////		MessageFormat s = new MessageFormat(sql);
//		sql = MessageFormat.format(sql,"aaaa","bb");
////		sql = String.format(sql, "aaa","bb");
//		
////		System.out.println(0&1);
////		System.out.println(2&2);
////		System.out.println(4&4);
//		int i = 13;//1|0|4|8;//|15;
//		System.out.println(i);
//		System.out.println(i&1);
//		System.out.println(i&2);
//		System.out.println(i&4);
//		System.out.println(i&8);
////		void a(){
//			Gson gson = new Gson();
//			AssessTaskHeaderData d = new AssessTaskHeaderData();
//			d.AssessNum = "111";
//			d.AssessState = "dd";
//			 String s = gson.toJson(d);
////			 DLog.LOG(SysMng.TAG_DB,s);
//			 System.out.println(s);
//
////		}
////		System.out.println(i&7);
////		System.out.println(i&15);
	}
	
	
	
	
	private static void t(){
		
//		if(conn == null || newConn == null){
//			return;
//		}
		
		Map oldTables = new HashMap<String, String>();
		
		String tableSql = "Select * From MAIN.[sqlite_master] where type = 'table' and tbl_name='test' limit 0,1";
		try {
			
			{
				Connection conn = getConnection(mConn);
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(tableSql);
				
				while (rs.next()) {
					 String tabName = rs.getString("name");
					 String createSql = rs.getString("sql");
					 oldTables.put(tabName, createSql);
	//				 String sql = updateTable(tabName,createSql);
	//				 System.out.print(sql);
				}
				rs.close();
			    conn.close(); 
			}
		    
		    
			StringBuilder sb = new StringBuilder();
			{
				Connection newConn = getConnection(mNewConn);
				Statement stat = newConn.createStatement();
				ResultSet rs = stat.executeQuery(tableSql);
				while (rs.next()) {
					 String tabName = rs.getString("name");
					 String newCreateSql = rs.getString("sql");
					 if(oldTables.get(tabName) == null){
						 ArrayList<String> sqls = new ArrayList<String>();
						 sqls.add(newCreateSql);
						 updateTable(sqls);
					 }else{
						 String oldCreateSql = oldTables.get(tabName).toString();
						 updateTable( getUpdateTableSql(tabName,newCreateSql,oldCreateSql));
					 }
				}
				rs.close();
				newConn.close(); 
			}
		    
		    
			
//			{
//				Connection conn = getConnection(mConn);
//				Statement stat = conn.createStatement();
//				
//				stat.addBatch("");
//				
//				stat.addBatch("");
//				
//				boolean res = stat.execute(sb.toString());
////				stat.executeBatch();
//			    conn.close(); 
//			    
//			    System.out.print(res?"更新成功":"更新失败");
//			}
//			System.out.print(sb.toString());
		    
		    
		    
		    
		    
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.print("err------------");
		}
		
	}
	
	private static void updateTable(ArrayList<String> sqls){
		Connection conn = null;
		Statement stat = null;
		try {
			conn = getConnection(mConn);
			stat = conn.createStatement();
			conn.setAutoCommit(false);  
			for (String s : sqls) {
				
				stat.addBatch(s);
				System.out.print(s+"\r\n");
			}
			stat.executeBatch();
			conn.commit();// 提交  
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {  
				conn.rollback();// 回滚  
            } catch (SQLException e1) {  
                e1.printStackTrace();  
            }  
		}finally {  
            if (stat != null) {  
                try {  
                	stat.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if(conn != null){
            	
            	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            }
        }  
		
	    
	}
	
	public static void appendLine(StringBuilder sb ,String s){
		
		sb.append(s);
		sb.append("");
	}
	
	public static ArrayList<String> getUpdateTableSql(String tabName,String newCreateSql,String oldCreateSql){
		ArrayList<String> sqls = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
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
	private static String getNewTableTempSql(String tabName,String createSql){
		String sql = createSql.replaceFirst(tabName, tabName + TEMP_FIX)+";";
//		appendLine(sb,sql);
//		appendLine(sb,"");
		return sql;
	}
	
	private static String getSyncDataSql(String tabName,String createSql){
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
	
	public static ArrayList<String> getFields(String tabName,String createSql){
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
	
	public static Connection getConnection(String connStr) {
		Connection conn = null;
		try {
			// 连接SQLite的JDBC
			Class.forName("org.sqlite.JDBC");
			// 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
			// String filename = "c:/IPaddress.db";
			conn = DriverManager.getConnection("jdbc:sqlite:/"+connStr);
			/*
			 * Statement stat = conn.createStatement();
			 * 
			 * stat.executeUpdate(sql);// 创建一个表，两列
			 */
		} catch (Exception e) {
			System.out.print("err------------"+ e.toString());
		}
		return conn;
	}
	
	
	

}
















