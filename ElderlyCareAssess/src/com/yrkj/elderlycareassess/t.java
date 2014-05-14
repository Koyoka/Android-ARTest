package com.yrkj.elderlycareassess;

import java.text.MessageFormat;

public class t {

	public static void main(String[] args) {
		String sql = "Select * From [QItem] where itemid in(Select itemid From [QSubcategoryDetail] where subcateid = {0} and itemtype = ''{1}'')";
        
//		MessageFormat s = new MessageFormat(sql);
		sql = MessageFormat.format(sql,"aaaa","bb");
//		sql = String.format(sql, "aaa","bb");
		
		System.out.println(sql);
	}
	
}
