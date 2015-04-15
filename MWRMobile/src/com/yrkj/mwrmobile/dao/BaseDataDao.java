package com.yrkj.mwrmobile.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.base.MWRBaseDBMng;
import com.yrkj.mwrmobile.base.MWRDBMng;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
import com.yrkj.mwrmobile.bean.request.RequestBody;
import com.yrkj.mwrmobile.bean.request.RequestInitWSBody;
import com.yrkj.mwrmobile.bean.request.RequestStartCarRecoverBody;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
import com.yrkj.mwrmobile.bean.response.ResponseInitMWSSubmitBody;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;


public class BaseDataDao {

	public static ArrayList<VendorData> getVendorList(Context c){
		MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
		
		DBCondition cdt = new DBCondition();
//		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(VendorData.TblName, 
				VendorData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<VendorData> itemList = new ArrayList<VendorData>();
		if(cursor.moveToFirst()){
			do {
				VendorData item = VendorData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public static ArrayList<WasteCategoryData> getWasterList(Context c){
		MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
		
		DBCondition cdt = new DBCondition();
//		cdt.Selection = QCategoryData.Col_IsActive + " = " + QCategoryData.ISACTIVE_YES;
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(WasteCategoryData.TblName, 
				WasteCategoryData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<WasteCategoryData> itemList = new ArrayList<WasteCategoryData>();
		if(cursor.moveToFirst()){
			do {
				WasteCategoryData item = WasteCategoryData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public final static int Opt_Sucess = 0;
	public final static int Opt_failed = 1;
	public static boolean RegistWS(Context c,String url,String wsCode,String accessKey,String secretKey
			,Handler handler){
		
		Message msg = new Message();
		
		RequestInitWSBody mBody = new RequestInitWSBody();
		mBody.mwsCode = wsCode;
		mBody.accessKey = accessKey;
		mBody.secretKey = secretKey;
		
		RequestBody rBody = new RequestBody();
		rBody.action = "InitMWSSubmit";
		rBody.value = mBody;
		
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		
		ResponseBody resBody = 
				ResJsonHelper.getBodyFromJson(resultStr);
		if(resBody == null){
//			ToastUtil.show(mContext, "网络连接错误");
			msg.what = Opt_failed;
			msg.obj = "网络连接错误";
			handler.sendMessage(msg);
			return false;
		}else if(resBody.Error){
			msg.what = Opt_failed;
			msg.obj = resBody.ErrMsg;
			handler.sendMessage(msg);
			return false;
//			ToastUtil.show(mContext, body.ErrMsg);
		}else if(!resBody.Error){
			
			ResponseInitMWSSubmitBody initBody = 
					ResJsonHelper.getInitBodyFromJson(resBody.Result);
//			DLog.LOG("resBody.Result  " + resBody.Result);
//			DLog.LOG("VendorList  " + initBody.VendorList);
//			DLog.LOG("WasteList " + initBody.WasteList);

			if(initBody == null){
//				ToastUtil.show(mContext, "网络连接错误");
				msg.what = Opt_failed;
				msg.obj = "网络连接错误";
				handler.sendMessage(msg);
				return false;
			}else{
				SysMng.saveWSInfo(initBody.WSCode, initBody.AssessKey, initBody.SecretKey, initBody.CrateMask);
			
				MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
				dbMng.open();
				
				if(initBody.VendorList != null){
					dbMng.delete(VendorData.TblName, "");
					for(VendorData data : initBody.VendorList){
						dbMng.insert(VendorData.TblName, 
								VendorData.getContentValues(data));
					}
				}
				if(initBody.WasteList != null){
					dbMng.delete(WasteCategoryData.TblName, "");
					for(WasteCategoryData data : initBody.WasteList){
						dbMng.insert(WasteCategoryData.TblName, 
								WasteCategoryData.getContentValues(data));
					}
				}
				dbMng.close();
			}
		}
		
		
		return true;
	}
	
	public static boolean AsynWSData(Context c,String url,String wsCode,String accessKey,String secretKey
			,Handler handler){
		
		Message msg = new Message();
		
		RequestInitWSBody mBody = new RequestInitWSBody();
		mBody.mwsCode = wsCode;
		mBody.accessKey = accessKey;
		mBody.secretKey = secretKey;
		
		RequestBody rBody = new RequestBody();
		rBody.action = "AysnMoblieData";
		rBody.value = mBody;
		
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		
		ResponseBody resBody = 
				ResJsonHelper.getBodyFromJson(resultStr);
		if(resBody == null){
//			ToastUtil.show(mContext, "网络连接错误");
			msg.what = Opt_failed;
			msg.obj = "网络连接错误";
			handler.sendMessage(msg);
			return false;
		}else if(resBody.Error){
			msg.what = Opt_failed;
			msg.obj = resBody.ErrMsg;
			handler.sendMessage(msg);
			return false;
//			ToastUtil.show(mContext, body.ErrMsg);
		}else if(!resBody.Error){
			
			ResponseInitMWSSubmitBody initBody = 
					ResJsonHelper.getInitBodyFromJson(resBody.Result);
//			DLog.LOG("resBody.Result  " + resBody.Result);
//			DLog.LOG("VendorList  " + initBody.VendorList);
//			DLog.LOG("WasteList " + initBody.WasteList);

			if(initBody == null){
//				ToastUtil.show(mContext, "网络连接错误");
				msg.what = Opt_failed;
				msg.obj = "网络连接错误";
				handler.sendMessage(msg);
				return false;
			}else{
				SysMng.saveAysnWSInfo(initBody.CrateMask);
			
				MWRBaseDBMng dbMng = new MWRBaseDBMng(c);
				dbMng.open();
				
				if(initBody.VendorList != null){
					dbMng.delete(VendorData.TblName, "");
					for(VendorData data : initBody.VendorList){
						dbMng.insert(VendorData.TblName, 
								VendorData.getContentValues(data));
					}
				}
				if(initBody.WasteList != null){
					dbMng.delete(WasteCategoryData.TblName, "");
					for(WasteCategoryData data : initBody.WasteList){
						dbMng.insert(WasteCategoryData.TblName, 
								WasteCategoryData.getContentValues(data));
					}
				}
				dbMng.close();
			}
		}
		
		
		return true;
	}
	
	public static String StartCarRecover(Context c,String url,String wsCode,String accessKey,String secretKey,
			String carCode,String driverCode,String inspectorCode){
		Message msg = new Message();
		
		RequestStartCarRecoverBody mBody = new RequestStartCarRecoverBody();
		mBody.mwsCode = wsCode;
		mBody.carCode = carCode;
		mBody.driverCode = driverCode;
		mBody.inspectorCode = inspectorCode;
		
		RequestBody rBody = new RequestBody();
		rBody.action = "StartCarRecoverShift";
		rBody.value = mBody;
		DLog.LOG("-----carCode " + carCode);
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		DLog.LOG("-----body " + body);
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		DLog.LOG("-----resultStr " + resultStr);
		return resultStr;
	}
	
}
