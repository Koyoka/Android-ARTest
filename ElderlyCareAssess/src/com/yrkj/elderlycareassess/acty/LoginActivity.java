package com.yrkj.elderlycareassess.acty;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessUserData;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.dao.SysDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.layout.ActivityLogin;
import com.yrkj.elderlycareassess.util.sound.Recorder;
import com.yrkj.util.dialog.DialogHelper;

public class LoginActivity extends FragmentActivity {

	LoginActivity mActy;
	ActivityLogin mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mActy = this;
		mLayout = new ActivityLogin(this);
		vib = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
		initActy();
		
	}
	
	private void initActy(){
		AssessUserData uData = SysMng.getUserInfo();
		if(uData != null){
			mLayout.getTxtUserNameView().setText(uData.UserId);
			mLayout.getTxtPasswordView().setText(uData.LocPassword);
		}
		
		mLayout.getBtnLoginView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				a();
//				login();
//				t();
//				showDialog();
			}
		});
	}
	Vibrator vib;
	public  void a(){
		
		
		final Dialog dialog = new Dialog(LoginActivity.this);
		dialog.setContentView(R.layout.recorder);
		dialog.setTitle("Recorder");
		dialog.setCancelable(true);
		dialog.show();
		Recorder newRecorder = new Recorder();
		View view = dialog.findViewById(R.id.recorderView);
		
		 
		// Vibrate for 300 milliseconds
		vib.vibrate(30);
		newRecorder.tonCreate(view,vib);//Call the
	}
	
	private void login(){
		
		String userId = mLayout.getTxtUserNameView().getText().toString();
		String pwd = mLayout.getTxtPasswordView().getText().toString();
		if(AssessUserDBCtrl.userLogin(this, userId.trim(), pwd)){
			
			SysMng.saveUserInfo(userId, pwd);
			SysDBCtrl.addSysLoginLog(this, userId);
			Intent intent = new Intent(this,MainHomeNoneActionBarActivity.class);
			startActivity(intent);
			this.finish();
		}else{
			DialogHelper.createTextDialog(mActy, "消息", "用户名或密码错误,请重新输入。");
			mLayout.getTxtUserNameView().setFocusable(true);
		}
		
		
		
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
