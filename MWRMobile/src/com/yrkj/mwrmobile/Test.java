package com.yrkj.mwrmobile;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.bean.RequestBody;
import com.yrkj.mwrmobile.bean.RequestTxnBean;
import com.yrkj.mwrmobile.bean.RequestTxnDetailBean;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.util.http.HttpMng;

public class Test {

	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	
	private static boolean validCodeMask(String mask,String code)
    {
       
		String p = "^";
		
        for (int i = 0; i < mask.length(); i++)
        {
//        	mask.charAt(index)
        	char defineMaskChar = mask.charAt(i);//.ToString();
        	
            if (defineMaskChar == '#')
            {
                //number
                p += "\\d";
            }
            else if (defineMaskChar == '@')
            {
                //char
                p += "[A-Za-z]";
            }
            else 
            {
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
//        _mask = mask;
//        _pattern = pattern;
    }
	public static void main(String[] args) {
//		class TxnData
//	    {
//	        public String drvier = "";
//	        public String drivercode = "";
//	        public String inspector = "";
//	        public String inspectorcode = "";
//	        public String mwscode = "";
//	        public String carcode = "";
//	        public ArrayList<DemoMWTxnDetail> txndetaillist = new ArrayList<DemoMWTxnDetail>();
//	       
//	    }
//		
//		RequestTxnBean defineData = new RequestTxnBean();
////		defineData.setName("elevne");
////		defineData.setAge("11");
//		defineData.drivercode = "eleven";
//		defineData.carcode = "111";
//		
//		RequestTxnDetailBean item = new RequestTxnDetailBean();
//		item.CrateCode = "HX001";		
//		item.Unit = "kg";
//		defineData.txndetaillist.add(item);
//		
//		Gson gson = new Gson();
//		String s = gson.toJson(defineData);
//		System.out.println(s+"  1");    
		
//		if(true)
//			return;
//		boolean b = validCodeMask("HX###","X001");
//		System.out.println(b);    
//		if(true)
//			return;
		
		
		System.out.println("11111");    
		String host = "localhost";
		int port = 15809;
		String url = "Services/MWMobileWSHandler.ashx?a=1";
		url = "http://localhost:15809/Services/MWMobileWSHandler.ashx";
		String accessKey = "9e15f4f7d6fdc178eeab8caf79d863054bdfea78";
		String secretKey = "ae46214f1ee0269f7eb5126895ff166f02ede4f1";
		String body = "";
		
		ArrayList<TxnDetailData> detailList = new ArrayList<TxnDetailData>();
		TxnDetailData d = new TxnDetailData();
		d.CrateCode = "ddd";
		detailList.add(d);
		
		RequestTxnBean txn = new RequestTxnBean();
		txn.txndetaillist = detailList;
		txn.carcode = "111";
		txn.drivercode = "222";
		txn.drvier = "222";
		txn.inspectorcode = "222";
		txn.inspector = "222";
		txn.mwscode = "222";
		
		RequestBody rBody = new RequestBody();
		rBody.action = "test";
		rBody.value = txn;
		Gson gson = new Gson();
		body =  gson.toJson(rBody);
		
//		JSONObject jo = new JSONObject();
//		try {
//			JSONObject subJo = new JSONObject();
//			subJo.put("name", "eleven");
//			jo.put("action", "test");
//			jo.put("value", subJo);
////			body = s;//jo.toString();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(body.toString() + "   dd"); 
		String result = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		System.out.println(body.toString() + " " +result + "  cc"); 
		
	}
	
//	public class TxnData
//    {
//        public String drvier = "";
//        public String drivercode = "";
//        public String inspector = "";
//        public String inspectorcode = "";
//        public String mwscode = "";
//        public String carcode = "";
//        public ArrayList<DemoMWTxnDetail> txndetaillist = new ArrayList<DemoMWTxnDetail>();
//       
//    }
	
//	public class TxnDetailData1 {
//		public static final String TblName = "TxnDetail";
//		
//		public static final String Col_TxnDetailId = "TxnDetailId";
//		public static final String Col_TxnType = "TxnType";
//		public static final String Col_TxnNum = "TxnNum";
//		public static final String Col_WSCode = "WSCode";
//		public static final String Col_EmpyName = "EmpyName";
//		public static final String Col_EmpyCode = "EmpyCode";
//		public static final String Col_CrateCode = "CrateCode";
//		public static final String Col_Vendor = "Vendor";
//		public static final String Col_VendorCode = "VendorCode";
//		public static final String Col_Waste = "Waste";
//		public static final String Col_WasteCode = "WasteCode";
//		public static final String Col_SubWeight = "SubWeight";
//		public static final String Col_TxnWeight = "TxnWeight";
//		public static final String Col_EntryDate = "EntryDate";
//		public static final String Col_InvRecordId = "InvRecordId";
//		public static final String Col_InvAuthId = "InvAuthId";
//		public static final String Col_Status = "Status";
//		
//		
//		public String TxnDetailId = "";
//		public String TxnType = "";
//		public String TxnNum = "";
//		public String WSCode = "";
//		public String EmpyName = "";
//		public String EmpyCode = "";
//		public String CrateCode = "";
//		public String Vendor = "";
//		public String VendorCode = "";
//		public String Waste = "";
//		public String WasteCode = "";
//		public String SubWeight = "";
//		public String TxnWeight = "";
//		public String EntryDate = "";
//		public String InvRecordId = "";
//		public String InvAuthId = "";
//		public String Status = "";
//
//		
//		
//		public final static String STATUS_Process = "P";
//		public final static String STATUS_Complete = "C";
//
//}
//	public class DemoMWTxnDetail extends TxnDetailData1
//    {
//        public String Unit = "";
//       
//    }
}
