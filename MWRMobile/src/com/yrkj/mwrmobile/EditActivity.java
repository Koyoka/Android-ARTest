package com.yrkj.mwrmobile;

import com.yrkj.mwrmobile.base.BaseApplication;
import com.yrkj.mwrmobile.base.SysMng;
import com.yrkj.mwrmobile.layout.ActivityEdit;
import com.yrkj.mwrmobile.layout.FragmentEditHost;
import com.yrkj.mwrmobile.layout.FragmentHeaderLayout;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;

public class EditActivity extends Activity implements OnClickListener {

	private FragmentHeaderLayout mHeaderLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		initActy();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		private FragmentEditHost mLayout = null;
		
		public PlaceholderFragment() {
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_host,
					container, false);
			initFrag(rootView);
			return rootView;
		}
		
		private void initFrag(View v){
			String host = SysMng.getSysInfo().SerciveHost;
			mLayout = new FragmentEditHost(v);
			mLayout.getBtnConfirmView().setOnClickListener(this);
			mLayout.getTxtEditView().setText(host);
			
		}

		@Override
		public void onClick(View v) {

			if(v.getId() == FragmentEditHost.BtnConfirmViewId){
				String host = mLayout.getTxtEditView().getText()+"";
//				BaseApplication.Servive_Host = host;
				SysMng.savePrefValue(SysMng.PREF_KEY_ServiceHost, host);
				getActivity().setResult(Activity.RESULT_OK);
				getActivity().finish();
			}
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == FragmentHeaderLayout.BtnActionBarBackId){
			finish();
		}
	}

}
