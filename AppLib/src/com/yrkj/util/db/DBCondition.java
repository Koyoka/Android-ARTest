package com.yrkj.util.db;

public class DBCondition {
	public String Selection = null;
	public String[] SelectionArgs = null; 
	public String GroupBy = null;  
	public String Having = null; 
	public String OrderBy = null;  
	public String Limit = null; 
	public String LimitStrat = "";
	public String LimitEnd = "";
	public int PageSize = 0;
	public int Page = 0;
	public void setPage(int page,int pageSize,String sortColName){
		OrderBy = sortColName;
		PageSize = pageSize;
		Page = page;
		
	}
}
