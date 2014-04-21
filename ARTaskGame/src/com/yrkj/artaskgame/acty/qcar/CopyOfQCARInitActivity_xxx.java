package com.yrkj.artaskgame.acty.qcar;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

import com.qualcomm.QCAR.QCAR;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.util.log.DebugLog;

public class CopyOfQCARInitActivity_xxx  extends Activity{
	final String TAG = "com.yrkj.artaskgame.acty.qcar.QCARInitActivity";
	
	CopyOfQCARInitActivity_xxx mActy = null;
	
	private Object mShutdownLock = new Object();
	private Vector<Texture> mTextures;
	
	private enum AppStatus{
		APPSTATUS_UNINITED,
		APPSTATUS_INIT_APP,
		APPSTATUS_INIT_QCAR,
		APPSTATUS_INIT_TRACKER,
		APPSTATUS_INIT_APP_AR,
		APPSTATUS_LOAD_TRACKER,
		APPSTATUS_INITED,
		APPSTATUS_CAMERA_STOPPED,
		APPSTATUS_CAMERA_RUNNING
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTextures = new Vector<Texture>();
		initActy();
	}
	
	 @Override
    protected void onDestroy() {
		DebugLog.LOGD("onDestroy");
		super.onDestroy();
		SysMng.OnDeInit();
		// Cancel potentially running tasks
		if (mInitVuforiaTask != null
				&& mInitVuforiaTask.getStatus() != InitVuforiaTask.Status.FINISHED) {
			mInitVuforiaTask.cancel(true);
			mInitVuforiaTask = null;
		}

		if (mLoadTrackerTask != null
				&& mLoadTrackerTask.getStatus() != LoadTrackerTask.Status.FINISHED) {
			mLoadTrackerTask.cancel(true);
			mLoadTrackerTask = null;
		}

		// Ensure that all asynchronous operations to initialize Vuforia
		// and loading the tracker datasets do not overlap:
		synchronized (mShutdownLock) {

			// Do application deinitialization in native code:
			deinitApplicationNative();

			// Unload texture:
			mTextures.clear();
			mTextures = null;

			// Destroy the tracking data set:
			destroyTrackerData();

			// Deinit the tracker:
			deinitTracker();

			// Deinitialize Vuforia SDK:
			QCAR.deinit();
		}

		System.gc();
    }

	 private void loadTextures() {
		mTextures.add(Texture.loadTextureFromApk("TextureTeapotBrass.png",
				getAssets()));
		mTextures.add(Texture.loadTextureFromApk("TextureTeapotBlue.png",
				getAssets()));
		mTextures.add(Texture.loadTextureFromApk("TextureTeapotRed.png",
				getAssets()));
		mTextures
				.add(Texture.loadTextureFromApk("Buildings.jpeg", getAssets()));
	}
	 
	public int getTextureCount() {
		return mTextures.size();
	}
	
	public Texture getTexture(int i) {
		return mTextures.elementAt(i);
	}
	
	private int mVuforiaFlags = 0;
	boolean mIsDroidDevice = false;
	private void initActy() {
		mVuforiaFlags = QCAR.GL_20;
		updateApplicationStatus(AppStatus.APPSTATUS_INIT_APP);
		mIsDroidDevice = android.os.Build.MODEL.toLowerCase().startsWith(
                "droid");
	}
	
	
	private AppStatus mAppStatus = AppStatus.APPSTATUS_UNINITED;
	private InitVuforiaTask mInitVuforiaTask;
	private LoadTrackerTask mLoadTrackerTask;
	private static final int CAMERA_DEFAULT = 0;
    private static final int CAMERA_BACK = 1;
    private static final int CAMERA_FRONT = 2;
	private synchronized void updateApplicationStatus(AppStatus appStatus) {
		if (mAppStatus == appStatus)
			return;

		mAppStatus = appStatus;

		switch (appStatus) {
		case APPSTATUS_INIT_APP:
			initApplication();
			updateApplicationStatus(AppStatus.APPSTATUS_INIT_QCAR);
			break;
			
		case APPSTATUS_INIT_QCAR:
			
			try {
				mInitVuforiaTask = new InitVuforiaTask();
				mInitVuforiaTask.execute();
			} catch (Exception e) {
				DebugLog.LOGE("Initializing Vuforia SDK failed");
			}
			
			break;
		case APPSTATUS_INIT_TRACKER:
			
			if (initTracker() > 0){
                // Proceed to next application initialization status:
                updateApplicationStatus(AppStatus.APPSTATUS_INIT_APP_AR);
            }
			
			break;
		case APPSTATUS_INIT_APP_AR:

			initApplicationAR();
            
            updateApplicationStatus(AppStatus.APPSTATUS_LOAD_TRACKER);
			
			break;
		case APPSTATUS_LOAD_TRACKER:
			
			try {
				mLoadTrackerTask = new LoadTrackerTask();
				mLoadTrackerTask.execute();
			} catch (Exception e) {
				DebugLog.LOGE("Loading tracking data set failed");
			}
			
			break;
		case APPSTATUS_INITED:
			
			System.gc();
            
            onQCARInitializedNative();
            
//            mRenderer.mIsActive = true;
            
//            addContentView(mGlView, new LayoutParams(
//                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//            
//            // Sets the UILayout to be drawn in front of the camera
//            mUILayout.bringToFront();
//            if(mScanView != null)
//            	mScanView.bringToFront();
            
            // Start the camera:
            updateApplicationStatus(AppStatus.APPSTATUS_CAMERA_RUNNING);
            
			break;
		case APPSTATUS_CAMERA_RUNNING:

			// Call the native function to start the camera:
			startCamera(CAMERA_DEFAULT);

			// Hides the Loading Dialog
//			loadingDialogHandler.sendEmptyMessage(HIDE_LOADING_DIALOG);

			// Sets the layout background to transparent
//			mUILayout.setBackgroundColor(Color.TRANSPARENT);

			// Set continuous auto-focus if supported by the device,
			// otherwise default back to regular auto-focus mode.
			// This will be activated by a tap to the screen in this
			// application.
//			boolean result = setFocusMode(FOCUS_MODE_CONTINUOUS_AUTO);
//			if (!result) {
//				DebugLog.LOGE("Unable to enable continuous autofocus");
//				mContAutofocus = false;
//				setFocusMode(FOCUS_MODE_NORMAL);
//			} else {
//				mContAutofocus = true;
//			}

//			if (mSampleAppMenu == null) {
//				mSampleAppMenu = new SampleAppMenu(this, this, "Image Targets",
//						mGlView, mUILayout, null);
//				setSampleAppMenuSettings();
//			}
			
			break;
		default:
			break;
		}
	}
	
