package com.yrkj.mwrmobile;

import com.dtr.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class RecoverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_recover,
					container, false);
			initFmt(rootView);
			return rootView;
		}
		
		private void initFmt(View root){
			root.findViewById(R.id.btnScanner).setOnClickListener(this);
			
		}

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btnScanner:
				
				Intent intent = new Intent(getActivity(),CaptureActivity.class);
				intent.putExtra(CaptureActivity.INTENT_KEY_ResultType,
						CaptureActivity.INTENT_VAL_ResultType_NewActy);
				intent.putExtra(CaptureActivity.INTENT_KEY_PKG,
						"com.yrkj.mwrmobile");
				intent.putExtra(CaptureActivity.INTENT_KEY_CLZName,
						"com.yrkj.mwrmobile.RecoverDetailActivity");
				startActivity(intent);
				break;

			default:
				break;
			}
		}
		
	}

}
