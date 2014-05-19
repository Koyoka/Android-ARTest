package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;
import java.util.List;


import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter.OnItemChangeListener;
import ru.truba.touchgallery.GalleryWidget.FilePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;
import com.yrkj.elderlycareassess.util.sound.Recorder;

public class SoundActivity extends Activity {

	SoundActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		mActy = this;
	}
	
	private void initSound(){
		vib = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
	}
	
	Vibrator vib;
	private void a(){
		final Dialog dialog = new Dialog(SoundActivity.this);
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
}
