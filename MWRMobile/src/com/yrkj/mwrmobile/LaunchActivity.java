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
import com.yrkj.mwrmobile.dao.BaseDataDao;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ActivityLaunch;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.mwrmobile.util.scanner.MWRCaptureActivity;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.ToastUtil;

public class LaunchActivity extends Activity implements OnClickListener {

	public final static String Tag = "com.yrkj.mwrmobile.LaunchActivity";
	
	private final static int SysStatus_NoInit = 1;
	private final static int SysStatus_NoLogin = 2;
//	private final static int SysStatus_
	
	private ActivityLaunch mLayout = null;
	private Context mContext = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		initData();
		initActy();
	}
	
	private void initData(){
		mContext = this;
	}
	
	private void initActy(){
		mLayout = new ActivityLaunch(this);
		
		mLayout.getBtnExit().setOnClickListener(this);
		mLayout.getBtnScan().setOnClickListener(this);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == Activity.RESULT_OK){
			String wsCode = data.getExtras().getString("wsCode");
			String ak = data.getExtras().getString("ak");
			String sk = data.getExtras().getString("sk");
			
			ToastUtil.show(this, wsCode+" " + ak + " " + sk);
			doTask(wsCode,ak,sk);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == ActivityLaunch.BtnExitId){
			finish();
		}else if(v.getId() == ActivityLaunch.BtnScanId){
			boolean hasInit = false;
			CaptureHelper.ShowCapture(this,
					hasInit ? MWRCaptureActivity.SCANNERTYPE_KEY_Login :
							  MWRCaptureActivity.SCANNERTYPE_KEY_InitSystem);
		}
		
	}
	
	private InitTask mTask = null;
	public void doTask(String code,String ak,String sk){
		
		if(mTask == null){
			mTask = new InitTask(code,ak, sk);
		}
		
		if(mTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mTask.getStatus() == Status.FINISHED){
			mTask = new InitTask(code,ak, sk);
		}
		
		
		if(mTask.getStatus() != Status.RUNNING){
			mTask.execute();
		}
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == TxnDao.Txn_failed){
				ToastUtil.show(mContext, msg.obj.toString());
			}
		}
	};
	
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
			// TODO Auto-generated method stub
			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			String url = BaseApplication.Service_URL;
			
			String s = BaseDataDao.RegistWS(mContext, url,wsCode, accessKey, secretKey, handler);//TxnDao.sendTxnToInventory(mContext, url, accessKey, secretKey, handler);
//			DLog.LOG(s+" init task");
//			
			return null;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			if(result){
////				AssessDBCtrl
////				.deletAssessTaskHeaderById(getActivity(), mLocTaskHeaderId);
////				reBind();
//			}
			
			DialogHelper.getProgressDialogInstance().close();
			
		}
		
	}
	
	
}
