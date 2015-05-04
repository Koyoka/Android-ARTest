package com.yrkj.mwrmobile.fragment;

import com.yrkj.mwrmobile.EmptyActivity;
import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.RecoverActivity;
import com.yrkj.mwrmobile.SettingsActivity;
import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.TxnInfo;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ActivityMain;
import com.yrkj.mwrmobile.service.BackWorkSerive;
import com.yrkj.mwrmobile.service.CommonBroadcast;
import com.yrkj.mwrmobile.service.CommonBroadcast.BroadcastListener;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.ToastUtil;

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
import android.widget.TextView;


public class MainFragment extends Fragment implements OnClickListener {

	private ActivityMain mLayout = null;
	private Context mContext = null;
	private String[] mReport = null;
	private CommonBroadcast b1 = null;
	
	public MainFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_main,
				container, false);
		initFrag(rootView);
		return rootView;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(b1!=null){
			this.getActivity().
			 unregisterReceiver(b1);  
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mReport = TxnDao.getTxnReport(mContext);
		mLayout.getTxtTotalCount().setText("总数量："+mReport[0]);
		mLayout.getTxtTotalWeight().setText("总重量："+mReport[1]+" kg");
	}
	
	

	private void initFrag(View v){
		mContext = this.getActivity();
		
		mLayout = new ActivityMain(v);
		
		mLayout.getBtnLogout().setOnClickListener(this);
		mLayout.getBtnRecoverCrate().setOnClickListener(this);
		mLayout.getBtnRecoverToDestroy().setOnClickListener(this);
		mLayout.getBtnSetting().setOnClickListener(this);
		mLayout.getBtnRecoverToInventroy().setOnClickListener(this);
		
		
//		mLayout.getBtnRecoverToInventroy().setEnabled(false);
//		mLayout.getBtnRecoverToDestroy().setEnabled(false);
		
		WSInfo wsInfo = SysMng.getWSInfo();
		TxnInfo txnInfo = SysMng.getTxnInfo();
		mLayout.getTxtWSCode().setText(mLayout.getTxtWSCode().getText()+wsInfo.WSCode);
		mLayout.getTxtDriver().setText(mLayout.getTxtDriver().getText()+txnInfo.DriverName);
		mLayout.getTxtInspector().setText(mLayout.getTxtInspector().getText() + txnInfo.InspectroName);
		mLayout.getTxtCarCode().setText(mLayout.getTxtCarCode().getText() + txnInfo.CarCode);
		
		b1 = CommonBroadcast.regist(mContext, new BroadcastListener() {
			
			@Override
			public void onListener(Bundle extras) {
				int state = extras.getInt(BackWorkSerive.INTENT_KEY_APNType);
				if(state == 1){
//					mLayout.getBtnScan().setEnabled(true);
					mLayout.getTxtNetState().setText("在线");
					mLayout.getBtnRecoverToInventroy().setEnabled(true);
					mLayout.getBtnRecoverToDestroy().setEnabled(true);
				}else{
					mLayout.getBtnRecoverToInventroy().setEnabled(false);
					mLayout.getBtnRecoverToDestroy().setEnabled(false);
//					mLayout.getBtnScan().setEnabled(false);
					mLayout.getTxtNetState().setText("离线");
				}
				
			}
		});
	}
	
	private void openInitFrag(){
		FragmentManager fm = this.getActivity().getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();  
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		InitFragment initFragment = new InitFragment();
        transaction.replace(R.id.container, initFragment,"carOut");  
        transaction.commit();  
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case ActivityMain.BtnRecoverCrateId:
			intent = new Intent(mContext, RecoverActivity.class);
			startActivity(intent);
			break;
		case ActivityMain.BtnLogoutId:
			this.getActivity().finish();
//			EmptyActivity.getInstance().finish();
			
//			BaseApplication.getInstance().mLocationClient.start();
			
			break;
		case ActivityMain.BtnSettingId:
			intent = new Intent(mContext,SettingsActivity.class);
			startActivity(intent);
			break;
		case ActivityMain.BtnRecoverToDestroyId:
			doTask(TxnDao.SendTxn_To_Destroy);
			break;
		
		case ActivityMain.BtnRecoverToInventroyId:
//			EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog");
			doTask(TxnDao.SendTxn_To_Inventory);
			break;
		default:
			break;
		}
	}
	
	private SendTask mTask = null;
	private void doTask(int sendType){
		if(mTask == null){
			mTask = new SendTask(sendType);
		}
		
		if(mTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mTask.getStatus() == Status.FINISHED){
			mTask = new SendTask(sendType);
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
			}else{
				ToastUtil.show(mContext, "数据提交成功");
			}
		}
	};
	
	class SendTask extends AsyncTask<Object, Object, Boolean>{
		int mSendType = 0;
		public SendTask(int sendType){
			mSendType = sendType;
			
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			DialogHelper.getProgressDialogInstance().show(mContext, "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			WSInfo ws = SysMng.getWSInfo();
			String url = BaseApplication.getServiceUrl();
			String accessKey = ws.AccessKey;//"9e15f4f7d6fdc178eeab8caf79d863054bdfea78";
			String secretKey = ws.SecretKey;//"ae46214f1ee0269f7eb5126895ff166f02ede4f1";
			
			boolean result = TxnDao.sendTxn(mContext, url, accessKey, secretKey,mSendType, handler);
			
			return result;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			DialogHelper.getProgressDialogInstance().close();
			if(result){
				mLayout.getTxtTotalCount().setText("总数量：0");
				mLayout.getTxtTotalWeight().setText("总重量：0 kg");
				SysMng.saveTxnInfo("", "", "", "", "");
				
				
				DialogHelper.createTextDialog(getActivity(), "班次完成", "请于管理中心提交终端，或重新接受任务",new ConfirmDialogListener() {
					
					@Override
					public void onClickListener(boolean result) {
						// TODO Auto-generated method stub
						openInitFrag();
					}
				});
			}
			
			
		}
		
		
	}
}
