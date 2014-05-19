package com.yrkj.elderlycareassess.util;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.ProgressBar;

import com.yrkj.util.log.DLog;

public class AudioHelper implements Runnable, OnCompletionListener {
	MediaPlayer mp;
	ProgressBar mSeekBar;
	String mfilePath = "";
	public AudioHelper(ProgressBar v){
		mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        mSeekBar = v;
        
	}
	
	public AudioHelper setFile(String filePath){
		mfilePath = filePath;
		return this;
	}
	private String lastPath = "";
//	public void stop
	
	public void play(){
		DLog.LOG("ELeven", mfilePath);
		if(!new File(mfilePath).exists()){
			return;
		}
		
		boolean isNew = false;
		if(!mfilePath.equals(lastPath)){
			isNew = true;
			lastPath = mfilePath;
		}
		
		if(!isNew){
			if(mp.isPlaying()){
				mp.pause();
//				mp.seekTo(0);
			}else{
				mp.start();
				Thread run = new Thread((Runnable)this);
				run.start();
			}
			return;
		}
		
		if(mp.isPlaying()){
			mp.stop();
			mp.reset();
//			v.setBackgroundResource(R.drawable.pause1);
		}
//		else
		{
			try {
				
				
				mp.setDataSource(
						mfilePath
//						Environment.getExternalStorageDirectory().getPath()+"/Custom SoundClips/"+filename+".wav"
						);
				mp.prepare();
				mSeekBar.setMax(mp.getDuration());
				mp.start();
				mSeekBar.setProgress(0);
//				v.setBackgroundResource(R.drawable.play1);
				Thread run = new Thread((Runnable)this);
				run.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.stop();
		mp.reset();
		lastPath = "";
		
	}

	@Override
	public void run() {
		int currentPosition = 0;
        int total = mp.getDuration();
        while (mp.isPlaying()) {
        	mSeekBar.setProgress(mp.getCurrentPosition());
        	
            try {
            	currentPosition = mp.getCurrentPosition();
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
        }
		
	}
}
