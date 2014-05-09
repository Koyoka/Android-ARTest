package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessTaskList extends BaseLayout{

    public static final int BtnRedirectViewId = R.id.btnRedirectView;

    protected android.widget.Button mBtnRedirectView;

    protected Activity mCurActy;

    public FragmentAssessTaskList(Activity acty){
        mCurActy = acty;
        mBtnRedirectView = (android.widget.Button) acty.findViewById(BtnRedirectViewId);
    }   

    public FragmentAssessTaskList(android.view.View acty){
        mBtnRedirectView = (android.widget.Button) acty.findViewById(BtnRedirectViewId);
    }   
    public android.widget.Button getBtnRedirectView() {
        return mBtnRedirectView;
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
                case BtnRedirectViewId:
                    setViewData(adp,getBtnRedirectView(),data,joinData.formatString,joinData.data);
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
                case BtnRedirectViewId:
                    setViewData(adp,getBtnRedirectView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
