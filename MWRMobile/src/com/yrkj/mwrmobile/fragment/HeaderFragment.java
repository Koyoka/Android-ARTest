package com.yrkj.mwrmobile.fragment;

import com.yrkj.mwrmobile.R;
import com.yrkj.mwrmobile.layout.FragmentHeaderLayout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeaderFragment extends Fragment {
	
	public final String Tag = "com.yrkj.mwrmobile.fragment.HeaderFragment";
	
	private FragmentHeaderLayout mLayout = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_header_layout,
				container, false);
		initData();
		initFragment(rootView);
		return rootView;
	}
	
	private void initData(){
		
	}
	
	private void initFragment(View root){
		mLayout = new FragmentHeaderLayout(root);
		
	}
}
