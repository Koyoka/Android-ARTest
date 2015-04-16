package com.yrkj.mwrmobile;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.fragment.InitFragment;
import com.yrkj.mwrmobile.fragment.MainFragment;
import com.yrkj.mwrmobile.layout.ActivityMain;
import com.yrkj.mwrmobile.service.BackWorkSerive;

public class MainActivity extends Activity  {

	public static final String INTENT_KEY_SHOW = "showFrag";
	public static final int INTENT_VAL_SHOW_ShowInit = 0;
	public static final int INTENT_VAL_SHOW_ShowMain = 1;
	
	private ActivityMain mLayout = null;
	private Context mContext = null;
	
	private String[] mReport = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		initActy1();
		
//		setContentView(R.layout.activity_main);
//		initData();
//		initActy();
	}
	
	private void initActy1(){
		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
    	startService(intent);
    	
    	BaseApplication.getInstance().mLocationClient.start();
		
    	Intent defineIntent = getIntent();
    	int val = defineIntent.getIntExtra(INTENT_KEY_SHOW, 0);
    	if(val == INTENT_VAL_SHOW_ShowInit){
    		openInitFrag();
    	}else{
    		openMainFrag();
    	}
    		
//		FragmentManager fm = getFragmentManager();  
//        FragmentTransaction transaction = fm.beginTransaction();  
//        
//        InitFragment initFragment = new InitFragment();
//        transaction.replace(R.id.container, initFragment);  
//        transaction.commit();  
		
	}
	
	private void openInitFrag(){
		FragmentManager fm = getFragmentManager();  
        FragmentTransaction transaction = fm.beginTransaction();  
        
        InitFragment initFragment = new InitFragment();
        transaction.replace(R.id.container, initFragment);  
        transaction.commit();  
	}
	private void openMainFrag(){
		FragmentManager fm = getFragmentManager();  
        FragmentTransaction transaction = fm.beginTransaction();  
        
        MainFragment mainFragment = new MainFragment();
        transaction.replace(R.id.container, mainFragment);  
        transaction.commit();  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
		stopService(intent);
		BaseApplication.getInstance().mLocationClient.stop();
	}
	
//	CommonBroadcast b1 = null;
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
////		EmptyActivity.getInstance().finish();
////		if(b1!=null){
////			 unregisterReceiver(b1);  
////		}
////		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
////		stopService(intent);
//	}
	
//	private void initData(){
//		
//	}
//	
//	private void initActy(){
//		mContext = this;
//		
//		mLayout = new ActivityMain(this);
//		
//		mLayout.getBtnLogout().setOnClickListener(this);
//		mLayout.getBtnRecoverCrate().setOnClickListener(this);
//		mLayout.getBtnRecoverToDestroy().setOnClickListener(this);
//		mLayout.getBtnSetting().setOnClickListener(this);
//		mLayout.getBtnRecoverToInventroy().setOnClickListener(this);
//		
//		
////		mLayout.getBtnRecoverToInventroy().setEnabled(false);
////		mLayout.getBtnRecoverToDestroy().setEnabled(false);
//		
//		WSInfo wsInfo = SysMng.getWSInfo();
//		TxnInfo txnInfo = SysMng.getTxnInfo();
//		mLayout.getTxtWSCode().setText(mLayout.getTxtWSCode().getText()+wsInfo.WSCode);
//		mLayout.getTxtDriver().setText(mLayout.getTxtDriver().getText()+txnInfo.DriverName);
//		mLayout.getTxtInspector().setText(mLayout.getTxtInspector().getText() + txnInfo.InspectroName);
//		mLayout.getTxtCarCode().setText(mLayout.getTxtCarCode().getText() + txnInfo.CarCode);
//		
//		b1 = CommonBroadcast.regist(this, new BroadcastListener() {
//			
//			@Override
//			public void onListener(Bundle extras) {
//				int state = extras.getInt(BackWorkSerive.INTENT_KEY_APNType);
//				if(state == 1){
////					mLayout.getBtnScan().setEnabled(true);
//					mLayout.getTxtNetState().setText("在线");
//					mLayout.getBtnRecoverToInventroy().setEnabled(true);
//					mLayout.getBtnRecoverToDestroy().setEnabled(true);
//				}else{
//					mLayout.getBtnRecoverToInventroy().setEnabled(false);
//					mLayout.getBtnRecoverToDestroy().setEnabled(false);
////					mLayout.getBtnScan().setEnabled(false);
//					mLayout.getTxtNetState().setText("离线");
//				}
//				
//			}
//		});
////		Intent intent = new Intent(getBaseContext(), BackWorkSerive.class);
////    	startService(intent);
//		
//	}
	
