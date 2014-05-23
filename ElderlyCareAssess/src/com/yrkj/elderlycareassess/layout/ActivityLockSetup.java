package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityLockSetup extends BaseLayout{

    public static final int TxtStepDescViewId = R.id.txtStepDescView;
    public static final int Lock_patternId = R.id.lock_pattern;
    public static final int Right_btnId = R.id.right_btn;
    public static final int Left_btnId = R.id.left_btn;

    protected android.widget.TextView mTxtStepDescView;
    protected com.yrkj.elderlycareassess.gusturelock.LockPatternView mLock_pattern;
    protected android.widget.Button mRight_btn;
    protected android.widget.Button mLeft_btn;

    protected Activity mCurActy;

    public ActivityLockSetup(Activity acty){
        mCurActy = acty;
        mTxtStepDescView = (android.widget.TextView) acty.findViewById(TxtStepDescViewId);
        mLock_pattern = (com.yrkj.elderlycareassess.gusturelock.LockPatternView) acty.findViewById(Lock_patternId);
        mRight_btn = (android.widget.Button) acty.findViewById(Right_btnId);
        mLeft_btn = (android.widget.Button) acty.findViewById(Left_btnId);
    }   

    public ActivityLockSetup(android.view.View acty){
        mTxtStepDescView = (android.widget.TextView) acty.findViewById(TxtStepDescViewId);
        mLock_pattern = (com.yrkj.elderlycareassess.gusturelock.LockPatternView) acty.findViewById(Lock_patternId);
        mRight_btn = (android.widget.Button) acty.findViewById(Right_btnId);
        mLeft_btn = (android.widget.Button) acty.findViewById(Left_btnId);
    }   
    public android.widget.TextView getTxtStepDescView() {
        return mTxtStepDescView;
    }
    public com.yrkj.elderlycareassess.gusturelock.LockPatternView getLock_pattern() {
        return mLock_pattern;
    }
    public android.widget.Button getRight_btn() {
        return mRight_btn;
    }
    public android.widget.Button getLeft_btn() {
        return mLeft_btn;
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
                case TxtStepDescViewId:
                    setViewData(adp,getTxtStepDescView(),data,joinData.formatString,joinData.data);
                    break;
                case Lock_patternId:
                    setViewData(adp,getLock_pattern(),data,joinData.formatString,joinData.data);
                    break;
                case Right_btnId:
                    setViewData(adp,getRight_btn(),data,joinData.formatString,joinData.data);
                    break;
                case Left_btnId:
                    setViewData(adp,getLeft_btn(),data,joinData.formatString,joinData.data);
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
                case TxtStepDescViewId:
                    setViewData(adp,getTxtStepDescView(),data,"",path);
                    break;
                case Lock_patternId:
                    setViewData(adp,getLock_pattern(),data,"",path);
                    break;
                case Right_btnId:
                    setViewData(adp,getRight_btn(),data,"",path);
                    break;
                case Left_btnId:
                    setViewData(adp,getLeft_btn(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
