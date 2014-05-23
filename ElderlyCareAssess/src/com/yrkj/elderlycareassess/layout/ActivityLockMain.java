package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityLockMain extends BaseLayout{

    public static final int LockId = R.id.lock;
    public static final int UnlockId = R.id.unlock;

    protected android.widget.Button mLock;
    protected android.widget.Button mUnlock;

    protected Activity mCurActy;

    public ActivityLockMain(Activity acty){
        mCurActy = acty;
        mLock = (android.widget.Button) acty.findViewById(LockId);
        mUnlock = (android.widget.Button) acty.findViewById(UnlockId);
    }   

    public ActivityLockMain(android.view.View acty){
        mLock = (android.widget.Button) acty.findViewById(LockId);
        mUnlock = (android.widget.Button) acty.findViewById(UnlockId);
    }   
    public android.widget.Button getLock() {
        return mLock;
    }
    public android.widget.Button getUnlock() {
        return mUnlock;
    }

    public void bindData(LayoutDataAdapter adp,BaseBean data){
        if(adp == null || data == null){
            return;
        }
        
        if(adp.BindJoinFiledList != null){
            java.util.Iterator iter = adp.BindJoinFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                LayoutDataAdapter.JoinData joinData = (LayoutDataAdapter.JoinData) val;
                
                switch (viewKey) {
                case LockId:
                    setViewData(adp,getLock(),data,joinData.formatString,joinData.data);
                    break;
                case UnlockId:
                    setViewData(adp,getUnlock(),data,joinData.formatString,joinData.data);
                    break;
                }
            }
        }
        
        if(adp.BindFiledList != null){
            java.util.Iterator iter = adp.BindFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                String path = (String) val;
                switch (viewKey) {
                case LockId:
                    setViewData(adp,getLock(),data,"",path);
                    break;
                case UnlockId:
                    setViewData(adp,getUnlock(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
