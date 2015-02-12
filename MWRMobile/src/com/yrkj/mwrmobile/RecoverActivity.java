package com.yrkj.mwrmobile;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.mwrmobile.fragment.EntryCrateDialogFragment;
import com.yrkj.mwrmobile.layout.ActivityRecover;
import com.yrkj.mwrmobile.layout.FragmentHeaderLayout;
import com.yrkj.mwrmobile.util.scanner.CaptureHelper;
import com.yrkj.util.log.ToastUtil;

public class RecoverActivity extends Activity implements OnClickListener {

	public final String Tag = "com.yrkj.mwrmobile.RecoverActivity";
	
	private ActivityRecover mLayout = null;
	private FragmentHeaderLayout mHeaderLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover);
		
//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		initActy();
	}
	
	private void initActy(){
		mLayout = new ActivityRecover(this);
		mLayout.getTxtTxnHeader().setText("eleven");
		
		{
			View v = this.findViewById(R.id.fmtHeader);
			mHeaderLayout = new FragmentHeaderLayout(v);
		
			mHeaderLayout.getTxtActionBarTitle().setText("∑œ¡œªÿ ’");
			
			mHeaderLayout.getBtnActionBarBack().setOnClickListener(this);
			mHeaderLayout.getBtnActionOps().setOnClickListener(this);
		}
		
//		{
//			View v = this.findViewById(R.id.fmtTxnDetail);
//			mTxnDetailLayout = new FragmentTxndetailLayout(v);
//			
//		}
		
	}

	@Override
	public void onClick(View v) {

		if(v.getId() == FragmentHeaderLayout.BtnActionBarBackId){
			ToastUtil.show(this, "back");
			finish();
		}else if(v.getId() == FragmentHeaderLayout.BtnActionOpsId){
			
			CaptureHelper.ShowCapture(this);
		}
			
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
				String crateCode = data.getExtras().getString("cratecode");
				String result = data.getExtras().getString("result");
				ToastUtil.show(this, crateCode+" "+ result);
				EntryCrateDialogFragment.getInstance().show(getFragmentManager(), "dialog");
			
		}
	}

	
	
}
