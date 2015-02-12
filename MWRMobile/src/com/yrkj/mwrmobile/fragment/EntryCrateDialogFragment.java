package com.yrkj.mwrmobile.fragment;


import java.util.ArrayList;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.layout.FragmentEntryCrateDialog;
import com.yrkj.util.log.ToastUtil;

public class EntryCrateDialogFragment extends DialogFragment implements OnClickListener {

	public final String Tag = "com.yrkj.mwrmobile.fragment.EntryCrateDialogFragment";
	
	private FragmentEntryCrateDialog mLayout = null;
	
	public static EntryCrateDialogFragment getInstance(){
		
		EntryCrateDialogFragment f = new EntryCrateDialogFragment();
//		Bundle args = new Bundle();
//        args.putInt("num", num);
//        f.setArguments(args);
		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		Dialog d = super.onCreateDialog(savedInstanceState);
//		d.setCancelable(false);
		return super.onCreateDialog(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		int width = getResources().getDimensionPixelSize(android.R.dimen.popup_width);
//		int height = getResources().getDimensionPixelSize(R.dimen.popup_height);        
//		getDialog().getWindow().setLayout(width, height);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getArguments()
		setCancelable(false);
		
		setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		 View v = inflater.inflate(R.layout.fragment_entry_crate_dialog, container, false);
		 initFragment(v);
		 return v;
//		return super.onCreateView(inflater, container, savedInstanceState);
	}
	ArrayList<String> mVendors = new ArrayList<String>();
	ArrayList<String> mWasters = new ArrayList<String>();
	private void initFragment(View v){
		mLayout = new FragmentEntryCrateDialog(v);
		mLayout.getBtnCloseDialog().setOnClickListener(this);
		mLayout.getBtnConfirmDialog().setOnClickListener(this);
		
		
		for(int i=0;i<54;i++){
			mVendors.add("医院_"+i);
			mWasters.add("废料_"+i);
//			VendorData item = new VendorData();
//			item.Code = i+"";
//			item.Desc = "医院_"+i;
//			mVendors.add(item);
		}

		{
			ArrayAdapter<String> adapter = null;
			adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mVendors);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
			mLayout.getSpnVendor().setAdapter(adapter);
			mLayout.getSpnVendor().setOnItemSelectedListener(
		                new OnItemSelectedListener() {
		                    public void onItemSelected(
		                            AdapterView<?> parent, View view, int position, long id) {
		                    	ToastUtil.show(getActivity(), mVendors.get(position));
		                    }
	
		                    public void onNothingSelected(AdapterView<?> parent) {
	//	                        showToast("Spinner1: unselected");
		                    }
		                });
		}
		{
			
			ArrayAdapter<String> adapter = null;
			adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mWasters);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
			mLayout.getSpnVendor().setAdapter(adapter);
			mLayout.getSpnVendor().setOnItemSelectedListener(
		                new OnItemSelectedListener() {
		                    public void onItemSelected(
		                            AdapterView<?> parent, View view, int position, long id) {
		                    	ToastUtil.show(getActivity(), mVendors.get(position));
		                    }
	
		                    public void onNothingSelected(AdapterView<?> parent) {
	//	                        showToast("Spinner1: unselected");
		                    }
		                });
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == FragmentEntryCrateDialog.BtnCloseDialogId){
			dismiss();
		}else if(v.getId() == FragmentEntryCrateDialog.BtnConfirmDialogId){
			ToastUtil.show(this.getActivity(), "confirm");
		}
		
	}
	class VendorData{
		public String Code = "";
		public String Desc = "";
		
	}
	
}
