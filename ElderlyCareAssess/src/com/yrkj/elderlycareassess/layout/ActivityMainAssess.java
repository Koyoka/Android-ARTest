package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityMainAssess extends BaseLayout{

    public static final int LayoutActionBarViewId = R.id.layoutActionBarView;
    public static final int BtnSettingViewId = R.id.btnSettingView;
    public static final int LayoutBodyViewId = R.id.layoutBodyView;
    public static final int BtnBackViewId = R.id.btnBackView;
    public static final int BtnGoViewId = R.id.btnGoView;

    protected android.widget.RelativeLayout mLayoutActionBarView;
    protected android.widget.Button mBtnSettingView;
    protected android.widget.FrameLayout mLayoutBodyView;
    protected android.widget.Button mBtnBackView;
    protected android.widget.Button mBtnGoView;

    protected Activity mCurActy;

    public ActivityMainAssess(Activity acty){
        mCurActy = acty;
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnSettingView = (android.widget.Button) acty.findViewById(BtnSettingViewId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
        mBtnBackView = (android.widget.Button) acty.findViewById(BtnBackViewId);
        mBtnGoView = (android.widget.Button) acty.findViewById(BtnGoViewId);
    }   

    public ActivityMainAssess(android.view.View acty){
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnSettingView = (android.widget.Button) acty.findViewById(BtnSettingViewId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
        mBtnBackView = (android.widget.Button) acty.findViewById(BtnBackViewId);
        mBtnGoView = (android.widget.Button) acty.findViewById(BtnGoViewId);
    }   
    public android.widget.RelativeLayout getLayoutActionBarView() {
        return mLayoutActionBarView;
    }
    public android.widget.Button getBtnSettingView() {
        return mBtnSettingView;
    }
    public android.widget.FrameLayout getLayoutBodyView() {
        return mLayoutBodyView;
    }
    public android.widget.Button getBtnBackView() {
        return mBtnBackView;
    }
    public android.widget.Button getBtnGoView() {
        return mBtnGoView;
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
                case BtnSettingViewId:
                    setViewData(adp,getBtnSettingView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutBodyViewId:
                    setViewData(adp,getLayoutBodyView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnGoViewId:
                    setViewData(adp,getBtnGoView(),data,joinData.formatString,joinData.data);
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
                case BtnSettingViewId:
                    setViewData(adp,getBtnSettingView(),data,"",path);
                    break;
                case LayoutBodyViewId:
                    setViewData(adp,getLayoutBodyView(),data,"",path);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,"",path);
                    break;
                case BtnGoViewId:
                    setViewData(adp,getBtnGoView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
