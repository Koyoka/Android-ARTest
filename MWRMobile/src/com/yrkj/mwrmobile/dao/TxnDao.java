package com.yrkj.mwrmobile.dao;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yrkj.mwrmobile.base.MWRDBMng;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.bean.RequestBody;
import com.yrkj.mwrmobile.bean.RequestTxnBean;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.bean.TxnHeaderData;
import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;
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

	public static String sendTxnToInventory(Context c,String url,String accessKey,String secretKey,Handler handler){
		
		Message msg = new Message();
		
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
			msg.obj = "交易信息生成错误";
			handler.sendMessage(msg);
			return null;
		}
		
		ArrayList<TxnDetailData> detailList =  getTxnDetail(c);
		
		WSInfo wsInfo = new WSInfo();
		
		RequestTxnBean txn = new RequestTxnBean();
		txn.txndetaillist = detailList;
		txn.carcode = wsInfo.CarCode;
		txn.drivercode = wsInfo.DriverCode;
		txn.drvier =wsInfo.DriverName;
		txn.inspectorcode = wsInfo.InspectorCode;
		txn.inspector = wsInfo.InspectroName;
		txn.mwscode = wsInfo.WSCode;
		
		RequestBody rBody = new RequestBody();
		rBody.action = "RecoverInventorySubmit";
		rBody.value = txn;
		
		Gson gson = new Gson();
		String body =  gson.toJson(rBody);
		
		String resultStr = HttpMng.doHttpSignatureURL(url, accessKey, secretKey, body);
		DLog.LOG(resultStr+"----------aaa "+ body);
		
		try {
			JSONObject jo = new JSONObject(resultStr);
			boolean error = jo.getBoolean("Error");
			String errMsg = jo.getString("ErrMsg");
			String result = jo.getString("Result");
			if(error){
				msg.what = Txn_failed;
				msg.obj = errMsg;
				handler.sendMessage(msg);
				return null;
			}else{
				return result;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			msg.what = Txn_failed;
			msg.obj = e.getMessage();
			handler.sendMessage(msg);
			e.printStackTrace();
			return null;
		}
	}
	
	public static void sendTxnToDestroy(Handler handler){
		
	}
	
	
}
