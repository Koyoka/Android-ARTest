package com.yrkj.elderlycareassess.util;

import java.io.File;
import java.io.IOException;

import com.yrkj.elderlycareassess.widget.UIRecordButton.OnFinishedRecordListener;
import com.yrkj.util.log.ToastUtil;


import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class RecordHelper {
	
	private static final int MIN_INTERVAL_TIME = 2000;// 2s
	private long startTime;
	private MediaRecorder recorder;
	private String mFileName = null;
	private Context mC;
	
	static class ShowVolumeHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
//			view.setImageResource(res[msg.what]);
		}
	}
	public RecordHelper(Context context){
		volumeHandler = new ShowVolumeHandler();
		mC = context;
	}
	
	private void init(){
		
//		startRecording();
	}
	
	boolean isBegin = false;
	
	public void startRecording(String fileName) {
		
		if(isBegin){
			ToastUtil.show(mC, "正在录音。");
			return;
		}
		
		isBegin = true;
		mFileName = fileName;
		startTime = System.currentTimeMillis();
		
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(mFileName);
		Toast.makeText(mC,"开始 ！！！", Toast.LENGTH_SHORT).show();
		try {
			recorder.prepare();
		} catch (IOException e) {
			
			Toast.makeText(mC, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

		recorder.start();
		thread = new ObtainDecibelThread();
		thread.start();

	}
	
	public void pauseRecord(){
//		recorder.
	}
	
	public void cancelRecord() {
		stopRecording();
//		recordIndicator.dismiss();
		
		Toast.makeText(mC, "取消录音！", Toast.LENGTH_SHORT).show();
		File file = new File(mFileName);
		file.delete();
	}
	
	private void stopRecording() {
		if (thread != null) {
			thread.exit();
			thread = null;
		}
		if (recorder != null) {
			recorder.stop();
			recorder.release();
			recorder = null;
		}
		isBegin = false;
	}
	
	public void finishRecord() {
		stopRecording();

		long intervalTime = System.currentTimeMillis() - startTime;
		if (intervalTime < MIN_INTERVAL_TIME) {
			Toast.makeText(mC, "时间太短！", Toast.LENGTH_SHORT).show();
			File file = new File(mFileName);
			file.delete();
			return;
		}

		if (finishedListener != null)
			finishedListener.onFinishedRecord(mFileName);
	}
	
	public void setOnFinishedRecordListener(OnHFinishedRecordListener listener) {
		finishedListener = listener;
	}
	
	private OnHFinishedRecordListener finishedListener;
	private ObtainDecibelThread thread;
	private Handler volumeHandler;
	private class ObtainDecibelThread extends Thread {

		private volatile boolean running = true;

		public void exit() {
			running = false;
		}

		@Override
		public void run() {
			while (running) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (recorder == null || !running) {
					break;
				}
				int x = recorder.getMaxAmplitude();
				if (x != 0) {
					int f = (int) (10 * Math.log(x) / Math.log(10));
					if (f < 26)
						volumeHandler.sendEmptyMessage(0);
					else if (f < 32)
						volumeHandler.sendEmptyMessage(1);
					else if (f < 38)
						volumeHandler.sendEmptyMessage(2);
					else
						volumeHandler.sendEmptyMessage(3);

				}

			}
		}

	}
	
	public interface OnHFinishedRecordListener {
		public void onFinishedRecord(String audioPath);
	}
	
}
