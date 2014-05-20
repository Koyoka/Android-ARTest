package com.yrkj.elderlycareassess.acty;

import android.app.Activity;
import android.os.Bundle;

import com.yrkj.elderlycareassess.R;

public class EditActivity extends Activity {

	EditActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		mActy = this;
	}

}