	//----- render activity ----
	private void initApplication(){
        setActivityPortraitMode(true);
        storeScreenDimensions();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
	
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
	private void storeScreenDimensions(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }
	
	private void initApplicationAR(){
		
	}
	//----- render activity ----
	
	
	private class InitVuforiaTask extends AsyncTask<Void, Integer, Boolean> {

		// Initialize with invalid value:
		private int mProgressValue = -1;

		protected Boolean doInBackground(Void... params) {
			// Prevent the onDestroy() method to overlap with initialization:
			synchronized (mShutdownLock) {
				QCAR.setInitParameters(CopyOfQCARInitActivity_xxx.this, mVuforiaFlags);
				do {
					mProgressValue = QCAR.init();
					// Publish the progress value:
					publishProgress(mProgressValue);
				} while (!isCancelled() && mProgressValue >= 0
						&& mProgressValue < 100);

				return (mProgressValue > 0);
			}
		}

		protected void onProgressUpdate(Integer... values) {
		}

		protected void onPostExecute(Boolean result) {
			// Done initializing Vuforia, proceed to next application
			// initialization status:
			if (result) {
				DebugLog.LOGD("InitVuforiaTask::onPostExecute: Vuforia "
						+ "initialization successful");
				updateApplicationStatus(AppStatus.APPSTATUS_INIT_TRACKER);
				
			} else {
				// Create dialog box for display error:
				AlertDialog dialogError = new AlertDialog.Builder(
						CopyOfQCARInitActivity_xxx.this).create();

				dialogError.setButton(DialogInterface.BUTTON_POSITIVE, "Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Exiting application:
								System.exit(1);
							}
						});

				String logMessage;

				if (mProgressValue == QCAR.INIT_DEVICE_NOT_SUPPORTED) {
					logMessage = "Failed to initialize Vuforia because this "
							+ "device is not supported.";
				} else {
					logMessage = "Failed to initialize Vuforia.";
				}

				// Log error:
				DebugLog.LOGE("InitVuforiaTask::onPostExecute: " + logMessage
						+ " Exiting.");

				// Show dialog box with error message:
				dialogError.setMessage(logMessage);
				dialogError.show();
			}
		}

	}
	
	private class LoadTrackerTask extends AsyncTask<Void, Integer, Boolean> {

		protected Boolean doInBackground(Void... params) {
			// Prevent the onDestroy() method to overlap:
			synchronized (mShutdownLock) {
				// Load the tracker data set:
				return (loadTrackerData() > 0);
			}
		}

		protected void onPostExecute(Boolean result) {
			DebugLog.LOGD("LoadTrackerTask::onPostExecute: execution "
					+ (result ? "successful" : "failed"));

			if (result) {
				// Done loading the tracker, update application status:
				updateApplicationStatus(AppStatus.APPSTATUS_INITED);
			} else {
				// Create dialog box for display error:
				AlertDialog dialogError = new AlertDialog.Builder(
						CopyOfQCARInitActivity_xxx.this).create();

				dialogError.setButton(DialogInterface.BUTTON_POSITIVE, "Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Exiting application:
								System.exit(1);
							}
						});

				// Show dialog box with error message:
				dialogError.setMessage("Failed to load tracker data.");
				dialogError.show();
			}
		}

	}
	
	private native void setActivityPortraitMode(boolean isPortrait);
	public  native int  initTracker();
	public  native int 	loadTrackerData();
	public  native void onQCARInitializedNative();
	
	private native void deinitApplicationNative();
	public  native void destroyTrackerData();
	public  native void deinitTracker();
	
	private native void startCamera(int camera);
	
}






