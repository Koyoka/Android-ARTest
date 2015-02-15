package com.yrkj.mwrmobile.util.scanner;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Base64;

import com.dtr.zxing.activity.CaptureActivity;
import com.google.zxing.Result;
import com.yrkj.mwrmobile.dao.TxnMng;
import com.yrkj.util.log.ToastUtil;

public class MWRCaptureActivity extends CaptureActivity {

	public static final String INTENT_KEY_ScannerType = "scannertype";
	public static final int SCANNERTYPE_KEY_RecoverCrate = 1;
	public static final int SCANNERTYPE_KEY_InitSystem = 2;
	public static final int SCANNERTYPE_KEY_Login = 3;
	
	private int mScannerType = SCANNERTYPE_KEY_RecoverCrate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		initActy();
	}
	
	private void initActy(){
		mScannerType = getIntent().getIntExtra(INTENT_KEY_ScannerType, SCANNERTYPE_KEY_RecoverCrate);
	}

	
	@Override
	public void handleDecode(Result rawResult, Bundle bundle) {
		// TODO Auto-generated method stub
		super.handleDecode(rawResult, bundle);
		
		String result = rawResult.getText();
		
		if(mScannerType == SCANNERTYPE_KEY_RecoverCrate){
			doRecoverCrate(result,bundle);
		}else if(mScannerType == SCANNERTYPE_KEY_InitSystem){
			doInitSystem(result,bundle);
		}else if(mScannerType == SCANNERTYPE_KEY_Login){
			doLogin(result,bundle);
		}
		
	}
	
	private void doInitSystem(String result, Bundle bundle){
		
//		BASE64Decoder.
//		(new BASE64Decoder()).decodeBuffer(result)
		try
		{
			String data = new String(Base64.decode(result,Base64.DEFAULT));
			
			String[] array = data.split(" ");
			if(array.length != 4){
				ToastUtil.show(this, "无效的初始化数据");
				getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
				return;
			}
			String tag = array[0].trim();
			if(!tag.equals("MWR-INITMOBILE")){
				ToastUtil.show(this, "无效的初始化数据");
				getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
				return;
			}
			
			String wsCode = array[1].trim();
			String ak = array[2].trim();
			String sk = array[3].trim();
			
			Intent intent = new Intent();
			bundle.putString("wsCode", wsCode);
			bundle.putString("ak", ak);
			bundle.putString("sk", sk);
			intent.putExtras(bundle);
			
			Message msg = new Message();
			msg.obj = intent;
			msg.what = com.dtr.zxing.R.id.return_scan_result;
			getHandler().sendMessageDelayed(msg, 500);
			
		}
		catch(Exception ex)
		{
			ToastUtil.show(this, "无效的初始化数据");
			getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
			
		}
//		String result = "";
	}
	
	private void doLogin(String result, Bundle bundle){
		
	}
	
	private void doRecoverCrate(String result, Bundle bundle){
		Intent intent = new Intent();
		bundle.putString("cratecode", result);
		intent.putExtras(bundle);
		
		if(TxnMng.ValidCrateCodeMask(result))	
		{
			Message msg = new Message();
			msg.obj = intent;
			msg.what = com.dtr.zxing.R.id.return_scan_result;
			getHandler().sendMessageDelayed(msg, 500);
			
		}else{
			ToastUtil.show(this, "无效的货箱编号");
			getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
			
		}
	}
	
}
//getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.return_scan_result, 500);