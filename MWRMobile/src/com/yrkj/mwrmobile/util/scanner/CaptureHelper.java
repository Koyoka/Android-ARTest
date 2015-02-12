package com.yrkj.mwrmobile.util.scanner;


import android.app.Activity;
import android.content.Intent;

public class CaptureHelper {

	public static void ShowCapture(Activity acty)
	{
		Intent intent = new Intent(acty,MWRCaptureActivity.class);
//		intent.putExtra(MWRCaptureActivity.INTENT_KEY_ResultType,
//				MWRCaptureActivity.INTENT_VAL_ResultType_NewActy);
//		intent.putExtra(MWRCaptureActivity.INTENT_KEY_PKG,
//				"com.yrkj.mwrmobile");
//		intent.putExtra(MWRCaptureActivity.INTENT_KEY_CLZName,
//				"com.yrkj.mwrmobile.RecoverDetailActivity");
//		acty.startActivity(intent);
		
		acty.startActivityForResult(intent, 0);
	}
}
