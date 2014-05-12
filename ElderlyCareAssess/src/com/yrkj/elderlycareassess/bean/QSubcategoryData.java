package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;


public class QSubcategoryData {
	
	public static final String TblName = "QSubcategory";
	public static final String Col_SubcateId = "SubcateId";
	public static final String Col_SubcateName = "SubcateName";
	public static final String Col_UseType = "UseType";
	public static final String Col_SortIndex = "SortIndex";
	
	public static final String USER_TYPE_ONLY_CHECK = "OC";
	public static final String USER_TYPE_ONLY_RADIO = "OR";
	public static final String USER_TYPE_ONLY_SUBCATGORY = "OS";
	public static final String USER_TYPE_ALTERNATIVE_CHECK = "AC";
//	public static final String USER_TYPE_ONLY_CHECK = "OC";
	
//	public int SubcateId = 0;
	public String SubcateId = "";
	public String SubcateName = "";
	public String UseType = "";
	public String SortIndex = "";
	
	
	public ArrayList<QItemData> ItemList = 
			new ArrayList<QItemData>();
	
	public ArrayList<QItemTagData> ItemLabList
	 = new ArrayList<QItemTagData>();
	
	public ArrayList<QSubcategoryData> ItemSubList
		= new ArrayList<QSubcategoryData>();
	
	//Alternative 
	//Only
	
	//Check
	//Radio
	//Subcate
}
