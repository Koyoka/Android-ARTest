package com.yrkj.elderlycareassess.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.broadcast.SyncBroadcast;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.layout.ListItemDoneTask;

public class AssessDoneListFragment extends ListFragment implements
OnItemClickListener {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();

	}

	private void initData() {
		mDataSource = new ArrayList<TaskData>();
		addData();

		mTaskAdapter = new TaskAdapter(getActivity());

		setListAdapter(mTaskAdapter);
		ListView listView = getListView();
		listView.setDivider(new ColorDrawable(Color.WHITE));
		listView.setDividerHeight(0);
		listView.setOnItemClickListener(this);
		// getListView().setDividerHeight(1); // 不显示分割线
		listView.setCacheColorHint(Color.TRANSPARENT); // 防止listview背景变黑
		mTaskAdapter.notifyDataSetChanged();

	}

	private void addData() {
		ArrayList<CustomerAssessTask> itemList =  AssessDBCtrl.getDoneAssessTaskList(getActivity());
		
		for (CustomerAssessTask itemData : itemList) {
			TaskData item = new TaskData();
			item.taskNum = itemData.mTask.AssessNum;//mDataSource.size() + "00000AS";
			item.taskState = AssessTaskHeaderData.getAssessTypeDesc(itemData.mTask.AssessState);
			item.taskLastDoDate = itemData.mTask.LastAssessDate;
			item.taskEndDate = itemData.mTask.EndAssessDate;
			item.userName = itemData.mCust.customername;
			item.sex = CustomerData.getSexDesc(itemData.mCust.sex);
			item.phoneNum = itemData.mCust.mobliephone;
			item.address = itemData.mCust.address;
			item.custId = itemData.mTask.CustId;
			item.taskHeaderId = itemData.mTask.Id;
			item.needSync = itemData.mTask.NeedSync;
			item.score = itemData.score;
			mDataSource.add(item);
		}
		
	}

	private void initFragment() {

	}

	ArrayList<TaskData> mDataSource;
	TaskAdapter mTaskAdapter;

	class TaskData {
		public String taskNum;
		public String taskState;
		public String taskLastDoDate;
		public String taskEndDate;
		public String userName;
		public String sex;
		public String phoneNum;
		public String address;
		public String custId;
		public String taskHeaderId;
		public boolean needSync = false;
		public int score = 0;
	}

	class ViewHolder {

	}


	class TaskAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public TaskAdapter(Context c) {
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
			ListItemDoneTask viewHolder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_item_done_task,
						null);
				viewHolder = new ListItemDoneTask(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ListItemDoneTask) convertView.getTag();
			}

			TaskData item = mDataSource.get(position);

			viewHolder.getTxtTaskNumView().setText(item.taskNum);
//			viewHolder.getTxtTaskAddressView().setText(item.address);
//			viewHolder.getTxtTaskEndDateView().setText(item.taskEndDate);
//			viewHolder.getTxtTaskLastDoDateView().setText(item.taskLastDoDate);
//			viewHolder.getTxtTaskPhoneNumView().setText(item.phoneNum);
//			viewHolder.getTxtTaskSexView().setText(item.sex);
//			viewHolder.getTxtTaskStateView().setText(item.taskState);
			viewHolder.getTxtTaskUserNameView().setText(item.userName);
			viewHolder.getImgAssessLevelView().setImageResource(AssessTaskHeaderData.getScoreLevel(item.score));
			if(item.needSync){
				viewHolder.getBtnSyncView().setVisibility(View.VISIBLE);
				viewHolder.getBtnSyncView().setTag(item.taskHeaderId);
				viewHolder.getBtnSyncView().setOnClickListener(SyncClick);
			}else{
				viewHolder.getBtnSyncView().setVisibility(View.GONE);
			}
			
			
			String s = AssessDBCtrl.getDoneAssessTaskDetailCateTotleScore(getActivity(), item.taskHeaderId);
			viewHolder.getTxtAssessTaskDetailTotleView().setText(s);

			return convertView;
		}

	}
	
	OnClickListener SyncClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v.getTag() == null)
				return;
			
			String id = v.getTag().toString();
			
			try{
				int taskHeaderId = Integer.parseInt(id, 10);
				SyncBroadcast.sendUploadSyncBroadcast(getActivity(), taskHeaderId);
			}
			catch(Exception e){
			}
		}
	};
	
	private void reBind(){
		mDataSource = new ArrayList<TaskData>();
		addData();
		mTaskAdapter.notifyDataSetChanged();
	}
	
	public final static int REQUESTCODE_ASSESS_ACTY = 1;
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Intent intent = new Intent(getActivity(), MainAssessActivity.class);
		intent.putExtra(MainAssessActivity.INTENT_KEY_CUSTID, mDataSource.get(position).custId);
		intent.putExtra(MainAssessActivity.INTENT_KEY_ASSESSID,mDataSource.get(position).taskHeaderId);
		startActivityForResult(intent, REQUESTCODE_ASSESS_ACTY);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUESTCODE_ASSESS_ACTY){
			reBind();
		}
	}
}
