package com.yrkj.mwrmobile;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.AsyncTask.Status;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yrkj.mwrmobile.LaunchActivity.InitTask;
import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.base.WSInfo;
import com.yrkj.mwrmobile.dao.BaseDataDao;
import com.yrkj.mwrmobile.layout.FragmentHeaderLayout;
import com.yrkj.mwrmobile.layout.FragmentSettings;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.mwrmobile.util.scanner.MWRCaptureActivity;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.ToastUtil;

public class SettingsActivity extends Activity implements OnClickListener {

	private FragmentHeaderLayout mHeaderLayout = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initActy();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new SettingsFragment()).commit();
		}
	}
	
	private void initActy(){
		View v = this.findViewById(R.id.fmtHeader);
		mHeaderLayout = new FragmentHeaderLayout(v);
		mHeaderLayout.getTxtActionBarTitle().setText("功能设置");
		mHeaderLayout.getBtnActionOps().setVisibility(View.INVISIBLE);
		
		mHeaderLayout.getBtnActionBarBack().setOnClickListener(this);
//		mHeaderLayout.getBtnActionOps().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == FragmentHeaderLayout.BtnActionBarBackId){
			finish();
		}
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class SettingsFragment extends Fragment implements OnClickListener {

		private Context mContext = null;
		private FragmentSettings mLayout = null;
		
		public SettingsFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mContext = this.getActivity();
			View rootView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			initFrm(rootView);
			return rootView;
		}
		
		private void initFrm(View v){
			mLayout = new FragmentSettings(v);
//			v.findViewById(R.id.btnSettingAysnData).setOnClickListener(this);
			mLayout.getBtnSettingAysnData().setOnClickListener(this);
			mLayout.getBtnSettingEditHost().setOnClickListener(this);
		}

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int viewId = v.getId();
			if(viewId == //R.id.btnSettingAysnData
					FragmentSettings.BtnSettingAysnDataId){
				String wsCode,ak,sk;
				
				WSInfo wsInfo = SysMng.getWSInfo();
				wsCode = wsInfo.WSCode;
				ak = wsInfo.AccessKey;
				sk = wsInfo.SecretKey;
				
				doInitTask(wsCode,ak,sk);
				
			}else if(viewId == FragmentSettings.BtnSettingEditHostId){
				Intent intent = new Intent(this.getActivity(),EditActivity.class);
//				this.startActivityForResult(intent, 1);
				startActivity(intent);
			}
			
		}
		
		private InitTask mInitTask = null;
		
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
				return BaseDataDao.AsynWSData(mContext, url,wsCode, accessKey, secretKey,handler);//TxnDao.sendTxnToInventory(mContext, url, accessKey, secretKey, handler);
			}
			
			@Override
			protected void onPostExecute(Boolean result) {
				if(result){
//					Intent intent = new Intent(mContext, LaunchActivity.class);
//					startActivity(intent);
//					finish();
					ToastUtil.show(mContext, "更新成功");
				}
				
				
				DialogHelper.getProgressDialogInstance().close();
				
//				
			}
			
		}
	}

	

}
