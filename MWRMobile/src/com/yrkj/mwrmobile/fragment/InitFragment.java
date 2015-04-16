package com.yrkj.mwrmobile.fragment;

import com.yrkj.mwrmobile.LaunchActivity;
import com.yrkj.mwrmobile.MainActivity;
import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.bean.response.ResponseBody;
import com.yrkj.mwrmobile.bean.response.ResponseStartCarRecoverShiftBody;
import com.yrkj.mwrmobile.dao.BaseDataDao;
import com.yrkj.mwrmobile.dao.ResJsonHelper;
import com.yrkj.mwrmobile.layout.ActivityLaunch;
import com.yrkj.mwrmobile.service.BackWorkSerive;
import com.yrkj.mwrmobile.service.CommonBroadcast;
import com.yrkj.mwrmobile.service.CommonBroadcast.BroadcastListener;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.mwrmobile.util.scanner.MWRCaptureActivity;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.AsyncTask.Status;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class InitFragment extends Fragment implements OnClickListener {
	
	private ActivityLaunch mLayout = null;
	private Context mContext = null;
	private CommonBroadcast b1 = null;
	private boolean mHasBeenInit = false;
	
	public InitFragment() {
		// Required empty public constructor
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		DLog.LOG("InitFragment--------[onCreateView]");
		View rootView = inflater.inflate(R.layout.activity_launch,
				container, false);
		initActy(rootView);
		return rootView;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		DLog.LOG("InitFragment--------[onDestroy]");
		if(b1!=null){
			this.getActivity().
			 unregisterReceiver(b1);  
		}
	}
	
	private void initActy(View v){
		mContext = this.getActivity();
		mLayout = new ActivityLaunch(v);
		
		mLayout.getBtnExit().setOnClickListener(this);
		mLayout.getBtnScan().setOnClickListener(this);
		
		String title = "系统信息:";
		String info = "";
		
		WSInfo ws = SysMng.getWSInfo();
		if(ws.WSCode.length() == 0
				|| ws.AccessKey.length() == 0
				|| ws.SecretKey.length() == 0
				|| ws.CrateMask.length() == 0){
			mHasBeenInit = false;
		}else{
			mHasBeenInit = true;
		}
		if(mHasBeenInit){
			info = "没有回收任务，请扫描获取任务";
			WSInfo wsInfo = SysMng.getWSInfo();
			mLayout.getTxtWSCode().setText(wsInfo.WSCode);
		}else{
			info = "系统没有初始化，请扫描注册";
		}
		mLayout.getTxtLoginInfo().setText(title+"\n"+info);
		b1 = CommonBroadcast.regist(mContext, new BroadcastListener() {
			
			@Override
			public void onListener(Bundle extras) {
				int state = extras.getInt(BackWorkSerive.INTENT_KEY_APNType);
				if(state == 1){
					mLayout.getBtnScan().setEnabled(true);
					mLayout.getTxtNetState().setText("在线");
				}else{
					mLayout.getBtnScan().setEnabled(false);
					mLayout.getTxtNetState().setText("离线");
				}
				
			}
		});
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
//			{
//				String wsCode = data.getExtras().getString("wsCode");
//				String ak = data.getExtras().getString("ak");
//				String sk = data.getExtras().getString("sk");
//				ToastUtil.show(mContext, wsCode+ " " + ak + " " + sk);
//			}
//			if(true)
//				return;
			
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
				
				doInitTask(wsCode,ak,sk);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == ActivityLaunch.BtnExitId){
			this.getActivity().
			finish();
			
		}else if(v.getId() == ActivityLaunch.BtnScanId){
			
			boolean hasInit = mHasBeenInit;
			CaptureHelper.ShowCapture(this,
					hasInit ? MWRCaptureActivity.SCANNERTYPE_KEY_Login :
							  MWRCaptureActivity.SCANNERTYPE_KEY_InitSystem);
		}
		
	}
	
	private void openMainFrag(){
		FragmentManager fm = this.getActivity().getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();  
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        MainFragment mainFragment = new MainFragment();
        transaction.replace(R.id.container, mainFragment);  
        transaction.commit();  
	}
	
	private void openInitFrag(){
		FragmentManager fm = this.getActivity().getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();  
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		InitFragment initFragment = new InitFragment();
        transaction.replace(R.id.container, initFragment,"carOut");  
        transaction.commit();  
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
			String url = BaseApplication.getServiceUrl();
			
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
				openMainFrag();
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
			
			String url = BaseApplication.getServiceUrl();
			DLog.LOG("doInBackground--------[url] " + url);
			return BaseDataDao.RegistWS(mContext, url,wsCode, accessKey, secretKey,handler);//TxnDao.sendTxnToInventory(mContext, url, accessKey, secretKey, handler);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				openInitFrag();
			}
			DialogHelper.getProgressDialogInstance().close();
			
		}
		
	}
}
