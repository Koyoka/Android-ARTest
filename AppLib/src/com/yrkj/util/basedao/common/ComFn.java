package com.yrkj.util.basedao.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import android.util.Base64;


public class ComFn {
	private final static double PI = 3.14159265358979323; // ‘≤÷‹¬ 
    private final static double R = 6371229; //
    public static double MathLatLngDistance(double lat1,double longt1,double lat2,  double longt2,int scale) {
        double x, y, distance;
        x = (longt2 - longt1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);

        BigDecimal bg = new BigDecimal(distance);
        double d = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
	
	
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
