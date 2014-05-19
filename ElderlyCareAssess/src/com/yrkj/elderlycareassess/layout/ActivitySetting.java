package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivitySetting extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutActionBarViewId = R.id.layoutActionBarView;
    public static final int BtnBackViewId = R.id.btnBackView;
    public static final int LayoutUserHeaderInfoContainerViewId = R.id.layoutUserHeaderInfoContainerView;
    public static final int BtnSetNetPwdViewId = R.id.btnSetNetPwdView;
    public static final int BtnSetLocPwdViewId = R.id.btnSetLocPwdView;
    public static final int BtnAysnViewId = R.id.btnAysnView;
    public static final int BtnClearViewId = R.id.btnClearView;
    public static final int BtnLogViewId = R.id.btnLogView;
    public static final int BtnLogoutViewId = R.id.btnLogoutView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.RelativeLayout mLayoutActionBarView;
    protected android.widget.ImageButton mBtnBackView;
    protected android.widget.RelativeLayout mLayoutUserHeaderInfoContainerView;
    protected android.widget.LinearLayout mBtnSetNetPwdView;
    protected android.widget.LinearLayout mBtnSetLocPwdView;
    protected android.widget.LinearLayout mBtnAysnView;
    protected android.widget.LinearLayout mBtnClearView;
    protected android.widget.LinearLayout mBtnLogView;
    protected android.widget.Button mBtnLogoutView;

    protected Activity mCurActy;

    public ActivitySetting(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mLayoutUserHeaderInfoContainerView = (android.widget.RelativeLayout) acty.findViewById(LayoutUserHeaderInfoContainerViewId);
        mBtnSetNetPwdView = (android.widget.LinearLayout) acty.findViewById(BtnSetNetPwdViewId);
        mBtnSetLocPwdView = (android.widget.LinearLayout) acty.findViewById(BtnSetLocPwdViewId);
        mBtnAysnView = (android.widget.LinearLayout) acty.findViewById(BtnAysnViewId);
        mBtnClearView = (android.widget.LinearLayout) acty.findViewById(BtnClearViewId);
        mBtnLogView = (android.widget.LinearLayout) acty.findViewById(BtnLogViewId);
        mBtnLogoutView = (android.widget.Button) acty.findViewById(BtnLogoutViewId);
    }   

    public ActivitySetting(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mLayoutUserHeaderInfoContainerView = (android.widget.RelativeLayout) acty.findViewById(LayoutUserHeaderInfoContainerViewId);
        mBtnSetNetPwdView = (android.widget.LinearLayout) acty.findViewById(BtnSetNetPwdViewId);
        mBtnSetLocPwdView = (android.widget.LinearLayout) acty.findViewById(BtnSetLocPwdViewId);
        mBtnAysnView = (android.widget.LinearLayout) acty.findViewById(BtnAysnViewId);
        mBtnClearView = (android.widget.LinearLayout) acty.findViewById(BtnClearViewId);
        mBtnLogView = (android.widget.LinearLayout) acty.findViewById(BtnLogViewId);
        mBtnLogoutView = (android.widget.Button) acty.findViewById(BtnLogoutViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.RelativeLayout getLayoutActionBarView() {
        return mLayoutActionBarView;
    }
    public android.widget.ImageButton getBtnBackView() {
        return mBtnBackView;
    }
    public android.widget.RelativeLayout getLayoutUserHeaderInfoContainerView() {
        return mLayoutUserHeaderInfoContainerView;
    }
    public android.widget.LinearLayout getBtnSetNetPwdView() {
        return mBtnSetNetPwdView;
    }
    public android.widget.LinearLayout getBtnSetLocPwdView() {
        return mBtnSetLocPwdView;
    }
    public android.widget.LinearLayout getBtnAysnView() {
        return mBtnAysnView;
    }
    public android.widget.LinearLayout getBtnClearView() {
        return mBtnClearView;
    }
    public android.widget.LinearLayout getBtnLogView() {
        return mBtnLogView;
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutUserHeaderInfoContainerViewId:
                    setViewData(adp,getLayoutUserHeaderInfoContainerView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSetNetPwdViewId:
                    setViewData(adp,getBtnSetNetPwdView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSetLocPwdViewId:
                    setViewData(adp,getBtnSetLocPwdView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAysnViewId:
                    setViewData(adp,getBtnAysnView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnClearViewId:
                    setViewData(adp,getBtnClearView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnLogViewId:
                    setViewData(adp,getBtnLogView(),data,joinData.formatString,joinData.data);
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,"",path);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,"",path);
                    break;
                case LayoutUserHeaderInfoContainerViewId:
                    setViewData(adp,getLayoutUserHeaderInfoContainerView(),data,"",path);
                    break;
                case BtnSetNetPwdViewId:
                    setViewData(adp,getBtnSetNetPwdView(),data,"",path);
                    break;
                case BtnSetLocPwdViewId:
                    setViewData(adp,getBtnSetLocPwdView(),data,"",path);
                    break;
                case BtnAysnViewId:
                    setViewData(adp,getBtnAysnView(),data,"",path);
                    break;
                case BtnClearViewId:
                    setViewData(adp,getBtnClearView(),data,"",path);
                    break;
                case BtnLogViewId:
                    setViewData(adp,getBtnLogView(),data,"",path);
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
