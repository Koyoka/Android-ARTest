package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.yrkj.artaskgame.R;

public class ScanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
