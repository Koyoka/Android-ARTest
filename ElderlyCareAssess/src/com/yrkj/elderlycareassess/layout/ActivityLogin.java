package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityLogin extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int TxtUserNameViewId = R.id.txtUserNameView;
    public static final int TxtPasswordViewId = R.id.txtPasswordView;
    public static final int BtnLoginViewId = R.id.btnLoginView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.EditText mTxtUserNameView;
    protected android.widget.EditText mTxtPasswordView;
    protected android.widget.Button mBtnLoginView;

    protected Activity mCurActy;

    public ActivityLogin(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtUserNameView = (android.widget.EditText) acty.findViewById(TxtUserNameViewId);
        mTxtPasswordView = (android.widget.EditText) acty.findViewById(TxtPasswordViewId);
        mBtnLoginView = (android.widget.Button) acty.findViewById(BtnLoginViewId);
    }   

    public ActivityLogin(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtUserNameView = (android.widget.EditText) acty.findViewById(TxtUserNameViewId);
        mTxtPasswordView = (android.widget.EditText) acty.findViewById(TxtPasswordViewId);
        mBtnLoginView = (android.widget.Button) acty.findViewById(BtnLoginViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.EditText getTxtUserNameView() {
        return mTxtUserNameView;
    }
    public android.widget.EditText getTxtPasswordView() {
        return mTxtPasswordView;
    }
    public android.widget.Button getBtnLoginView() {
        return mBtnLoginView;
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
                    break;
                case TxtUserNameViewId:
                    setViewData(adp,getTxtUserNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtPasswordViewId:
                    setViewData(adp,getTxtPasswordView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnLoginViewId:
                    setViewData(adp,getBtnLoginView(),data,joinData.formatString,joinData.data);
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                case TxtUserNameViewId:
                    setViewData(adp,getTxtUserNameView(),data,"",path);
                    break;
                case TxtPasswordViewId:
                    setViewData(adp,getTxtPasswordView(),data,"",path);
                    break;
                case BtnLoginViewId:
                    setViewData(adp,getBtnLoginView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
