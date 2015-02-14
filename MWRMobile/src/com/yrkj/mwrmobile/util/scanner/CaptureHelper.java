package com.yrkj.mwrmobile.util.scanner;


import android.app.Activity;
import android.content.Intent;

public class CaptureHelper {

	public static void ShowCapture(Activity acty,int scannerType)
	{
		Intent intent = new Intent(acty,MWRCaptureActivity.class);
		intent.putExtra(MWRCaptureActivity.INTENT_KEY_ScannerType, scannerType);
		acty.startActivityForResult(intent, 0);
	}
}
