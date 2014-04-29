package com.yrkj.artaskgame.cmobile.acty;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qualcomm.ar.pl.DebugLog;
import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.artaskgame.cmobile.control.DBCtrl;
import com.yrkj.artaskgame.cmobile.control.TblTaskDetail;
import com.yrkj.artaskgame.cmobile.control.UploadPhotoTaskDao;
import com.yrkj.artaskgame.cmobile.control.UploadPhotoTaskDao.ResponseHeaderBean;
import com.yrkj.config.SysConfig;
import com.yrkj.util.bitmap.BitmapHelper;
import com.yrkj.util.bitmap.MediaHelper;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.http.InputFileObj;
import com.yrkj.util.log.DebugTrace;
import com.yrkj.util.log.ToastUtil;
import com.yrkj.util.ui.layout.CommDialogSelectphotoView;

public class ConfirmTaskActivity extends Activity implements
OnClickListener,
UploadPhotoTaskDao.PostTaskListener,
UploadPhotoTaskDao.PreTaskListener{

	final String TAG = "com.yrkj.artaskgame.cmobile.acty.ConfirmTaskActivity";
	
	ConfirmTaskActivity mActy = null;
	
	private Button mBtnGoView = null;
	private Button mBtnHelpView = null;
	
	private TextView mTxtTaskTitleView = null;
	private TextView mTxtTaskDescView = null;
	
	private Bitmap mBitmap;
	private UploadPhotoTaskDao mUploadPhotoTaskDao = null;
	
	private String mSavePath = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_task);
		mActy = this;
		initData();
		initActy();
		loadData();
	}
	
	private void initData() {
		mSavePath = MediaHelper.getSavePath();
		
		mUploadPhotoTaskDao = new UploadPhotoTaskDao(SysConfig.DEFAULT_INTERFACE_NAME);
		mUploadPhotoTaskDao.setTask(this, this);
	}

	private void initActy() {
		mTxtTaskTitleView = (TextView) findViewById(R.id.txtTaskTitleView);
		mTxtTaskDescView = (TextView) findViewById(R.id.txtTaskDescView);
		
		mBtnGoView = (Button) findViewById(R.id.btnGoView);
		mBtnGoView.setOnClickListener(this);
		
		mBtnHelpView = (Button) findViewById(R.id.btnHelpView);
		mBtnHelpView.setOnClickListener(this);
	}

	private void loadData() {
		TblTaskDetail item = DBCtrl.getSelectTask(this, SysMng.getCurrentId());
//		ToastUtil.show(this, "item["+(item != null)+"] -- id["+SysMng.getCurrentId()+"]");
		DebugTrace.Print("Eleven", "item["+(item != null)+"] -- id["+SysMng.getCurrentId()+"]");
		//		if(item != null){
			mTxtTaskTitleView.setText(item.TaskTitle);
			mTxtTaskDescView.setText(item.TaskContent);
//		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if(isUploading){
//				return false;
//			}
//		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnGoView:
//			goActy(EndTaskActivity.class);
//			this.finish();
			
			DialogHelper.createSelectPhotoDialog(ConfirmTaskActivity.this, ConfirmTaskActivity.this);
			break;
		case R.id.btnHelpView:
			DialogHelper.createTextDialog(ConfirmTaskActivity.this,"帮助","您需要按照任务要求，使用相机拍照或选择准备好的照片上传至服务器来完成任务。");
			break;
		case CommDialogSelectphotoView.BtnOpenCameraViewId:
//			curType = MediaHelper.MEDIA_IMG_CAMERA;
//			MediaHelper.getMedia_Camera(this, photoFile);
//			mMediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/);
//			MediaHelper.getInstance().setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/);
			MediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/,mSavePath);
			break;
		case CommDialogSelectphotoView.BtnOpenPhotoAlbumViewId:
//			curType = MediaHelper.MEDIA_IMG_PHOTO;
//			mMediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_PHOTO /*| MediaHelper.MEDIA_IMG_CROP*/);
//			MediaHelper.getInstance().setMedia(this,MediaHelper.MEDIA_IMG_PHOTO /*| MediaHelper.MEDIA_IMG_CROP*/);
			MediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_PHOTO /*| MediaHelper.MEDIA_IMG_CROP*/,mSavePath);
			break;
		default:
			break;
		}
	}
	File photoFile;
	int curType = 0;
