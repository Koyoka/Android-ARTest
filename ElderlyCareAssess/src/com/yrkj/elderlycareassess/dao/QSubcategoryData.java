package com.yrkj.elderlycareassess.dao;

import java.util.ArrayList;


public class QSubcategoryData {
	public int SubcateId = 0;
	public String SubcateName = "";
	public ArrayList<QItemData> ItemList = 
			new ArrayList<QItemData>();
	
	public ArrayList<QItemLabData> ItemLabList
	 = new ArrayList<QItemLabData>();
}
