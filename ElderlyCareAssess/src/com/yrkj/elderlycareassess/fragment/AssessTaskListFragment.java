package com.yrkj.elderlycareassess.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.dao.HttpSync;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment.onDialogClosed;
import com.yrkj.elderlycareassess.layout.ListItemDoingTask;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.ToastUtil;

public class AssessTaskListFragment extends ListFragment implements
		OnItemClickListener, OnItemLongClickListener {
	

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
		listView.setOnItemLongClickListener(this);
		// getListView().setDividerHeight(1); // 不显示分割线
		listView.setCacheColorHint(Color.TRANSPARENT); // 防止listview背景变黑
		mTaskAdapter.notifyDataSetChanged();

	}

	private void addData() {
		ArrayList<CustomerAssessTask> itemList =  AssessDBCtrl.getDoingAssessTaskList(getActivity());
		
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
			item.assessId = itemData.mTask.Id;
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
		public String assessId;
	}

	class ViewHolder {

	}


	class TaskAdapter extends BaseAdapter implements OnClickListener {

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
			ListItemDoingTask viewHolder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_item_doing_task,
						null);
				viewHolder = new ListItemDoingTask(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ListItemDoingTask) convertView.getTag();
			}

			TaskData item = mDataSource.get(position);

			viewHolder.getTxtTaskNumView().setText(item.taskNum);
			viewHolder.getTxtTaskAddressView().setText(item.address);
			viewHolder.getTxtTaskEndDateView().setText(item.taskEndDate);
			viewHolder.getTxtTaskLastDoDateView().setText(item.taskLastDoDate);
			viewHolder.getTxtTaskPhoneNumView().setText(item.phoneNum);
			viewHolder.getTxtTaskSexView().setText(item.sex);
			viewHolder.getTxtTaskStateView().setText(item.taskState);
			viewHolder.getTxtTaskUserNameView().setText(item.userName);
			
			viewHolder.getLayoutReturnTaskView().setVisibility(View.GONE);
			viewHolder.getBtnReturnTaskView().setTag(item.assessId);
			viewHolder.getBtnReturnTaskView().setOnClickListener(this);

			return convertView;
		}

		@Override
		public void onClick(final View v) {
			// TODO Auto-generated method stub
			MyDialogFragment d = MyDialogFragment.newInstance(MyDialogFragment.ALTER_DIALOG);
			d.setOnDialogClosed(new onDialogClosed() {
				
				@Override
				public void onClosed(boolean r) {

					if(r){
						
						doBackTask(v.getTag().toString());
						
					}else{
						((View)v.getParent()).setVisibility(View.GONE);
					}
				}
			});
			d.show(getChildFragmentManager(), "是否需要退回？");
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), MainAssessActivity.class);
		intent.putExtra(MainAssessActivity.INTENT_KEY_CUSTID, mDataSource.get(position).custId);
		intent.putExtra(MainAssessActivity.INTENT_KEY_ASSESSID,mDataSource.get(position).assessId);
//		getActivity().startActivityForResult(intent, 1);
//		getActivity().startActivity(intent);
		startActivityForResult(intent, REQUESTCODE_ASSESS_ACTY);
	}
	
	public final static int REQUESTCODE_ASSESS_ACTY = 1;
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUESTCODE_ASSESS_ACTY){
			reBind();
		}
	}
	
	private void reBind(){
		mDataSource = new ArrayList<TaskData>();
		addData();
		mTaskAdapter.notifyDataSetChanged();
	}


	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		ListItemDoingTask v =
		(ListItemDoingTask) view.getTag();
		v.getLayoutReturnTaskView().setVisibility(v.getLayoutReturnTaskView().getVisibility()==View.VISIBLE?view.GONE:view.VISIBLE);
		return true;
	}
	
	private void doBackTask(String taskHeaderId){
		if(mLoginTask == null){
			mLoginTask = new BackTask(taskHeaderId);
		}
		
		if(mLoginTask.getStatus() == Status.RUNNING){
			return;
		}
		
		if(mLoginTask.getStatus() == Status.FINISHED){
			mLoginTask = new BackTask(taskHeaderId);
		}
		
		
		if(mLoginTask.getStatus() != Status.RUNNING){
			mLoginTask.execute();
		}
	}
	private BackTask mLoginTask;
	class BackTask extends AsyncTask<Object, Object, Boolean>{

		String mLocTaskHeaderId = "";
		
		public BackTask(String taskHeaderId){
			mLocTaskHeaderId = taskHeaderId;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			DialogHelper.getProgressDialogInstance().show(getActivity(), "数据提交中");
		}
		
		@Override
		protected Boolean doInBackground(Object... params) {
			String userId = SysMng.getUserInfo().UserId;
			String mNetTaskHeaderId = "";
			AssessTaskHeaderData data =
					AssessDBCtrl.getAssessTaskById(getActivity(), mLocTaskHeaderId);
			if(data != null){
				mNetTaskHeaderId = data.NetTaskHeaderId;
				return HttpSync.backAssessTask(getActivity(), userId, mNetTaskHeaderId);
			}
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				AssessDBCtrl
				.deletAssessTaskHeaderById(getActivity(), mLocTaskHeaderId);
				reBind();
			}
			
			DialogHelper.getProgressDialogInstance().close();
			
		}
		
		
	}

}















