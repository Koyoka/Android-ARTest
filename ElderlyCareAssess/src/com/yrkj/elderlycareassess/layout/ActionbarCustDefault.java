package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActionbarCustDefault extends BaseLayout{

    public static final int BtnActionBarBackViewId = R.id.btnActionBarBackView;
    public static final int BtnActionBarRightViewId = R.id.btnActionBarRightView;

    protected android.widget.ImageView mBtnActionBarBackView;
    protected android.widget.ImageView mBtnActionBarRightView;

    protected Activity mCurActy;

    public ActionbarCustDefault(Activity acty){
        mCurActy = acty;
        mBtnActionBarBackView = (android.widget.ImageView) acty.findViewById(BtnActionBarBackViewId);
        mBtnActionBarRightView = (android.widget.ImageView) acty.findViewById(BtnActionBarRightViewId);
    }   

    public ActionbarCustDefault(android.view.View acty){
        mBtnActionBarBackView = (android.widget.ImageView) acty.findViewById(BtnActionBarBackViewId);
        mBtnActionBarRightView = (android.widget.ImageView) acty.findViewById(BtnActionBarRightViewId);
    }   
    public android.widget.ImageView getBtnActionBarBackView() {
        return mBtnActionBarBackView;
    }
    public android.widget.ImageView getBtnActionBarRightView() {
        return mBtnActionBarRightView;
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
                case BtnActionBarBackViewId:
                    setViewData(adp,getBtnActionBarBackView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnActionBarRightViewId:
                    setViewData(adp,getBtnActionBarRightView(),data,joinData.formatString,joinData.data);
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
                case BtnActionBarBackViewId:
                    setViewData(adp,getBtnActionBarBackView(),data,"",path);
                    break;
                case BtnActionBarRightViewId:
                    setViewData(adp,getBtnActionBarRightView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
