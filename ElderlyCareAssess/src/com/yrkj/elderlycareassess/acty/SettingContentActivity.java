package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.SysLogData;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.layout.ActivitySettingContent;
import com.yrkj.elderlycareassess.layout.ListItemLog;

public class SettingContentActivity extends FragmentActivity {

	public static final String INTENT_KEY_LOG = "log";
	
	SettingContentActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_content);
		mActy = this;
		initActy();
	}
	
	private void initActy(){
//		Intent intent = getIntent();
//		
//		if(intent != null){
//			
//		}
		ActivitySettingContent mLayout = new ActivitySettingContent(this);
		mLayout.getBtnBackView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActy.finish();
			}
		});
		
		FragmentManager fMng = getSupportFragmentManager();
		FragmentTransaction ft = fMng.beginTransaction();
		ContentFragment ff = new ContentFragment();
		ft.add(R.id.container, ff, INTENT_KEY_LOG);
		ft.commit();
		
	}
	
	public  class ContentFragment extends ListFragment{
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			init();
		}
		
		private void init(){
			
			mDataSource = SysDBCtrl.getAllLog(getActivity(),20);
			
			
			mAdapter = new LogAdapter(getActivity());
			setListAdapter(mAdapter);
			
			ListView listView = getListView();
			listView.setDivider(new ColorDrawable(Color.WHITE));
			listView.setDividerHeight(0);
			listView.setCacheColorHint(Color.TRANSPARENT); 
			mAdapter.notifyDataSetChanged();
		}
		private LogAdapter mAdapter;// = new LogAdapter();
		private ArrayList<SysLogData> mDataSource ;
		
		
		class LogAdapter extends BaseAdapter {
			private LayoutInflater mInflater;

			public LogAdapter(Context c) {
				mInflater = LayoutInflater.from(c);
			}

			@Override
			public int getCount() {
				if (mDataSource != null) {
					return mDataSource.size();
				}
				return 0;
			}

			@Override
			public Object getItem(int position) {
				if (mDataSource != null) {
					return mDataSource.get(position);
				}
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ListItemLog viewHolder;
				if (convertView == null) {
					convertView = mInflater.inflate(R.layout.list_item_log,
							null);
					viewHolder = new ListItemLog(convertView);
					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ListItemLog) convertView.getTag();
				}
				
				
				
				String s = "";
				if(mDataSource.get(position).LogType.equals(SysLogData.LOGTYPE_ASSESS)){
					s = "完成评估  - 评估编号 - ";
				}else if(mDataSource.get(position).LogType.equals(SysLogData.LOGTYPE_SYNC)){
					s = "同步数据  - 评估编号 - ";
				}else if(mDataSource.get(position).LogType.equals(SysLogData.LOGTYPE_SYS_LOGIN)){
					s = "用户登录 - ";
				}
				
				viewHolder.getTxtLogTitleView().setText(s + mDataSource.get(position).LogDesc);
				viewHolder.getTxtLogDescView().setText(mDataSource.get(position).LogDate);
				
				return convertView;
			}
			
		}
		
	}
	
	
	
	

}
