package com.yrkj.elderlycareassess;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class TestPopActionBarActivity extends Activity {

	private static final String TAG = "HelloActionBarActivity";
	private ActionBar actionBar;
	private ShareActionProvider mShareActionProvider;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        
        
        ActionBar bar = getActionBar();
        bar.setTitle("asdffd");
//        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.pop_action_menu, menu);  
		return super.onCreateOptionsMenu(menu);
	}
	
	
}

