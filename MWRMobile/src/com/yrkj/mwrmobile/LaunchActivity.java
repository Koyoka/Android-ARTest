package com.yrkj.mwrmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
import com.yrkj.mwrmobile.bean.response.ResponseInitMWSSubmitBody;
import com.yrkj.mwrmobile.bean.response.ResponseStartCarRecoverShiftBody;
import com.yrkj.mwrmobile.dao.BaseDataDao;
import com.yrkj.mwrmobile.dao.ResJsonHelper;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ActivityLaunch;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.mwrmobile.util.scanner.MWRCaptureActivity;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class LaunchActivity extends Activity implements OnClickListener {

	public final static String Tag = "com.yrkj.mwrmobile.LaunchActivity";
	
	private final static int SysStatus_NoInit = 1;
	private final static int SysStatus_NoLogin = 2;
//	private final static int SysStatus_
	
	private ActivityLaunch mLayout = null;
	private Context mContext = null;
	
	private boolean mHasBeenInit = false;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_launch);
		initData();
		initActy();
	}
	
	private void initData(){
		mContext = this;
		
		WSInfo ws = SysMng.getWSInfo();
		
		if(ws.WSCode.length() == 0
				|| ws.AccessKey.length() == 0
				|| ws.SecretKey.length() == 0
				|| ws.CrateMask.length() == 0){
			mHasBeenInit = false;
		}else{
			mHasBeenInit = true;
		}
		
		TxnInfo txnInfo = SysMng.getTxnInfo();
		if(mHasBeenInit && txnInfo.CarCode.length() != 0
				&& txnInfo.DriverCode.length() != 0
				&& txnInfo.InspectorCode.length() != 0)
		{
			Intent intent = new Intent(mContext, MainActivity.class);
			startActivity(intent);
			finish();
		}
		
//		DLog.LOG("mHasBeenInit " + mHasBeenInit);
	}
	
	private void initActy(){
		mLayout = new ActivityLaunch(this);
		
		mLayout.getBtnExit().setOnClickListener(this);
		mLayout.getBtnScan().setOnClickListener(this);
		
		String title = "系统信息:";
		String info = "";
		if(mHasBeenInit){
			info = "没有回收任务，请扫描获取任务";
			WSInfo wsInfo = SysMng.getWSInfo();
			mLayout.getTxtWSCode().setText(wsInfo.WSCode);
		}else{
			info = "系统没有初始化，请扫描注册";
		}
		mLayout.getTxtLoginInfo().setText(title+"\n"+info);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == Activity.RESULT_OK){
			
			if(mHasBeenInit){//login start recover{}
				String ws, car, ak, sk, driver, inspector;
				WSInfo wsInfo = SysMng.getWSInfo();
				
				ws = wsInfo.WSCode;
				ak = wsInfo.AccessKey;
				sk = wsInfo.SecretKey;
				
				car = data.getExtras().getString("carCode");
				driver = data.getExtras().getString("driverCode");
				inspector = data.getExtras().getString("inspectorCode");
				
				doStartCarRecoverTask(ws,  ak, sk,car, driver, inspector);
			}else{

				String wsCode = data.getExtras().getString("wsCode");
				String ak = data.getExtras().getString("ak");
				String sk = data.getExtras().getString("sk");
				
//				ToastUtil.show(this, wsCode+" " + ak + " " + sk);
				doInitTask(wsCode,ak,sk);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == ActivityLaunch.BtnExitId){
			finish();
		}else if(v.getId() == ActivityLaunch.BtnScanId){
			boolean hasInit = mHasBeenInit;
			CaptureHelper.ShowCapture(this,
					hasInit ? MWRCaptureActivity.SCANNERTYPE_KEY_Login :
							  MWRCaptureActivity.SCANNERTYPE_KEY_InitSystem);
		}
		
	}
	
	private InitTask mInitTask = null;
	private StartCarRecoverTask mStartTask = null;
	
	private void doStartCarRecoverTask(String ws,String ak,String sk,String car,String driver,String inspector){
		if(mStartTask == null){
			mStartTask = new StartCarRecoverTask(ws,ak,sk,car,driver,inspector);
		}
		
		if(mStartTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mStartTask.getStatus() == Status.FINISHED){
			mStartTask = new StartCarRecoverTask(ws,ak,sk,car,driver,inspector);
		}
		
		
		if(mStartTask.getStatus() != Status.RUNNING){
			mStartTask.execute();
		}
	}
	private void doInitTask(String code,String ak,String sk){
		if(mInitTask == null){
			mInitTask = new InitTask(code,ak, sk);
		}
		
		if(mInitTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mInitTask.getStatus() == Status.FINISHED){
			mInitTask = new InitTask(code,ak, sk);
		}
		
		
		if(mInitTask.getStatus() != Status.RUNNING){
			mInitTask.execute();
		}
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == BaseDataDao.Opt_failed){
				ToastUtil.show(mContext, msg.obj.toString());
			}
		}
	};
	
	class StartCarRecoverTask extends AsyncTask<Object, Object, String>{

		private String wsCode = "";
		private String accessKey = "";
		private String secretKey = "";
		private String carCode = "";
		private String driverCode = "";
		private String inspectorCode = "";
		
		public StartCarRecoverTask(String ws,String ak,String sk,String car,String driver,String inspector){
			wsCode = ws;
			accessKey = ak;
			secretKey = sk;
			carCode = car;
			driverCode = driver;
			inspectorCode = inspector;
		}
		

		@Override
		protected void onPreExecute() {
			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
		}
		
		@Override
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String url = BaseApplication.Service_URL;
			String result = BaseDataDao.StartCarRecover(mContext, 
					url, wsCode, accessKey, secretKey, 
					carCode, driverCode, inspectorCode);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			ResponseBody body = 
			ResJsonHelper.getBodyFromJson(result);
			if(body == null){
				ToastUtil.show(mContext, "网络连接错误");
			}else if(body.Error){
				ToastUtil.show(mContext, body.ErrMsg);
			}else if(!body.Error){
//				ToastUtil.show(mContext, "ע��ɹ�" );
				ResponseStartCarRecoverShiftBody valBody 
					= ResJsonHelper.ConventJsonToBody(body.Result,ResponseStartCarRecoverShiftBody.class);
				if(valBody == null){
					ToastUtil.show(mContext, "返回参数错误");
				}else{
					
					DLog.LOG("-----result " + result);
					SysMng.
					saveTxnInfo(
							valBody.CarCode,
							valBody.DriverCode,
							valBody.Driver,
							valBody.InspectorCode,
							valBody.Inspector);
					
//					WSInfo ws = SysMng.getWSInfo();
					StringBuilder sb = new StringBuilder();
					sb.append(valBody.CarCode + " \n");
					sb.append(valBody.DriverCode + " \n");
					sb.append(valBody.Driver + " \n");
					sb.append(valBody.InspectorCode + " \n");
					sb.append(valBody.Inspector + " \n");
//					sb.append(ws.AccessKey+"\n");
//					sb.append(ws.SecretKey+"\n");
//					sb.append(ws.CrateMask+"\n");
//					sb.append(ws.WSCode+"\n");
					DLog.LOG("-----txninfo " + sb.toString());
					
				}
				finish();
				
				Intent intent = new Intent(mContext, MainActivity.class);
				startActivity(intent);
			}
			DialogHelper.getProgressDialogInstance().close();
		}
		
	}
	
	class InitTask extends AsyncTask<Object, Object, Boolean>{
		private String accessKey = "";
		private String secretKey = "";
		private String wsCode = "";
		
		public InitTask(String code,String ak,String sk){
			wsCode = code;
			accessKey = ak;
			secretKey = sk;
		}

		@Override
		protected void onPreExecute() {
			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			
			String url = BaseApplication.Service_URL;
			return BaseDataDao.RegistWS(mContext, url,wsCode, accessKey, secretKey,handler);//TxnDao.sendTxnToInventory(mContext, url, accessKey, secretKey, handler);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				Intent intent = new Intent(mContext, LaunchActivity.class);
				startActivity(intent);
				finish();
			}
			DialogHelper.getProgressDialogInstance().close();
			
//			ResponseBody body = 
//			ResJsonHelper.getBodyFromJson(result);
//			if(body == null){
//				ToastUtil.show(mContext, "网络连接错误");
//			}else if(body.Error){
//				ToastUtil.show(mContext, body.ErrMsg);
//			}else if(!body.Error){
////				ToastUtil.show(mContext, "ע��ɹ�" );
////				ToastUtil.show(mContext, body.Result+" ע��ɹ�" );
//			}
//			
//			ResponseInitMWSSubmitBody initBody = 
//					ResJsonHelper.getInitBodyFromJson(body.Result);
//			
//			if(initBody == null){
//				ToastUtil.show(mContext, "网络连接错误");
//			}else{
//				SysMng.saveWSInfo(initBody.WSCode, initBody.AssessKey, initBody.SecretKey, initBody.CrateMask);
//				
//				finish();
//			
//				Intent intent = new Intent(mContext, LaunchActivity.class);
//				startActivity(intent);
//				
//			}
			
//			WSInfo ws = SysMng.getWSInfo();
//			StringBuilder sb = new StringBuilder();
			
//			sb.append(ws.AccessKey+"\n");
//			sb.append(ws.SecretKey+"\n");
//			sb.append(ws.CrateMask+"\n");
//			sb.append(ws.WSCode+"\n");
//			DLog.LOG("-----wsinfo " + sb.toString());
			
//			SysMng.saveWSInfo(), crateMask)
			
		}
		
	}
	
	
}
