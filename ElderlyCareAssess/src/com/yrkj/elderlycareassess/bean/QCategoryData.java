package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;


public class QCategoryData {
	public static final String TblName = "QCategory";
	public static final String Col_CateId = "CateId";
	public static final String Col_CateName = "CateName";
	public static final String Col_SortIndex = "SortIndex";
	public static final String Col_IsActive = "IsActive";
	
	public static final String ISACTIVE_YES = "1"; 
//	public int CateId = 0;
	public String CateId = "";
	public String CateName = "";
//	public int SortIndex = 0;
	public String SortIndex = "";
//	public boolean IsActive = false;
	public String IsActive = "";
	
	public ArrayList<QSubcategoryData> SubcateList = 
			new ArrayList<QSubcategoryData>();
}
