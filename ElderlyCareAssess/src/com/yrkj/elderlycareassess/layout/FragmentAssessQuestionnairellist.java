package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessQuestionnairellist extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutBodyId = R.id.layoutBody;
    public static final int BtnStopRecordViewId = R.id.btnStopRecordView;
    public static final int TxtRecordDescViewId = R.id.txtRecordDescView;

    protected android.widget.ScrollView mContainer;
    protected android.widget.LinearLayout mLayoutBody;
    protected android.widget.LinearLayout mBtnStopRecordView;
    protected android.widget.TextView mTxtRecordDescView;

    protected Activity mCurActy;

    public FragmentAssessQuestionnairellist(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.ScrollView) acty.findViewById(ContainerId);
        mLayoutBody = (android.widget.LinearLayout) acty.findViewById(LayoutBodyId);
        mBtnStopRecordView = (android.widget.LinearLayout) acty.findViewById(BtnStopRecordViewId);
        mTxtRecordDescView = (android.widget.TextView) acty.findViewById(TxtRecordDescViewId);
    }   

    public FragmentAssessQuestionnairellist(android.view.View acty){
        mContainer = (android.widget.ScrollView) acty.findViewById(ContainerId);
        mLayoutBody = (android.widget.LinearLayout) acty.findViewById(LayoutBodyId);
        mBtnStopRecordView = (android.widget.LinearLayout) acty.findViewById(BtnStopRecordViewId);
        mTxtRecordDescView = (android.widget.TextView) acty.findViewById(TxtRecordDescViewId);
    }   
    public android.widget.ScrollView getContainer() {
        return mContainer;
    }
    public android.widget.LinearLayout getLayoutBody() {
        return mLayoutBody;
    }
    public android.widget.LinearLayout getBtnStopRecordView() {
        return mBtnStopRecordView;
    }
    public android.widget.TextView getTxtRecordDescView() {
        return mTxtRecordDescView;
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
                case LayoutBodyId:
                    setViewData(adp,getLayoutBody(),data,joinData.formatString,joinData.data);
                    break;
                case BtnStopRecordViewId:
                    setViewData(adp,getBtnStopRecordView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtRecordDescViewId:
                    setViewData(adp,getTxtRecordDescView(),data,joinData.formatString,joinData.data);
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
                case LayoutBodyId:
                    setViewData(adp,getLayoutBody(),data,"",path);
                    break;
                case BtnStopRecordViewId:
                    setViewData(adp,getBtnStopRecordView(),data,"",path);
                    break;
                case TxtRecordDescViewId:
                    setViewData(adp,getTxtRecordDescView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
