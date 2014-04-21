package com.yrkj.artaskgame.acty.qcar;

import java.lang.ref.WeakReference;

import com.qualcomm.QCAR.QCAR;
import com.yrkj.artaskgame.R;
import com.yrkj.artaskgame.acty.qcar.SampleAppMenu.SampleAppMenu;
import com.yrkj.artaskgame.base.SysMng;
import com.yrkj.artaskgame.cmobile.acty.ConfirmTaskActivity;
import com.yrkj.artaskgame.cmobile.control.DBCtrl;
import com.yrkj.artaskgame.cmobile.control.TblTaskDetail;
import com.yrkj.util.log.DebugLog;
import com.yrkj.util.log.ToastUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class ARCameraActivity extends Activity {
	
	private static final int FOCUS_MODE_NORMAL = 0;
	private static final int FOCUS_MODE_CONTINUOUS_AUTO = 1;
	
	private static final int APPSTATUS_UNINITED = -1;
    private static final int APPSTATUS_INIT_APP = 0;
    private static final int APPSTATUS_INIT_QCAR = 1;
    private static final int APPSTATUS_INIT_TRACKER = 2;
    private static final int APPSTATUS_INIT_APP_AR = 3;
    private static final int APPSTATUS_LOAD_TRACKER = 4;
    private static final int APPSTATUS_INITED = 5;
    public static final int APPSTATUS_CAMERA_STOPPED = 6;
    public static final int APPSTATUS_CAMERA_RUNNING = 7;
    
    final static int CAMERA_DEFAULT = 0;
    final static int CAMERA_BACK = 1;
    final static int CAMERA_FRONT = 2;
	
    public int mAppStatus = APPSTATUS_UNINITED;
    private boolean mContAutofocus = false;
    boolean mIsDroidDevice = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		storeScreenDimensions();
//		initApplicationAR();
//		initend();
		updateApplicationStatus(APPSTATUS_INIT_APP);
		
		mIsDroidDevice = android.os.Build.MODEL.toLowerCase().startsWith(
	            "droid");
	}
	
	public synchronized void updateApplicationStatus(int appStatus)
    {
        // Exit if there is no change in status:
        if (mAppStatus == appStatus)
            return;
        
        // Store new status value:
        mAppStatus = appStatus;
        
        // Execute application state-specific actions:
        switch (mAppStatus)
        {
            case APPSTATUS_INIT_APP:
//                initApplication();
                updateApplicationStatus(APPSTATUS_INIT_QCAR);
                break;
            
            case APPSTATUS_INIT_QCAR:
            	updateApplicationStatus(APPSTATUS_INIT_TRACKER);
                break;
            
            case APPSTATUS_INIT_TRACKER:
                // Initialize the ImageTracker:
//                if (initTracker() > 0)
//                {
                    // Proceed to next application initialization status:
                    updateApplicationStatus(APPSTATUS_INIT_APP_AR);
//                }
                break;
            
            case APPSTATUS_INIT_APP_AR:
                // Initialize Augmented Reality-specific application elements
                // that may rely on the fact that the Vuforia SDK has been
                // already initialized:
                initApplicationAR();
                
                // Proceed to next application initialization status:
                updateApplicationStatus(APPSTATUS_LOAD_TRACKER);
                break;
            
            case APPSTATUS_LOAD_TRACKER:
                // Load the tracking data set:
                //
                // NOTE: This task instance must be created and invoked on the
                // UI thread and it can be executed only once!
                updateApplicationStatus(APPSTATUS_INITED);
                break;
            
            case APPSTATUS_INITED:
                // Hint to the virtual machine that it would be a good time to
                // run the garbage collector:
                //
                // NOTE: This is only a hint. There is no guarantee that the
                // garbage collector will actually be run.
//                System.gc();
                
                // Native post initialization:
//                onQCARInitializedNative();
                
                // Activate the renderer:
                mRenderer.mIsActive = true;
                
                // Now add the GL surface view. It is important
                // that the OpenGL ES surface view gets added
                // BEFORE the camera is started and video
                // background is configured.
                addContentView(mGlView, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                
                // Sets the UILayout to be drawn in front of the camera
                if(mUILayout != null)
                	mUILayout.bringToFront();
                
                if(mScanView != null)
                	mScanView.bringToFront();
                
                // Start the camera:
                updateApplicationStatus(APPSTATUS_CAMERA_RUNNING);
                
                break;
            
            case APPSTATUS_CAMERA_STOPPED:
                // Call the native function to stop the camera:
                stopCamera();
                break;
            
            case APPSTATUS_CAMERA_RUNNING:
                // Call the native function to start the camera:
                startCamera(CAMERA_DEFAULT);
                
                // Hides the Loading Dialog
                loadingDialogHandler.sendEmptyMessage(HIDE_LOADING_DIALOG);
                
                // Sets the layout background to transparent
                if(mUILayout != null)
                	mUILayout.setBackgroundColor(Color.TRANSPARENT);
                
                // Set continuous auto-focus if supported by the device,
                // otherwise default back to regular auto-focus mode.
                // This will be activated by a tap to the screen in this
                // application.
                boolean result = setFocusMode(FOCUS_MODE_CONTINUOUS_AUTO);
                if (!result)
                {
                    DebugLog.LOGE("Unable to enable continuous autofocus");
                    mContAutofocus = false;
                    setFocusMode(FOCUS_MODE_NORMAL);
                } else
                {
                    mContAutofocus = true;
                }
                
//                if( mSampleAppMenu == null)
//                {
//                    mSampleAppMenu = new SampleAppMenu(this, this, "Image Targets",
//                        mGlView, mUILayout, null);
//                    setSampleAppMenuSettings();
//                }
                
                break;
            
            default:
                throw new RuntimeException("Invalid application state");
        }
    }
	
	
	
//	QCARInitActivity mARInitActy = null;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if (mIsDroidDevice)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        
        // Vuforia-specific resume operation
        QCAR.onResume();
        
        // We may start the camera only if the Vuforia SDK has already been
        // initialized
        if (mAppStatus == APPSTATUS_CAMERA_STOPPED)
        {
        	updateApplicationStatus(APPSTATUS_CAMERA_RUNNING);
        }
        
        // Resume the GL view:
        if (mGlView != null)
        {
        	mGlView.setVisibility(View.VISIBLE);
        	mGlView.onResume();
        }
        hasBeenTracking = false;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		if (mGlView != null)
        {
			mGlView.setVisibility(View.INVISIBLE);
			mGlView.onPause();
        }
        
        // Turn off the flash
//        if (mFlashOptionView != null && mFlash)
//        {
//            // OnCheckedChangeListener is called upon changing the checked state
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
//            {
//                ((Switch) mFlashOptionView).setChecked(false);
//            } else
//            {
//                ((CheckBox) mFlashOptionView).setChecked(false);
//            }
//        }
        
        if (mAppStatus == APPSTATUS_CAMERA_RUNNING)
        {
        	updateApplicationStatus(APPSTATUS_CAMERA_STOPPED);
        }
        
        // Vuforia-specific pause operation
        QCAR.onPause();
        hasBeenTracking = false;
	}

	
	
	private void storeScreenDimensions()
    {
        // Query display dimensions:
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }
	
	public QCARSampleGLView mGlView;
	private ImageTargetsRenderer mRenderer;
	private View mLoadingDialogContainer;
	private RelativeLayout mUILayout;
	private Handler loadingDialogHandler = new LoadingDialogHandler(this);
	private int mScreenWidth = 0;
    private int mScreenHeight = 0;
	private void initApplicationAR()
    {
		// Do application initialization in native code (e.g. registering
        // callbacks, etc.):
        initApplicationNative(mScreenWidth, mScreenHeight);
        
        // Create OpenGL ES view:
        int depthSize = 16;
        int stencilSize = 0;
        boolean translucent = QCAR.requiresAlpha();
        
        mGlView = new QCARSampleGLView(this);
        mGlView.init(translucent, depthSize, stencilSize);
        
        mRenderer = new ImageTargetsRenderer();
        mRenderer.mActivity = this;
        mGlView.setRenderer(mRenderer);
        
        addScanView();
        
//        LayoutInflater inflater = LayoutInflater.from(this);
//        mUILayout = (RelativeLayout) inflater.inflate(R.layout.camera_overlay,
//            null, false);
//        
//        mUILayout.setVisibility(View.VISIBLE);
//        mUILayout.setBackgroundColor(Color.BLACK);
//        
//        // Gets a reference to the loading dialog
//        mLoadingDialogContainer = mUILayout
//            .findViewById(R.id.loading_indicator);
//        
//        // Shows the loading indicator at start
//        loadingDialogHandler.sendEmptyMessage(SHOW_LOADING_DIALOG);
        
        // Adds the inflated layout to the view
//        addContentView(mUILayout, new LayoutParams(LayoutParams.MATCH_PARENT,
//            LayoutParams.MATCH_PARENT));
        
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
	
	
	private static final int INVALID_SCREEN_ROTATION = -1;
	private int mLastScreenRotation = INVALID_SCREEN_ROTATION;
	public void updateRenderView()
    {
        int currentScreenRotation = getWindowManager().getDefaultDisplay()
            .getRotation();
        if (currentScreenRotation != mLastScreenRotation)
        {
            // Set projection matrix if there is already a valid one:
            if (QCAR.isInitialized()
                && (mAppStatus == APPSTATUS_CAMERA_RUNNING))
            {
                DebugLog.LOGD("updateRenderView");
                
                // Query display dimensions:
                storeScreenDimensions();
                
                // Update viewport via renderer:
                mRenderer.updateRendering(mScreenWidth, mScreenHeight);
                
                // Update projection matrix:
                setProjectionMatrix();
                
                // Cache last rotation used for setting projection matrix:
                mLastScreenRotation = currentScreenRotation;
            }
        }
    }
	
	
	static final int HIDE_LOADING_DIALOG = 0;
    static final int SHOW_LOADING_DIALOG = 1;
    private static final int TRACKING_SHOW = 2;
	static class LoadingDialogHandler extends Handler
    {
        private final WeakReference<ARCameraActivity> mImageTargets;
        
        LoadingDialogHandler(ARCameraActivity imageTargets)
        {
            mImageTargets = new WeakReference<ARCameraActivity>(imageTargets);
        }
        
        
        public void handleMessage(Message msg)
        {
        	ARCameraActivity imageTargets = mImageTargets.get();
            if (imageTargets == null)
            {
                return;
            }
            
            if (msg.what == SHOW_LOADING_DIALOG)
            {
//                imageTargets.mLoadingDialogContainer
//                    .setVisibility(View.VISIBLE);
                
            } else if (msg.what == HIDE_LOADING_DIALOG)
            {
//                imageTargets.mLoadingDialogContainer.setVisibility(View.GONE);
            }else if (msg.what == TRACKING_SHOW){
				String dataSetName = msg.obj.toString();
				imageTargets.findTarget(dataSetName);
				
//				Intent intent = new Intent(acty, MoodActivity.class);
//				intent.putExtra(MoodActivity.INTENT_KEY_DTNAME, dataSetName);
//				acty.startActivity(intent);
			}
        }
    }
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	        DebugLog.LOGD("onConfigurationChanged");
	        super.onConfigurationChanged(newConfig);
	        storeScreenDimensions();
	        // Invalidate screen rotation to trigger query upon next render call:
	        mLastScreenRotation = INVALID_SCREEN_ROTATION;

	}
	
	
	public int initTracker(){
    	return QCARInitActivity.getInstance().initTracker();
    }
    public void deinitTracker(){
    	QCARInitActivity.getInstance().deinitTracker();
    }
    public int loadTrackerData(){
    	return QCARInitActivity.getInstance().loadTrackerData();
    }
    
    public void destroyTrackerData(){
    	QCARInitActivity.getInstance().destroyTrackerData();
    }
    
    public void onQCARInitializedNative(){
    	QCARInitActivity.getInstance().onQCARInitializedNative();
    }
    
    private void startCamera(int camera){
    	QCARInitActivity.getInstance().startCamera(camera);
    }
    
    private void stopCamera(){
    	QCARInitActivity.getInstance().stopCamera();
    }
    
    private void setProjectionMatrix(){
    	QCARInitActivity.getInstance().setProjectionMatrix();
    }
    
    private boolean startExtendedTracking(){
    	return QCARInitActivity.getInstance().startExtendedTracking();
    }
    
    private boolean stopExtendedTracking(){
    	return QCARInitActivity.getInstance().stopExtendedTracking();
    }
    
    private void deinitApplicationNative(){
    	QCARInitActivity.getInstance().deinitApplicationNative();
    }
    
    private void setActivityPortraitMode(boolean isPortrait){
    	QCARInitActivity.getInstance().setActivityPortraitMode(isPortrait);
    }
    
    private void initApplicationNative(int width, int height){
    	QCARInitActivity.getInstance().initApplicationNative(width,height);
    }
    
    private void switchDatasetAsap(int datasetId){
    	QCARInitActivity.getInstance().switchDatasetAsap(datasetId);
    }
    
    private boolean autofocus(){
    	return QCARInitActivity.getInstance().autofocus();
    }
    
    private boolean setFocusMode(int mode){
    	return QCARInitActivity.getInstance().setFocusMode(mode);
    }
    
    private boolean activateFlash(boolean flash){
    	return QCARInitActivity.getInstance().activateFlash(flash);
    }
    
    public boolean hasBeenTracking = false;
    public void onTracking(String s){
//    	ToastUtil.show(this, s);
    	if(!hasBeenTracking){
    		hasBeenTracking = true;
    		Message msg = new Message();
    		msg.what = TRACKING_SHOW;
    		msg.obj = s;
//		loadingDialogHandler.sendMessageAtFrontOfQueue(msg);
    		loadingDialogHandler.sendMessageDelayed(msg, 500);
    	}
    }
    
    public void findTarget(String s){
    	ToastUtil.show(this, s);
    	
    	TblTaskDetail item = DBCtrl.getSelectTask(this, SysMng.biz_currentTaskId);
    	if(item != null){
    		if(item.TargetId != null && item.TargetId.equals(s)){
    			
    			goActy(ConfirmTaskActivity.class);
    			return;
//    			hasBeenTracking = false;
//    			this.finish();
    		}
    	}else{
    	}
    	hasBeenTracking = false;
    }
    private void goActy(Class<?> cls){
		Intent intent = new Intent(this, cls);
		this.startActivity(intent);
	}
	
}
