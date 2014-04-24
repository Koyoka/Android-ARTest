package com.yrkj.artaskgame.cmobile.acty;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.acty.qcar.QCARInitActivityActy;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.artaskgame.cmobile.control.DBCtrl;
import com.yrkj.artaskgame.cmobile.control.TblTaskDetail;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.ToastUtil;

public class TaskListActivity extends Activity implements
OnClickListener,
OnItemClickListener{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.TaskListActivity";
	public final static String INTENT_KEY_SELECTTASK = "selectTask";
	
	TaskListActivity mActy = null;
	
	private ListView mListView;
	private Button mBtnBackView;
	private LinearLayout mBtnReInitView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);
		mActy = this;
		initData();
		initActy();
		loadData();
	}

	private void initData() {
		
		mDataAdapter = new TaskGameListDataAdapter(this);
		mDataSource = new ArrayList<TblTaskDetail>();
		
	}

	private void initActy() {
		mBtnReInitView = (LinearLayout) findViewById(R.id.btnReInitView);
		mBtnReInitView.setOnClickListener(this);
		
		mBtnBackView = (Button) findViewById(R.id.btnBackView);
		mBtnBackView.setOnClickListener(this);
		
		mListView = (ListView) findViewById(R.id.lstView);
		mListView.setAdapter(mDataAdapter);

		mListView.setOnItemClickListener(this);
	}

	private void loadData() {
		mDataSource = DBCtrl.getAllTaskList(this);
		mDataAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnBackView:
			this.finish();
			break;
		case R.id.btnReInitView:
			reInit();
			break;
		default:
			break;
		}
	}
	
	private void reInit(){
		
		DialogHelper.createConfirmDialog(TaskListActivity.this,"确认初始化退出？",new ConfirmDialogListener(){

			@Override
			public void onClickListener(boolean result) {
				// TODO Auto-generated method stub
						if (result) {

							boolean re = false;
							re = DBCtrl.reInitDB(mActy);
							if (result) {
								SysMng.reInit();
							}

							SysMng.closeApp(mActy);
						}
			}
			
		});
		
		
//		SysMng.sys_closeApp = true;
//		Intent intent = new Intent(this,QCARInitActivityActy.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//		this.finish();
		
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		int position = index - mListView.getHeaderViewsCount();
//		String taskDesc =  this.mDataSource.get(position).TaskDesc;
//		ToastUtil.show(mActy, "current task: "+taskDesc);
		if(this.mDataSource.get(position).Finish != null && this.mDataSource.get(position).Finish.equals(TblTaskDetail.FINISH_YES)){
			ToastUtil.show(this, "当前任务已完成。");
			return;
		}
		Intent intent = new Intent(this, MainTaskActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(INTENT_KEY_SELECTTASK, this.mDataSource.get(position).Id);
		this.startActivity(intent);
	}

	
	private View listGetView(int position, View convertView, ViewGroup parent,
			LayoutInflater inflater) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.list_taskgame_item, null);
			viewHolder.ImgTaskStatusView = (ImageView) convertView
					.findViewById(R.id.imgTaskStatusView);
			viewHolder.TxtTaskDescView = (TextView) convertView
					.findViewById(R.id.txtTaskDescView);
			viewHolder.TxtTaskScoreView = (TextView) convertView
					.findViewById(R.id.txtTaskScoreView);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if(mDataSource.get(position).Finish.equals(TblTaskDetail.FINISH_YES)){
			viewHolder.ImgTaskStatusView.setImageResource(R.drawable.tasklist_task_off);
			viewHolder.TxtTaskScoreView.setText("+" + mDataSource.get(position).Score);
			viewHolder.TxtTaskScoreView.setVisibility(View.VISIBLE);
		}else{
			viewHolder.TxtTaskScoreView.setVisibility(View.GONE);
			viewHolder.ImgTaskStatusView.setImageResource(R.drawable.tasklist_task_on);
		}
		viewHolder.TxtTaskDescView.setText(mDataSource.get(position).TaskTitle);

		return convertView;
	}

	private ArrayList<TblTaskDetail> mDataSource = null;
	private TaskGameListDataAdapter mDataAdapter = null;
	class TaskGameListDataAdapter extends BaseAdapter {

//		private Context mContext;
		private LayoutInflater mInflater;
		
		public TaskGameListDataAdapter(Context context) {
//			mContext = context;
			mInflater = LayoutInflater.from(context);
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
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return listGetView(position, convertView, parent, mInflater);
		}
		
//		public void RefAdapter() {
//			notifyDataSetChanged();
//		}
		
	}
	
	class ViewHolder {
		public ImageView ImgTaskStatusView;
		public TextView TxtTaskDescView;
		public TextView TxtTaskScoreView;
	}
	
//	class TaskData{
//		public boolean TaskFinish = false;
//		public String TaskDesc = "";
//	}

	


}
