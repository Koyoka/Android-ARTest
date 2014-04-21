package com.yrkj.artaskgame.acty.qcar;

import java.lang.ref.WeakReference;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.qualcomm.QCAR.QCAR;
import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.util.log.DebugLog;
//import com.yrkj.smoking.R;
//import com.yrkj.smoking.ar.ImageTargetsRenderer;
//import com.yrkj.smoking.ar.QCARSampleGLView;
//import com.yrkj.smoking.ar.Texture;
//import com.yrkj.smoking.launch.SysMng;
//import com.yrkj.smoking.util.log.DebugLog;

public class MainActivity extends Activity {

	private Object mShutdownLock = new Object();
	
	boolean mIsDroidDevice = false;
	
	private boolean mContAutofocus = false;
	// Vuforia initialization flags:
    private int mVuforiaFlags = 0;
 // Detects the double tap gesture for launching the Camera menu
    private GestureDetector mGestureDetector;
	
    private Vector<Texture> mTextures;
    
	// Display size of the device:
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    
    private View mLoadingDialogContainer;
    
 // Our OpenGL view:
    private QCARSampleGLView mGlView;
    // Our renderer:
    private ImageTargetsRenderer mRenderer;
    
    private RelativeLayout mUILayout;
    
    private InitVuforiaTask mInitVuforiaTask;
    private LoadTrackerTask mLoadTrackerTask;
	
	
	private static final String NATIVE_LIB_SAMPLE = "ImageTargetsNative";
    private static final String NATIVE_LIB_QCAR = "Vuforia";
    private static final int CAMERA_DEFAULT = 0;
    private static final int CAMERA_BACK = 1;
    private static final int CAMERA_FRONT = 2;
    
    private static final int HIDE_LOADING_DIALOG = 0;
    private static final int SHOW_LOADING_DIALOG = 1;
    
    private static final int FOCUS_MODE_NORMAL = 0;
    private static final int FOCUS_MODE_CONTINUOUS_AUTO = 1;
    
    private static final int INVALID_SCREEN_ROTATION = -1;
    private int mLastScreenRotation = INVALID_SCREEN_ROTATION;
    
