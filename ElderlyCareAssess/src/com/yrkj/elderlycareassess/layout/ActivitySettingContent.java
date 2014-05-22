package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivitySettingContent extends BaseLayout{

    public static final int LayoutActionBarViewId = R.id.layoutActionBarView;
    public static final int BtnBackViewId = R.id.btnBackView;
    public static final int ContainerId = R.id.container;

    protected android.widget.RelativeLayout mLayoutActionBarView;
    protected android.widget.ImageButton mBtnBackView;
    protected android.widget.FrameLayout mContainer;

    protected Activity mCurActy;

    public ActivitySettingContent(Activity acty){
        mCurActy = acty;
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
    }   

    public ActivitySettingContent(android.view.View acty){
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
    }   
    public android.widget.RelativeLayout getLayoutActionBarView() {
        return mLayoutActionBarView;
    }
    public android.widget.ImageButton getBtnBackView() {
        return mBtnBackView;
    }
    public android.widget.FrameLayout getContainer() {
        return mContainer;
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
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,joinData.formatString,joinData.data);
                    break;
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
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
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,"",path);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,"",path);
                    break;
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
