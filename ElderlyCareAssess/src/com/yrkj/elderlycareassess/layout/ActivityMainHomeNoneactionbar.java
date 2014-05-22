package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityMainHomeNoneactionbar extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutActionBarViewId = R.id.layoutActionBarView;
    public static final int BtnSettingViewId = R.id.btnSettingView;
    public static final int LayoutAssessTabViewId = R.id.layoutAssessTabView;
    public static final int BtnAssessTaskViewId = R.id.btnAssessTaskView;
    public static final int BtnAssessDoneViewId = R.id.btnAssessDoneView;
    public static final int LayoutFootViewId = R.id.layoutFootView;
    public static final int RdoTabsViewId = R.id.rdoTabsView;
    public static final int BtnHomeViewId = R.id.btnHomeView;
    public static final int BtnAssessViewId = R.id.btnAssessView;
    public static final int BtnReportViewId = R.id.btnReportView;
    public static final int BtnSyncViewId = R.id.btnSyncView;
    public static final int TxtSyncCountId = R.id.txtSyncCount;
    public static final int LayoutBodyViewId = R.id.layoutBodyView;

    protected android.widget.RelativeLayout mContainer;
    protected android.widget.RelativeLayout mLayoutActionBarView;
    protected android.widget.ImageButton mBtnSettingView;
    protected android.widget.RadioGroup mLayoutAssessTabView;
    protected android.widget.RadioButton mBtnAssessTaskView;
    protected android.widget.RadioButton mBtnAssessDoneView;
    protected android.widget.RelativeLayout mLayoutFootView;
    protected android.widget.RadioGroup mRdoTabsView;
    protected android.widget.RadioButton mBtnHomeView;
    protected android.widget.RadioButton mBtnAssessView;
    protected android.widget.RadioButton mBtnReportView;
    protected android.widget.Button mBtnSyncView;
    protected android.widget.TextView mTxtSyncCount;
    protected android.widget.FrameLayout mLayoutBodyView;

    protected Activity mCurActy;

    public ActivityMainHomeNoneactionbar(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnSettingView = (android.widget.ImageButton) acty.findViewById(BtnSettingViewId);
        mLayoutAssessTabView = (android.widget.RadioGroup) acty.findViewById(LayoutAssessTabViewId);
        mBtnAssessTaskView = (android.widget.RadioButton) acty.findViewById(BtnAssessTaskViewId);
        mBtnAssessDoneView = (android.widget.RadioButton) acty.findViewById(BtnAssessDoneViewId);
        mLayoutFootView = (android.widget.RelativeLayout) acty.findViewById(LayoutFootViewId);
        mRdoTabsView = (android.widget.RadioGroup) acty.findViewById(RdoTabsViewId);
        mBtnHomeView = (android.widget.RadioButton) acty.findViewById(BtnHomeViewId);
        mBtnAssessView = (android.widget.RadioButton) acty.findViewById(BtnAssessViewId);
        mBtnReportView = (android.widget.RadioButton) acty.findViewById(BtnReportViewId);
        mBtnSyncView = (android.widget.Button) acty.findViewById(BtnSyncViewId);
        mTxtSyncCount = (android.widget.TextView) acty.findViewById(TxtSyncCountId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
    }   

    public ActivityMainHomeNoneactionbar(android.view.View acty){
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnSettingView = (android.widget.ImageButton) acty.findViewById(BtnSettingViewId);
        mLayoutAssessTabView = (android.widget.RadioGroup) acty.findViewById(LayoutAssessTabViewId);
        mBtnAssessTaskView = (android.widget.RadioButton) acty.findViewById(BtnAssessTaskViewId);
        mBtnAssessDoneView = (android.widget.RadioButton) acty.findViewById(BtnAssessDoneViewId);
        mLayoutFootView = (android.widget.RelativeLayout) acty.findViewById(LayoutFootViewId);
        mRdoTabsView = (android.widget.RadioGroup) acty.findViewById(RdoTabsViewId);
        mBtnHomeView = (android.widget.RadioButton) acty.findViewById(BtnHomeViewId);
        mBtnAssessView = (android.widget.RadioButton) acty.findViewById(BtnAssessViewId);
        mBtnReportView = (android.widget.RadioButton) acty.findViewById(BtnReportViewId);
        mBtnSyncView = (android.widget.Button) acty.findViewById(BtnSyncViewId);
        mTxtSyncCount = (android.widget.TextView) acty.findViewById(TxtSyncCountId);
        mLayoutBodyView = (android.widget.FrameLayout) acty.findViewById(LayoutBodyViewId);
    }   
    public android.widget.RelativeLayout getContainer() {
        return mContainer;
    }
    public android.widget.RelativeLayout getLayoutActionBarView() {
        return mLayoutActionBarView;
    }
    public android.widget.ImageButton getBtnSettingView() {
        return mBtnSettingView;
    }
    public android.widget.RadioGroup getLayoutAssessTabView() {
        return mLayoutAssessTabView;
    }
    public android.widget.RadioButton getBtnAssessTaskView() {
        return mBtnAssessTaskView;
    }
    public android.widget.RadioButton getBtnAssessDoneView() {
        return mBtnAssessDoneView;
    }
    public android.widget.RelativeLayout getLayoutFootView() {
        return mLayoutFootView;
    }
    public android.widget.RadioGroup getRdoTabsView() {
        return mRdoTabsView;
    }
    public android.widget.RadioButton getBtnHomeView() {
        return mBtnHomeView;
    }
    public android.widget.RadioButton getBtnAssessView() {
        return mBtnAssessView;
    }
    public android.widget.RadioButton getBtnReportView() {
        return mBtnReportView;
    }
    public android.widget.Button getBtnSyncView() {
        return mBtnSyncView;
    }
    public android.widget.TextView getTxtSyncCount() {
        return mTxtSyncCount;
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
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSettingViewId:
                    setViewData(adp,getBtnSettingView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutAssessTabViewId:
                    setViewData(adp,getLayoutAssessTabView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAssessTaskViewId:
                    setViewData(adp,getBtnAssessTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAssessDoneViewId:
                    setViewData(adp,getBtnAssessDoneView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutFootViewId:
                    setViewData(adp,getLayoutFootView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoTabsViewId:
                    setViewData(adp,getRdoTabsView(),data,joinData.formatString,joinData.data);
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
                case TxtSyncCountId:
                    setViewData(adp,getTxtSyncCount(),data,joinData.formatString,joinData.data);
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
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,"",path);
                    break;
                case BtnSettingViewId:
                    setViewData(adp,getBtnSettingView(),data,"",path);
                    break;
                case LayoutAssessTabViewId:
                    setViewData(adp,getLayoutAssessTabView(),data,"",path);
                    break;
                case BtnAssessTaskViewId:
                    setViewData(adp,getBtnAssessTaskView(),data,"",path);
                    break;
                case BtnAssessDoneViewId:
                    setViewData(adp,getBtnAssessDoneView(),data,"",path);
                    break;
                case LayoutFootViewId:
                    setViewData(adp,getLayoutFootView(),data,"",path);
                    break;
                case RdoTabsViewId:
                    setViewData(adp,getRdoTabsView(),data,"",path);
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
                case TxtSyncCountId:
                    setViewData(adp,getTxtSyncCount(),data,"",path);
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