    private AppStatus mAppStatus = AppStatus.APPSTATUS_UNINITED;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       
        initActy();
    }
    
    @Override
    protected void onResume() {
		DebugLog.LOGD("onResume");
		super.onResume();
		
		
		// This is needed for some Droid devices to force portrait
		if (mIsDroidDevice) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}

		// Vuforia-specific resume operation
		QCAR.onResume();

		// We may start the camera only if the Vuforia SDK has already been
		// initialized
		if (mAppStatus == AppStatus.APPSTATUS_CAMERA_STOPPED) {
			updateApplicationStatus(AppStatus.APPSTATUS_CAMERA_RUNNING);
		}

		// Resume the GL view:
		if (mGlView != null) {
			mGlView.setVisibility(View.VISIBLE);
			mGlView.onResume();
		}
		hasBeenTracking = false;
    }
    
    @Override
	protected void onPause() {
		DebugLog.LOGD("onPause");
		super.onPause();

		
		if (mGlView != null) {
			mGlView.setVisibility(View.INVISIBLE);
			mGlView.onPause();
		}

		// Turn off the flash
//		if (mFlashOptionView != null && mFlash) {
//			// OnCheckedChangeListener is called upon changing the checked state
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//				((Switch) mFlashOptionView).setChecked(false);
//			} else {
//				((CheckBox) mFlashOptionView).setChecked(false);
//			}
//		}

		if (mAppStatus == AppStatus.APPSTATUS_CAMERA_RUNNING) {
			updateApplicationStatus(AppStatus.APPSTATUS_CAMERA_STOPPED);
		}

		// Vuforia-specific pause operation
		QCAR.onPause();
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
    
    private void initActy(){
    	SysMng.OnInit();
		mTextures = new Vector<Texture>();
		DebugLog.LOG("1.--------");
		loadTextures();
		
    	mVuforiaFlags = QCAR.GL_20;
        
        mGestureDetector = new GestureDetector(this, new GestureListener());
        DebugLog.LOG("2.--------");
        updateApplicationStatus(AppStatus.APPSTATUS_INIT_APP);
        
        mIsDroidDevice = android.os.Build.MODEL.toLowerCase().startsWith(
                "droid");
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
            
            mRenderer.mIsActive = true;
            
            addContentView(mGlView, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            
            // Sets the UILayout to be drawn in front of the camera
            mUILayout.bringToFront();
            if(mScanView != null)
            	mScanView.bringToFront();
            
            // Start the camera:
            updateApplicationStatus(AppStatus.APPSTATUS_CAMERA_RUNNING);
            
			break;
		case APPSTATUS_CAMERA_STOPPED:
			
			stopCamera();
			
			break;
		case APPSTATUS_CAMERA_RUNNING:

			// Call the native function to start the camera:
			startCamera(CAMERA_DEFAULT);

			// Hides the Loading Dialog
			loadingDialogHandler.sendEmptyMessage(HIDE_LOADING_DIALOG);

			// Sets the layout background to transparent
			mUILayout.setBackgroundColor(Color.TRANSPARENT);

			// Set continuous auto-focus if supported by the device,
			// otherwise default back to regular auto-focus mode.
			// This will be activated by a tap to the screen in this
			// application.
			boolean result = setFocusMode(FOCUS_MODE_CONTINUOUS_AUTO);
			if (!result) {
				DebugLog.LOGE("Unable to enable continuous autofocus");
				mContAutofocus = false;
				setFocusMode(FOCUS_MODE_NORMAL);
			} else {
				mContAutofocus = true;
			}

//			if (mSampleAppMenu == null) {
//				mSampleAppMenu = new SampleAppMenu(this, this, "Image Targets",
//						mGlView, mUILayout, null);
//				setSampleAppMenuSettings();
//			}
			
			break;
		case APPSTATUS_UNINITED:

			break;
		default:
			throw new RuntimeException("Invalid application state");
		}

	}
	
	 private void initApplication(){
        setActivityPortraitMode(true);
        
        storeScreenDimensions();
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
	 
	 private void initApplicationAR(){
		initApplicationNative(mScreenWidth, mScreenHeight);

		// Create OpenGL ES view:
		int depthSize = 16;
		int stencilSize = 0;
		boolean translucent = QCAR.requiresAlpha();

		mGlView = new QCARSampleGLView(this);
		mGlView.init(translucent, depthSize, stencilSize);

		mRenderer = new ImageTargetsRenderer();
//		mRenderer.mActivity = this;
		mGlView.setRenderer(mRenderer);

		LayoutInflater inflater = LayoutInflater.from(this);
		mUILayout = (RelativeLayout) inflater.inflate(R.layout.camera_overlay,
				null, false);

		mUILayout.setVisibility(View.VISIBLE);
		mUILayout.setBackgroundColor(Color.BLACK);

		// Gets a reference to the loading dialog
		mLoadingDialogContainer = mUILayout
				.findViewById(R.id.loading_indicator);

		// Shows the loading indicator at start
		loadingDialogHandler.sendEmptyMessage(SHOW_LOADING_DIALOG);
		addScanView();
		// Adds the inflated layout to the view
		addContentView(mUILayout, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		
		
	 }
	 
	 
	private RelativeLayout mScanView; 
	private void addScanView() {
		LayoutInflater inflater = LayoutInflater.from(this);
		mScanView = (RelativeLayout) inflater.inflate(R.layout.layout_scan_view,
				null, false);
		addContentView(mScanView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		View lineView = mScanView.findViewById(R.id.lineView);
		
		final View target = lineView;
        final View targetParent = (View) target.getParent();
        
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        targetParent.measure(w, h);
        int width =targetParent.getMeasuredWidth();
        int height =targetParent.getMeasuredHeight();
		
		Animation a 
//		= new TranslateAnimation(0.0f,
//                targetParent.getWidth() - target.getWidth() - targetParent.getPaddingLeft() -
//                targetParent.getPaddingRight(), 0.0f, 0.0f);
		= new TranslateAnimation(0.0f,0.0f, 
				0.0f,
				height
				);
		
		
		
        a.setDuration(800);
        a.setStartOffset(300);
        a.setRepeatMode(Animation.RESTART);
        a.setRepeatCount(Animation.INFINITE);
        
        a.setInterpolator(AnimationUtils.loadInterpolator(this,
                android.R.anim.accelerate_interpolator));
        target.startAnimation(a);
	}
	 
	private void storeScreenDimensions(){
        // Query display dimensions:
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }
    
	
	
	static class LoadingDialogHandler extends Handler {
		private final WeakReference<MainActivity> mActy;

		LoadingDialogHandler(MainActivity acty) {
			mActy = new WeakReference<MainActivity>(acty);
		}

		public void handleMessage(Message msg) {
			MainActivity acty = mActy.get();
			if (acty == null) {
				return;
			}

			if(acty.mLoadingDialogContainer == null){
				return;
			}
			
			if (msg.what == SHOW_LOADING_DIALOG) {
				acty.mLoadingDialogContainer
						.setVisibility(View.VISIBLE);
			} else if (msg.what == HIDE_LOADING_DIALOG) {
				acty.mLoadingDialogContainer.setVisibility(View.GONE);
			} else if (msg.what == TRACKING_SHOW){
				String dataSetName = msg.obj.toString();
				
//				Intent intent = new Intent(acty, MoodActivity.class);
//				intent.putExtra(MoodActivity.INTENT_KEY_DTNAME, dataSetName);
//				acty.startActivity(intent);
//				acty.hasBeenTracking = true;
			}
		}
	}

	private Handler loadingDialogHandler = new LoadingDialogHandler(this);
	
	private class InitVuforiaTask extends AsyncTask<Void, Integer, Boolean> {

		// Initialize with invalid value:
		private int mProgressValue = -1;

		protected Boolean doInBackground(Void... params) {
			// Prevent the onDestroy() method to overlap with initialization:
			synchronized (mShutdownLock) {
				QCAR.setInitParameters(MainActivity.this, mVuforiaFlags);
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
						MainActivity.this).create();

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
						MainActivity.this).create();

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
	
    static{
        loadLibrary(NATIVE_LIB_QCAR);
        loadLibrary(NATIVE_LIB_SAMPLE);
    }
    
    public static boolean loadLibrary(String nLibName)
    {
        try
        {
            System.loadLibrary(nLibName);
            DebugLog.LOGI("Native library lib" + nLibName + ".so loaded");
            return true;
        } catch (UnsatisfiedLinkError ulee)
        {
            DebugLog.LOGE("The library lib" + nLibName
                + ".so could not be loaded");
        } catch (SecurityException se)
        {
            DebugLog.LOGE("The library lib" + nLibName
                + ".so was not allowed to be loaded");
        }
        
        return false;
    }
    
    
	private class GestureListener extends
			GestureDetector.SimpleOnGestureListener {
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			//return super.onSingleTapUp(e);
			
			return true;
		}
	}
    
	private native void setActivityPortraitMode(boolean isPortrait);
	public  native int  initTracker();
	private native void initApplicationNative(int width, int height);
	public  native void onQCARInitializedNative();
	public  native int 	loadTrackerData();
	private native void stopCamera();
	private native void startCamera(int camera);
	private native boolean setFocusMode(int mode);
	private native void deinitApplicationNative();
	public  native void destroyTrackerData();
	public  native void deinitTracker();
	private native void setProjectionMatrix();
	
	/** Returns the number of registered textures. */
	public int getTextureCount() {
		return mTextures.size();
	}
	
	public Texture getTexture(int i) {
		return mTextures.elementAt(i);
	}
	
	public void updateRenderView() {
		int currentScreenRotation = getWindowManager().getDefaultDisplay()
				.getRotation();
		if (currentScreenRotation != mLastScreenRotation) {
			// Set projection matrix if there is already a valid one:
			if (QCAR.isInitialized()
					&& (mAppStatus == AppStatus.APPSTATUS_CAMERA_RUNNING)) {
				DebugLog.LOGD("updateRenderView");

				storeScreenDimensions();

				mRenderer.updateRendering(mScreenWidth, mScreenHeight);

				setProjectionMatrix();

				mLastScreenRotation = currentScreenRotation;
			}
		}
	}
	
	private static final int TRACKING_SHOW = 2;
	public boolean hasBeenTracking = false;
	public void onTracking(String dataSetName){
		DebugLog.LOG("======" + dataSetName);
		if(!hasBeenTracking){
			hasBeenTracking = true;
//			loadingDialogHandler.sendEmptyMessage(TRACKING_SHOW);
//			loadingDialogHandler.sendEmptyMessageDelayed(TRACKING_SHOW, 300);
			Message msg = new Message();
			msg.what = TRACKING_SHOW;
			msg.obj = dataSetName;
//			loadingDialogHandler.sendMessageAtFrontOfQueue(msg);
			loadingDialogHandler.sendMessageDelayed(msg, 500);
		}
	}
    
}
