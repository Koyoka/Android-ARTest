package com.yrkj.mwrmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.view.View;
import android.widget.TextView;

import com.yrkj.mwrmobile.LaunchActivity.InitTask;
import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.bean.response.ResponseConfigBody;
import com.yrkj.mwrmobile.dao.ResJsonHelper;
import com.yrkj.mwrmobile.service.BackWorkSerive;
import com.yrkj.mwrmobile.service.CommonBroadcast;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.http.HttpMng;
import com.yrkj.util.log.DLog;

public class EmptyActivity extends Activity {
	private Context mContext = null;
	private boolean mHasBeenInit = false;
	private TextView mTextView = null;
	
	private static EmptyActivity mActy = null;
	public static EmptyActivity getInstance(){
		return mActy;
	}
	public  void _finish(){
		if(mActy != null){
			mActy.finish();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empy);
//		DLog.LOG("EmptyActivity--------[onCreate]");
		mContext = this;
//		Intent intent1 = new Intent(mContext, MainActivity.class);
//		startActivity(intent1);
		
		
		mActy = this;
		mContext = this;
		mTextView = (TextView) findViewById(R.id.txtTextView);
		
		WSInfo ws = SysMng.getWSInfo();
		if(ws.WSCode.length() == 0
				|| ws.AccessKey.length() == 0
				|| ws.SecretKey.length() == 0
				|| ws.CrateMask.length() == 0){
			mHasBeenInit = false;
		}else{
			mHasBeenInit = true;
		}
		doInitTask();
//		initActy();

		
//		Intent intent = new Intent(this, LaunchActivity.class);
//		startActivity(intent);
//		finish();
		
		
	}
	
	private void initActy(){
//		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
//    	startService(intent);
//    	
//    	BaseApplication.getInstance().mLocationClient.start();
    	
		
		TxnInfo txnInfo = SysMng.getTxnInfo();
		if(mHasBeenInit && txnInfo.CarCode.length() != 0
				&& txnInfo.DriverCode.length() != 0
				&& txnInfo.InspectorCode.length() != 0)
		{
			Intent intent1 = new Intent(mContext, MainActivity.class);
			intent1.putExtra(MainActivity.INTENT_KEY_SHOW, MainActivity.INTENT_VAL_SHOW_ShowMain);
			startActivity(intent1);
		}else{
			
			Intent intent1 = new Intent(mContext, MainActivity.class);
			intent1.putExtra(MainActivity.INTENT_KEY_SHOW, MainActivity.INTENT_VAL_SHOW_ShowInit);
			startActivity(intent1);
		}
		this.finish();
//    	mTextView = (TextView) findViewById(R.id.txtTextView);
//    	BaseApplication.getInstance().locationTextView = mTextView;
	}

//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
////		DLog.LOG("EmptyActivity--------[onDestroy]");
////		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
////		stopService(intent);
////		BaseApplication.getInstance().mLocationClient.stop();
//		
//	}
	
//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
////		DLog.LOG("EmptyActivity--------[onResume]");
//	}
	private void doInitTask(){
		if(mInitTask == null){
			mInitTask = new InitSystem();
		}
		
		if(mInitTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mInitTask.getStatus() == Status.FINISHED){
			mInitTask = new InitSystem();
		}
		
		
		if(mInitTask.getStatus() != Status.RUNNING){
			mInitTask.execute();
		}
	}
	InitSystem mInitTask= null;
	class InitSystem extends AsyncTask<Object, Object, Boolean>{

		@Override
		protected void onPreExecute() {
//			DialogHelper.getProgressDialogInstance().show(mContext, "系统初始化据提交中");
			mTextView = (TextView) findViewById(R.id.txtTextView);
			mTextView.setText("系统初始化配置");
		}
		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String url = BaseApplication.getServiceConfig();
			String cfgJson = HttpMng.doHttpReadFileURL(url);
			if(cfgJson == null){
				return false;
			}else{
				ResponseConfigBody body = ResJsonHelper.getConfigBodyFromJson(cfgJson);
				if(body != null){
					BaseApplication.setSerciverUrlHandler(body.MWR_Moblie_Service);
					return true;
				}
				
			}
			return false;
		}
	
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				mTextView.setText("初始化完成");
			}else{
				mTextView.setText("初始化失败，读取配置文件错误。请检查网络！");
				
			}
			
			TxnInfo txnInfo = SysMng.getTxnInfo();
			if(mHasBeenInit && txnInfo.CarCode.length() != 0
					&& txnInfo.DriverCode.length() != 0
					&& txnInfo.InspectorCode.length() != 0)
			{
				Intent intent1 = new Intent(mContext, MainActivity.class);
				intent1.putExtra(MainActivity.INTENT_KEY_SHOW, MainActivity.INTENT_VAL_SHOW_ShowMain);
				startActivity(intent1);
				mActy.finish();
			}else{
				if(result){
					Intent intent1 = new Intent(mContext, MainActivity.class);
					intent1.putExtra(MainActivity.INTENT_KEY_SHOW, MainActivity.INTENT_VAL_SHOW_ShowInit);
					startActivity(intent1);
					mActy.finish();
				}
			}
			
			
		}
	}
}
