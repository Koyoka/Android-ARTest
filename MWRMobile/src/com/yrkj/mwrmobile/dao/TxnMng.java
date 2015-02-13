package com.yrkj.mwrmobile.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;

public class TxnMng {

	public static boolean ValidCrateCodeMask(String crateCode){
		
		TxnInfo txnInfo = SysMng.getTxnInfo();
		String crateMask = txnInfo.CrateMask;
		return validCodeMask(crateMask,crateCode);
		
	}
	
	private static boolean validCodeMask(String mask,String code){
		String p = "^";
        for (int i = 0; i < mask.length(); i++)
        {
        	char defineMaskChar = mask.charAt(i);//.ToString();
        	
            if (defineMaskChar == '#'){
                //number
                p += "\\d";
            }
            else if (defineMaskChar == '@'){
                //char
                p += "[A-Za-z]";
            }
            else {
                p += defineMaskChar;
            }
            p += "{1}";
        }
        p += "$";
        
        Pattern pattern = Pattern.compile(p); 
        Matcher matcher = pattern.matcher(code);  
        if(!matcher.find()){  
        	return false;
        }
        
        return true;
    }
}
