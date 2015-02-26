package com.yrkj.util.basedao.common;

import java.io.UnsupportedEncodingException;

import android.util.Base64;


public class ComFn {

	//==== Base64
	public static String EncryptStringBy64_GB2312(String s){
		String enCode = null;
		try {
			byte[] enStr = Base64.encode(s.getBytes("GB2312"),Base64.DEFAULT);
			enCode = new String(enStr,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
       
        return enCode;
	}
	
	public static String DecryptStringBy64_GB2312(String s){
		
		String enCode = null;
		try {
			byte[] enStr = Base64.decode(s.getBytes("utf-8"),Base64.DEFAULT);
			enCode = new String(enStr,"GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
       
        return enCode;
	}
}
