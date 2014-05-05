package com.yrkj.elderlycareassess.acty;

import android.app.Activity;
import android.os.Bundle;

import com.yrkj.elderlycareassess.R;

public class TempActivity extends Activity {

	TempActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActy = this;
	}

}
