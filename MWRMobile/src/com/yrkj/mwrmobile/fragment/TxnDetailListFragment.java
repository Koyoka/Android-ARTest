package com.yrkj.mwrmobile.fragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.bean.TxnDetailData;
import com.yrkj.mwrmobile.dao.TxnDao;
import com.yrkj.mwrmobile.layout.ListItemTxndetail;
import com.yrkj.mwrmobile.util.view.ListViewMng;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class TxnDetailListFragment extends ListFragment {

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
		ListViewMng.setListView(lv);
		
		mTxnDetailAdapter.notifyDataSetChanged();
	}
	
	public void reload(){
		ToastUtil.show(getActivity(), "TxnDetailListFragment reload");
		mDataSource = TxnDao.getTxnDetail(getActivity());
		mTxnDetailAdapter.notifyDataSetChanged();
		
	}
	
	class TxnDetailAdapter extends BaseAdapter{

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
			viewHolder.getTxtVendor().setText(item.Vendor);
			viewHolder.getTxtWaste().setText(item.Waste);
			viewHolder.getTxtCrateCode().setText(item.CrateCode);
			viewHolder.getTxtWeight().setText(item.SubWeight);
			DLog.LOG(item.TxnDetailId+"  =============");
			
			return convertView;
		}
		
		
	}
}
