/*==============================================================================
 Copyright (c) 2012-2013 Qualcomm Connected Experiences, Inc.
 All Rights Reserved.
 ==============================================================================*/

package com.yrkj.util.log;

import android.util.Log;


public class DLog
{
    public static final String LOGTAG = "ARTaskGame";
    
    public static final void LOG(String nMessage)
    {
    	Log.i(LOGTAG, nMessage);
    }
    public static final void LOG(String TAG,String nMessage)
    {
    	Log.i(TAG, nMessage);
    }
    
    public static final void LOGE(String nMessage)
    {
        Log.e(LOGTAG, nMessage);
    }
    
    
    public static final void LOGW(String nMessage)
    {
        Log.w(LOGTAG, nMessage);
    }
    
    
    public static final void LOGD(String nMessage)
    {
        Log.d(LOGTAG, nMessage);
    }
    
    
    public static final void LOGI(String nMessage)
    {
        Log.i(LOGTAG, nMessage);
    }
}
