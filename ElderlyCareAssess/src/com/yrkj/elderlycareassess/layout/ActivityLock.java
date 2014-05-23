package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityLock extends BaseLayout{

    public static final int Lock_patternId = R.id.lock_pattern;
    public static final int BtnLogoutViewId = R.id.btnLogoutView;

    protected com.yrkj.elderlycareassess.gusturelock.LockPatternView mLock_pattern;
    protected android.widget.Button mBtnLogoutView;

    protected Activity mCurActy;

    public ActivityLock(Activity acty){
        mCurActy = acty;
        mLock_pattern = (com.yrkj.elderlycareassess.gusturelock.LockPatternView) acty.findViewById(Lock_patternId);
        mBtnLogoutView = (android.widget.Button) acty.findViewById(BtnLogoutViewId);
    }   

    public ActivityLock(android.view.View acty){
        mLock_pattern = (com.yrkj.elderlycareassess.gusturelock.LockPatternView) acty.findViewById(Lock_patternId);
        mBtnLogoutView = (android.widget.Button) acty.findViewById(BtnLogoutViewId);
    }   
    public com.yrkj.elderlycareassess.gusturelock.LockPatternView getLock_pattern() {
        return mLock_pattern;
    }
    public android.widget.Button getBtnLogoutView() {
        return mBtnLogoutView;
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
                case Lock_patternId:
                    setViewData(adp,getLock_pattern(),data,joinData.formatString,joinData.data);
                    break;
                case BtnLogoutViewId:
                    setViewData(adp,getBtnLogoutView(),data,joinData.formatString,joinData.data);
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
                case Lock_patternId:
                    setViewData(adp,getLock_pattern(),data,"",path);
                    break;
                case BtnLogoutViewId:
                    setViewData(adp,getBtnLogoutView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
