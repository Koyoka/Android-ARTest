package com.yrkj.elderlycareassess.gusturelock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;


public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.lock:
            Intent intent = new Intent(this, LockSetupActivity.class);
            startActivity(intent);
            break;
        case R.id.unlock:
//            getSharedPreferences(LOCK, MODE_PRIVATE).edit().clear().commit();
            break;

        default:
            break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