//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		mReport = TxnDao.getTxnReport(this);
//		mLayout.getTxtTotalCount().setText("总数量："+mReport[0]);
//		mLayout.getTxtTotalWeight().setText("总重量："+mReport[1]+" kg");
//	}

//	@Override
//	public void onClick(View v) {
//		Intent intent;
//		switch (v.getId()) {
//		case ActivityMain.BtnRecoverCrateId:
//			intent = new Intent(this, RecoverActivity.class);
//			startActivity(intent);
//			break;
//		case ActivityMain.BtnLogoutId:
//			finish();
//			EmptyActivity.getInstance().finish();
//			
////			BaseApplication.getInstance().mLocationClient.start();
//			
//			break;
//		case ActivityMain.BtnSettingId:
//			intent = new Intent(this,SettingsActivity.class);
//			startActivity(intent);
//			break;
//		case ActivityMain.BtnRecoverToDestroyId:
//			doTask(TxnDao.SendTxn_To_Destroy);
//			break;
//		
//		case ActivityMain.BtnRecoverToInventroyId:
////			EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog");
//			doTask(TxnDao.SendTxn_To_Inventory);
//			break;
//		default:
//			break;
//		}
//	}
	
//	private SendTask mTask = null;
//	private void doTask(int sendType){
//		if(mTask == null){
//			mTask = new SendTask(sendType);
//		}
//		
//		if(mTask.getStatus() == Status.RUNNING){
//			return;
//		}
//		
//		if(mTask.getStatus() == Status.FINISHED){
//			mTask = new SendTask(sendType);
//		}
//		
//		
//		if(mTask.getStatus() != Status.RUNNING){
//			mTask.execute();
//		}
//	}
//	
//	Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			if(msg.what == TxnDao.Txn_failed){
//				ToastUtil.show(mContext, msg.obj.toString());
//			}else{
//				ToastUtil.show(mContext, "数据提交成功");
//			}
//		}
//	};
//	
//	class SendTask extends AsyncTask<Object, Object, Boolean>{
//		int mSendType = 0;
//		public SendTask(int sendType){
//			mSendType = sendType;
//			
//		}
//		
//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
//		}
//		
//		@Override
//		protected Boolean doInBackground(Object... params) {
//			// TODO Auto-generated method stub
//			WSInfo ws = SysMng.getWSInfo();
//			String url = BaseApplication.getServiceUrl();
//			String accessKey = ws.AccessKey;//"9e15f4f7d6fdc178eeab8caf79d863054bdfea78";
//			String secretKey = ws.SecretKey;//"ae46214f1ee0269f7eb5126895ff166f02ede4f1";
//			
//			boolean result = TxnDao.sendTxn(mContext, url, accessKey, secretKey,mSendType, handler);
////			ToastUtil.show(mContext,"request result : "+s);
////			DLog.LOG(s+" main task");
//			
//			return result;
//		}
//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
////			if(result){
//////				AssessDBCtrl
//////				.deletAssessTaskHeaderById(getActivity(), mLocTaskHeaderId);
//////				reBind();
////			}
//			if(result){
//				mLayout.getTxtTotalCount().setText("总数量：0");
//				mLayout.getTxtTotalWeight().setText("总重量：0 kg");
//				SysMng.saveTxnInfo("", "", "", "", "");
//			}
//			DialogHelper.getProgressDialogInstance().close();
//			
//		}
//	}
	
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
