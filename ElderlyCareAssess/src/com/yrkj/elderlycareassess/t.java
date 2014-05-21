package com.yrkj.elderlycareassess;

import java.text.MessageFormat;

import com.google.gson.Gson;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.util.log.DLog;

public class t {

	public static void main(String[] args) {
		String sql = "Select * From [QItem] where itemid in(Select itemid From [QSubcategoryDetail] where subcateid = {0} and itemtype = ''{1}'')";
        
//		MessageFormat s = new MessageFormat(sql);
		sql = MessageFormat.format(sql,"aaaa","bb");
//		sql = String.format(sql, "aaa","bb");
		
//		System.out.println(0&1);
//		System.out.println(2&2);
//		System.out.println(4&4);
		int i = 13;//1|0|4|8;//|15;
		System.out.println(i);
		System.out.println(i&1);
		System.out.println(i&2);
		System.out.println(i&4);
		System.out.println(i&8);
//		void a(){
			Gson gson = new Gson();
			AssessTaskHeaderData d = new AssessTaskHeaderData();
			d.AssessNum = "111";
			d.AssessState = "dd";
			 String s = gson.toJson(d);
//			 DLog.LOG(SysMng.TAG_DB,s);
			 System.out.println(s);

//		}
//		System.out.println(i&7);
//		System.out.println(i&15);
	}
	
}
