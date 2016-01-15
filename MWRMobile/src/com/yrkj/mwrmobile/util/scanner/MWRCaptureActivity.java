package com.yrkj.mwrmobile.util.scanner;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.google.zxing.Result;
import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.dao.TxnMng;
import com.yrkj.util.basedao.common.ComFn;
import com.yrkj.util.log.ToastUtil;

public class MWRCaptureActivity extends CaptureActivity implements OnClickListener {

	public static final String INTENT_KEY_ScannerType = "scannertype";
	public static final int SCANNERTYPE_KEY_RecoverCrate = 1;
	public static final int SCANNERTYPE_KEY_InitSystem = 2;
	public static final int SCANNERTYPE_KEY_Login = 3;
	
	private int mScannerType = SCANNERTYPE_KEY_RecoverCrate;
	
	TextView mTitleView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		initActy();
	}
	
	private void initActy(){
		mScannerType = getIntent().getIntExtra(INTENT_KEY_ScannerType, SCANNERTYPE_KEY_RecoverCrate);
		if(mScannerType == SCANNERTYPE_KEY_RecoverCrate){
			mTitleView.setText("扫描货箱");
		}else if(mScannerType == SCANNERTYPE_KEY_InitSystem){
			mTitleView.setText("扫描初始化");
		}else if(mScannerType == SCANNERTYPE_KEY_Login){
			mTitleView.setText("扫描回收任务");
		}
	}

	@Override
	protected View getView() {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(this).inflate(R.layout.view_scanner,
				null);
		v.findViewById(R.id.btnScannerBack).setOnClickListener(this);
		mTitleView = (TextView) v.findViewById(R.id.txtScannerTitle);
		
		return v;
	}
	
	@Override
	public void handleDecode(Result rawResult, Bundle bundle) {
		// TODO Auto-generated method stub
		super.handleDecode(rawResult, bundle);
		
		String result = rawResult.getText();
		ToastUtil.show(this, result);
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
				ToastUtil.show(this, "无效的条形码格式");
				getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
				return;
			}
			String tag = array[0].trim();
			if(!tag.equals("MWR-INITMOBILE")){
				ToastUtil.show(this, "无效的条形码格式");
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
			ToastUtil.show(this, "无效的条形码格式");
			getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
			
		}
//		String result = "";
	}
	
	private void doLogin(String result, Bundle bundle){
		try
		{
			String data = "";
			data = ComFn.DecryptStringBy64_GB2312(result);
			if(data==null){
				ToastUtil.show(this, "无效的条形码格式");
				return;
			}
			
			
	//		DLog.LOG("=========data "+data);
	//		DLog.LOG("=========result "+result);
			String[] array = data.split(" ");
			if(array.length != 4){
				ToastUtil.show(this, "无效的条形码格式");
				getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
				return;
			}// "MWR-STARTSHIFT {0} {1} {2}";
			String tag = array[0].trim();
			if(!tag.equals("MWR-STARTSHIFT")){
				ToastUtil.show(this, "无效的条形码格式");
				getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
				return;
			}
			
			String carCode, driverCode, inspectorCode;
			
			carCode = array[1].trim();
			driverCode = array[2].trim();
			inspectorCode = array[3].trim();
			
			Intent intent = new Intent();
			bundle.putString("carCode", carCode);
			bundle.putString("driverCode", driverCode);
			bundle.putString("inspectorCode", inspectorCode);
			intent.putExtras(bundle);
			
			Message msg = new Message();
			msg.obj = intent;
			msg.what = com.dtr.zxing.R.id.return_scan_result;
			getHandler().sendMessageDelayed(msg, 500);
		}
		catch(Exception ex)
		{
			ToastUtil.show(this, "无效的条形码格式");
			getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
			
		}
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
			ToastUtil.show(this, "无效的条形码格式");
			getHandler().sendEmptyMessageDelayed(com.dtr.zxing.R.id.decode_failed, 2000);
			
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnScannerBack){
			finish();
		}
		
	}
	
}
//getHandler().sen