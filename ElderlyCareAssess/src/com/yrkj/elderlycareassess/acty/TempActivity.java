package com.yrkj.elderlycareassess.acty;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.yrkj.elderlycareassess.R;

public class TempActivity extends ActionBarActivity {

	TempActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActy = this;
	}

}
