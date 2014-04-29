package com.yrkj.artaskgame.acty.qcar;

import java.util.Vector;

import com.yrkj.artaskgame.base.BaseApplication;
import com.yrkj.util.log.DebugLog;
import com.yrkj.util.log.DebugTrace;

public class QCARInitActivity  {
    // Contextual Menu Options for Camera Flash - Autofocus
	
	private Vector<Texture> mTextures;
	public void setTextures(Vector<Texture> t){
			mTextures = t;
	}
	public int getTextureCount()
    {
//		return 4;
		return mTextures.size();
    }
	public Texture getTexture(int i)
    {
        return mTextures.elementAt(i);
    }
   
	public QCARInitActivity(){
		
	}
	
	private static QCARInitActivity mQCARInitActivity = null;
	public static QCARInitActivity getInstance(){
//		return BaseApplication.getInstance().mQCAR;
		if(mQCARInitActivity == null){
			DebugTrace.Print("ElevenQCAR","mQCARInitActivity == null");
			mQCARInitActivity = new QCARInitActivity();
		}
		return mQCARInitActivity;
	}
	
	public static boolean isReClear1(){
		if(mQCARInitActivity == null){
			return true;
		}
		return false;
	}
	
	private static final String NATIVE_LIB_SAMPLE = "ImageTargetsNative";
	private static final String NATIVE_LIB_QCAR = "Vuforia";
    /** Static initializer block to load native libraries on start-up. */
    static
    {
        loadLibrary(NATIVE_LIB_QCAR);
        loadLibrary(NATIVE_LIB_SAMPLE);
    }
    
    public native int initTracker();
    public native void deinitTracker();
    public native int loadTrackerData();
    public native void destroyTrackerData();
    public native void onQCARInitializedNative();
    public native void startCamera(int camera);
    public native void stopCamera();
    public native void setProjectionMatrix();
    public native boolean startExtendedTracking();
    public native boolean stopExtendedTracking();
    public native void deinitApplicationNative();
    public native void setActivityPortraitMode(boolean isPortrait);
    public native void initApplicationNative(int width, int height);
    public native void switchDatasetAsap(int datasetId);
    public native boolean autofocus();
    public native boolean setFocusMode(int mode);
    public native boolean activateFlash(boolean flash);
    
    
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
    
  
	
}






