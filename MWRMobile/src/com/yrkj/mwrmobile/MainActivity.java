package com.yrkj.mwrmobile;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.AsyncTask.Status;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ActivityMain;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class MainActivity extends Activity implements OnClickListener {

	private ActivityMain mLayout = null;
	private Context mContext = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initActy();
	}
	
	private void initActy(){
		mContext = this;
		
		mLayout = new ActivityMain(this);
		
		mLayout.getBtnRecoverToInventroy().setOnClickListener(this);
		
		ArrayList<TxnDetailData> ds =
		 TxnDao.getTxnDetail(this);
		 DLog.LOGD(ds + " size:" + ds.size());
		findViewById(R.id.btnRecoverCrate).setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnRecoverCrate:
			Intent intent = new Intent(this, RecoverActivity.class);
			startActivity(intent);
			break;
		case ActivityMain.BtnRecoverToInventroyId:
//			EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog");
			doTask();
			break;
		default:
			break;
		}
	}
	
	private SendTask mTask = null;
	private void doTask(){
		if(mTask == null){
			mTask = new SendTask();
		}
		
		if(mTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mTask.getStatus() == Status.FINISHED){
			mTask = new SendTask();
		}
		
		
		if(mTask.getStatus() != Status.RUNNING){
			mTask.execute();
		}
	}
	class SendTask extends AsyncTask<Object, Object, Boolean>{

		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == TxnDao.Txn_failed){
					ToastUtil.show(mContext, msg.obj.toString());
				}
			}
		};
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
//			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			
			String url = "http://192.168.1.201/Services/MWMobileWSHandler.ashx";
			String accessKey = "9e15f4f7d6fdc178eeab8caf79d863054bdfea78";
			String secretKey = "ae46214f1ee0269f7eb5126895ff166f02ede4f1";
			
			String s = TxnDao.sendTxnToInventory(mContext, url, accessKey, secretKey, handler);
//			ToastUtil.show(mContext,"request result : "+s);
			DLog.LOG(s+" main task");
			
			return null;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
//			if(result){
////				AssessDBCtrl
////				.deletAssessTaskHeaderById(getActivity(), mLocTaskHeaderId);
////				reBind();
//			}
			
//			DialogHelper.getProgressDialogInstance().close();
			
		}
	}
	
}

/*
 * 
 * 	
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(1==resultCode){
//			
//			int width = data.getExtras().getInt("width");//.getIntExtra("width", 0);
//			int height =  data.getExtras().getInt("height");
//			String result = data.getExtras().getString("result");
//			Toast.makeText(this, "result:" + result, Toast.LENGTH_LONG).show();
//		
//		}
//	}

 * //		Intent intent = getIntent();
//		if(intent != null)
//		{
//			String result = intent.getStringExtra("result");
//			ToastUtil.show(this, result +"   eleven");
//		}

 * Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
if(false){
	intent.putExtra(CaptureActivity.INTENT_KEY_ResultType,
			CaptureActivity.INTENT_VAL_ResultType_SetResult);
	startActivityForResult(intent, 9);
}else{
	
	intent.putExtra(CaptureActivity.INTENT_KEY_ResultType,
			CaptureActivity.INTENT_VAL_ResultType_NewActy);
	intent.putExtra(CaptureActivity.INTENT_KEY_PKG,
			"com.yrkj.mwrmobile");
	intent.putExtra(CaptureActivity.INTENT_KEY_CLZName,
			"com.yrkj.mwrmobile.MainActivity");
	startActivity(intent);
}
		
 * */
