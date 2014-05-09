package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssess extends BaseLayout{

    public static final int LayoutSwitchViewId = R.id.layoutSwitchView;
    public static final int BtnAssessTaskViewId = R.id.btnAssessTaskView;
    public static final int BtnAssessDoneViewId = R.id.btnAssessDoneView;
    public static final int LayoutAssessBodyViewId = R.id.layoutAssessBodyView;

    protected android.widget.LinearLayout mLayoutSwitchView;
    protected android.widget.Button mBtnAssessTaskView;
    protected android.widget.Button mBtnAssessDoneView;
    protected android.widget.RelativeLayout mLayoutAssessBodyView;

    protected Activity mCurActy;

    public FragmentAssess(Activity acty){
        mCurActy = acty;
        mLayoutSwitchView = (android.widget.LinearLayout) acty.findViewById(LayoutSwitchViewId);
        mBtnAssessTaskView = (android.widget.Button) acty.findViewById(BtnAssessTaskViewId);
        mBtnAssessDoneView = (android.widget.Button) acty.findViewById(BtnAssessDoneViewId);
        mLayoutAssessBodyView = (android.widget.RelativeLayout) acty.findViewById(LayoutAssessBodyViewId);
    }   

    public FragmentAssess(android.view.View acty){
        mLayoutSwitchView = (android.widget.LinearLayout) acty.findViewById(LayoutSwitchViewId);
        mBtnAssessTaskView = (android.widget.Button) acty.findViewById(BtnAssessTaskViewId);
        mBtnAssessDoneView = (android.widget.Button) acty.findViewById(BtnAssessDoneViewId);
        mLayoutAssessBodyView = (android.widget.RelativeLayout) acty.findViewById(LayoutAssessBodyViewId);
    }   
    public android.widget.LinearLayout getLayoutSwitchView() {
        return mLayoutSwitchView;
    }
    public android.widget.Button getBtnAssessTaskView() {
        return mBtnAssessTaskView;
    }
    public android.widget.Button getBtnAssessDoneView() {
        return mBtnAssessDoneView;
    }
    public android.widget.RelativeLayout getLayoutAssessBodyView() {
        return mLayoutAssessBodyView;
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
                case LayoutSwitchViewId:
                    setViewData(adp,getLayoutSwitchView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAssessTaskViewId:
                    setViewData(adp,getBtnAssessTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAssessDoneViewId:
                    setViewData(adp,getBtnAssessDoneView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutAssessBodyViewId:
                    setViewData(adp,getLayoutAssessBodyView(),data,joinData.formatString,joinData.data);
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
                case LayoutSwitchViewId:
                    setViewData(adp,getLayoutSwitchView(),data,"",path);
                    break;
                case BtnAssessTaskViewId:
                    setViewData(adp,getBtnAssessTaskView(),data,"",path);
                    break;
                case BtnAssessDoneViewId:
                    setViewData(adp,getBtnAssessDoneView(),data,"",path);
                    break;
                case LayoutAssessBodyViewId:
                    setViewData(adp,getLayoutAssessBodyView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
