package com.yrkj.mwrmobile.util.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.dtr.zxing.activity.CaptureActivity;
import com.google.zxing.Result;
import com.yrkj.mwrmobile.dao.TxnMng;
import com.yrkj.util.log.ToastUtil;

public class MWRCaptureActivity extends CaptureActivity {

	public final String INTENT_KEY_ScannerType = "scannertype";
	public final String SCANNERTYPE_KEY_CrateCode = "CrateCode";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
//		setOnCaptureListen(this);
		super.onCreate(savedInstanceState);
		initActy();
	}
	
	private void initActy(){
	}

//	@Override
//	public void onCapture(int width, int height, String result) {
//		ToastUtil.show(this, result);
//		if(result.equals("HX020")){
//			finish();
//		}
////		finish();
//		EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog1");
//		
//	}
	
	@Override
	public void handleDecode(Result rawResult, Bundle bundle) {
		// TODO Auto-generated method stub
		super.handleDecode(rawResult, bundle);
		
		String result = rawResult.getText();
//		if(result.equals("HX020"))
		if(TxnMng.ValidCrateCodeMask(result))	
		{
			ToastUtil.show(this, "success");
			Intent intent = new Intent();
			bundle.putString("cratecode", result);
			intent.putExtras(bundle);
//			bundle.putString("result", rawResult.getText());
			
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