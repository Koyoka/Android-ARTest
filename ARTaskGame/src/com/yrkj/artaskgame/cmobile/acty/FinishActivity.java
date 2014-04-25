package com.yrkj.artaskgame.cmobile.acty;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.base.SysMng;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class FinishActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_finish);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			SysMng.closeApp(this);
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
}
