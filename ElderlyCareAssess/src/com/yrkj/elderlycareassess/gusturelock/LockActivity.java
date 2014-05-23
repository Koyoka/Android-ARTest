package com.yrkj.elderlycareassess.gusturelock;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainHomeNoneActionBarActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.gusturelock.LockPatternView.Cell;
import com.yrkj.elderlycareassess.gusturelock.LockPatternView.DisplayMode;

/*
 * Author: Ruils 蹇冩�浜у搧姊︾殑瀹夊崜鐮佸啘 
 * Blog: http://blog.csdn.net/ruils
 * QQ: 5452781
 * Email: 5452781@qq.com
 */

public class LockActivity extends Activity implements
        LockPatternView.OnPatternListener {
    private static final String TAG = "LockActivity";

    private List<Cell> lockPattern;
    private LockPatternView lockPatternView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String patternString = SysMng.getLockPattenString();
        		
        
        if (patternString == null) {
        	Intent intent = new Intent(this, LockSetupActivity.class);
        	intent.putExtra("Login", true);
        	startActivity(intent);
        	finish();
        	
            return;
        }
        
       
        
        lockPattern = LockPatternView.stringToPattern(patternString);
        setContentView(R.layout.activity_lock);
        lockPatternView = (LockPatternView) findViewById(R.id.lock_pattern);
        lockPatternView.setOnPatternListener(this);
        findViewById(R.id.btnLogoutView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // disable back key
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            return true;
//        }

        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public void onPatternStart() {
        Log.d(TAG, "onPatternStart");
    }

    @Override
    public void onPatternCleared() {
        Log.d(TAG, "onPatternCleared");
    }

    @Override
    public void onPatternCellAdded(List<Cell> pattern) {
        Log.d(TAG, "onPatternCellAdded");
        Log.e(TAG, LockPatternView.patternToString(pattern));
        // Toast.makeText(this, LockPatternView.patternToString(pattern),
        // Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPatternDetected(List<Cell> pattern) {
        Log.d(TAG, "onPatternDetected");

        if (pattern.equals(lockPattern)) {
//            finish();
        	Intent i = getIntent();
            if(i != null && i.getBooleanExtra(LockSetupActivity.INITENT_KEY_LOGIN, false)){
            	Intent intent = new Intent(this, LockSetupActivity.class);
            	startActivity(intent);
            }else{
            	Intent intent = new Intent(this, MainHomeNoneActionBarActivity.class);
            	startActivity(intent);
            }
            finish();
            
            
        } else {
            lockPatternView.setDisplayMode(DisplayMode.Wrong);
            Toast.makeText(this, R.string.lockpattern_error, Toast.LENGTH_LONG)
                    .show();
        }

    }

}
