package com.yrkj.elderlycareassess.acty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.dao.AssessUserDBCtrl;
import com.yrkj.elderlycareassess.layout.ActivityEdit;
import com.yrkj.util.dialog.DialogHelper;

public class EditActivity extends Activity {

	EditActivity mActy;
	ActivityEdit mLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		mActy = this;
		mLayout = new ActivityEdit(this);
		mLayout.getBtnOkView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changePwd();
			}
		});
		mLayout.getBtnBackView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	private void changePwd(){
		String oldPwd = mLayout.getTxtOldPwdView().getText().toString();
		String newPwd = mLayout.getTxtNewPwdView().getText().toString();
		String confirmPwd = mLayout.getTxtConfirmPwdView().getText().toString();
		
		String userPwd = SysMng.getUserInfo().LocPassword;
		
		if(!oldPwd.equals(userPwd)){
			DialogHelper.createTextDialog(mActy, "消息", "旧密码错误，请重新输入。");
			mLayout.getTxtOldPwdView().setFocusable(true);
			return;
		}
		
		if(!newPwd.equals(confirmPwd)){
			DialogHelper.createTextDialog(mActy, "消息", "新密码确认不一致，请重新输入。");
			mLayout.getTxtNewPwdView().setFocusable(true);
			return;
		}
		String userId = SysMng.getUserInfo().UserId;
		
		if(AssessUserDBCtrl.updateUserPassword(this, userId, newPwd)){
			SysMng.saveUserInfo(userId, newPwd);
			DialogHelper.createTextDialog(mActy, "消息", "密码修改成功！");
			
		}
		
	}
	
	private void clear(){
		mLayout.getTxtOldPwdView().setText("");
		mLayout.getTxtNewPwdView().setText("");
		mLayout.getTxtConfirmPwdView().setText("");
	}

}
