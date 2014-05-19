package com.yrkj.elderlycareassess.fragment;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.AlbumActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentSoundData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentAttachment;
import com.yrkj.elderlycareassess.util.AudioHelper;
import com.yrkj.elderlycareassess.util.FragmentMediaHelper;
import com.yrkj.elderlycareassess.widget.UIRecordButton.OnFinishedRecordListener;
import com.yrkj.util.bitmap.BitmapHelper;
import com.yrkj.util.bitmap.MediaHelper;

public class AttachmentFragment extends Fragment implements OnClickListener {
	
	private FragmentAttachment mLayout;
	AudioHelper mAudioHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_attachment, container,
				false);
		
		mLayout = new FragmentAttachment(v);
		initFragment();
		return v;
	}
	
	private void initFragment(){
		mLayout.getBtnDiseaseView().setOnClickListener(this);
		mLayout.getBtnCameraView().setOnClickListener(this);
		mLayout.getBtnSoundView().setSavePath(getSaveSoundFileName());
		mAudioHelper = new AudioHelper(mLayout.getProgressBar1());
		mLayout.getBtnSoundView().setOnFinishedRecordListener(new OnFinishedRecordListener() {

			@Override
			public void onFinishedRecord(String audioPath) {
//				Toast.makeText(RecordingActivity.this,audioPath, Toast.LENGTH_SHORT).show();
				AssessTaskAttachmentSoundData d = new AssessTaskAttachmentSoundData();
				d.SoundPath = audioPath;
				AttachmentDBCtrl.addAttachmentSound(getActivity(), d);	
				bindSound();
//				ToastUtil.show(getActivity(), audioPath);
				mLayout.getBtnSoundView().setSavePath(getSaveSoundFileName());
//				openFile(new File(audioPath));
//				
			}
		});
		bindImg();
		bindSound();
	}

	
	private void popDisease(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getBtnDiseaseView());
		popup.getMenuInflater().inflate(R.menu.disease, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				mLayout.getBtnDiseaseView().setText(item.getTitle());
				return true;
			}
		});
		
		popup.show();
	}
	private String mSavePath = "";
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case FragmentAttachment.BtnDiseaseViewId:
			popDisease();
			break;

		case FragmentAttachment.BtnCameraViewId:
			
			mSavePath = FragmentMediaHelper.getSavePath();
			FragmentMediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/,mSavePath);
			break;
			
		default:
			break;
		}
			
	}
	private static final File SOUND_DIR = 
			new File(Environment.getExternalStorageDirectory() + "/ECA_Sound");//图片的存储目录
	
	private static String getSaveSoundFileName(){
		if(!SOUND_DIR.exists()){
			SOUND_DIR.mkdir();
		}
		
		Date date = new Date(System.currentTimeMillis());  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("'SOUND'_yyyyMMdd_HHmmss");  
	    String saveSoundFileName = dateFormat.format(date) + ".amr";  
	    
	    File f = new File(SOUND_DIR, saveSoundFileName); // 用当前时间给取得的图片命名
		return f.getPath();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String imgPath = FragmentMediaHelper.getMedioResultPath(this,data,requestCode,mSavePath);
		
		AssessTaskAttachmentImageData d = new AssessTaskAttachmentImageData();
		d.ImgPath = imgPath;
		
		AttachmentDBCtrl.addAttachmentImg(getActivity(), d);
		bindImg();
	}
	
	private void bindImg(){
		mLayout.getLayoutImgList().removeAllViews();
		ArrayList<AssessTaskAttachmentImageData> itemlist =
				AttachmentDBCtrl.getAttachmentImgList(getActivity());
		for (AssessTaskAttachmentImageData item : itemlist) {
			
			Bitmap b = BitmapHelper.decodeSampledBitmapFromLacolPath(item.ImgPath,40,40);
			
			ImageView v = new ImageView(getActivity());
			
			LinearLayout.LayoutParams lp =
					new LinearLayout.LayoutParams(120, 120);
			lp.setMargins(10, 0, 10, 0);
			v.setLayoutParams(lp);
			
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
            v.setImageBitmap(b);
            mLayout.getLayoutImgList().addView(v);
            v.setOnClickListener(imgClick);
           
		}
		
	}
	private void bindSound(){
		mLayout.getLayoutSoundList().removeAllViews();
		ArrayList<AssessTaskAttachmentSoundData> itemlist =
				AttachmentDBCtrl.getAttachmentSoundList(getActivity());
		LayoutInflater l = LayoutInflater.from(getActivity());
		for (AssessTaskAttachmentSoundData item : itemlist) {
			
//			Bitmap b = BitmapHelper.decodeSampledBitmapFromLacolPath(item.ImgPath,40,40);
			
//			<RadioButton 
//            android:layout_width="wrap_content"
//            android:layout_height="wrap_content"
//            android:drawableTop="@drawable/icon_audio_x"
//            android:text="1"
//            android:gravity="center"
//            android:button="@null"/>
//			RadioButton v = (RadioButton) l.from(getActivity()).inflate(R.layout.view_radio, 
//					mLayout.getLayoutSoundList(),
//					false);
//			v.setTag(item.SoundPath);
//			v.setText(item.Id+"");
//			mLayout.getLayoutSoundList().addView(v);
//			v.setOnClickListener(soundClick);
//			RadioButton vv = new RadioButton(getActivity());
//			LinearLayout.LayoutParams lp1 =
//					new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//			lp1.setMargins(10, 0, 10, 0);
//			vv.setLayoutParams(lp1);
//			vv.setd
			
			
			ImageView v = new ImageView(getActivity());
			
			LinearLayout.LayoutParams lp =
					new LinearLayout.LayoutParams(120, 120);
			lp.setMargins(10, 0, 10, 0);
			v.setLayoutParams(lp);
			
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
//			v.setImageBitmap(b);
			v.setTag(item.SoundPath);
			
			v.setImageResource(R.drawable.icon_audio);
			mLayout.getLayoutSoundList().addView(v);
			v.setOnClickListener(soundClick);
			
		}
		
	}
	
	
	
	OnClickListener imgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(getActivity(), AlbumActivity.class);
			startActivity(intent);
		}
	};
	OnClickListener soundClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			v.setBackgroundColor(Color.parseColor("red"));
			mAudioHelper.setFile(v.getTag().toString()).play();
//			Intent intent = new Intent(getActivity(), AlbumActivity.class);
//			startActivity(intent);
		}
	};
	
	
}
