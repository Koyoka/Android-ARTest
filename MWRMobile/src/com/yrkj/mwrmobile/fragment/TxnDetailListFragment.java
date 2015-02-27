package com.yrkj.mwrmobile.fragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ListItemTxndetail;
import com.yrkj.mwrmobile.util.view.ListViewMng;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.dialog.DialogHelper.ConfirmDialogListener;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class TxnDetailListFragment extends ListFragment implements OnItemLongClickListener {

	public final String Tag = "com.yrkj.mwrmobile.fragment.TxnDetailListFragment";
	
	private ArrayList<TxnDetailData> mDataSource;
	private TxnDetailAdapter mTxnDetailAdapter; 
	
	public TxnDetailListFragment() {
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
		initFmt();
	}

	private void initData()
	{
		mDataSource = TxnDao.getTxnDetail(getActivity());
		mTxnDetailAdapter = new TxnDetailAdapter(this.getActivity());
	}
	
	private void initFmt(){
		ListView lv = getListView();
		setListAdapter(mTxnDetailAdapter);
		ListViewMng.setListView(this.getActivity(),lv);
		lv.setOnItemLongClickListener(this);
		mTxnDetailAdapter.notifyDataSetChanged();
	}
	
	public void reload(){
		mDataSource = TxnDao.getTxnDetail(getActivity());
		mTxnDetailAdapter.notifyDataSetChanged();
		
	}
	
	class TxnDetailAdapter extends BaseAdapter implements OnClickListener{

		private LayoutInflater mInflater;

		public TxnDetailAdapter(Context c) {
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
			ListItemTxndetail viewHolder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_item_txndetail,
						null);
				viewHolder = new ListItemTxndetail(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ListItemTxndetail) convertView.getTag();
			}
			
			TxnDetailData item = mDataSource.get(position);
			viewHolder.getTxtVendor().setText("来源："+item.Vendor);
			viewHolder.getTxtWaste().setText("类型："+item.Waste);
			viewHolder.getTxtCrateCode().setText("货箱编号："+item.CrateCode);
			viewHolder.getTxtWeight().setText("重量："+item.SubWeight+" kg");
			DLog.LOG(item.TxnDetailId+"  =============");
			viewHolder.getBtnDelTxnDetail().setTag(item.TxnDetailId);
			viewHolder.getBtnDelTxnDetail().setOnClickListener(this);
			return convertView;
		}

		@Override
		public void onClick(final View v) {
			// TODO Auto-generated method stub
			
			final String txnDetailId = v.getTag().toString();
			DialogHelper.createConfirmDialog(getActivity(), "确认删除？", new ConfirmDialogListener() {
				
				@Override
				public void onClickListener(boolean result) {

					if(result){
						TxnDao.delTxnDetail(getActivity(), txnDetailId);
						mDataSource = TxnDao.getTxnDetail(getActivity());
						mTxnDetailAdapter.notifyDataSetChanged();
					}else{
						
					}
					v.setVisibility(View.GONE);
				}
			});
		}
		
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		ListItemTxndetail v = 
		 (ListItemTxndetail) view.getTag();
		int visibily = v.getBtnDelTxnDetail().getVisibility();
		v.getBtnDelTxnDetail().setVisibility(visibily==View.VISIBLE?view.GONE:view.VISIBLE);
		
		return true;
	}
}
