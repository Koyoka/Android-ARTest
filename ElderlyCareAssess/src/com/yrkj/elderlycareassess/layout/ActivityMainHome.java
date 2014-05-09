package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityMainHome extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutFootViewId = R.id.layoutFootView;
    public static final int BtnHomeViewId = R.id.btnHomeView;
    public static final int BtnAssessViewId = R.id.btnAssessView;
    public static final int BtnReportViewId = R.id.btnReportView;
    public static final int BtnSyncViewId = R.id.btnSyncView;
    public static final int LayoutBodyViewId = R.id.layoutBodyView;

    protected android.widget.RelativeLayout mContainer;
    protected android.widget.LinearLayout mLayoutFootView;
    protected android.widget.Button mBtnHomeView;
    protected android.widget.Button mBtnAssessView;
    protected android.widget.Button mBtnReportView;
    protected android.widget.Button mBtnSyncView;
    protected android.widget.FrameLayout mLayoutBodyView;

    protected Activity mCurActy;

    public ActivityMainHome(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mLayoutFootView = (android.widget.LinearLayout) acty.findViewById(LayoutFootViewId);
        mBtnHomeView = (android.widget.Button) acty.findViewById(BtnHomeViewId);
        mBtnAssessView = (android.widget.Button) acty.findViewById(BtnAssessViewId);
        mBtnReportView = (android.widget.Button) acty.findViewById(BtnReportViewId);
        mBtnSyncView = (android.widget.Button) acty.findViewById(BtnSyncViewId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
    }   

    public ActivityMainHome(android.view.View acty){
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mLayoutFootView = (android.widget.LinearLayout) acty.findViewById(LayoutFootViewId);
        mBtnHomeView = (android.widget.Button) acty.findViewById(BtnHomeViewId);
        mBtnAssessView = (android.widget.Button) acty.findViewById(BtnAssessViewId);
        mBtnReportView = (android.widget.Button) acty.findViewById(BtnReportViewId);
        mBtnSyncView = (android.widget.Button) acty.findViewById(BtnSyncViewId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
    }   
    public android.widget.RelativeLayout getContainer() {
        return mContainer;
    }
    public android.widget.LinearLayout getLayoutFootView() {
        return mLayoutFootView;
    }
    public android.widget.Button getBtnHomeView() {
        return mBtnHomeView;
    }
    public android.widget.Button getBtnAssessView() {
        return mBtnAssessView;
    }
    public android.widget.Button getBtnReportView() {
        return mBtnReportView;
    }
    public android.widget.Button getBtnSyncView() {
        return mBtnSyncView;
    }
    public android.widget.FrameLayout getLayoutBodyView() {
        return mLayoutBodyView;
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
                case LayoutFootViewId:
                    setViewData(adp,getLayoutFootView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnHomeViewId:
                    setViewData(adp,getBtnHomeView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAssessViewId:
                    setViewData(adp,getBtnAssessView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnReportViewId:
                    setViewData(adp,getBtnReportView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSyncViewId:
                    setViewData(adp,getBtnSyncView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutBodyViewId:
                    setViewData(adp,getLayoutBodyView(),data,joinData.formatString,joinData.data);
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
                case LayoutFootViewId:
                    setViewData(adp,getLayoutFootView(),data,"",path);
                    break;
                case BtnHomeViewId:
                    setViewData(adp,getBtnHomeView(),data,"",path);
                    break;
                case BtnAssessViewId:
                    setViewData(adp,getBtnAssessView(),data,"",path);
                    break;
                case BtnReportViewId:
                    setViewData(adp,getBtnReportView(),data,"",path);
                    break;
                case BtnSyncViewId:
                    setViewData(adp,getBtnSyncView(),data,"",path);
                    break;
                case LayoutBodyViewId:
                    setViewData(adp,getLayoutBodyView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
