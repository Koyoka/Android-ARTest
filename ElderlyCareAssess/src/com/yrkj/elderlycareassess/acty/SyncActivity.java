package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.SysSyncData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast.UploadProcessSyncListener;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.layout.ActivitySync;
import com.yrkj.elderlycareassess.layout.FragmentSyncItem;

public class SyncActivity extends FragmentActivity {

	SyncActivity mActy;
	ActivitySync mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
		mActy = this;

		mLayout = new ActivitySync(this);
		
		initActy();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	
	private void initActy(){
		ArrayList<SysSyncData> items = SysDBCtrl.getWaitingSyncTask(this, 0);
		
		FragmentManager fMng = getSupportFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		for (SysSyncData item : items) {
			Fragment f = SyncTaskFragment.newInstance(item.TaskHeaderId);
			ft.add(ActivitySync.ContainerId, f, item.Id+"");
		}
		ft.commit();
	}
	
	public static class SyncTaskFragment extends Fragment{
		
		SyncBroadcast b;
		
		public static SyncTaskFragment newInstance(int taskHeaderId){
			SyncTaskFragment f = new SyncTaskFragment(taskHeaderId);
			return f;
		}
		
		
		private int mTaskHeaderId = 0;
		public SyncTaskFragment(int taskHeaderId){
			mTaskHeaderId = taskHeaderId;
		}
		FragmentSyncItem mLayout;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_sync_item, container, false);
			init();
			mLayout = new FragmentSyncItem(v);
			return v;
		}
		
		private void init(){
			b = SyncBroadcast.registUploadProcessSyncBroadcast(getActivity(), new UploadProcessSyncListener() {
				
				@Override
				public void onListener(Bundle bundle, int taskHeaderId,String assessNum, int processVal) {

					if(mTaskHeaderId == taskHeaderId){
						mLayout.getTxtTestDescView().setText(assessNum);
						mLayout.getProgressBar1().setProgress(processVal);
						
					}
				}
			});
		}
		
		
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			if(b!=null){
				getActivity().
				 unregisterReceiver(b);  
			}
		}
		
	}

}