//	MediaHelper mMediaHelper = null;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK){
    		
//			String imgPath = mMediaHelper.getMedioResultPath(data);
//			String imgPath = MediaHelper.getMedioResultPath(this,data,requestCode,mSavePath);
			String imgPath = MediaHelper.getMedioResultPath(this,data,requestCode,mSavePath);
//    		String imgPath = MediaHelper.getMedioResultPath(this,data,curType,photoFile);
//    		String imgPath = MediaHelper.getInstance().getMedioResultPath(data);
    		if(imgPath != null && imgPath.length() != 0){
    			
    			if(mBitmap != null && !mBitmap.isRecycled()){
    				mBitmap.recycle();
    			}
    			mBitmap = BitmapHelper.decodeSampledBitmapFromLacolPath(imgPath, 240, 400);
//    			12 20 3 5
    			InputFileObj fileObj = new InputFileObj(new File(imgPath).getName(), 
    					BitmapHelper.Bitmap2IS(mBitmap));
    			
    			TblTaskDetail item = DBCtrl.getSelectTask(this, SysMng.getCurrentId());
    			DebugTrace.Print("Eleven", 
    					"item.TaskTitle["+item.TaskTitle+"] "
    					+"item.Id["+item.Id+"] "
    					+"SysMng.getUserName()["+SysMng.getUserName()+"] "
    					+"SysMng.getDriverId()["+SysMng.getDriverId()+"] "
    					);
    			mUploadPhotoTaskDao
    			.addPostParams(
    					"submitTask", 
    					item.TaskTitle, 
    					item.Id, 
//    					SysMng.sys_UserName,
    					SysMng.getUserName(),
    					SysMng.getDriverId())
//    					SysMng.sys_DriverId)
    			.addFileParams(fileObj).runTask();
//    			mUpdateMyInfoDetailTaskDao
//    					.addPostParams(
//							BaseApplication.mToken,
//							BaseApplication.mUserId,
//							"updateMyInfoDetail",
//							"1",
//							"1",
//							"userimg",
//							""
////    					token, userid, business_id, gzip_type, decode_type, updatekey, updatevalue
//    					).addFileParams(avatarFileObj)
//    					.runTask();
    			
    			
    			
    		}else{
    			DebugTrace.Print("Eleven","imgPath ["+imgPath+"]");
    			ToastUtil.show(this, "imgPath ["+imgPath+"]");
    		}
    	}
		
	}

//	private void goActy(Class<?> cls){
//		Intent intent = new Intent(this, cls);
//		this.startActivity(intent);
//	}

	boolean isUploading = false;
	
	@Override
	public HttpRequestValue UpdateMyInfoDetailTaskDao_onPreTask(int taskId) {
		isUploading = true;
		DialogHelper.getProgressDialogInstance().show(this, "数据提交中");
		return null;
	}

	@Override
	public void UpdateMyInfoDetailTaskDao_onPostTask(int taskId,
			boolean isSuccess, String errMsg, ResponseHeaderBean result) {
		DialogHelper.getProgressDialogInstance().close();
//		DebugTrace.Print("Eleven", "isSuccess["+isSuccess+"] -- errMsg["+errMsg+"]");
		if(mBitmap != null && !mBitmap.isRecycled()){
			mBitmap.recycle();
		}
		if(isSuccess){
			SysMng.finishFirstTask();
			DBCtrl.finishTask(this, SysMng.getCurrentId());
			
//			goActy(EndTaskActivity.class);
			Intent intent = new Intent(this, EndTaskActivity.class);
			intent.putExtra(EndTaskActivity.INTENT_KEY_TASKID, SysMng.getCurrentId());
//			SysMng.biz_currentTaskId = "";
			SysMng.clearCurrentId();
			this.startActivity(intent);
			
			this.finish();
		}else{
			ToastUtil.show(this, errMsg);
		}
			
//		ToastUtil.show(this, "Done! " + errMsg);
		
		isUploading = false;
	}
}
