package com.yrkj.artaskgame.cmobile.acty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.cmobile.control.DBCtrl;
import com.yrkj.artaskgame.cmobile.control.TblTaskDetail;

public class EndTaskActivity extends Activity implements
OnClickListener{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.EndTaskActivity";
	public static final String INTENT_KEY_TASKID = "curTaskId";
	
	private String mTaskScore = "";
	
	EndTaskActivity mActy = null;
	
	private Button mBtnGoView;
	private TextView mTxtScoreView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_task);
		mActy = this;
		initData();
		initActy();
		loadData();
		
	}

	private void initData() {
		Intent intent = getIntent();
		String mTaskId = "";
		if(intent != null){
			mTaskId = intent.getStringExtra(INTENT_KEY_TASKID);
		}
		
		if(mTaskId != null && !mTaskId.isEmpty()){
			TblTaskDetail item = DBCtrl.getSelectTask(this, mTaskId);
			if(item != null){
				mTaskScore = item.Score;
			}
		}
	}

	private void initActy() {
		mBtnGoView = (Button) findViewById(R.id.btnGoView);
		mBtnGoView.setOnClickListener(this);
		mTxtScoreView = (TextView) findViewById(R.id.txtScoreView);
		
	}

	private void loadData() {
		// TODO Auto-generated method stub
		mTxtScoreView.setText("½±Àø+"+mTaskScore+"·Ö");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGoView:
			finishCurrentTask();
			break;

		default:
			break;
		}
	}
	
	
	private void finishCurrentTask(){
		if(checkFinishAllTask()){
			DBCtrl.openHideTask(this);
		}
		
		goActy(MainTaskActivity.class,Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		this.finish();
	}

	private void goActy(Class<?> cls,int flags){
		Intent intent = new Intent(this, cls);
		intent.setFlags(flags);
		this.startActivity(intent);
		
	}
	
	private boolean checkFinishAllTask(){
		String count = DBCtrl.getTaskUnFinishCount(this);
		if(count.equals("0")){
			return true;
		}
		return false;
	}

}
