package com.yrkj.mwrmobile.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.base.MWRDBMng;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.bean.TxnHeaderData;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
import com.yrkj.mwrmobile.bean.request.RequestBody;
import com.yrkj.mwrmobile.bean.request.RequestTxnBody;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
import com.yrkj.util.date.DateHelper;
import com.yrkj.util.db.DBCondition;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.log.DLog;

public class TxnDao {

	public static ArrayList<TxnDetailData> getTxnDetail(Context c){
		
		MWRDBMng dbMng = new MWRDBMng(c);
		
		DBCondition cdt = new DBCondition();
		cdt.Selection = TxnDetailData.Col_Status + " = '" + TxnDetailData.STATUS_Process +"'";
//		cdt.OrderBy = QCategoryData.Col_SortIndex + " ASC";
		dbMng.open();
		Cursor cursor = dbMng.query(TxnDetailData.TblName, 
				TxnDetailData.getColumnColl(), cdt);
		dbMng.close();
		
		ArrayList<TxnDetailData> itemList = new ArrayList<TxnDetailData>();
		if(cursor.moveToFirst()){
			do {
				TxnDetailData item = TxnDetailData.convertDataToModule(cursor);
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return itemList;
	}
	
	public final static int Txn_Sucess = 0;
	public final static int Txn_failed = 1;
	public final static int SendTxn_To_Inventory = 1;
	public final static int SendTxn_To_Destroy = 2;
	
	public static void delTxnDetail(Context c,String txnDetailId){
		MWRDBMng dbMng = new MWRDBMng(c);
		dbMng.open();
		String condition = TxnDetailData.Col_TxnDetailId + " = '"+txnDetailId+"'";
		dbMng.delete(TxnDetailData.TblName, condition);
		dbMng.close();
	}
	
	public static long AddTxn(Context c,String crateCode,String weight,VendorData vendor,WasteCategoryData waster,Handler handler){
		MWRDBMng dbMng = new MWRDBMng(c);
		
		dbMng.open();
		long result = 0;
		
		Message msg = new Message();
		//add txn header
		String now = DateHelper.getNowTimeStr();
		{
			TxnDetailData validTxnDetail = null;
			DBCondition cdt = new DBCondition();
			cdt.Selection = 
					TxnDetailData.Col_Status + " = '" + TxnDetailData.STATUS_Process +"'" +
					" AND " + TxnDetailData.Col_CrateCode + " = '" + crateCode + "'";
			Cursor cursor = dbMng.query(TxnDetailData.TblName, 
					TxnDetailData.getColumnColl(), cdt);
			if(cursor.moveToFirst()){
				validTxnDetail = TxnDetailData.convertDataToModule(cursor);
			}
			cursor.close();
			
			if(validTxnDetail != null){
				msg.what = Txn_failed;
				msg.obj = "当前货箱已添加。";
				handler.sendMessage(msg);
				return 0;
			}
		}
		
		TxnHeaderData curHeader = null;
		{
			DBCondition cdt = new DBCondition();
			cdt.Selection = TxnHeaderData.Col_Status + " = '" + TxnHeaderData.STATUS_Precess +"'";
			Cursor cursor = dbMng.query(TxnHeaderData.TblName, 
					TxnHeaderData.getColumnColl(), cdt);
			if(cursor.moveToFirst()){
				curHeader = TxnHeaderData.convertDataToModule(cursor);
			}
			cursor.close();
			
			if(curHeader == null){
				UUID uuid = UUID.randomUUID();  
				curHeader = new TxnHeaderData();
				curHeader.RecoHeaderId = uuid.toString();
				curHeader.TxnNum = uuid.toString();
				curHeader.StartDate = now;
				curHeader.Status = TxnHeaderData.STATUS_Precess;
				result = dbMng.insert(TxnHeaderData.TblName, 
						TxnHeaderData.getContentValues(curHeader));
			}
		}
		
		// add txn detail
		if(curHeader == null){
			msg.what = Txn_failed;
			msg.obj = "交易信息生成错误。";
			handler.sendMessage(msg);
			return 0;
		}else{
			TxnDetailData txnDetail = new TxnDetailData();
			UUID uuid = UUID.randomUUID();  
			txnDetail.TxnDetailId = uuid.toString();
			txnDetail.TxnNum = curHeader.TxnNum;
			txnDetail.CrateCode = crateCode;
			txnDetail.SubWeight = weight;
			txnDetail.Vendor = vendor.Vendor;
			txnDetail.VendorCode = vendor.VendorCode;
			txnDetail.Waste = waster.Waste;
			txnDetail.WasteCode = waster.WasteCode;
			txnDetail.EntryDate = now;
			txnDetail.Status = TxnDetailData.STATUS_Process;
			
			result = dbMng.insert(TxnDetailData.TblName, 
					TxnDetailData.getContentValues(txnDetail));
		}
		
		dbMng.close();
		return result;
		
	}

	public static boolean sendTxn(Context c,String url,String accessKey,String secretKey
			,int sendType
			,Handler handler){
		
		Message msg = new Message();
		String sendActionType = "";
		if(sendType == SendTxn_To_Inventory){
			sendActionType = "RecoverInventorySubmit";
		}else if(sendType == SendTxn_To_Destroy){
			sendActionType = "RecoverDestroySubmit";
		}else{
			msg.what = Txn_failed;
			msg.obj = "coding error[invalid sendtype]";
			handler.sendMessage(msg);
			return false;
		}
		
		
		
		TxnHeaderData header = null;
		{
			MWRDBMng dbMng = new MWRDBMng(c);
			dbMng.open();
			
			DBCondition cdt = new DBCondition();
			cdt.Selection = TxnHeaderData.Col_Status + " = '" + TxnHeaderData.STATUS_Precess +"'";
			Cursor cursor = dbMng.query(TxnHeaderData.TblName, 
					TxnHeaderData.getColumnColl(), cdt);
			if(cursor.moveToFirst()){
				header = TxnHeaderData.convertDataToModule(cursor);
			}
			cursor.close();
			dbMng.close();
		}
		
		if(header == null){
			msg.what = Txn_failed;
			msg.obj = "没有可用数据提交";
			handler.sendMessage(msg);
			return false;
		}
		
		ArrayList<TxnDetailData> detailList =  getTxnDetail(c);
		
		
		if(detailList.size() == 0){
			msg.what = Txn_failed;
			msg.obj = "没有可用数据提交";
			handler.sendMessage(msg);
			return false;
		}
		
		TxnInfo txnInfo = new TxnInfo();
		WSInfo wsInfo = new WSInfo();
		txnInfo = SysMng.getTxnInfo();
		wsInfo = SysMng.getWSInfo();
		
		RequestTxnBody txn = new RequestTxnBody();
		txn.txndetaillist = detailList;
		txn.carcode = txnInfo.CarCode;
		txn.drivercode = txnInfo.DriverCode;
		txn.drvier =txnInfo.DriverName;
		txn.inspectorcode = txnInfo.InspectorCode;
		txn.inspector = txnInfo.InspectroName;
		txn.mwscode = wsInfo.WSCode;
		
		RequestBody rBody = new RequestBody();
		rBody.action = sendActionType;//"RecoverInventorySubmit";
		rBody.value = txn;
		
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		DLog.LOG(resultStr+"----------aaa "+ body);
		
		ResponseBody resBody = 
				ResJsonHelper.getBodyFromJson(resultStr);
		
		if(body == null){
			msg.what = Txn_failed;
			msg.obj = "网络连接错误";
			handler.sendMessage(msg);
			return false;
		}else if(resBody.Error){
			msg.what = Txn_failed;
			msg.obj = resBody.ErrMsg;
			handler.sendMessage(msg);
			return false;
		}else{// if(!resBody.Error){
			MWRDBMng dbMng = new MWRDBMng(c);
			dbMng.open();
			
			{
				ContentValues values = new ContentValues();
				values.put(TxnHeaderData.Col_Status,TxnHeaderData.STATUS_Complete);
				
				String condition = TxnHeaderData.Col_TxnNum + " = '"+header.TxnNum+"'";
				dbMng.update(TxnHeaderData.TblName, values, condition);
			}
			{
				ContentValues values = new ContentValues();
				values.put(TxnDetailData.Col_Status,TxnDetailData.STATUS_Complete);
				
				String condition = TxnDetailData.Col_TxnNum + " = '"+header.TxnNum+"'";
				dbMng.update(TxnDetailData.TblName, values, condition);
			}
			
			dbMng.close();
			
			msg.what = Txn_Sucess;
//			msg.obj = resBody.ErrMsg;
			handler.sendMessage(msg);
			return true;
		}
	}
	
	public static String[] getTxnReport(Context c){
		
		String[] report = new String[]{"0","0"};
		ArrayList<TxnDetailData> detailList =  getTxnDetail(c);
		
		if(detailList.size() == 0)
			return report;
		
		BigDecimal defineWeight = new BigDecimal(0);
		for(TxnDetailData data : detailList){
			BigDecimal d = new BigDecimal(data.SubWeight);
			defineWeight = defineWeight.add(d);
		}
		report[0] = detailList.size()+"";
		report[1] = defineWeight.toString();
		return report;
		
	}
	
//	public static void sendTxnToDestroy(Handler handler){
//		
//	}
	
	
}
