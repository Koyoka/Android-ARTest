package com.yrkj.elderlycareassess;

import android.app.Activity;
import android.os.Bundle;

public class TempActivity extends Activity {

	TempActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActy = this;
	}

}
