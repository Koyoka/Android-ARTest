package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskHeaderData;
import com.yrkj.elderlycareassess.bean.CustomerAssessTask;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.layout.ActivityLogin;
import com.yrkj.util.log.DLog;

public class LoginActivity extends FragmentActivity {

	LoginActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mActy = this;
		initActy();
	}
	
	private void initActy(){
		ActivityLogin mActyCtrl = new ActivityLogin(this);
		mActyCtrl.getBtnLoginView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
//				t();
//				showDialog();
			}
		});
	}
	
	private void login(){
		
		
		Intent intent = new Intent(this,MainHomeNoneActionBarActivity.class);
		startActivity(intent);
		this.finish();
		
//		ArrayList<CustomerAssessTask> itemList =  AssessDBCtrl.getDoingAssessTaskList(this);
//		
//		DLog.LOG(SysMng.TAG_DB, itemList.size()+"  test===========");
		
	}
	
	
	public void showDialog()
    {
    	//根据传进的参数来实例化DialogFragment.
//    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALTER_DIALOG);
    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.DATE_PICKER_DIALOG);
//    	MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.TIME_PICKER_DiALOG);
//    	getSupportFragmentManager()
    	newDialog.show(getSupportFragmentManager(), "alert msg");
    }
	
	public void t(){
//		AssessTaskHeaderData item = AssessDBCtrl.getAssessTaskById(this,""+1);
//		if(item != null){
//			DLog.LOG(SysMng.TAG_DB, "1----"+item.AssessNum + " " + item.CustId);
//			item.AssessNum = "eleven";
//			item.CustId = "adsasdf";
//			boolean r = AssessDBCtrl.updateAssessTaskHeaderById(this,item);
//			
//			
//			item = AssessDBCtrl.getAssessTaskById(this,""+1);
//			DLog.LOG(SysMng.TAG_DB, "2----" + r +"  "+item.AssessNum + " " + item.CustId);
//		}
		
		
		
		
	}

}
