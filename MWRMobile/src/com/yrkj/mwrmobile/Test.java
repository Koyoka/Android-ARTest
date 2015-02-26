package com.yrkj.mwrmobile;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.bean.request.RequestBody;
import com.yrkj.mwrmobile.bean.request.RequestTxnBody;
import com.yrkj.mwrmobile.bean.request.RequestTxnDetailBody;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
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
		
		{
			
			
			String result = "TVdSLVNUQVJUU0hJRlQgtvVBMDAwMDIgWUcwMDAyIFlHMDAwNg==";
//			String result = "TVdSLVNUQVJUU0hJRlQg6YSCQTAwMDAyIFlHMDAwMiBZRzAwMDY=";
			//TVdSLVNUQVJUU0hJRlQgP0EwMDAwMiBZRzAwMDIgWUcwMDA2
//			//TVdSLVNUQVJUU0hJRlQgP0EwMDAwMiBZRzAwMDIgWUcwMDA2
			try {
				
//				String charset = "UTF-8";
//				charset = "GB2312";
//				byte[] bs;
//				bs = Base64.encodeBase64("MWR-STARTSHIFT 鄂A00002 YG0002 YG0006".getBytes(charset));
//				result = new String(bs);
//				System.out.println(result);
				
				byte[] enStr = Base64.encodeBase64("MWR-STARTSHIFT 鄂A00002 YG0002 YG0006".getBytes("GB2312"));
	            String enCode = new String(enStr,"utf-8");
	            System.out.println(enCode);
			
//				byte[] bs2 = Base64.decodeBase64(result);
//				String s = new String(bs2,"UTF-8");
//				System.out.println(s);
				
				
				byte[] deCode = Base64.decodeBase64(result.getBytes("utf-8"));
	            System.out.println(new String(deCode,"GB2312"));
	            
//				System.out.println(new String(Base64.decodeBase64(result.getBytes(charset)))); 
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
//			System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(result))); 
//			org.apache.commons.codec.binary.Base64.decodeBase64(result.getBytes("utf-8"));
			
//			try {
//				
////				String data = new String(org.apache.commons.codec.binary.Base64.decodeBase64(result), "utf-8");
////				String data = new String(org.apache.commons.codec.binary.Base64.decodeBase64(result.getBytes("utf-8")));
////				System.out.println(data); 
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
if(true)
	return;
			
		}
		
		{
			
			String result = "";
			Gson gs =  new Gson();
//			new GsonBuilder()
////	        .excludeFieldsWithoutExposeAnnotation() //������ʵ����û����@Exposeע�������
////	        .enableComplexMapKeySerialization() //֧��Map��keyΪ���Ӷ������ʽ
////	        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//ʱ��ת��Ϊ�ض���ʽ
////	        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)//����ֶ�����ĸ��д,ע:����ʵ����ʹ����@SerializedNameע��Ĳ�����Ч.
////	        .setPrettyPrinting() //��json����ʽ��.
////	        .setVersion(1.0)    //�е��ֶβ���һ��ʼ���е�,�����Ű汾������ӽ���,��ô�ڽ������л��ͷ����л���ʱ��ͻ��ݰ汾����ѡ���Ƿ�Ҫ���л�.
//	                            //@Since(�汾��)��������ʵ���������.�����ֶο���,���Ű汾�����ɾ��,��ô
//	                            //@Until(�汾��)Ҳ��ʵ���������,GsonBuilder.setVersion(double)������Ҫ����.
//	        .create();
//					
					
			ResponseBody ccc = new ResponseBody();
			ccc.Error = true;
			ccc.ErrMsg = "asdf";
			ccc.Result = "asdfadf";
			
			
//			RequestBody rBody = new RequestBody();
//			rBody.action = "InitMWSSubmit";
//			rBody.value = "casdf";
			
//			String sss = gs.toJson(rBody);
			String sss = gs.toJson(ccc);
			
			System.out.println(sss+"  22"); 
//			RequestBody c = gs.fromJson(sss, RequestBody.class);
			ResponseBody c = gs.fromJson(sss,ResponseBody.class);
			System.out.println(c.ErrMsg); 
		}
		if(true){
			
			return;
		}
		
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
		
		{
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
			
			RequestTxnBody txn = new RequestTxnBody();
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
